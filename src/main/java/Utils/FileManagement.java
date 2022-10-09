package Utils;

import Exceptions.SudokuFormat;
import SudokuAlgorithms.SudokuPPC;
import com.opencsv.CSVReader;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class FileManagement {
    static ArrayList<String> sudokuCvs = new ArrayList<>();

    static String sudokuFig1 = "src/main/java/sudokuFiles/sudokuFig1.cvs";
    static String sudokuFig2 = "src/main/java/sudokuFiles/sudokuFig2.cvs";
    static String sudokuFig3 = "src/main/java/sudokuFiles/sudokuFig3.cvs";
    static String sudokuFig4 = "src/main/java/sudokuFiles/sudokuFig4.cvs";

    public static ArrayList<String> readSudoku(int sudokuNumber) throws IOException {
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

    public static boolean readSudokuFile(String sudokuFilePath) throws IOException {
        boolean sukokuIsCorrect = true;
        int lignesLength = 0;
        FileReader filereader = null;
        CSVReader csvReader = null;
        sudokuCvs.clear();
        try {
            System.out.println("path " + sudokuFilePath);
            filereader = new FileReader(sudokuFilePath);
            csvReader = new CSVReader(filereader);
            String[] nextRecord;
            int columnLength = 0;
            // we are going to read data line by line
            while ((nextRecord = csvReader.readNext()) != null) {
                lignesLength++;
                columnLength = nextRecord[0].split(" ").length;
                for (String cell : nextRecord) {
                    sudokuCvs.add(cell);
                    if (cell.split(" ").length != columnLength) {
                        System.out.println("ERROR IN FIRST CONDITION");
                        sukokuIsCorrect = false;
                    }
                }
            }
            if (lignesLength != columnLength) {
                sukokuIsCorrect = false;
            }
            SudokuPPC.instance = lignesLength;
            SudokuPPC.n = lignesLength;
            SudokuPPC.s = (int) Math.sqrt(lignesLength);
            SudokuFormat.checkSudokuFormat(sukokuIsCorrect);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (filereader != null) {
                filereader.close();
            }
            if (csvReader != null) {
                csvReader.close();
            }
        }
        return sukokuIsCorrect;
    }
}
