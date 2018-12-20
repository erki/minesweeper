package at.erki.training.minesweeper.domain.model;

import java.util.Random;
import java.util.function.Consumer;

public class Board {

    private static final int[][] NEIGHBOR_DELTAS = new int[][]{
            {-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}
    };

    private Cell[][] cells;

    private MineCell[] mines;

    public Board(int rowCellCount, int mineCount) {
        initBoard(rowCellCount, mineCount);
    }

    public void reveal(Position position) {
        Cell cellToReveal = cells[position.x][position.y];
        int number = cellToReveal.expose();
        if (number == 0) {
            forEachNeighbor(cellToReveal, neighbor -> reveal(neighbor.position()));
        }
    }

    private void initBoard(int rowCellCount, int mineCount) {
        cells = new Cell[rowCellCount][rowCellCount];
        mines = new MineCell[mineCount];
        populateBoard(rowCellCount, mineCount);
        shuffleBoard(rowCellCount * rowCellCount);
        numberNonMineCells();
    }

    private void populateBoard(int rowCellCount, int numberOfMines) {
        int numberOfMinesPlaced = 0;
        for (int i = 0; i < rowCellCount; i++) {
            for (int j = 0; j < rowCellCount; j++) {
                if (numberOfMinesPlaced != numberOfMines) {
                    mines[numberOfMinesPlaced] = new MineCell(Position.of(i, j));
                    cells[i][j] = mines[numberOfMinesPlaced];
                } else {
                    cells[i][j] = new NumberCell(Position.of(i, j), 0);
                }
            }
        }
    }

    private void shuffleBoard(int numberOfCells) {
        Random random = new Random();
        for (int i = 0; i < numberOfCells; i++) {
            int j = i + random.nextInt(numberOfCells - i);
            if (i != j) {
                Position position1 = calculatePosition(numberOfCells, i);
                Position position2 = calculatePosition(numberOfCells, j);
                swapCells(position1, position2);
            }
        }
    }

    private Position calculatePosition(int numberOfCells, int i) {
        return Position.of(i / numberOfCells,
                (i - (i / numberOfCells) * numberOfCells) % numberOfCells);
    }

    private void swapCells(Position position1, Position position2) {
        Cell firstCell = cells[position1.x][position1.y];
        Cell secondCell = cells[position2.x][position2.y];
        cells[position2.x][position2.y] = firstCell;
        cells[position1.x][position1.y] = secondCell;
        firstCell.setPosition(position2);
        secondCell.setPosition(position1);
    }

    private void numberNonMineCells() {
        for (MineCell mine : mines) {
            forEachNeighbor(mine, cell -> {
                if (cell instanceof NumberCell) {
                    ((NumberCell) cell).incrementNumber();
                }
            });
        }
    }

    private void forEachNeighbor(Cell cell, Consumer<Cell> executionBlock) {
        for (int[] delta : NEIGHBOR_DELTAS) {
            cell.position().adjustBy(delta[0], delta[1]).ifPresent(position -> executionBlock.accept(cells[position.x][position.y]));
        }
    }

}
