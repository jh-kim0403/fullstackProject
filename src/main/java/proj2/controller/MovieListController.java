package proj2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import proj2.DTO.Movie;
import proj2.service.MovieListService;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class MovieListController {

    private final MovieListService movieListService;

    @Autowired
    public MovieListController(MovieListService movieListService) {
        this.movieListService = movieListService;
    }

    @RequestMapping("/movie_list")
    public List<Movie> sayHello() {
        return movieListService.getTopMovies();

    }

}
