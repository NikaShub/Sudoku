import org.junit.Before;
import org.junit.Test;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class MetropolisDAOTest {

    private MetropolisDAO dao;
    private static final String URL = "jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1";
    private static final String USER = "sa";
    private static final String PASSWORD = "";

    @Before
    public void setUp() throws Exception {
        dao = new MetropolisDAO(URL, USER, PASSWORD);

        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = con.createStatement()) {

            stmt.execute("DROP TABLE IF EXISTS metropolises");
            stmt.execute("CREATE TABLE metropolises (metropolis VARCHAR(64), continent VARCHAR(64), population BIGINT)");
            stmt.execute("INSERT INTO metropolises VALUES ('Mumbai', 'Asia', 20400000)");
            stmt.execute("INSERT INTO metropolises VALUES ('San Francisco', 'North America', 5780000)");
            stmt.execute("INSERT INTO metropolises VALUES ('San Jose', 'North America', 7354555)");
            stmt.execute("INSERT INTO metropolises VALUES ('Rome', 'Europe', 2715000)");
            stmt.execute("INSERT INTO metropolises VALUES ('London', 'Europe', 8580000)");
        }
    }

    /*
      * ADD tests
     */

    @Test
    public void testAddMetropolisStandard() {
        Metropolis polis = new Metropolis("Berlin", "Europe", 3645000L);
        dao.addMetropolis(polis);

        List<Metropolis> result = dao.searchMetropolises("Berlin", "Europe", "3645000", true, false);
        assertEquals(1, result.size());
        assertEquals("Berlin", result.get(0).getName());
        assertEquals("Europe", result.get(0).getContinent());
        assertEquals(3645000L, result.get(0).getPopulation());

        // ADD second city
        polis = new Metropolis("Bremen", "Europe", 345000L);
        dao.addMetropolis(polis);

        result = dao.searchMetropolises("Bremen", "Europe", "345000", true, false);
        assertEquals(1, result.size());
        assertEquals("Bremen", result.get(0).getName());
        assertEquals("Europe", result.get(0).getContinent());
        assertEquals(345000L, result.get(0).getPopulation());
    }

    @Test
    public void testAddMetropolisEmpty() {
        Metropolis m = new Metropolis("", "", 0L);
        dao.addMetropolis(m);

        List<Metropolis> result = dao.searchMetropolises("", "", "0", true, false);
        assertEquals(1, result.size());
        assertEquals("", result.get(0).getName());
        assertEquals("", result.get(0).getContinent());
        assertEquals(0L, result.get(0).getPopulation());
    }

    @Test
    public void testAddMetropolisMax() {
        long maxPop = Long.MAX_VALUE;
        Metropolis m = new Metropolis("Future City", "Mars", maxPop);
        dao.addMetropolis(m);

        List<Metropolis> result = dao.searchMetropolises("Future City", "", "", true, false);
        assertEquals(1, result.size());
        assertEquals(maxPop, result.get(0).getPopulation());
    }


    /*
     *   SEARCH tests
     */

    @Test
    public void testSearchExactName() {
        List<Metropolis> result = dao.searchMetropolises("Rome", "", "", true, false);
        assertEquals(1, result.size());
        assertEquals("Rome", result.get(0).getName());
        result = dao.searchMetropolises("San Francisco", "", "", true, false);
        assertEquals( 1, result.size());

        result = dao.searchMetropolises("NonExistent", "", "", true, false);
        assertEquals(0, result.size());
    }

    @Test
    public void testSearchPartialName() {
        List<Metropolis> result = dao.searchMetropolises("San", "", "", false, false);
        assertEquals(2, result.size());
        assertEquals("San Francisco", result.get(0).getName());
    }

    @Test
    public void testSearchPopulationLarger() {
        List<Metropolis> result = dao.searchMetropolises("", "", "8000000", false, true);
        assertEquals(2, result.size());

        result = dao.searchMetropolises("", "", "20000000", false, true);
        assertEquals( 1, result.size());

        result = dao.searchMetropolises("", "", "30000000", false, true);
        assertEquals(0, result.size());
    }

    @Test
    public void testSearchPopulationLessOrEqual() {
        List<Metropolis> result = dao.searchMetropolises("", "", "6000000", false, false);
        assertEquals(2, result.size());

        result = dao.searchMetropolises("", "", "2715000", false, false);
        assertEquals(1, result.size());

        result = dao.searchMetropolises("", "", "1000000", false, false);
        assertEquals(0, result.size());
    }

    @Test
    public void testSearchContinentAndPopulation() {
        List<Metropolis> result = dao.searchMetropolises("", "North America", "6000000", true, true);
        assertEquals(1, result.size());
        assertEquals("San Jose", result.get(0).getName());

        result = dao.searchMetropolises("", "Europe", "8580000", true, false);
        assertEquals( 2, result.size());

        result = dao.searchMetropolises("", "Asia", "0", true, true);
        assertEquals(1, result.size());
    }

    @Test
    public void testSearchEmpty() {
        List<Metropolis> result = dao.searchMetropolises("", "", "", false, false);
        assertEquals( 5, result.size());

        result = dao.searchMetropolises(null, null, null, true, true);
        assertEquals(5, result.size());

       result = dao.searchMetropolises("", "", "", true, true);
        assertEquals( 5, result.size());
    }


    @Test
    public void testSearchExactMatchFails() {
        List<Metropolis> result = dao.searchMetropolises("San", "", "", true, false);
        assertEquals(0, result.size());
        result = dao.searchMetropolises("ncisc", "", "", true, false);
        assertEquals( 0, result.size());

        result = dao.searchMetropolises("London ", "", "", true, false);
        assertEquals(0, result.size());
    }

    @Test
    public void testSearchSqlCommands() {
        List<Metropolis> result = dao.searchMetropolises("' OR 1=1 --", "", "", true, false);
        assertEquals( 0, result.size());

        result = dao.searchMetropolises("", "Asia' UNION SELECT * FROM metropolises --", "", false, false);
        assertEquals( 0, result.size());
        result = dao.searchMetropolises("Rome\"; DROP TABLE metropolises; --", "", "", true, false);
        assertEquals(0, result.size());
        assertEquals( 1, dao.searchMetropolises("Rome", "", "", true, false).size());
    }

    @Test
    public void testSearchAll() {
        List<Metropolis> result = dao.searchMetropolises("San Francisco", "North America", "5000000", true, true);
        assertEquals( 1, result.size());
        assertEquals("San Francisco", result.get(0).getName());

        result = dao.searchMetropolises("San", "North", "6000000", false, false);
        assertEquals(1, result.size());
        assertEquals("San Francisco", result.get(0).getName());

        result = dao.searchMetropolises("Rome", "Europe", "10000000", true, true);
        assertEquals( 0, result.size());
        dao.addMetropolis(new Metropolis("San Diego", "North America", 1400000L));
        List<Metropolis> res4 = dao.searchMetropolises("San", "America", "1000000", false, true);
        assertEquals( 3, res4.size());
    }

    // I added basic constructor test, because we use h2 above, i wanted to check creating basic DAO
    @Test
    public void testMetropolisDAOBConstructor() {
        MetropolisDAO dao = new MetropolisDAO();
        org.junit.Assert.assertNotNull(dao);
    }

}
