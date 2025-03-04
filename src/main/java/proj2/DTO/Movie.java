package proj2.DTO;

public class Movie {
    String id;
    String title;
    String genres;
    String director;
    String year;
    String stars;
    double rating;
    String starsId;
    String genresId;



    public Movie(){

    }

    public Movie(String id, String title, String genres, String director, String year, String stars, double rating, String starsId, String genresId) {
        super();
        this.id = id;
        this.title = title;
        this.genres = genres;
        this.director = director;
        this.year = year;
        this.rating = rating;
        this.stars = stars;
        this.starsId = starsId;
        this.genresId = genresId;
    }
    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getGenres() {
        return genres;
    }

    public String getDirector() {
        return director;
    }

    public String getYear() {
        return year;
    }

    public double getRating() {
        return rating;
    }

    public String getStars() {
        return stars;
    }

    public String getStarsId() {
        return starsId;
    }

    public String getGenresId() {
        return genresId;
    }


}
