package proj2.repos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class PaymentRepo {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Map<String, String> validatePayment(String firstName, String lastName, String ccId, String expiration) {
        String query = "SELECT c.id, c.firstName, c.lastName, c.ccId\n" +
                "FROM customers as c INNER JOIN creditcards as cc ON c.ccId = cc.id\n" +
                "WHERE c.firstName = ? and cc.lastName = ? and c.ccId = ? and expiration = ?;";

        List<Object> params = new ArrayList<>();
        params.add(firstName);
        params.add(lastName);
        params.add(ccId);
        params.add(expiration);

        Map<String, String> response = new HashMap<>();
        try {
            jdbcTemplate.queryForObject(query, params.toArray(), (rs, rowNum) -> {
                response.put("firstName", rs.getString("firstName"));
                response.put("lastName", rs.getString("lastName"));
                response.put("ccId", rs.getString("ccId"));
                response.put("id", rs.getString("id"));
                return response;
            });
        } catch (EmptyResultDataAccessException emptyResultDataAccessException){
            System.out.println(emptyResultDataAccessException.getMessage());
        } catch (NullPointerException e) {
            System.out.println("PaymentRepo: Null pointer exception in first query");
            throw new RuntimeException(e);
        }

        return response;
    }

    public int insertSales(String id, String movieId, String currentDate){
        String query = "INSERT INTO sales (customerId, movieId, saleDate)\n" +
        "VALUES (?, ?, ?);";
        try{
            return jdbcTemplate.update(query, id, movieId, currentDate);
        } catch (NullPointerException e){
            System.out.println("PaymentRepo: Null pointer exception in second query");
            return 0;
        }

    }
}
