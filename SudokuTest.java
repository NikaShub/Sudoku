import org.junit.Test;

import java.util.Set;

import static org.junit.Assert.*;

public class SudokuTest {

    private Sudoku sudoku;

    @Test
    public void testToStringEasy() {
        sudoku = new Sudoku(Sudoku.easyGrid);
        String sudokusString = sudoku.toString();
        assertTrue(sudokusString.contains("1 6 4 0 0 0 0 0 2"));
        assertTrue(sudokusString.contains("4 0 0 0 0 0 6 7 9"));
    }


    @Test
    public void testGetSolutionTextEasy() {
        sudoku = new Sudoku(Sudoku.easyGrid);
        sudoku.solve();
        String solution = sudoku.getSolutionText();
        assertNotNull(solution);
        assertFalse(solution.contains("0"));
        assertTrue( solution.length() > 80);
    }

    @Test
    public void testGetElapsedEasy() {
        sudoku = new Sudoku(Sudoku.easyGrid);
        sudoku.solve();
        long time = sudoku.getElapsed();
        assertTrue(time >= 0);
    }

    @Test
    public void testStringConstructorEasy() {
        sudoku = new Sudoku(Sudoku.easyGrid);
        String textInString = "1 6 4 0 0 0 0 0 2 " +
                "2 0 0 4 0 3 9 1 0 " +
                "0 0 5 0 8 0 4 0 7 " +
                "0 9 0 0 0 6 5 0 0 " +
                "5 0 0 1 0 2 0 0 8 " +
                "0 0 8 9 0 0 0 3 0 " +
                "8 0 9 0 4 0 2 0 0 " +
                "0 7 3 5 0 9 0 0 1 " +
                "4 0 0 0 0 0 6 7 9";

        Sudoku stringSudoku = new Sudoku(textInString);
        assertEquals(sudoku.toString(), stringSudoku.toString());
    }

    @Test
    public void testSpotMethodsEasy() {
        sudoku = new Sudoku(Sudoku.easyGrid);
        Sudoku.Spot spot = sudoku.new Spot(0, 0);
        assertEquals(1, spot.getVal());

        // On that point val is 0
        Sudoku.Spot emptySpot = sudoku.new Spot(0, 3);
        assertEquals(0, emptySpot.getVal());

        // check setVal
        emptySpot.setVal(7);
        assertEquals(7, emptySpot.getVal());
        emptySpot.setVal(0);

        // Check getAssignableNumbers
        Set<Integer> validNumbers = emptySpot.getAssignableNumbers();
        assertNotNull(validNumbers);
        assertFalse(validNumbers.contains(1));
        assertFalse(validNumbers.contains(6));
        assertTrue(validNumbers.size() > 0);
    }


    @Test
    public void testSolveMedium() {
        sudoku = new Sudoku(Sudoku.mediumGrid);
        int solution = sudoku.solve();
        assertEquals(1, solution);
    }

    @Test
    public void testToStringMedium() {
        sudoku = new Sudoku(Sudoku.mediumGrid);
        String sudokusString = sudoku.toString();
        assertTrue( sudokusString.contains("5 3 0 0 7 0 0 0 0"));
        assertTrue(sudokusString.contains("0 0 0 0 8 0 0 7 9"));
    }

    @Test
    public void testGetSolutionTextMedium() {
        sudoku = new Sudoku(Sudoku.mediumGrid);
        sudoku.solve();
        String solution= sudoku.getSolutionText();

        assertNotNull( solution);
        assertFalse( solution.contains("0"));
        assertTrue(solution.length() > 80);
    }

    @Test
    public void testGetElapsedMedium() {
        sudoku = new Sudoku(Sudoku.mediumGrid);
        sudoku.solve();
        long time = sudoku.getElapsed();
        assertTrue(time >= 0);
    }

    @Test
    public void testStringConstructor() {
        sudoku = new Sudoku(Sudoku.mediumGrid);
        String mediumText = "530070000" +
                "600195000" +
                "098000060" +
                "800060003" +
                "400803001" +
                "700020006" +
                "060000280" +
                "000419005" +
                "000080079";

        Sudoku stringSudoku = new Sudoku(mediumText);
        assertEquals(sudoku.toString(), stringSudoku.toString());
    }

    @Test
    public void testSpotMethods() {
        sudoku = new Sudoku(Sudoku.mediumGrid);

        Sudoku.Spot spot = sudoku.new Spot(0, 0);
        assertEquals(5, spot.getVal());

        // on that position it is 0 and we check that
        Sudoku.Spot emptySpot = sudoku.new Spot(0, 2);
        assertEquals(0, emptySpot.getVal());

        // check set Val
        emptySpot.setVal(2);
        assertEquals(2, emptySpot.getVal());
        emptySpot.setVal(0);

       // Check if valid numbers contains that numbers.
        Set<Integer> validNumbers = emptySpot.getAssignableNumbers();
        assertNotNull(validNumbers);
        assertFalse( validNumbers.contains(5));
        assertFalse( validNumbers.contains(3));
        assertFalse(validNumbers.contains(7));
        assertFalse(validNumbers.contains(8));
    }

    @Test
    public void testSolveHard() {
        sudoku = new Sudoku(Sudoku.hardGrid);
        int solution = sudoku.solve();
        assertEquals(1, solution);
    }

