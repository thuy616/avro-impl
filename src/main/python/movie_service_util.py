#!/usr/bin/env python
# -*- coding: utf-8 -*-
import json


class MovieServiceUtil:
    @staticmethod
    def create_movies_test():
        item = dict()
        item["adult"] = False
        item["backdrop_path"] = "/m5O3SZvQ6EgD5XXXLPIP1wLppeW.jpg"
        item["belongs_to_collection"] = {
            "id": 10002,
            "name": "Captain America Collection",
            "poster_path": "/m5O3SZvQ6EgD5XXXLPIP1wLppeW.jpg",
            "backdrop_path": "/m5O3SZvQ6EgD5XXXLPIP1wLppeW.jpg"
        }
        item["budget"] = 250000000
        item["genres"] = [
            {
                "id": 28,
                "name": "Action"
            },
            {
                "id": 53,
                "name": "Thriller"
            },
            {
                "id": 878,
                "name": "Science Fiction"
            }
        ]
        item["homepage"] = "http://marvel.com/captainamericapremiere"
        item["id"] = 271110
        item["imdb_id"] = "tt3498820"
        item["original_language"] = "en"
        item["original_title"] = "Captain America: Civil War"
        item["overview"] = "Following the events of Age of Ultron, the collective governments of the world pass an act designed to regulate all superhuman activity. This polarizes opinion amongst the Avengers, causing two factions to side with Iron Man or Captain America, which causes an epic battle between former allies."
        item["popularity"] = 8.9
        item["poster_path"] = "/m5O3SZvQ6EgD5XXXLPIP1wLppeW.jpg"
        item["production_companies"] = [{"name": "Marvel Studio", "id": 420}]
        item["production_countries"] = [{"iso_3166_1": "US", "name": "United States of America"}]
        item["release_date"] = "2016-05-10"
        item["revenue"] = 206000000
        item["runtime"] = 146
        item["spoken_languages"] = [
            {
                "iso_639_1": "ro",
                "name": "Rom"
            },
            {
                "iso_639_1": "en",
                "name": "English"
            },
            {
                "iso_639_1": "de",
                "name": "Deutsch"
            },
            {
                "iso_639_1": "ru",
                "name": "Russian"
            }
        ]
        item["status"] = "Released"
        item["tagline"] = "Divided we fall"
        item["title"] = "Captain America: Civil War"
        item["video"] = False
        item["vote_average"] = 6.9
        item["vote_count"] = 1380

        movies = []
        # for x in range (0,34):
        movies.append(item)
        moviesData = {"movies": movies}
        return moviesData

    @staticmethod
    def create_movies():
        movie = {"adult": False, "backdrop_path": "/m5O3SZvQ6EgD5XXXLPIP1wLppeW.jpg",
                            "belongs_to_collection": {"id": 131295, "name": "Captain America Collection",
                                                      "poster_path": "/keRsZpI.jpg",
                                                      "backdrop_path": "/uhSC8.jpg"},
                            "budget": 250000000,
                            "genres": [{"id": 28, "name": "Action"}, {"id": 53, "name": "Thriller"},
                                       {"id": 878, "name": "Science Fiction"}],
                            "homepage": "http://marvel.com/captainamericapremiere", "id": 271110,
                            "imdb_id": "tt3498820", "original_language": "en",
                            "original_title": "Captain America: Civil War",
                            "overview": "Following the events of Age of Ultron, the collective governments of the world pass an act designed to regulate all superhuman activity. This polarizes opinion amongst the Avengers, causing two factions to side with Iron Man or Captain America, which causes an epic battle between former allies.",
                            "popularity": 68.890327, "poster_path": "/rDT86hJCxnoOs4ARjrCiRej7pOi.jpg",
                            "production_companies": [{"name": "Marvel Studios", "id": 420}],
                            "production_countries": [{"iso_3166_1": "US", "name": "United States of America"}],
                            "release_date": "2016-04-27", "revenue": 206000000, "runtime": 146,
                            "spoken_languages": [{"iso_639_1": "ro", "name": "Rom"},
                                                 {"iso_639_1": "en", "name": "English"},
                                                 {"iso_639_1": "de", "name": "Deutsch"},
                                                 {"iso_639_1": "ru", "name": "Russian"}], "status": "Released",
                            "tagline": "Divided We Fall", "title": "Captain America: Civil War", "video": False,
                            "vote_average": 6.9, "vote_count": 1380}
        movie_list = {"movies": []}
        for i in range(0, 34):
            movie_list["movies"].append(movie)
        return movie_list


    @staticmethod
    def read_json_string():
        m = dict()
        with open("movie_service_db.json", "rb") as data:
            m = json.load(data)
            data.close()
        # print(str(m))
        return m
