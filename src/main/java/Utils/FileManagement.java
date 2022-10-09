package Utils;

import Exceptions.SudokuFormat;
import SudokuAlgorithms.SudokuPPC;
import com.opencsv.CSVReader;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class FileManagement {
    static ArrayList<String> sudokuCvs = new ArrayList<>();

    static String sudokuFig1 = "/home/ibrahim/Documents/M2 GL/IA pour GL (HAI916I)/com.sudoku.algortihm/src/main/java/sudokuFiles/sudokuFig1.cvs";
    static String sudokuFig2 = "/home/ibrahim/Documents/M2 GL/IA pour GL (HAI916I)/com.sudoku.algortihm/src/main/java/sudokuFiles/sudokuFig2.cvs";
    static String sudokuFig3 = "/home/ibrahim/Documents/M2 GL/IA pour GL (HAI916I)/com.sudoku.algortihm/src/main/java/sudokuFiles/sudokuFig3.cvs";

    static String sudokuFig4 = "/home/ibrahim/Documents/M2 GL/IA pour GL (HAI916I)/com.sudoku.algortihm/src/main/java/sudokuFiles/sudokuFig4.cvs";

    public static ArrayList<String> readSudoku(int sudokuNumber){
        if(sudokuNumber == 1){
            readSudokuFile(sudokuFig1);
        }else if(sudokuNumber == 2){
            readSudokuFile(sudokuFig2);
        }else if(sudokuNumber == 3){
            readSudokuFile(sudokuFig3);
        }else if(sudokuNumber == 4){
            readSudokuFile(sudokuFig4);
        }
        return sudokuCvs;
    }

    public static boolean readSudokuFile(String sudokuFilePath){
        boolean sukokuIsCorrect = true;
        int lignesLength=0;
        try {
//            "/home/ibrahim/Documents/M2 GL/IA pour GL (HAI916I)/com.sudoku.algortihm/src/main/java/sudokuFig3.cvs"
            FileReader filereader = new FileReader(sudokuFilePath);
            CSVReader csvReader = new CSVReader(filereader);
            String[] nextRecord;
            int columnLength = 0;
            // we are going to read data line by line
            while ((nextRecord = csvReader.readNext()) != null) {
                lignesLength++;
                columnLength = nextRecord[0].split(" ").length;
                for (String cell : nextRecord) {
                    sudokuCvs.add(cell);
                    if(cell.split(" ").length != columnLength){
                        System.out.println("ERROR IN FIRST CONDITION");
                        sukokuIsCorrect = false;
                    }
                }
            }
            if(lignesLength != columnLength){
                sukokuIsCorrect = false;
            }
            SudokuPPC.instance = lignesLength;
            SudokuPPC.n = lignesLength;
            SudokuPPC.s = (int) Math.sqrt(lignesLength);
            SudokuFormat.checkSudokuFormat(sukokuIsCorrect);

//            if(!sudokuFilePath.equals(sudokuFig4)){
//                Exceptions.SudokuFormat.checkSudokuFormat(sukokuIsCorrect);
//            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return sukokuIsCorrect;
    }
}
