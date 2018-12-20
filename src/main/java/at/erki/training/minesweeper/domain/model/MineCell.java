package at.erki.training.minesweeper.domain.model;

public class MineCell extends Cell {

    public MineCell(Position position) {
        super(position);
    }

    @Override
    public int expose() {
        this.isExposed = true;
        throw new GameOverException("Boom!!");
    }

    @Override
    public String toString() {
        if(!isExposed) {
            return "? ";
        }
        return "* ";
    }
}
