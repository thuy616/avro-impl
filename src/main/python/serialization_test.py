from avro.protocol import Protocol

from movie_service_util import MovieServiceUtil
import logging
import avro.schema
from avro.datafile import DataFileReader, DataFileWriter
from avro.io import DatumReader, DatumWriter
from avro.io import BinaryDecoder, BinaryEncoder
import time, timeit, datetime
import os
import io
import avro_json_serializer as ajs
from json import dumps as json_dumps


class SerializationTest:
    def __init__(self):
        self.movies_data = MovieServiceUtil.create_movies()
        self.movies_from_file = MovieServiceUtil.read_json_string()
        logger = logging.getLogger('SerializationTest')
        logger.setLevel(logging.DEBUG)  # create file handler which logs even debug messages
        directory = "Logging"
        if not os.path.exists(directory):
            os.makedirs(directory)
        ts = time.time()
        st = datetime.datetime.fromtimestamp(ts).strftime('%Y_%m_%d_%H%M%S')
        filepath = directory + "//Avro_Serialization_" + st + ".log"

        fh = logging.FileHandler(filepath)
        fh.setLevel(logging.INFO)
        # create console handler with a higher log level
        ch = logging.StreamHandler()
        ch.setLevel(logging.INFO)  # handler = PingHandler()
        # create formatter and add it to the handlers
        formatter = logging.Formatter('%(asctime)s - %(name)s - %(levelname)s - %(message)s')
        fh.setFormatter(formatter)
        ch.setFormatter(formatter)
        # add the handlers to the logger
        logger.addHandler(fh)
        logger.addHandler(ch)
        self.logger = logger
        self.logger.log(logging.INFO, "initializing completed...")
        union_schema = avro.schema.parse(open("../avro/movie.avsc").read())
        schemas = union_schema.schemas
        for s in schemas:
            if s.name == "Movies":
                print("found")
                self.movies_schema = s

    def run(self, n):
        # JSON Serializer
        # serializer = ajs.AvroJsonSerializer(self.movies_schema)
        # json_data = serializer.to_json(self.movies_data)
        total_ser = 0
        total_deser = 0
        bytes_len = 0
        for i in range(0, n):
            datum_writer = DatumWriter(self.movies_schema)
            bytes_writer = io.BytesIO()

            encoder = BinaryEncoder(bytes_writer)
            tic = timeit.default_timer()
            datum_writer.write(self.movies_data, encoder)
            elapsed = timeit.default_timer() - tic
            payload = bytes_writer.getvalue()
            total_ser = total_ser + elapsed
            bytes_len = len(payload)

            bytes_reader = io.BytesIO(payload)
            decoder = BinaryDecoder(bytes_reader)
            reader = DatumReader(self.movies_schema)
            tic2 = timeit.default_timer()
            movies = reader.read(decoder)
            elapsed2 = timeit.default_timer() - tic2
            total_deser = total_deser + elapsed2

        self.logger.log(logging.INFO, "serialized len: %s bytes", bytes_len)
        avg_ser = (total_ser*(10**9))/n
        avg_deser = (total_deser*(10**9))/n
        self.logger.log(logging.INFO, "Serialization time: \n%s", avg_ser)
        self.logger.log(logging.INFO, "De-serialization time: \n%s", avg_deser)

if __name__ == '__main__':
    n = 10000
    tester = SerializationTest()
    for j in range(0, 10):
        tester.logger.log(logging.INFO, "######### ITERATION %s ##########", j)
        tester.run(n)
    tester.logger.log(logging.INFO, "######### FINISHED ##########")
