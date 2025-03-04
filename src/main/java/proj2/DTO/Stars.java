package proj2.DTO;

public class Stars {
    String name;
    int DOB;
    String allMovies;
    String moviesId;

    public Stars() {}

    public Stars(String name, int DOB, String allMovies, String moviesId) {
        super();
        this.name = name;
        this.DOB = DOB;
        this.allMovies = allMovies;
        this.moviesId = moviesId;
    }

    public String getAllMovies() {
        return allMovies;
    }

    public int getDOB() {
        return DOB;
    }

    public String getMoviesId() {
        return moviesId;
    }

    public String getName() {
        return name;
    }
}
