package at.erki.training.minesweeper.domain.model;

import javafx.geometry.Pos;

public abstract class Cell {

    private Position position;

    public Cell(Position position) {
        this.position = position;
    }

    public abstract int expose();

    public Position position() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
}
