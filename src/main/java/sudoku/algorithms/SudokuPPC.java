package sudoku.algorithms;

import utils.SudokuAlphabet;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.chocosolver.solver.Model;
import org.chocosolver.solver.variables.IntVar;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


import static org.chocosolver.solver.search.strategy.Search.minDomLBSearch;
import static org.chocosolver.util.tools.ArrayUtils.append;

public class SudokuPPC {
    public static int n;
    public static int s;
    public static int instance;
    private static long timeout = 3600000; // one hour
    IntVar[][] rows, cols, shapes;
    Model model;
    SudokuAlphabet sudokuAlphabet = new SudokuAlphabet();


    public void solve(List<String> sudokuCvs, boolean withAllSolutions, boolean isGreaterThanSudoku) {
        buildModel(sudokuCvs, isGreaterThanSudoku);
        if (withAllSolutions) {
            while (model.getSolver().solve()) {
                printGrid();
                model.getSolver().printStatistics();
            }
        } else {
            model.getSolver().solve();
            printGrid();
            model.getSolver().printStatistics();
        }
    }

    public void solve() {
        defaultBuildModel();
        while (model.getSolver().solve()) {
            printGrid();
            model.getSolver().printStatistics();
        }
    }

    public void printGrid() {

        String a;
        a = "┌───";
        String b;
        b = "├───";
        String c;
        c = "─┬────┐";
        String d;
        d = "─┼────┤";
        String e;
        e = "─┬───";
        String f;
        f = "─┼───";
        String g;
        g = "└────┴─";
        String h;
        h = "───┘";
        String k;
        k = "───┴─";
        String esp = " ";

        for (int i = 0; i < n; i++) {

            for (int line = 0; line < n; line++) {
                if (line == 0) {
                    System.out.print(i == 0 ? a : b);
                } else if (line == n - 1) {
                    System.out.print(i == 0 ? c : d);
                } else {
                    System.out.print(i == 0 ? e : f);
                }
            }
            System.out.println("");
            System.out.print("│ ");
            for (int j = 0; j < n; j++) {
                if (rows[i][j].getValue() > 9)
                    System.out.print(esp + sudokuAlphabet.getKey(rows[i][j].getValue()) + " │ ");
                else
                    System.out.print(esp + rows[i][j].getValue() + " │ ");
            }

            if (i == n - 1) {
                System.out.println("");
                for (int line = 0; line < n; line++) {
                    System.out.print(line == 0 ? g : (line == n - 1 ? h : k));
                }
            }
            System.out.println("");

        }
    }

    public void addConstraintsForGreaterThanSudoku(List<String> sudokuCvs) {
        for (int i = 0; i < n; i++) {
            String[] pairOperations = sudokuCvs.get(i).split(" ");
            for (int j = 0; j < n; j++) {
                String[] pairOperation = pairOperations[j].split(";");
                for (int k = 0; k < pairOperation.length; k++) {
                    if (!Objects.equals(pairOperation[k], "|")) {
                        if (k == 0) {
                            model.arithm(rows[i][j], pairOperation[k], rows[i + 1][j]).post();
                        } else if (k == 1) {
                            model.arithm(rows[i][j], pairOperation[k], rows[i][j + 1]).post();
                        }
                    }
                }
            }
        }
    }

    public void buildModel(List<String> sudokuCvs, boolean isGreaterThanSudoku) {
        defaultBuildModel();
        if (isGreaterThanSudoku) {
            addConstraintsForGreaterThanSudoku(sudokuCvs);
        } else {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    String valuesCvs = sudokuCvs.get(i).split(" ")[j];
                    if (!Objects.equals(valuesCvs, "0")) {
                        if (sudokuAlphabet.getAlphabet().containsKey(valuesCvs)) {
                            model.arithm(rows[i][j], "=", sudokuAlphabet.getValue(valuesCvs)).post();
                        } else {
                            model.arithm(rows[i][j], "=", Integer.parseInt(valuesCvs)).post();
                        }
                    }
                }
            }
        }
    }

    private void defaultBuildModel() {
        model = new Model();
        rows = new IntVar[n][n];
        cols = new IntVar[n][n];
        shapes = new IntVar[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                rows[i][j] = model.intVar("c_" + i + "_" + j, 1, n, false);
                cols[j][i] = rows[i][j];
            }
        }

        for (int i = 0; i < s; i++) {
            for (int j = 0; j < s; j++) {
                for (int k = 0; k < s; k++) {
                    for (int l = 0; l < s; l++) {
                        shapes[j + k * s][i + (l * s)] = rows[l + k * s][i + j * s];
                    }
                }
            }
        }

        for (int i = 0; i < n; i++) {
            model.allDifferent(rows[i]).post();
            model.allDifferent(cols[i]).post();
            model.allDifferent(shapes[i]).post();
        }
    }

    // Check all parameters values
    public static void checkOption(CommandLine line, String option) {

        switch (option) {
            case "inst":
                instance = Integer.parseInt(line.getOptionValue(option));
                break;
            case "timeout":
                timeout = Long.parseLong(line.getOptionValue(option));
                break;
            default: {
                System.err.println("Bad parameter: " + option);
                System.exit(2);
            }
        }

    }

    // Add options here
    public static Options configParameters() {

        final Option helpFileOption = Option.builder("h").longOpt("help").desc("Display help message").build();

        final Option instOption = Option.builder("i").longOpt("instance").hasArg(true).argName("sudoku instance")
                .desc("sudoku instance size").required(false).build();

        final Option limitOption = Option.builder("t").longOpt("timeout").hasArg(true).argName("timeout in ms")
                .desc("Set the timeout limit to the specified time").required(false).build();

        // Create the options list
        final Options options = new Options();
        options.addOption(instOption);
        options.addOption(limitOption);
        options.addOption(helpFileOption);

        return options;
    }

    public void configureSearch() {
        model.getSolver().setSearch(minDomLBSearch(append(rows)));
    }

}