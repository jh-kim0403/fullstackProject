package proj2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import proj2.DTO.Movie;
import proj2.repos.SingleMovieRepo;

import java.util.List;

@Service
public class SingleMovieService {

    @Autowired
    private final SingleMovieRepo singleMovieRepo;

    public SingleMovieService(SingleMovieRepo singleMovieRepo, SingleMovieRepo singleMovieRepo1) {
        this.singleMovieRepo = singleMovieRepo1;
    }

    public List<Movie> getSingleMovie(String id) {
        return singleMovieRepo.getSingleMovie(id);
    }

}
