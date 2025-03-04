package proj2.repos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import proj2.DTO.Movie;

import java.util.List;

@Repository
public class SingleMovieRepo {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Movie> getSingleMovie(String id){
        String sql = "SELECT \n" +
                "\tm.id,\n" +
                "    m.title,\n" +
                "    m.year,\n" +
                "    m.director,\n" +
                "    GROUP_CONCAT(DISTINCT g.name SEPARATOR ', ') AS genres,\n" +
                "    GROUP_CONCAT(DISTINCT s.name SEPARATOR ', ') AS stars,\n" +
                "    IFNULL(r.rating, 'N/A') AS rating,\n" +
                "    GROUP_CONCAT(DISTINCT s.id SEPARATOR ', ') AS starsId\n" +
                "FROM \n" +
                "    movies m\n" +
                "LEFT JOIN \n" +
                "    genres_in_movies gim ON m.id = gim.movieId\n" +
                "LEFT JOIN \n" +
                "    genres g ON gim.genreId = g.id\n" +
                "LEFT JOIN \n" +
                "    stars_in_movies sim ON m.id = sim.movieId\n" +
                "LEFT JOIN \n" +
                "    stars s ON sim.starId = s.id\n" +
                "LEFT JOIN \n" +
                "    ratings r ON m.id = r.movieId\n" +
                "WHERE\n" +
                "\tm.id = \"" + id + "\"\n" +
                "GROUP BY \n" +
                "    m.id, m.title, m.year, m.director, r.rating;";

        return jdbcTemplate.query(sql, (rs, rowNum) ->
                new Movie(
                        rs.getString("id"),
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
