package proj2.repos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import proj2.DTO.Movie;

import java.util.ArrayList;
import java.util.List;

@Repository
public class BrowseMovieRepo {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Movie> getMovies(String title, String genre) {
        String genreQuery = "SELECT * FROM\n" +
                "(SELECT movie.movieId, movie.title, movie.year, movie.director, genres.genres, stars.stars, movie.rating, stars.starsId, genres.genresId\n" +
                " FROM\n" +
                "     (SELECT r.movieId, m.title, m.year, m.director, r.rating\n" +
                "      FROM ratings r RIGHT JOIN movies m on r.movieId = m.id\n" +
                "     ) as movie LEFT JOIN\n" +
                "     (SELECT gim.movieId,\n" +
                "             GROUP_CONCAT(g.name ORDER BY g.name SEPARATOR ', ') AS genres,\n" +
                "             GROUP_CONCAT(g.id SEPARATOR ', ') as genresId\n" +
                "      FROM genres g\n" +
                "               JOIN genres_in_movies gim ON g.id = gim.genreId\n" +
                "      GROUP BY gim.movieId) as genres ON movie.movieId = genres.movieId  LEFT JOIN\n" +
                "     (SELECT sim.movieId,\n" +
                "             GROUP_CONCAT(s.name SEPARATOR ', ') AS stars,\n" +
                "             GROUP_CONCAT(s.id SEPARATOR ', ') AS starsId\n" +
                "      FROM stars s\n" +
                "               JOIN stars_in_movies sim ON s.id = sim.starId\n" +
                "      GROUP BY sim.movieId) as stars ON movie.movieId = stars.movieId) as rs\n" +
                "WHERE genres LIKE CONCAT('%', ?, '%')";

        String titleQuery = "SELECT * FROM\n" +
                "(SELECT movie.movieId, movie.title, movie.year, movie.director, genres.genres, stars.stars, movie.rating, stars.starsId, genres.genresId\n" +
                " FROM\n" +
                "     (SELECT r.movieId, m.title, m.year, m.director, r.rating\n" +
                "      FROM ratings r RIGHT JOIN movies m on r.movieId = m.id\n" +
                "     ) as movie LEFT JOIN\n" +
                "     (SELECT gim.movieId,\n" +
                "             SUBSTRING_INDEX(GROUP_CONCAT(g.name ORDER BY g.name SEPARATOR ', '), ', ', 3) AS genres,\n" +
                "             GROUP_CONCAT(g.id SEPARATOR ', ') as genresId\n" +
                "      FROM genres g\n" +
                "               JOIN genres_in_movies gim ON g.id = gim.genreId\n" +
                "      GROUP BY gim.movieId) as genres ON movie.movieId = genres.movieId LEFT JOIN\n" +
                "     (SELECT sim.movieId,\n" +
                "             SUBSTRING_INDEX(GROUP_CONCAT(s.name SEPARATOR ', '), ', ', 3) AS stars,\n" +
                "             SUBSTRING_INDEX(GROUP_CONCAT(s.id SEPARATOR ', '), ', ', 3) AS starsId\n" +
                "      FROM stars s\n" +
                "               JOIN stars_in_movies sim ON s.id = sim.starId\n" +
                "      GROUP BY sim.movieId) as stars ON movie.movieId = stars.movieId) as rs\n" +
                "WHERE title LIKE CONCAT(?, '%')";
        if(title == null){
            return jdbcTemplate.query(genreQuery, new Object[] { genre}, (rs, rowNum) ->
                    new Movie(
                            rs.getString("movieId"),
                            rs.getString("title"),
                            rs.getString("genres"),
                            rs.getString("director"),
                            rs.getString("year"),
                            rs.getString("stars"),
                            rs.getDouble("rating"),
                            rs.getString("starsId"),
                            rs.getString("genresId")
                    )
            );
        } else {

            return jdbcTemplate.query(titleQuery, new Object[] {title}, (rs, rowNum) ->
                    new Movie(
                            rs.getString("movieId"),
                            rs.getString("title"),
                            rs.getString("year"),
                            rs.getString("director"),
                            rs.getString("genres"),
                            rs.getString("stars"),
                            rs.getDouble("rating"),
                            rs.getString("starsId"),
                            rs.getString("genresId")
                    )
            );
        }

    }
}
