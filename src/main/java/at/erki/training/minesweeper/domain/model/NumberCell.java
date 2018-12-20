package at.erki.training.minesweeper.domain.model;

public class NumberCell extends Cell {

    private int number;

    public NumberCell(Position position, int value) {
        super(position);
        this.number = value;
    }

    public int expose() {
        this.isExposed = true;
        return number;
    }

    void incrementNumber() {
        number++;
    }

    @Override
    public String toString() {
        if (!isExposed) {
            return "? ";
        }
        if (number == 0) {
            return "  ";
        }
        return number + " ";
    }
}
