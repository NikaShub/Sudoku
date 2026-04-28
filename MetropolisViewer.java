import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MetropolisViewer extends JFrame {

    private JTextField metropolis;
    private JTextField continent;
    private JTextField population;

    private JLabel searchOptions;

    private JButton addButton;
    private JButton searchButton;

    private JComboBox<String> popLargeOrLessEqual;
    private JComboBox<String> matchOrPartial;

    private JTable table;

    private MetropolisTableModel tableModel;

    public MetropolisViewer() {
        super("Metropolis Viewer");
        tableModel = new MetropolisTableModel();

        addGUI();
        setUpListeners();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    // Responsible for adding listeners to our buttons
    private void setUpListeners() {
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String name = metropolis.getText();
                String cont = continent.getText();
                String pop = population.getText();
                tableModel.addMetropolis(name, cont, pop);
            }
        });

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String name = metropolis.getText();
                String cont = continent.getText();
                String pop = population.getText();
                tableModel.searchMetropolises(name, cont, pop, matchOrPartial.getSelectedIndex() == 0, popLargeOrLessEqual.getSelectedIndex() == 0);
            }
        });
    }

    // Responsible for creating buttons and fields
    private void addGUI() {
        setLayout(new BorderLayout(10, 10));
        addTextFields();
        addTable();
        addDropDownsAndButtons();
    }

    // Adds text fields to the north
    private void addTextFields() {
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));
        topPanel.add(new JLabel("Metropolis: "));

        metropolis = new JTextField(15);
        topPanel.add(metropolis);
        topPanel.add(new JLabel("Continent: "));

        continent = new JTextField(15);
        topPanel.add(continent);
        topPanel.add(new JLabel("Population: "));

        population = new JTextField(15);
        topPanel.add(population);
        add(topPanel, BorderLayout.NORTH);
    }


    // Adds table in the center
    private void addTable() {
        table = new JTable(tableModel);
        table.setRowHeight(100);
        table.setFont(new Font("SansSerif", Font.PLAIN, 34));
        table.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 34));
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);
    }

    // Adds two dropDowns choices and buttons
    private void addDropDownsAndButtons() {
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        searchOptions = new JLabel("search options: ");
        searchOptions.setVisible(true);

        addButton = new JButton("Add");

        searchButton = new JButton("Search");

        String[] popOptions = {"Population Larger Than", "Population Smaller Than or Equal"};
        popLargeOrLessEqual = new JComboBox<>(popOptions);

        String[] matchOptions = {"Exact Match", "Partial Match"};
        matchOrPartial = new JComboBox<>(matchOptions);

        Dimension maxSize = new Dimension(Integer.MAX_VALUE, 70);
        searchOptions.setMaximumSize(maxSize);
        addButton.setMaximumSize(maxSize);
        searchButton.setMaximumSize(maxSize);
        popLargeOrLessEqual.setMaximumSize(maxSize);
        matchOrPartial.setMaximumSize(maxSize);

        rightPanel.add(addButton);
        rightPanel.add(searchButton);
        rightPanel.add(searchOptions);
        rightPanel.add(popLargeOrLessEqual);
        rightPanel.add(matchOrPartial);
        add(rightPanel, BorderLayout.EAST);
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {}

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new MetropolisViewer();
            }
        });
    }
}
