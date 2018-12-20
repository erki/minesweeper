package at.erki.training.minesweeper.interfaces.console;

import at.erki.training.minesweeper.domain.model.Board;
import at.erki.training.minesweeper.domain.model.PlayerMove;
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
        System.out.println("Hint: Type in the cells you want to reveal in the form: X Y (e.g. 1 1)");
        while(state == State.RUNNING) {
            System.out.print("Position to reveal: ");
            String userInput = userIn.nextLine();
            if(userInput.equals("exit")) {
                state = State.ABORTED;
                break;
            }
            try {
                parseInput(userInput).ifPresent(move -> board.executeMove(move));
                if (!board.hasRemainingCells()) {
                    state = State.WON;
                }
            } catch(GameOverException e) {
                state = State.LOST;
            }
            System.out.println();
            printer.print(board);
        }
        userIn.close();
    }

    private Optional<PlayerMove> parseInput(String userInput) {
        if(userInput.startsWith("X ")) {
            return parsePosition(userInput.substring(2).split(" ")).map(position -> new PlayerMove(position,
                true));
        }
        return parsePosition(userInput.split(" ")).map(position -> new PlayerMove(position, false));
    }

    private Optional<Position> parsePosition(String[] coordinates) {
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
