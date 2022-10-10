package utils;

import exceptions.SudokuFormat;
import sudoku.algorithms.SudokuPPC;
import com.opencsv.CSVReader;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileManagement {
    static ArrayList<String> sudokuCvs = new ArrayList<>();


    public static List<String> readSudoku(int sudokuNumber) throws IOException {
        FilesPaths filesPaths = new FilesPaths();
        if(sudokuNumber == 1){
            readSudokuFile(filesPaths.getSudokuFig1());
        }else if(sudokuNumber == 2){
            readSudokuFile(filesPaths.getSudokuFig2());
        }else if(sudokuNumber == 3){
            System.out.println("sudokuFig3 " + filesPaths.getSudokuFig3());
            readSudokuFile(filesPaths.getSudokuFig3());
        }else if(sudokuNumber == 4){
            readSudokuFile(filesPaths.getSudokuFig4());
        }
        return sudokuCvs;
    }

    public static void readSudokuFile(InputStream sudokuFilePath) throws IOException {
        boolean sukokuIsCorrect = true;
        int lignesLength = 0;
        CSVReader csvReader = null;
        InputStreamReader filereader = null;
        sudokuCvs.clear();
        try {
            System.out.println("path " + sudokuFilePath);
            filereader = new InputStreamReader(sudokuFilePath);
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
            if (csvReader != null) {
                csvReader.close();
            }
            if (filereader != null) {
                filereader.close();
            }
        }
    }
}
