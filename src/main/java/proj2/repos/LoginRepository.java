package proj2.repos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import proj2.DTO.UserDTO;

import java.util.HashMap;
import java.util.Map;

@Repository
public class LoginRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public boolean authenticateUserName(String username) {
        String sql = "SELECT * FROM customers where email = ?";
        try{
            Integer count = jdbcTemplate.queryForObject(sql, Integer.class, username);
            return count > 0;
        } catch (EmptyResultDataAccessException e){
            return false;
        }
    }

    public Map<String, Object> loginUser(String username) {
        String sql = "SELECT id, email, password, firstName, lastName FROM customers WHERE email = ?";

        try{
            return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {

                UserDTO userDTO = new UserDTO(rs.getInt("id"),
                        rs.getString("email"),
                        null,
                        rs.getString("firstName"),
                        rs.getString("lastName"));

                Map<String, Object> response = new HashMap<>();
                response.put("user", userDTO);
                response.put("password", rs.getString("password"));

                return response;
            }, username);
        } catch (EmptyResultDataAccessException e){
            return null;
        }


    }
}
