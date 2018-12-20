package at.erki.training.minesweeper.domain.services;

import at.erki.training.minesweeper.domain.model.Cell;
import at.erki.training.minesweeper.domain.model.Position;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Random;

import static org.junit.Assert.assertArrayEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class FisherYatesRandomizingStrategyTest {

    @Test
    public void aBoardWithOneCellIsRandomized() throws Exception {
        Cell[][] cells = new Cell[][]{{new Cell(Position.of(0, 0))}};
        new FisherYatesRandomizingStrategy().randomize(cells);
        assertArrayEquals(new Cell[][]{{new Cell(Position.of(0, 0))}}, cells);
    }

    @Test
    public void anEmptyBoardIsRandomized() throws Exception {
        Cell[][] cells = new Cell[0][0];
        new FisherYatesRandomizingStrategy().randomize(cells);
        assertArrayEquals(new Cell[0][0], cells);
    }

    @Test
    public void aBoardWithThreeCellsPerRowIsRandomized() throws Exception {
        Random random = mock(Random.class);
        when(random.nextInt(anyInt()))
                .thenReturn(7).thenReturn(3)
                .thenReturn(2).thenReturn(4)
                .thenReturn(0).thenReturn(0)
                .thenReturn(2).thenReturn(1)
                .thenReturn(0);

        Cell[][] cells = createBoard();
        new FisherYatesRandomizingStrategy(random).randomize(cells);
        assertArrayEquals(randomizedBoard(), cells);
    }

    private Cell[][] createBoard() {
        return new Cell[][]{
                {mine(0,0), mine(0,1), mine(0,2)},
                {mine(1,0), mine(1,1), emptyCell(1,2)},
                {emptyCell(2,0), emptyCell(2,1), emptyCell(2,2)}
        };
    }

    private Cell[][] randomizedBoard() {
        return new Cell[][]{
                {emptyCell(0,0), mine(0,1), mine(0,2)},
                {mine(1,0), mine(1,1), emptyCell(1,2)},
                {emptyCell(2,0), emptyCell(2,1), mine(2,2)}
        };
    }

    private Cell mine(int x, int y) {
        return new Cell(Position.of(x,y), true);
    }

    private Cell emptyCell(int x, int y) {
            return new Cell(Position.of(x,y));
    }

}
