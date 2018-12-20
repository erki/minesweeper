package at.erki.training.minesweeper.interfaces.console;

import at.erki.training.minesweeper.domain.model.Board;

class BoardPrinter {

    void print(Board board) {
        String[][] boardAsString = board.toArray();
        for (String[] row : boardAsString) {
            for (String cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
    }
    
}
