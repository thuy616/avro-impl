package example;

import example.proto.Movies;
import org.apache.avro.Protocol;
import org.apache.avro.Schema;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericDatumReader;
import org.apache.avro.generic.GenericDatumWriter;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.io.*;
import org.apache.commons.io.FileUtils;

import java.io.*;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * Created by thuy on 28/05/16.
 */
public class SerializationTest {
    private FileHandler fh;
    private static Logger logger = Logger.getLogger(SerializationTest.class.getName());
    private static Schema schema;
    private Movies movies;
    private String json;
    private Protocol protocol;

    public SerializationTest() throws IOException {

        SimpleDateFormat format = new SimpleDateFormat("MM_dd_yyyy_HHmmss");
        try {
            String dir = Paths.get("").toAbsolutePath().toString() + "//Logging";
            File directory = new File(dir);

            if (!directory.exists()) {
                directory.mkdir();
            }

            fh = new FileHandler(dir + "//Avro_Serialization_Log_"
                    + format.format(Calendar.getInstance().getTime()) + ".log");
        } catch (Exception e) {
            e.printStackTrace();
        }

        fh.setFormatter(new SimpleFormatter());
        fh.setLevel(Level.INFO);
        logger.addHandler(fh);

        movies = MovieServiceUtil.createMovies();
        json = MovieServiceUtil.readJsonString();

        String schemaPath = Paths.get("").toAbsolutePath().toString() + "//src//main//avro//movieservice.avpr";
        File file = new File(schemaPath);
        InputStream input = FileUtils.openInputStream(file);
//        schema = new Schema.Parser().parse(input);
        protocol = Protocol.parse(input);
        schema = protocol.getType("Movies"); // movies schema
        info("Schema: {0}", schema.getName());
    }

    private static void info(String msg, Object... params) {
        logger.log(Level.INFO, msg, params);
    }

    public void run(int calls) throws IOException {

        long totalSer = 0;
        long totalDeser = 0;
        int payloadLength = 0;
        for (int i = 0; i < calls; i++) {
            ByteArrayOutputStream bOut = new ByteArrayOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(bOut);
            long startSer = System.nanoTime();
            movies.writeExternal(out);
            long endSer = System.nanoTime();
            totalSer += endSer - startSer;
            out.close();

            byte[] payload = bOut.toByteArray();
            payloadLength = payload.length;

            ByteArrayInputStream bIn = new ByteArrayInputStream(payload);
            ObjectInputStream in = new ObjectInputStream(bIn);
            Movies m = new Movies();
            long startDeser = System.nanoTime();
            m.readExternal(in);
            long endDeser = System.nanoTime();
            totalDeser += endDeser - startDeser;
            in.close();
        }
        info("Serialized time: \n{0}", totalSer / (float) calls);
        info("De-Serialized time: \n{0}", totalDeser / (float) calls);
        info("Serialized length: {0}", payloadLength);
    }

    public static byte[] jsonToAvro(String json, Schema schema) throws IOException {
        InputStream input = null;
        DatumWriter<Movies> writer = null;
        Encoder encoder = null;
        ByteArrayOutputStream output = null;
        try {
            DatumReader<Movies> reader = new GenericDatumReader<Movies>(schema);
            input = new ByteArrayInputStream(json.getBytes());
            output = new ByteArrayOutputStream();
            DataInputStream din = new DataInputStream(input);
            writer = new GenericDatumWriter<Movies>(schema);
            Decoder decoder = DecoderFactory.get().jsonDecoder(schema, din);
            encoder = EncoderFactory.get().binaryEncoder(output, null);
            Movies datum;
            while (true) {
                try {
                    datum = reader.read(null, decoder);
                } catch (EOFException eofe) {
                    break;
                }
                writer.write(datum, encoder);
            }
            encoder.flush();
            return output.toByteArray();
        } finally {
            try { input.close(); } catch (Exception e) { }
        }
    }

    public static String avroToJson(byte[] avro, Schema schema) throws IOException {
        boolean pretty = false;
        GenericDatumReader<GenericRecord> reader = null;
        JsonEncoder encoder = null;
        ByteArrayOutputStream output = null;
        try {
            reader = new GenericDatumReader<GenericRecord>(schema);
            InputStream input = new ByteArrayInputStream(avro);
            output = new ByteArrayOutputStream();
            DatumWriter<GenericRecord> writer = new GenericDatumWriter<GenericRecord>(schema);
            encoder = EncoderFactory.get().jsonEncoder(schema, output, pretty);
            Decoder decoder = DecoderFactory.get().binaryDecoder(input, null);
            GenericRecord datum;
            while (true) {
                try {
                    datum = reader.read(null, decoder);
                } catch (EOFException eofe) {
                    break;
                }
                writer.write(datum, encoder);
            }
            encoder.flush();
            output.flush();
            return new String(output.toByteArray());
        } finally {
            try { if (output != null) output.close(); } catch (Exception e) { }
        }
    }

    public void runJsonStringToAvro(int calls) throws IOException {

        long totalSer = 0;
        long totalDeser = 0;
        int payloadLength = 0;
        for (int i = 0; i < calls; i++) {
            long startSer = System.nanoTime();
            byte[] payload = jsonToAvro(json, schema);
            long endSer = System.nanoTime();
            totalSer += endSer - startSer;
            payloadLength = payload.length;

            long startDeser = System.nanoTime();
            String output = avroToJson(payload, schema);
            long endDeser = System.nanoTime();
            totalDeser += endDeser - startDeser;
        }
        info("Serialized time: \n{0}", totalSer / (float) calls);
        info("De-Serialized time: \n{0}", totalDeser / (float) calls);
        info("Serialized length: {0}", payloadLength);
    }

    public static void main(String[] args) {
        int iterations = 1;
        int calls = 1;

        SerializationTest tester = null;
        try {
            tester = new SerializationTest();
        } catch (IOException e) {
            e.printStackTrace();
        }
        info("START SERIALIZATION TESTS");
        for (int i = 0; i < iterations; i++) {
            info("************* ITERATION {0} *************", i);
            try {
                tester.runJsonStringToAvro(calls);
            } catch (IOException e) {
                logger.log(Level.SEVERE, "IOException", e);
                e.printStackTrace();
            }
        }
        info("SERIALIZATION TESTS COMPLETED!!");
    }
}
