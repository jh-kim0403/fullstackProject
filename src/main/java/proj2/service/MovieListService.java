package proj2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proj2.DTO.Movie;
import proj2.repos.MovieListRepo;

import java.util.List;

@Service
public class MovieListService {

    @Autowired
    private final MovieListRepo movieListRepo;

    public MovieListService(MovieListRepo movieListRepo) {
        this.movieListRepo = movieListRepo;
    }

    public List<Movie> getTopMovies() {
        System.out.println(movieListRepo.getTop20Movies().size());
        return movieListRepo.getTop20Movies();
    }


}
