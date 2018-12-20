package at.erki.training.minesweeper.domain.model;

import at.erki.training.minesweeper.domain.model.exceptions.GameOverException;
import javafx.geometry.Pos;

public class Cell {

    private Position position;

    private int number;

    private boolean isMine = false;
    
    private boolean isExposed = false;

    Cell(Position position) {
        this.position = position;
    }

    Cell(Position position, boolean isMine) {
        this(position);
        this.isMine = isMine;
    }

    boolean isMine() {
        return isMine;
    }

    void setMine(boolean mine) {
        isMine = mine;
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

    void setPosition(Position position) {
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
}
