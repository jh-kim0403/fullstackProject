package proj2.DTO;

public class AddMovieRequestDTO {
    private String title;
    private Integer year;
    private String director;
    private String starName;
    private Integer birthYear;
    private String genre;

    public AddMovieRequestDTO(String title, Integer year, String director, String starName, Integer birthYear, String genre) {
        this.title = title;
        this.year = year;
        this.director = director;
        this.starName = starName;
        this.birthYear = birthYear;
        this.genre = genre;
    }

    public String getGenre() {
        return genre;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public void setStarName(String starName) {
        this.starName = starName;
    }

    public void setBirthYear(Integer birthYear) {
        this.birthYear = birthYear;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Integer getBirthYear() {
        return birthYear;
    }

    public String getStarName() {
        return starName;
    }

    public String getDirector() {
        return director;
    }

    public Integer getYear() {
        return year;
    }

    public String getTitle() {
        return title;
    }
}
