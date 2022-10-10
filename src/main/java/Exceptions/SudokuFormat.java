package exceptions;

public class SudokuFormat {
    public static void checkSudokuFormat(boolean isCorrect) throws SudokuFormatIsNotCorrectException {
        if(!isCorrect){
            throw new SudokuFormatIsNotCorrectException("Le format du Sudoku que vous avez saisit n'est pas correct");
        }
    }
}
