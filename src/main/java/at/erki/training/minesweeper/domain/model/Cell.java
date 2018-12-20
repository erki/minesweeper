package at.erki.training.minesweeper.domain.model;

import at.erki.training.minesweeper.domain.model.exceptions.GameOverException;

import java.util.Objects;

public class Cell {

    private Position position;

    private int number;

    private boolean isMine = false;
    
    private boolean isExposed = false;

    public Cell(Position position) {
        this.position = position;
    }

    public Cell(Position position, boolean isMine) {
        this(position);
        this.isMine = isMine;
    }

    boolean isMine() {
        return isMine;
    }

    boolean isExposed() {
        return isExposed;
    }

    int expose() {
        this.isExposed = true;
        if(isMine) {
            throw new GameOverException("Boom!!");
        }
        return number;
    }

    void incrementNumber() {
        number++;
    }

    Position position() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    @Override
    public String toString() {
        if(!isExposed) {
            return "?";
        }
        if(isMine) {
            return "*";
        }
        return number == 0 ? " " : String.valueOf(number);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cell cell = (Cell) o;
        return number == cell.number &&
                isMine == cell.isMine &&
                isExposed == cell.isExposed &&
                position.equals(cell.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(position, number, isMine, isExposed);
    }
}
