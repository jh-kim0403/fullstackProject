package proj2.DAO;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import javax.persistence.ParameterMode;



@Repository
public class AddMovieDAO {

    @PersistenceContext
    private EntityManager entityManager;

    public String addMovie(String title, int year, String director, String star, int birthYear, String genre) {
        StoredProcedureQuery storedProcedure = entityManager.createStoredProcedureQuery("add_movie");

        // Register input parameters
        storedProcedure.registerStoredProcedureParameter("movieTitle", String.class, ParameterMode.IN);
        storedProcedure.registerStoredProcedureParameter("movieYear", Integer.class, ParameterMode.IN);
        storedProcedure.registerStoredProcedureParameter("movieDir", String.class, ParameterMode.IN);
        storedProcedure.registerStoredProcedureParameter("star", String.class, ParameterMode.IN);
        storedProcedure.registerStoredProcedureParameter("birthYear", Integer.class, ParameterMode.IN);
        storedProcedure.registerStoredProcedureParameter("genre", String.class, ParameterMode.IN);

        // Register output parameter
        storedProcedure.registerStoredProcedureParameter("message", String.class, ParameterMode.OUT);

        // Set values for input parameters
        storedProcedure.setParameter("movieTitle", title);
        storedProcedure.setParameter("movieYear", year);
        storedProcedure.setParameter("movieDir", director);
        storedProcedure.setParameter("star", star);
        storedProcedure.setParameter("birthYear", birthYear);
        storedProcedure.setParameter("genre", genre);

        // Execute the stored procedure
        storedProcedure.execute();

        // Retrieve the output message
        return (String) storedProcedure.getOutputParameterValue("message");
    }
}
