import SudokuAlgorithms.SudokuBT;
import SudokuAlgorithms.SudokuPPC;
import Utils.FileManagement;
import org.apache.commons.cli.*;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws ParseException {
        final Options options = SudokuPPC.configParameters();
        final CommandLineParser parser = new DefaultParser();
        final CommandLine line = parser.parse(options, args);

        boolean helpMode = line.hasOption("help"); // args.length == 0
        if (helpMode) {
            final HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp("sudoku", options, true);
            System.exit(0);
        }
        for (Option opt : line.getOptions()) {
            SudokuPPC.checkOption(line, opt.getLongOpt());
        }

        int sudokuType = chooseSudokuType();
        if(sudokuType == 1){
            ComparisonPart(FileManagement.readSudoku(sudokuType));
        }else if(sudokuType == 0){
           System.exit(0);
        }else
            PPCPart(FileManagement.readSudoku(sudokuType), sudokuType == 4);
    }


    public static Integer chooseSudokuType(){
        int choice = 0;
        System.out.println("────────────────────────────────────────────");
        System.out.println("| 1. Sudoku (4 x 4) (Figure 1)");
        System.out.println("| 2. Sudoku sans alphabet (Figure 2)");
        System.out.println("| 3. Sudoku avec alphabet (Figure 3)");
        System.out.println("| 4. Greater Than Sudoku (Figure 4)");
        System.out.println("| 0. Exit.");
//        System.out.println("| 2. Greater Than Sudoku");
        System.out.println("────────────────────────────────────────────\n");
        System.out.print("Le type du Sudoku: ");
        Scanner sc = new Scanner(System.in);
        choice = sc.nextInt();
        return choice;
    }

    public static Integer menuChoiceComparisonPart(){
        int choice = 0;
        System.out.println("──────────────────────────────────────────────────────────\n"+
                "| 1. Backtrack runtime pour une solution unique\n"+
                "| 2. Backtrack runtime pour plusieurs solutions\n"+
                "| 3. PPC runtime pour une solution unique\n"+
                "| 4. PPC runtime pour plusieurs solutions\n"+
                "| 5. Retour au menu précédent\n"+
                "| 0. Exit.\n"+
                "──────────────────────────────────────────────────────────\n");
        System.out.print("Saisir votre choix: ");
        Scanner sc = new Scanner(System.in);
        choice = sc.nextInt();
        return choice;
    }

    public static void ComparisonPart(ArrayList<String> sudokuCvs){
        SudokuPPC sudokuPPC = new SudokuPPC();
        SudokuBT sudokuBT = new SudokuBT(4);
        do {
            switch (menuChoiceComparisonPart()) {
                case 1:
                    sudokuBT.solve(false);
                    break;
                case 2:
                    sudokuBT.solve(true);
                    break;
                case 3:
                    sudokuPPC.solve(sudokuCvs, false, false);
                    break;
                case 4:
                    sudokuPPC.solve(sudokuCvs, true, false);
                    break;
                case 5:
                    chooseSudokuType();
                    break;
            }
        }while(menuChoiceComparisonPart()!=0);
    }

    public static Integer menuChoicePPCPart(){
        int choice = 0;
        System.out.println("────────────────────────────────────────────────────────────────────────────────\n"+
                "| 1. Résoudre le Sudoku par PPC\n"+
                "| 2. Résoudre le Sudoku par PPC en retournant toutes les solutions\n"+
                "| 3. Retour au menu précédent\n"+
                "| 0. Exit.\n"+
                "────────────────────────────────────────────────────────────────────────────────\n");
        System.out.print("Saisir votre choix: ");
        Scanner sc = new Scanner(System.in);
        choice = sc.nextInt();
        return choice;

    }

    public static void PPCPart(ArrayList<String> sudokuCvs, boolean isGreaterThanSudoku){
        SudokuPPC sudokuPPC = new SudokuPPC();
        do{
            switch (menuChoicePPCPart()){
                case 1:
                    sudokuPPC.solve(sudokuCvs, false, isGreaterThanSudoku);
                    break;
                case 2:
                    sudokuPPC.solve(sudokuCvs, true, isGreaterThanSudoku);
                    break;
                case 3:
                    chooseSudokuType();
                    break;
            }
        }while (menuChoicePPCPart()!=0);
    }
}