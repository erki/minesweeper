package at.erki.training.minesweeper.interfaces.console;

import at.erki.training.minesweeper.domain.model.Board;
import at.erki.training.minesweeper.domain.model.exceptions.GameOverException;
import at.erki.training.minesweeper.domain.model.Position;

import java.util.Optional;
import java.util.Scanner;

public class ConsoleGame {
    
    private State state;
    
    private Board board;
    
    private BoardPrinter printer;
    
    public ConsoleGame(int numberOfCellsPerRow, int numberOfMines) {
        this.board = new Board(numberOfCellsPerRow, numberOfMines);
        this.printer = new BoardPrinter();
    }
    
    public void start() {
        state = State.RUNNING;
        Scanner userIn = new Scanner(System.in);
        printer.print(board);
        while(state == State.RUNNING) {
            String userInput = userIn.nextLine();
            if(userInput.equals("exit")) {
                state = State.ABORTED;
                break;
            }
            try {
                parseInput(userInput).ifPresent(position -> board.reveal(position));
                if (!board.hasRemainingCells()) {
                    state = State.WON;
                }
            } catch(GameOverException e) {
                state = State.LOST;
            }
            printer.print(board);
        }
        userIn.close();
    }

    private Optional<Position> parseInput(String userInput) {
        String[] coordinates = userInput.split(" ");
        try {
            return Optional.of(Position.of(Integer.parseInt(coordinates[0]), Integer.parseInt(coordinates[1])));
        } catch(Exception e) {
            System.out.println("Invalid Input. Please provide position in format 'x y'!");
            return Optional.empty();
        }
    }

    public State getState() {
        return state;
    }

    public enum State {
        WON, LOST, RUNNING, ABORTED
    }
    
}