    @Test
    public void testToStringHard() {
        sudoku = new Sudoku(Sudoku.hardGrid);
        String sudokusString = sudoku.toString();
        assertTrue(sudokusString.contains("3 7 0 0 0 0 0 8 0"));
        assertTrue(sudokusString.contains("0 3 0 0 0 0 0 5 1"));
    }

    @Test
    public void testGetSolutionTextHard() {
        sudoku = new Sudoku(Sudoku.hardGrid);
        sudoku.solve();
        String solution = sudoku.getSolutionText();
        assertNotNull(solution);
        assertFalse(solution.contains("0"));
        assertTrue( solution.length() > 80);
    }

    @Test
    public void testGetElapsedHard() {
        sudoku = new Sudoku(Sudoku.hardGrid);
        sudoku.solve();
        long time = sudoku.getElapsed();
        assertTrue(time >= 0);
    }

    @Test
    public void testStringConstructorHard() {
        sudoku = new Sudoku(Sudoku.hardGrid);
        String hardText = "3 7 0 0 0 0 0 8 0 " +
                "0 0 1 0 9 3 0 0 0 " +
                "0 4 0 7 8 0 0 0 3 " +
                "0 9 3 8 0 0 0 1 2 " +
                "0 0 0 0 4 0 0 0 0 " +
                "5 2 0 0 0 6 7 9 0 " +
                "6 0 0 0 2 1 0 4 0 " +
                "0 0 0 5 3 0 9 0 0 " +
                "0 3 0 0 0 0 0 5 1";

        Sudoku stringSudoku = new Sudoku(hardText);
        assertEquals(sudoku.toString(), stringSudoku.toString());
    }

    @Test
    public void testSpotMethodsHard() {
        sudoku = new Sudoku(Sudoku.hardGrid);

        // on that positions it is 3
        Sudoku.Spot spot = sudoku.new Spot(0, 0);
        assertEquals(3, spot.getVal());

        // On these positions it is 0
        Sudoku.Spot emptySpot = sudoku.new Spot(0, 2);
        assertEquals(0, emptySpot.getVal());

        // check setVal
        emptySpot.setVal(9);
        assertEquals(9, emptySpot.getVal());
        emptySpot.setVal(0);

        // check getAssignableNumbers logic
        Set<Integer> validNumbers = emptySpot.getAssignableNumbers();
        assertNotNull(validNumbers);
        assertFalse( validNumbers.contains(3));
        assertFalse(validNumbers.contains(7));
        assertFalse(validNumbers.contains(8));
        assertFalse( validNumbers.contains(1));
        assertFalse(validNumbers.contains(4));
        assertTrue(validNumbers.size() > 0);
    }

    // Now some edge cases
    @Test
    public void testMultipleSolutions() {
        int[][] multiGrid = Sudoku.stringsToGrid(
                "3 0 0 0 0 0 0 8 0",
                "0 0 1 0 9 3 0 0 0",
                "0 4 0 7 8 0 0 0 3",
                "0 9 3 8 0 0 0 1 2",
                "0 0 0 0 4 0 0 0 0",
                "5 2 0 0 0 6 7 9 0",
                "6 0 0 0 2 1 0 4 0",
                "0 0 0 5 3 0 9 0 0",
                "0 3 0 0 0 0 0 5 1"
        );
        Sudoku sudoku = new Sudoku(multiGrid);
        int solutions = sudoku.solve();
        assertEquals(6, solutions);
    }

    // Empty Grid
    @Test
    public void testEmptyGrid() {
        int[][] emptyGrid = new int[9][9];
        Sudoku sudoku = new Sudoku(emptyGrid);
        int solutions = sudoku.solve();
        assertEquals( 100, solutions);
    }

    // Solved grid
    @Test
    public void testAlreadySolvedGrid() {
        Sudoku firstPass = new Sudoku(Sudoku.easyGrid);
        firstPass.solve();
        String solvedText = firstPass.getSolutionText();
        Sudoku solvedSudoku = new Sudoku(solvedText);
        int solutions = solvedSudoku.solve();

        assertEquals(1, solutions);
    }

    // Unsolvable
    @Test
    public void testUnsolvableGrid() {
        int[][] unsolvable = Sudoku.stringsToGrid(
                "1 6 4 0 0 0 0 0 2",
                "2 0 0 4 0 3 9 1 0",
                "0 0 5 0 8 0 4 0 7",
                "0 9 0 0 0 6 5 0 0",
                "5 0 0 1 0 2 0 0 8",
                "0 0 8 9 0 0 0 3 0",
                "8 0 9 0 4 0 2 0 0",
                "0 7 3 5 0 9 0 0 1",
                "4 0 0 0 0 0 6 7 1"
        );
        Sudoku sudoku = new Sudoku(unsolvable);
        int solutions = sudoku.solve();
        assertEquals(0, solutions);
        assertEquals( "", sudoku.getSolutionText());
    }


    @Test(expected = RuntimeException.class)
    public void testInvalidStringLengthThrowsException() {
        Sudoku.textToGrid("1 2 3 4 5 6 7 8 9 0");
    }

    @Test
    public void testMain() {
        Sudoku.main(new String[]{});
        assertTrue(true);
    }
}
