import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class MetropolisTableModel extends AbstractTableModel {

    private List<Metropolis> metropolises;
    private final String[] columnNames = {"Metropolis", "Continent", "Population"};
    private final MetropolisDAO dao;

    public MetropolisTableModel() {
        this.metropolises = new ArrayList<>();
        this.dao = new MetropolisDAO();
    }

    @Override
    public int getRowCount() {
        return metropolises.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public Object getValueAt(int index, int columnIndex) {
        Metropolis mp = metropolises.get(index);
        switch (columnIndex) {
            case 0: return mp.getName();
            case 1: return mp.getContinent();
            case 2: return mp.getPopulation();
            default: return null;
        }
    }

    /**
     * adds polis into base and renews table and only shows this row which we added on view
     * * @param name city name
     * @param continent continent
     * @param populationStr population but as a string, because we have it from user
     */
    public void addMetropolis(String name, String continent, String populationStr) {
        long population = 0;
        try {
            if (!populationStr.isEmpty()) {
                population = Long.parseLong(populationStr);
            }
        } catch (NumberFormatException ignored) {
            throw new IllegalArgumentException();
        }
        Metropolis m = new Metropolis(name, continent, population);
        dao.addMetropolis(m);
        metropolises = new ArrayList<>();
        metropolises.add(m);
        fireTableDataChanged();
    }

    /**
     * searches for polis in database
     * * @param name city name
     * @param continent continent
     * @param population population of country
     * @param exactMatch exact match (true) or partial (false)
     * @param populationLarger more population (true) or less or equal (false)
     */
    public void searchMetropolises(String name, String continent, String population,
                                   boolean exactMatch, boolean populationLarger) {
        metropolises = dao.searchMetropolises(name, continent, population, exactMatch, populationLarger);
        fireTableDataChanged();
    }
}
