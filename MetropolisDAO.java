import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MetropolisDAO {
    private static final String URL = "jdbc:mysql://localhost:3306/metropolis_db?allowPublicKeyRetrieval=true&useSSL=false";
    private static final String USER = "nika";
    private static final String PASSWORD = "1234";

    /**
     * Makes contact with base
     */
    private Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * adds new polis into base
     * * @param m Metropolis polis object
     */
    public void addMetropolis(Metropolis m) {
        String query = "INSERT INTO metropolises (metropolis, continent, population) VALUES (?, ?, ?)";
        try (Connection con = getConnection(); PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setString(1, m.getName());
            stmt.setString(2, m.getContinent());
            stmt.setLong(3, m.getPopulation());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * Searches for polis
     * * @param name city name
     * @param continent continent
     * @param population population of people
     * @param exactMatch exact match (true) or partial (false)
     * @param populationLarger more population (true) or less or equal (false)
     * @return list of foundings
     */
    public List<Metropolis> searchMetropolises(String name, String continent, String population,
                                               boolean exactMatch, boolean populationLarger) {
        List<Metropolis> result = new ArrayList<>();
        StringBuilder query = new StringBuilder("SELECT * FROM metropolises WHERE 1=1 ");
        List<Object> args = new ArrayList<>();  // here i will have symbols which i will use to change ? into
        nameQuery(exactMatch, name, query, args);
        contQuery(exactMatch, continent, query, args);
        populationQuery(populationLarger, population, query, args);

        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(query.toString())) {
            for (int i = 0; i < args.size(); i++) {
                stmt.setObject(i + 1, args.get(i));
            }
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                result.add(new Metropolis(
                        rs.getString("metropolis"),
                        rs.getString("continent"),
                        rs.getLong("population")
                ));
            }

        } catch(SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    // Adds population specifications to query to execute
    private void populationQuery(boolean populationLarger, String population, StringBuilder query, List<Object> args) {
        if (population != null && !population.isEmpty()) {
            try {
                long popNum = Long.parseLong(population);
                if (populationLarger) query.append("AND population > ? ");
                else query.append("AND population <= ? ");
                args.add(popNum);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException(e);
            }
        }
    }

    // Adds continent specifications to query to execute
    private void contQuery(boolean exactMatch, String continent, StringBuilder query, List<Object> args) {
        if (continent != null && !continent.isEmpty()) {
            if (exactMatch) {
                query.append("AND continent = ? ");
                args.add(continent);
            } else {
                query.append("AND continent LIKE ? ");
                args.add("%" + continent + "%");
            }
        }
    }

    // Adds name specifications to query to execute
    private void nameQuery(boolean exactMatch, String name, StringBuilder query, List<Object> args) {
        if (name != null && !name.isEmpty()) {
            if (exactMatch) {
                query.append("AND metropolis = ? ");
                args.add(name);
            } else {
                query.append("AND metropolis LIKE ? ");
                args.add("%" + name + "%");
            }
        }
    }
}
