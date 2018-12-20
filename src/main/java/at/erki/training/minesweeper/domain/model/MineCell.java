package at.erki.training.minesweeper.domain.model;

import javafx.geometry.Pos;

public class MineCell extends Cell {

    public MineCell(Position position) {
        super(position);
    }

    @Override
    public int expose() {
        return 0;
    }
}
