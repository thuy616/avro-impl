package example;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import example.proto.*;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by thuy on 28/05/16.
 */
public class MovieServiceUtil {

    private static final Gson gson = new Gson();
    public static URL file = MovieServiceUtil.class.getResource("movie_service_db.json");

    public static Movies createMovies() {
        Genre genre1 = new Genre(28, "Action");
        Genre genre2 = new Genre(53, "Thriller");
        Genre genre3 = new Genre(878, "Science Fiction");

        List<Genre> genres = new ArrayList<Genre>();
        genres.add(genre1);
        genres.add(genre2);
        genres.add(genre3);

        Collection collection = new Collection((long) 131295, "Captain America Collection",
                "/2tOgiY533JSFp7OrVlkeRJvsZpI.jpg", "/2tOgiY533JSFp7OrVlkeRJvsZpI.jpg");

        ProductionCompany proCom = new ProductionCompany("Marvel Studios", 420);
        List<ProductionCompany> proComs = new ArrayList<ProductionCompany>();
        proComs.add(proCom);

        ProductionCountry proCountry = new ProductionCountry("US", "United States of America");
        List<ProductionCountry> countries = new ArrayList<ProductionCountry>();
        countries.add(proCountry);

        SpokenLanguage lan1 = new SpokenLanguage("ro", "Romana");
        SpokenLanguage lan2 = new SpokenLanguage("en", "English");
        SpokenLanguage lan3 = new SpokenLanguage("en", "Deutsch");
        SpokenLanguage lan4 = new SpokenLanguage("en", "Russian");

        List<SpokenLanguage> languages = new ArrayList<SpokenLanguage>();
        languages.add(lan1);
        languages.add(lan2);
        languages.add(lan3);
        languages.add(lan4);

        Movie movie = new Movie(
                false,
                "/m5O3SZvQ6EgD5XXXLPIP1wLppeW.jpg",
                collection,
                (long) 250000000,
                genres,
                "http://marvel.com/captainamericapremiere",
                (long) 71110,
                "tt3498820",
                "en",
                "Captain America: Civil War",
                "Following the events of Age of Ultron, the collective governments of the world pass an act designed to regulate all superhuman activity. This polarizes opinion amongst the Avengers, causing two factions to side with Iron Man or Captain America, which causes an epic battle between former allies.",
                68.890327,
                "/rDT86hJCxnoOs4ARjrCiRej7pOi.jpg",
                proComs,
                countries,
                "2016-04-27",
                (long) 206000000,
                146,
                languages,
                "Released", "Divided We Fall", "Captain America: Civil War", false, 6.9, 1380
        );

        List<Movie> movieList = new ArrayList<Movie>();
        for (int i = 0; i < 34; i++) {
            movieList.add(movie);
        }

        Movies movies = new Movies(movieList);
        return movies;

    }

//    public static Movies readMoviesDB() throws IOException {
//
//        InputStream input = file.openStream();
//        try {
//            Reader reader = new InputStreamReader(input);
//            try {
//                JsonReader jsonReader = new JsonReader(reader);
//                Movies movies = gson.fromJson(jsonReader, Movies.class);
//                return movies;
//            } finally {
//                reader.close();
//            }
//        } finally {
//            input.close();
//        }
//    }

    public static String readJsonString() throws IOException {
        JsonParser parser = new JsonParser();
        InputStream input = file.openStream();
        try {
            Reader reader = new InputStreamReader(input);
            JsonReader jsonReader = new JsonReader(reader);
            JsonElement json = parser.parse(jsonReader);
            return json.toString();
        } finally {
            input.close();
        }
    }

}

