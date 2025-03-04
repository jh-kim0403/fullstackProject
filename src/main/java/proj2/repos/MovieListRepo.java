package proj2.repos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import proj2.DTO.Movie;

import java.util.List;

@Repository
public class MovieListRepo {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Movie> getTop20Movies(){
        String sql = "SELECT movie.movieId, movie.title, movie.year, movie.director, genres.genres, stars.stars, movie.rating, stars.starsId\n" +
                "FROM\n" +
                "(SELECT r.movieId, m.title, m.year, m.director, r.rating\n" +
                "FROM ratings r, movies m\n" +
                "WHERE r.movieId = m.id\n" +
                "ORDER BY r.rating DESC\n" +
                "LIMIT 20) as movie,\n" +
                "(SELECT gim.movieId, \n" +
                "       SUBSTRING_INDEX(GROUP_CONCAT(g.name ORDER BY g.name SEPARATOR ', '), ', ', 3) AS genres\n" +
                "FROM genres g\n" +
                "JOIN genres_in_movies gim ON g.id = gim.genreId\n" +
                "GROUP BY gim.movieId\n" +
                "ORDER BY gim.movieId) as genres ,\n" +
                "(SELECT sim.movieId,\n" +
                "       SUBSTRING_INDEX(GROUP_CONCAT(s.name SEPARATOR ', '), ', ', 3) AS stars,\n" +
                "       SUBSTRING_INDEX(GROUP_CONCAT(s.id SEPARATOR ', '), ', ', 3) AS starsId\n" +
                "FROM stars s\n" +
                "JOIN stars_in_movies sim ON s.id = sim.starId\n" +
                "GROUP BY sim.movieId\n" +
                "ORDER BY sim.movieId) as stars\n" +
                "WHERE movie.movieId = genres.movieId AND movie.movieId = stars.movieId\n" +
                "ORDER BY movie.rating DESC;";
        return jdbcTemplate.query(sql, (rs, rowNum) ->
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
