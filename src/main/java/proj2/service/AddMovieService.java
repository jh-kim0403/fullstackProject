package proj2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proj2.DAO.AddMovieDAO;

@Service
public class AddMovieService {

    @Autowired
    private AddMovieDAO addMovieDAO = new AddMovieDAO();

    public String addMovie(String title, Integer year, String director, String starName, Integer birthYear, String genre){
        return addMovieDAO.addMovie(title, year, director, starName, birthYear, genre);
    }
}
