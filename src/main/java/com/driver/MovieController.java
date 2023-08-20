package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {
    @Autowired
    private MovieService movieService;

    @PostMapping("/add-movie")
    public ResponseEntity<String> addMovie(@RequestBody Movie movie){
        return ResponseEntity.ok(movieService.addMovie(movie));
    }

    @PostMapping("/add-director")
    public ResponseEntity<String> addDirector(@RequestBody Director director){
        return ResponseEntity.ok(movieService.addDirector(director));
    }

    @PutMapping("/add-movie-director-pair")
    public ResponseEntity<String> addMovieDirectorPair(@RequestParam("mname") String movieName,@RequestParam("dname") String directorName){
        return ResponseEntity.ok(movieService.addMovieDirectorPair(movieName,directorName));
    }
    @GetMapping("/get-movies-by-name/{name}")
    public ResponseEntity<Movie> getMovieByName(@PathVariable("name") String movieName){
        return ResponseEntity.ok(movieService.getMovieByName(movieName));
    }
    @GetMapping("/get-director-by-name/{name}")
    public ResponseEntity<Director> getDirectorByName(@PathVariable("name") String directorName){
        return ResponseEntity.ok(movieService.getDirectorByName(directorName));
    }
    @GetMapping("/get-movies-by-director-name/{name}")
    public ResponseEntity<List<String>> getMoviesByDirectorName(@PathVariable("name") String directorName){
        return ResponseEntity.ok(movieService.getMoviesByDirectorName(directorName));
    }
    @GetMapping("/get-all-movies")
    public ResponseEntity<List<String>> findAllMovies(){
        return ResponseEntity.ok(movieService.findAllMovies());
    }
    @DeleteMapping("/delete-director-by-name")
    public ResponseEntity<String> deleteDirectorByName(@RequestParam("name") String directorName){
        return ResponseEntity.ok(movieService.deleteDirectorByName(directorName));
    }
    @DeleteMapping("/delete-all-directors")
    public ResponseEntity<String> deleteAllDirectors(){
        return ResponseEntity.ok(movieService.deleteAllDirectors());
    }
}
