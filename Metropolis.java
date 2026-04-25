

/**
 * Polis class which will save name, cont, and population
 */
public class Metropolis {
    private String name;
    private String continent;
    private Long population;

    /**
     * creates Metropolis object.
     * * @param name city name
     * @param continent continent
     * @param population amount of people
     */
    public Metropolis(String name, String continent, Long population) {
        this.name = name;
        this.continent = continent;
        this.population = population;
    }

    public String getName() {
        return name;
    }

    public String getContinent() {
        return continent;
    }

    public long getPopulation() {
        return population;
    }
}
