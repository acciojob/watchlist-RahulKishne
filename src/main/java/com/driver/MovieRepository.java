package com.driver;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class MovieRepository {

    private Map<String,Movie> db1=new HashMap<>();
    private Map<String,Director> db2=new HashMap<>();

    private Map<String,String> db3=new HashMap<>();
    public String addMovie(Movie movie) {
        db1.put(movie.getName(),movie);
        return "You Have successfully Added your movie...";
    }

    public String addDirector(Director director) {
        db2.put(director.getName(),director);
        return "Successfully Added Director to your database";
    }

    public String addMovieDirectorPair(String movieName, String directorName) {
        if(db1.containsKey(movieName) && db2.containsKey(directorName)){
            db3.put(directorName,movieName);
            return "Successfully Added MovieDirectorPair";
        }
        return "";
    }

    public Movie getMovieByName(String movieName) {
        return db1.get(movieName);
    }

    public Director getDirectorByName(String directorName) {
        return db2.get(directorName);
    }

    public List<String> getMoviesByDirectorName(String directorName) {
        List<String> list=new ArrayList<>();
        if(db3.containsKey(directorName)){
            list.add(db3.get(directorName));
        }
        return list;
    }
    public List<String> findAllMovies() {
        List<String> ans=new ArrayList<>();
        for (Map.Entry<String, Movie> entry : db1.entrySet()) {
            String key = entry.getKey();
            ans.add(key);
        }
        return ans;
    }

    public String deleteDirectorByName(String directorName) {
        if(db3.containsKey(directorName)){
            String mname=db3.get(directorName);
            db3.remove(directorName);
            db2.remove(directorName);
            db1.remove(mname);
            return "Successfully deleted movie and director";
        }
        return "";
    }

    public String deleteAllDirectors() {
        for (Map.Entry<String, String> entry : db3.entrySet()) {
            String key = entry.getKey();
            String value=entry.getValue();
            if(db1.containsKey(value)){
                db1.remove(value);
            }
            if(db2.containsKey(key)){
                db2.remove(key);
            }
        }
        db3.clear();
        return "Successfully deleted all movies which made by directors";
    }
}
