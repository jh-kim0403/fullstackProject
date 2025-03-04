package proj2.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import proj2.DTO.Movie;
import proj2.service.SingleMovieService;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class SingleMovieController {

    private final SingleMovieService singleMovieService;

    @Autowired
    SingleMovieController(SingleMovieService singleMovieService) {
        this.singleMovieService = singleMovieService;
    }

    @RequestMapping("/singlemovie")
    public List<Movie> singleMovie(@RequestParam String id) {
        return singleMovieService.getSingleMovie(id);
    }


}
