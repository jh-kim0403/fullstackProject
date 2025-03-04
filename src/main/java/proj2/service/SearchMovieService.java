package proj2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import proj2.DTO.Movie;
import proj2.repos.SearchMovieRepo;

import java.util.List;

@Service
public class SearchMovieService {

    @Autowired
    private SearchMovieRepo searchMovieRepo;

    public SearchMovieService(SearchMovieRepo searchMovieRepo) {
        this.searchMovieRepo = searchMovieRepo;
    }
    public List<Movie> searchMovie(String title,
                                   Integer year,
                                   String director,
                                   String starName,
                                   String order,
                                   int page,
                                   int ipp) {
        return searchMovieRepo.search(title, year, director, starName, order, page, ipp);
    }
}
