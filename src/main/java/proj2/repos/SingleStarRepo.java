package proj2.repos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import proj2.DTO.Stars;

import java.util.List;

@Repository
public class SingleStarRepo {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Stars> getSingleStar(String id){
        String sql = "SELECT \n" +
                "    s.name AS star_name,\n" +
                "    IFNULL(s.birthYear, 0) AS year_of_birth,\n" +
                "    GROUP_CONCAT(DISTINCT m.title SEPARATOR ', ') AS movies,\n" +
                "    GROUP_CONCAT(DISTINCT m.id SEPARATOR ', ') AS moviesId\n" +
                "FROM \n" +
                "    stars s\n" +
                "LEFT JOIN \n" +
                "    stars_in_movies sim ON s.id = sim.starId\n" +
                "LEFT JOIN \n" +
                "    movies m ON sim.movieId = m.id\n" +
                "WHERE s.id = \"" + id + "\"\n" +
                "GROUP BY \n" +
                "    s.id, s.name, s.birthYear;";
        return jdbcTemplate.query(sql, (rs, numRow) ->
                new Stars(
                        rs.getString("star_name"),
                        rs.getInt("year_of_birth"),
                        rs.getString("movies"),
                        rs.getString("moviesId")
                ));
    }

}
