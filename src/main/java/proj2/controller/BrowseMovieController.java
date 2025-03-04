package proj2.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import proj2.DTO.Movie;
import proj2.service.BrowseMovieService;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:3000", "http://54.193.13.146"})
public class BrowseMovieController {

    private final BrowseMovieService browseMovieService;

    public BrowseMovieController(BrowseMovieService browseMovieService) {
        this.browseMovieService = browseMovieService;
    }

    @RequestMapping("/browse")
    public List<Movie> browse(@RequestParam(name = "genre", required = false) String genre,
                              @RequestParam (name = "title", required = false)String title) {
        return browseMovieService.getSingleMovie(title, genre);
    }


}
