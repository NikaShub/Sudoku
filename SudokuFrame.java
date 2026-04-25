import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.*;

import java.awt.*;
import java.awt.event.*;


 public class SudokuFrame extends JFrame {

	 private JTextArea puzzleArea;
	 private JTextArea solutionArea;
	 private JButton checkButton;
	 private JCheckBox autoCheck;
	
	public SudokuFrame() {
		super("Sudoku Solver");
		addGUI();

		checkButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				parseAndSolve();
			}
		});

		puzzleArea.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent documentEvent) {
				autoSolve();
			}

			@Override
			public void removeUpdate(DocumentEvent documentEvent) {
				autoSolve();
			}

			@Override
			public void changedUpdate(DocumentEvent documentEvent) {
                autoSolve();
			}

			private void autoSolve() {
				if (autoCheck.isSelected()) parseAndSolve();
			}
		});

		setLocationByPlatform(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setVisible(true);
	}

	 private void addGUI() {
		 setLayout(new BorderLayout(4, 4));
		 puzzleArea = new JTextArea(15, 20);
		 puzzleArea.setBorder(new TitledBorder("Puzzle"));
		 add(puzzleArea, BorderLayout.CENTER);
		 solutionArea = new JTextArea(15, 20);
		 solutionArea.setBorder(new TitledBorder("Solution"));
		 solutionArea.setEditable(false);
		 add(solutionArea, BorderLayout.EAST);
		 Box controlBox = Box.createHorizontalBox();
		 checkButton = new JButton("Check");
		 autoCheck = new JCheckBox("Auto Check", true);
		 controlBox.add(checkButton);
		 controlBox.add(autoCheck);
		 add(controlBox, BorderLayout.SOUTH);
	 }

	 public void parseAndSolve() {
		try {
			String text = puzzleArea.getText();
			Sudoku board = new Sudoku(text);
			int count = board.solve();
			String result = board.getSolutionText() + "\n";
			result += "solutions: " + count + "\n";
			result += "time: " + board.getElapsed() + "ms\n";
			solutionArea.setText(result);
		} catch (Exception ex) {
			solutionArea.setText("Parsing problem");
		}
	}
	
	
	public static void main(String[] args) {
		// GUI Look And Feel
		// Do this incantation at the start of main() to tell Swing
		// to use the GUI LookAndFeel of the native platform. It's ok
		// to ignore the exception.
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception ignored) { }
		
		SudokuFrame frame = new SudokuFrame();
	}

}
