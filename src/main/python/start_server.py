#!/usr/bin/env python

# Licensed to the Apache Software Foundation (ASF) under one
# or more contributor license agreements.  See the NOTICE file
# distributed with this work for additional information
# regarding copyright ownership.  The ASF licenses this file
# to you under the Apache License, Version 2.0 (the
# "License"); you may not use this file except in compliance
# with the License.  You may obtain a copy of the License at
#
#     http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.

from BaseHTTPServer import BaseHTTPRequestHandler, HTTPServer
import avro.ipc as ipc
import avro.protocol as protocol
import avro.schema as schema
from movie_service_util import MovieServiceUtil

# PROTOCOL = protocol.parse(open("../avro/mail.avpr").read())
PROTOCOL = protocol.parse(open("../avro/movieservice.avpr").read())


class MovieServiceResponder(ipc.Responder):
    def __init__(self):
        ipc.Responder.__init__(self, PROTOCOL)
        self.moviesData = MovieServiceUtil.create_movies()

    def invoke(self, msg, req):
        # print("message: " + str(msg))
        # print("req:" + str(req))
        # print("messsage name :" + str(msg.name))
        if msg.name == 'getMovies':
            # print("request to be processed")
            # print("local data: " + str(self.moviesData))
            return self.moviesData
        else:
            raise schema.AvroException("unexpected message:", msg.getname())


class MovieServiceHandler(BaseHTTPRequestHandler):

    def do_POST(self):
        # print("do post")
        self.responder = MovieServiceResponder()
        call_request_reader = ipc.FramedReader(self.rfile)
        call_request = call_request_reader.read_framed_message()
        # print("waiting for response")
        resp_body = self.responder.respond(call_request)
        self.send_response(200)
        self.send_header('Content-Type', 'avro/binary')
        self.end_headers()
        resp_writer = ipc.FramedWriter(self.wfile)
        resp_writer.write_framed_message(resp_body)


server_addr = ('127.0.0.1', 9090)

if __name__ == '__main__':
    server = HTTPServer(server_addr, MovieServiceHandler)

    print("[Server] starting on port " + str(server_addr[1]))
    server.allow_reuse_address = True
    server.serve_forever()
