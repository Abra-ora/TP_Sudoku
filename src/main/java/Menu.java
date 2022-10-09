import SudokuAlgorithms.SudokuBT;
import SudokuAlgorithms.SudokuPPC;
import Utils.FileManagement;

import java.io.IOException;
import java.util.Scanner;

public class Menu {

    public static void main(String[] args) throws IOException {
        switchCases();
    }

    public static String  menu(){
        return ""+
                "──────────────────────────────────────────────────────────\n"+
                "| 1. Backtrack avec une solution unique (4 x 4)\n"+
                "| 2. Backtrack avec toutes les solutions unique (4 x 4)\n"+
                "| 3. Sudoku avec sans alphabets(9 x 9)\n"+
                "| 4. Sudoku avec les alphabets(16 x 16)\n"+
                "| 5. Greater Than Sudoku(9 x 9)\n"+
                "| 0. Exit.\n"+
                "──────────────────────────────────────────────────────────\n";
    }

    public static void switchCases() throws IOException {
        int choice = 0;
        do{
            System.out.println(menu());
            System.out.print("Veuillez saisir votre choix: ");
            Scanner sc = new Scanner(System.in);
            choice = sc.nextInt();
            SudokuBT sudokuBT = new SudokuBT(4);

            switch (choice){
                case 1:
                    System.out.println("Veuillez patientez svp ...");
                    sudokuBT.solve(false);
                    break;
                case 2:
                    System.out.println("Veuillez patientez svp ...");
                    sudokuBT.solve(true);
                    break;
                case 3:
                    SudokuPPC sudokuPPC1 = new SudokuPPC();
                    sudokuPPC1.solve(FileManagement.readSudoku(2), false, false);
                    break;
                case 4:
                    SudokuPPC sudokuPPC2 = new SudokuPPC();
                    sudokuPPC2.solve(FileManagement.readSudoku(3), false, false);
                    break;
                case 5:
                    SudokuPPC sudokuPPC3 = new SudokuPPC();
                    sudokuPPC3.solve(FileManagement.readSudoku(4), false, true);
                    break;
                case 0:
                    System.exit(0);
                default:
                    System.out.println("Veuillez saisir un choix entre 1 et 5.");
                    break;
            }
        }while(choice !=0);
    }
}
