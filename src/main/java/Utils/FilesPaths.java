package utils;

import java.io.InputStream;
import java.util.Objects;

public class FilesPaths {
//    private final String sudokuFig1 = "src/main/java/sudoku/files/sudokuFig1.cvs";
//    private final String sudokuFig2 = "src/main/java/sudoku/files/sudokuFig2.cvs";
//    private final String sudokuFig3 = "src/main/java/sudoku/files/sudokuFig3.cvs";
//    private final String sudokuFig4 = "src/main/java/sudoku/files/sudokuFig4.cvs";

    private final InputStream sudokuFig1 = this.getClass().getResourceAsStream("/sudokuFig1.cvs");
    private final InputStream  sudokuFig2 = this.getClass().getResourceAsStream("/sudokuFig2.cvs");
    private final InputStream  sudokuFig3 = this.getClass().getResourceAsStream("/sudokuFig3.cvs");
    private final InputStream  sudokuFig4 = this.getClass().getResourceAsStream("/sudokuFig4.cvs");


    public InputStream getSudokuFig1() {
        System.out.println("sudokuFig1 " + sudokuFig1);
        return sudokuFig1;
    }

    public InputStream getSudokuFig2() {
        System.out.println("sudokuFig2 " + sudokuFig2);
        return sudokuFig2;
    }

    public InputStream getSudokuFig3() {
        System.out.println("sudokuFig3 " + sudokuFig3);
        return sudokuFig3;
    }

    public InputStream getSudokuFig4() {
        System.out.println("sudokuFig4 " + sudokuFig4);
        return sudokuFig4;
    }
}
