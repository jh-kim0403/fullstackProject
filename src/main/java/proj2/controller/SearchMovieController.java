package proj2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import proj2.DTO.Movie;
import proj2.service.SearchMovieService;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:3000", "http://54.193.13.146"})
public class SearchMovieController {
    private final SearchMovieService searchMovieService;

    @Autowired
    public SearchMovieController(SearchMovieService searchMovieService){
        this.searchMovieService = searchMovieService;
    }

    @RequestMapping("/search")
    public List<Movie> searchMovie(@RequestParam String title,
                                   @RequestParam (name = "year", required = false, defaultValue = "") Integer year,
                                   @RequestParam (name = "director", required = false)String director,
                                   @RequestParam (name = "starName", required = false)String starName,
                                   @RequestParam (name = "order", required = false, defaultValue = "ta")String order,
                                   @RequestParam (name = "page", required = false, defaultValue = "1")int page,
                                   @RequestParam (name = "pageSize", required = false, defaultValue = "10") Integer pageSize) {

        return searchMovieService.searchMovie(title, year, director, starName, order, page, pageSize);
    }
}
