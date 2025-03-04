package proj2.repos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;
import proj2.DTO.Movie;
import java.util.ArrayList;
import java.util.List;

@Repository
public class SearchMovieRepo {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Movie> search( String title,
                               Integer year,
                               String director,
                               String starName,
                               String order,
                               int page,
                               int pageSize) {
        String query = 	"SELECT * FROM\n" +
                "(SELECT movie.movieId, movie.title, movie.year, movie.director, genres.genres, stars.stars, movie.rating, stars.starsId, genres.genres_id\n" +
                " FROM\n" +
                "     (SELECT r.movieId, m.title, m.year, m.director, r.rating\n" +
                "      FROM ratings r RIGHT JOIN movies m on r.movieId = m.id\n" +
                "     ) as movie LEFT JOIN\n" +
                "     (SELECT gim.movieId,\n" +
                "             GROUP_CONCAT(g.name ORDER BY g.name SEPARATOR ', ') AS genres,\n" +
                "             GROUP_CONCAT(g.id SEPARATOR ', ') as genres_id\n" +
                "      FROM genres g\n" +
                "               JOIN genres_in_movies gim ON g.id = gim.genreId\n" +
                "      GROUP BY gim.movieId) as genres ON movie.movieId = genres.movieId  LEFT JOIN\n" +
                "     (SELECT sim.movieId,\n" +
                "             GROUP_CONCAT(s.name SEPARATOR ', ') AS stars,\n" +
                "             GROUP_CONCAT(s.id SEPARATOR ', ') AS starsId\n" +
                "      FROM stars s\n" +
                "               JOIN stars_in_movies sim ON s.id = sim.starId\n" +
                "      GROUP BY sim.movieId) as stars ON movie.movieId = stars.movieId) as rs\n" +
                "WHERE title LIKE '%%'";
        String subquery = "";
        int count = 0;
        List<Object> params = new ArrayList<>();

        System.err.println(year + " director: " + director + " starname: " + starName);
        System.err.println("order: " + order);
        System.err.println("page: " + page);
        System.err.println("pageSize: " + pageSize);

        if(title != null) {
            query += " AND title LIKE '%' || ? || '%'";
            params.add(title);
            count++;
        }

        if(year != null) {
            query += " AND year = ?";
            params.add(year);
            count++;
        }

        if(director != null) {
            query += " AND director LIKE '%' || ? || '%'";
            params.add(director);
            count++;
        }
        if(starName != null) {
            subquery += " AND stars LIKE '%' || ? || '%'";
            params.add(starName);
            count++;
        }

        if(count == 0) {
            query += " AND title = ''";
        }
/* MAYBE COULD BE ADDED FOR PAGINATION SEE WHAT I CAN DO IN FRONTEND
        if (order != null) {
            subquery += " ORDER BY ";

            if (order.charAt(0) == 't') {
                subquery += "title";
            } else {
                subquery += "rating";
            }

            if (order.charAt(1) == 'a') {
                subquery += " ASC";
            } else {
                subquery += " DESC";
            }
        }


        int pageVolume = (page - 1) * pageSize;
        subquery += " LIMIT " + String.valueOf(pageSize) + " OFFSET " + String.valueOf(pageVolume);
        */
        query += subquery;


        return jdbcTemplate.query(query, params.toArray(), (rs, rowNum) ->
                new Movie(
                        rs.getString("movieId"),
                        rs.getString("title"),
                        rs.getString("year"),
                        rs.getString("director"),
                        rs.getString("genres"),
                        rs.getString("stars"),
                        rs.getDouble("rating"),
                        rs.getString("starsId"),
                        rs.getString("genres_id")

                )
        );
    }
}
