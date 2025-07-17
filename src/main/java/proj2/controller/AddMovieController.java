package proj2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import proj2.DTO.AddMovieRequestDTO;
import proj2.service.AddMovieService;

@RestController
@CrossOrigin(origins = {"http://localhost:3000", "http://54.193.13.146"})
public class AddMovieController {

    @Autowired
    AddMovieService addMovieService = new AddMovieService();

    @PostMapping
    @RequestMapping("/api/dashboard/addmovie")
    public String AddMovie(@RequestBody AddMovieRequestDTO addMovieRequestDTO){
        return addMovieService.addMovie(addMovieRequestDTO.getTitle(),
                addMovieRequestDTO.getYear(),
                addMovieRequestDTO.getDirector(),
                addMovieRequestDTO.getStarName(),
                addMovieRequestDTO.getBirthYear(),
                addMovieRequestDTO.getGenre());
    }
}
