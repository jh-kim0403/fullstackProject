package proj2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proj2.DTO.Movie;
import proj2.repos.BrowseMovieRepo;

import java.util.List;

@Service
public class BrowseMovieService {

    @Autowired
    private final BrowseMovieRepo browseMovieRepo;

    public BrowseMovieService(BrowseMovieRepo browseMovieRepo) {
        this.browseMovieRepo = browseMovieRepo;
    }

    public List<Movie> getSingleMovie(String title, String genre) {
        return browseMovieRepo.getMovies(title, genre);
    }

}
