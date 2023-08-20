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
            db3.put(movieName,directorName);
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
        for (Map.Entry<String, String> entry : db3.entrySet()) {
            String mname=entry.getKey();
            String dname=entry.getValue();
            if(dname.equals(directorName)){
                list.add(mname);
            }
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
        for (Map.Entry<String, String> entry : db3.entrySet()) {
            String key = entry.getKey();
            if(db3.containsValue(directorName)){
                db3.remove(key,directorName);
                db1.remove(key);
                db2.remove(directorName);
                return "Successfully deleted movie and director";
            }
        }
        return "";
    }

    public String deleteAllDirectors() {
        for (Map.Entry<String, String> entry : db3.entrySet()) {
            String key = entry.getKey();
            String value=entry.getValue();
            if(db1.containsKey(key)){
                db1.remove(key);
            }
            if(db2.containsValue(value)){
                db2.remove(value);
            }
        }
        db3.clear();
        return "Successfully deleted all movies which made by directors";
    }
}
