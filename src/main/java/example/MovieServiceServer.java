package example;

import example.proto.EmptyRequest;
import example.proto.MovieService;
import example.proto.Movies;
import org.apache.avro.AvroRemoteException;
import org.apache.avro.ipc.HttpServer;
import org.apache.avro.ipc.HttpTransceiver;
import org.apache.avro.ipc.NettyServer;
import org.apache.avro.ipc.Server;
import org.apache.avro.ipc.specific.SpecificResponder;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;
import org.jboss.netty.handler.execution.ExecutionHandler;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.URL;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by thuy on 29/05/16.
 */
public class MovieServiceServer {
    public static class MovieServiceImpl implements MovieService {
        private static Movies moviesData;

        public MovieServiceImpl(Movies movies) {
            this.moviesData = movies;
        }

        public Movies getMovies(EmptyRequest request) throws AvroRemoteException {
            return moviesData;
        }
    }

    private static Server server;

    private static void startServer(int mode) throws IOException {
        if (mode == 1) {
        server = new NettyServer(
                new SpecificResponder(MovieService.class, new MovieServiceImpl(MovieServiceUtil.createMovies())),
                new InetSocketAddress(9111),
                new NioServerSocketChannelFactory(Executors.newCachedThreadPool(), Executors.newCachedThreadPool()),
                new ExecutionHandler(Executors.newCachedThreadPool()));
        } else if (mode == 2) {
            server = new HttpServer(new SpecificResponder(MovieService.class, new MovieServiceImpl(MovieServiceUtil.createMovies())),
                    9111);
        }
        server.start();
    }

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage:<mode 1=NettyServer, 2=HTTPServer>");
        }
        int mode = Integer.parseInt(args[0]);
        System.out.println("[Server] Starting...");
        // usually this would be another app, but for simplicity
        try {
            startServer(mode);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("[Server] started...");
    }
}
