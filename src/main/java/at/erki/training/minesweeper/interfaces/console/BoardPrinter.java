package at.erki.training.minesweeper.interfaces.console;

import at.erki.training.minesweeper.domain.model.Board;

public class BoardPrinter {

    public void print(Board board) {
        String[][] boardAsString = board.asString();
        for (String[] row : boardAsString) {
            for (String cell : row) {
                System.out.print(cell);
            }
            System.out.println();
        }
    }
    
}
