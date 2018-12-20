package at.erki.training.minesweeper.interfaces.console;

import at.erki.training.minesweeper.domain.model.Board;

public class BoardPrinter {

    public void print(Board board) {
        String[][] boardAsString = board.asString();
        for(int i = 0; i < boardAsString.length; i++) {
            for(int j = 0; j < boardAsString[i].length; j++) {
                System.out.print(boardAsString[i][j]);
            }
            System.out.println();
        }
    }
    
}
