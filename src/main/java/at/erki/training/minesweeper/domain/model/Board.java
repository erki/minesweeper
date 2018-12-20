package at.erki.training.minesweeper.domain.model;

import at.erki.training.minesweeper.domain.services.FisherYatesRandomizingStrategy;
import at.erki.training.minesweeper.domain.services.RandomizingStrategy;

import java.util.Arrays;
import java.util.function.Consumer;

public class Board {

    private static final int[][] NEIGHBOR_DELTAS = new int[][]{{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1},
            {1, 0}, {1, 1}};

    private Cell[][] cells;

    private Cell[] mines;

    public Board(int numberOfCellsPerRow, int numberOfMines) {
        initBoard(numberOfCellsPerRow, numberOfMines);
    }

    private void initBoard(int rowCellCount, int mineCount) {
        cells = new Cell[rowCellCount][rowCellCount];
        mines = new Cell[mineCount];
        populateBoard(rowCellCount, mineCount);
        shuffleBoard();
        numberNonMineCells();
    }

    private void populateBoard(int rowCellCount, int numberOfMines) {
        int numberOfMinesPlaced = 0;
        for (int i = 0; i < rowCellCount; i++) {
            for (int j = 0; j < rowCellCount; j++) {
                if (numberOfMinesPlaced != numberOfMines) {
                    mines[numberOfMinesPlaced] = new Cell(Position.of(i, j), true);
                    cells[i][j] = mines[numberOfMinesPlaced];
                    numberOfMinesPlaced++;
                } else {
                    cells[i][j] = new Cell(Position.of(i, j));
                }
            }
        }
    }

    private void shuffleBoard() {
        RandomizingStrategy randomizer = new FisherYatesRandomizingStrategy();
        randomizer.randomize(cells);
    }


    private void numberNonMineCells() {
        for (Cell mine : mines) {
            forEachNeighbor(mine, cell -> {
                if (!cell.isMine()) {
                    cell.incrementNumber();
                }
            });
        }
    }

    /**
     * executes a players move. A move can either be marking a certain cell as a possible mine or revealing a cell
     * at a given position.
     *
     * @param move A move to execute identified by a {@link Position} of a cell to be revealed or marked
     */
    public void executeMove(PlayerMove move) {
        if(move.isMarkingMove()) {
            markPosition(move.position());
        }
        else {
            reveal(move.position());
        }
    }

    private void markPosition(Position position) {
        cells[position.x][position.y].toggleMark();
    }

    private void reveal(Position position) {
        Cell cellToReveal = cells[position.x][position.y];
        if (!cellToReveal.isExposed()) {
            int number = cellToReveal.expose();
            if (number == 0) {
                //we've found a blank cell, let's reveal it's neighbors as well
                forEachNeighbor(cellToReveal, neighbor -> reveal(neighbor.position()));
            }
        }
    }

    /**
     * Returns <code>true</code> iff the Board has still cells that are not mines and need to be revealed.
     *
     * @return <code>true</code> if there are more cells to be revealed
     */
    public boolean hasRemainingCells() {
        return Arrays.stream(cells).flatMap(Arrays::stream).filter(cell -> !cell.isExposed()).count() != mines.length;
    }

    /**
     * Transforms the Board to a two-dimensional array using a String representation for each cell.
     *
     * @return a two-dimensional array containing a string representation for each cell of the board
     */
    public String[][] toArray() {
        return Arrays.stream(cells).map(row -> Arrays.stream(row).map(Cell::toString).toArray(String[]::new))
                .toArray(String[][]::new);
    }

    private void forEachNeighbor(Cell cell, Consumer<Cell> executionBlock) {
        for (int[] delta : NEIGHBOR_DELTAS) {
            cell.position().adjustBy(delta[0], delta[1]).ifPresent(position -> {
                if (position.inBounds(cells.length)) {
                    executionBlock.accept(cells[position.x][position.y]);
                }
            });
        }
    }
}
