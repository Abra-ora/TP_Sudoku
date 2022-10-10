import sudoku.algorithms.SudokuBT;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.logging.Logger;

public class SudokuBTTest {
    Logger logger = Logger.getLogger(SudokuBTTest.class.getName());

    @Test
    @DisplayName("Tester le temps execution de la methode findSolution")
    public void testFindSolution() {
        SudokuBT sudokuBT = new SudokuBT(4);;
        long start = System.currentTimeMillis();
        // Pour testez jute pour une seul solution
        sudokuBT.findSolution(0, 0);

        // Pour testez pour toutes les solutions
        // sudokuBT.findAllSolutions(0, 0);

        long end = System.currentTimeMillis();
        logger.info("Temps execution de la methode findSolution BT: " + (end - start) + " ms");
    }
}