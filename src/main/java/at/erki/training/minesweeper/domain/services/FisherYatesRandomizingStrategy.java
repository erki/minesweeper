package at.erki.training.minesweeper.domain.services;

import at.erki.training.minesweeper.domain.model.Cell;
import at.erki.training.minesweeper.domain.model.Position;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class FisherYatesRandomizingStrategy implements RandomizingStrategy {

    private Random random;

    public FisherYatesRandomizingStrategy() {
        this.random = ThreadLocalRandom.current();
    }

    FisherYatesRandomizingStrategy(Random random) {
        this.random = random;
    }

    @Override
    public void randomize(Cell[][] cells) {
        int numberOfCells = cells.length * cells.length;
        for (int i = 0; i < numberOfCells; i++) {
            int j = i + random.nextInt(numberOfCells - i);
            if (i != j) {
                Position position1 = calculate2DPosition(i, cells.length);
                Position position2 = calculate2DPosition(j, cells.length);
                swapCellsAtPositions(cells, position1, position2);
            }
        }
    }

    private Position calculate2DPosition(int flatIndex, int numberOfCellsPerRow) {
        return Position.of(flatIndex / numberOfCellsPerRow,
                (flatIndex - (flatIndex / numberOfCellsPerRow) * numberOfCellsPerRow) % numberOfCellsPerRow);
    }

    private void swapCellsAtPositions(Cell[][] cells, Position position1, Position position2) {
        Cell firstCell = cells[position1.x][position1.y];
        Cell secondCell = cells[position2.x][position2.y];
        cells[position2.x][position2.y] = firstCell;
        cells[position1.x][position1.y] = secondCell;
        firstCell.setPosition(position2);
        secondCell.setPosition(position1);
    }


}
