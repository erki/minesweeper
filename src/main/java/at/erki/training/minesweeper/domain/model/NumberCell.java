package at.erki.training.minesweeper.domain.model;

public class NumberCell extends Cell {

    private int number;

    public NumberCell(Position position, int value) {
        super(position);
        this.number = value;
    }

    public int expose() {
        return number;
    }

    void incrementNumber() {
        number++;
    }

}
