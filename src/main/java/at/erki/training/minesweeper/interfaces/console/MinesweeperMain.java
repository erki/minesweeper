package at.erki.training.minesweeper.interfaces.console;

public class MinesweeperMain {

    public static void main(String[] args) {
        int numberOfCellsPerRow = Integer.parseInt(args[0]);
        int numberOfMines = Integer.parseInt(args[1]);
        ConsoleGame.State result = play(numberOfCellsPerRow, numberOfMines);
        switch(result) {
            case WON: System.out.println("Congrats! You won!"); return;
            case LOST: System.out.println("Sorry, you lost!"); return;
            case ABORTED: System.out.println("OK, see you!"); return;
        }
    }

    private static ConsoleGame.State play(int numberOfCellsPerRow, int numberOfMines) {
        ConsoleGame game = new ConsoleGame(numberOfCellsPerRow, numberOfMines);
        game.start();
        return game.getState();
    }

}
