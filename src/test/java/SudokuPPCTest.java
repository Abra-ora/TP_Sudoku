//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//
//import java.util.logging.Logger;
//
//
//class SudokuPPCTest {
//    Logger logger = Logger.getLogger(SudokuPPCTest.class.getName());
//    @BeforeEach
//    void setUp() {
//        SudokuAlgorithms.SudokuPPC.n = 4;
//        SudokuAlgorithms.SudokuPPC.s = (int) Math.sqrt(SudokuAlgorithms.SudokuPPC.n);
//    }
//    @Test
//    @DisplayName("Tester le temps execution de la methode findSolution PPC")
//    public void testFindSolution() {
//        long start = System.currentTimeMillis();
//        new SudokuAlgorithms.SudokuPPC().solve();
//        long end = System.currentTimeMillis();
//        logger.info("Temps execution de la methode solve PPC: " + (end - start) + " ms");
//    }
//
//}