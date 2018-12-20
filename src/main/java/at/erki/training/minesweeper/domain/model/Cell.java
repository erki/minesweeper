package at.erki.training.minesweeper.domain.model;

public abstract class Cell {

    private Position position;
    
    protected boolean isExposed = false;

    public Cell(Position position) {
        this.position = position;
    }

    public boolean isExposed() {
        return isExposed;
    }

    public abstract int expose();

    public Position position() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
}
