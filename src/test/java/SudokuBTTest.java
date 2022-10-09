import SudokuAlgorithms.SudokuBT;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.logging.Logger;

public class SudokuBTTest {
    Logger logger = Logger.getLogger(SudokuBTTest.class.getName());
    SudokuBT sudokuBT;

    @BeforeEach
    void setUp() {
        sudokuBT = new SudokuBT(4);
    }
    @Test
    @DisplayName("Tester le temps execution de la methode findSolution")
    public void testFindSolution() {
        long start = System.currentTimeMillis();
        sudokuBT.findSolution(0, 0);
        long end = System.currentTimeMillis();
        logger.info("Temps execution de la methode findSolution BT: " + (end - start) + " ms");
    }
}