package at.erki.training.minesweeper.domain.services;

import at.erki.training.minesweeper.domain.model.Cell;

public interface RandomizingStrategy {

    void randomize(Cell[][] cells);

}
