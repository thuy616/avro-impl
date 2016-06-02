/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package example;

import example.proto.EmptyRequest;
import example.proto.MovieService;
import example.proto.Movies;
import org.apache.avro.AvroRemoteException;
import org.apache.avro.ipc.*;
import org.apache.avro.ipc.specific.SpecificRequestor;

import java.io.File;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.URL;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * Start a server, attach a client, and send a message.
 */
public class Main {
    public static final java.util.logging.Logger logger = Logger.getLogger(Main.class.getName());
    public static FileHandler fh;

    private static void info(String msg, Object... params) {
        logger.log(Level.INFO, msg, params);
    }

    private static void runSynchronousTests(MovieService proxy, int calls, int iterations) throws AvroRemoteException, InterruptedException {
        EmptyRequest request = new EmptyRequest();
        long totalElapsed = 0;
        for (int j = 0; j < iterations; j++) {
            info("##############    ITERATION {0}   ################", j);
            info("Calling proxy.getMovies {0} times", calls);
            long elapsed = 0;
            for (int i = 0; i < calls; i++) {
                long start = System.nanoTime();
                Movies result = proxy.getMovies(request);
                long end = System.nanoTime();
                elapsed += end - start;
            }
            info("Transmission time: {0}", elapsed);
            totalElapsed += elapsed;
            Thread.sleep(10000);
        }
        info("AVERAGE time for {0} calls: {1} ", calls, totalElapsed /(float)iterations);
    }

    // test async rpc with callback
    public static long testAsyncRpcCallback(MovieService.Callback proxy, int count) throws Exception {
        long totalTransmission = 0;
        for (int i=0; i<count; i++) {
            final int currentCount = i;
            EmptyRequest request = new EmptyRequest();
//            final CallFuture<Movies> future = new CallFuture<>();
            final CountDownLatch latch = new CountDownLatch(1);
            final AtomicBoolean returned = new AtomicBoolean(false);

            long start = System.nanoTime();
            proxy.getMovies(request, new Callback<Movies>() {

                @Override
                public void handleResult(Movies movies) {
//                    future.handleResult(movies);
                    returned.set(true);
                    latch.countDown();
                }

                @Override
                public void handleError(Throwable throwable) {
                    try {
                        logger.log(Level.SEVERE, "RPC Failed!!!", throwable);
                    } finally {
                        latch.countDown();
                    }
                }
            });


            long end = System.nanoTime();
            totalTransmission += end - start;
            boolean calledBack = latch.await(100, TimeUnit.SECONDS);
            info("if called back on count: {0}: {1}", currentCount, calledBack);
            info("if result is returned on count {0}: {1}", currentCount, returned.get());
        }

        info("Transmission time for {0} ASYNC calls: {1}", count, totalTransmission);
        return totalTransmission;
    }

    public static void runAsyncTests(MovieService.Callback proxy, int count, int iterations) throws Exception{
        long transmissionTime = 0;
        for (int i=0; i<iterations; i++) {
            transmissionTime += testAsyncRpcCallback(proxy, count);
            Thread.sleep(10000);
        }
        info("AVERAGE time for {0} ASYNC calls: {1} ", count, transmissionTime/(float)iterations);
    }


    public static void main(String[] args) throws IOException {

        SimpleDateFormat format = new SimpleDateFormat("MM_dd_yyyy_HHmmss");
        try {
            String dir = Paths.get("").toAbsolutePath().toString() + "//Logging";
            File directory = new File(dir);

            if (!directory.exists()) {
                directory.mkdir();
            }

            fh = new FileHandler(dir + "//Avro_Java_Client_Server_"
                    + format.format(Calendar.getInstance().getTime()) + ".log");
        } catch (Exception e) {
            e.printStackTrace();
        }

        fh.setFormatter(new SimpleFormatter());
        fh.setLevel(Level.INFO);
        logger.addHandler(fh);


        if (args.length != 4) {
            System.out.println("Usage: <client mode 1=Java, 2=Python> <conn mode 1=netty, 2=http> <rpc mode 1=sync, 2=async> <iterations>");
            System.exit(1);
        }
        int clientMode = Integer.parseInt(args[0]);
        int connMode = Integer.parseInt(args[1]);
        int rpcMode = Integer.parseInt(args[2]);
        int iterations = Integer.parseInt(args[3]);
        MovieService.Callback proxy = null;
        Transceiver client = null;
        if (clientMode == 1) {
            if (connMode == 1) {
                client = new NettyTransceiver(new InetSocketAddress(9111));
            } else if (connMode == 2) {
                client = new HttpTransceiver(new URL("http://127.0.0.1:9111/"));
            }
        } else if (clientMode == 2) {
            client = new HttpTransceiver(new URL("http://127.0.0.1:9090/")); // python

        }
        proxy = (MovieService.Callback) SpecificRequestor.getClient(MovieService.Callback.class, client);
        info("[Client] Client built, got proxy...");
        // force handshake
        SpecificRequestor.getRemote(proxy);
        info("Handshake done!");

        if (rpcMode == 1) {

            info("[Client] RUNNING SYNCHRONOUS TESTS");
            try {
                runSynchronousTests(proxy, 1, iterations);
                Thread.sleep(30*1000);
                runSynchronousTests(proxy, 1000, iterations);
                Thread.sleep(30*1000);
                runSynchronousTests(proxy, 10000, iterations);
                Thread.sleep(30*1000);
                runSynchronousTests(proxy, 100000, iterations);
            } catch(Exception e) {
                e.printStackTrace();
            }
        } else if (rpcMode == 2) {
            info("[Client] RUNNING ASYNC TESTS");
            try {
                info ("IGNORE ---- Warming up TIME --------");
                runAsyncTests(proxy, 1, 2);
                info ("IGNORE ---- Warming up TIME END ------");
                runAsyncTests(proxy, 1, iterations);
                Thread.sleep(30*1000);
                runAsyncTests(proxy, 1000, iterations);
                Thread.sleep(1000*30);
                runAsyncTests(proxy, 10000, iterations);
                Thread.sleep(1000*30);
                runAsyncTests(proxy, 100000, 1);
                Thread.sleep(30*1000);

            } catch(Exception e) {
                e.printStackTrace();
            }
        }
        // cleanup
        client.close();
//        server.close();

    }
}


