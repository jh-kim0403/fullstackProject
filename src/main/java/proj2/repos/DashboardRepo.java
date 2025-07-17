package proj2.repos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import proj2.DTO.MetadataResponseDTO;

import java.util.List;

@Repository
public class DashboardRepo {

    @Autowired
    JdbcTemplate jdbcTemplate = new JdbcTemplate();

    public int addStar(String name, Integer birthYear){
        System.out.println("birthYear is " + birthYear);
        String query = "INSERT INTO moviedb.stars(id, name, birthYear)\n" +
        "SELECT CONCAT('nm', LPAD(CONVERT(SUBSTRING(MAX(id), 3), UNSIGNED) + 1, 7, '0')), ?, ? " +
                "FROM moviedb.stars;";

        return jdbcTemplate.update(query, name, birthYear);

    }

    public List<MetadataResponseDTO> getMetadata(){
        String query = "SELECT TABLE_NAME, \n" +
                "       GROUP_CONCAT(CONCAT(COLUMN_NAME, ' (', DATA_TYPE, ')') ORDER BY ORDINAL_POSITION SEPARATOR ', ') AS columns\n" +
                "FROM INFORMATION_SCHEMA.COLUMNS\n" +
                "WHERE TABLE_SCHEMA = 'moviedb'\n" +
                "GROUP BY TABLE_NAME\n" +
                "ORDER BY TABLE_NAME;";
        return jdbcTemplate.query(query, (rs, rowNum) ->
            new MetadataResponseDTO(
                    rs.getString("TABLE_NAME"),
                    rs.getString("columns")
            )
        );

    }
}
