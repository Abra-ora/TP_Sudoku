import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import sudoku.algorithms.SudokuPPC;

import java.util.logging.Logger;


class SudokuPPCTest {
    Logger logger = Logger.getLogger(SudokuPPCTest.class.getName());
    @BeforeEach
    void setUp() {
        SudokuPPC.n = 4;
        SudokuPPC.s = (int) Math.sqrt(SudokuPPC.n);
    }
    @Test
    @DisplayName("Tester le temps execution de la methode findSolution PPC")
    public void testFindSolution() {
        long start = System.currentTimeMillis();
        new SudokuPPC().solve();
        long end = System.currentTimeMillis();
        logger.info("Temps execution de la methode solve PPC: " + (end - start) + " ms");
    }

}