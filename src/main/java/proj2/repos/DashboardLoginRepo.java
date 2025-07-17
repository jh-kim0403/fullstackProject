package proj2.repos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class DashboardLoginRepo {

    @Autowired
    private JdbcTemplate jdbcTemplate = new JdbcTemplate();

    public int loginDashboardUser(String username, String password){
        String query = "SELECT COUNT(*) FROM employees WHERE email = ? AND password = ?;";

        return jdbcTemplate.queryForObject(query, Integer.class, username, password);
    }
}
