package at.erki.training.minesweeper.domain.model;

public class PlayerMove {

    private final Position position;

    private final boolean markingMove;

    public PlayerMove(Position position, boolean markingMove) {
        this.position = position;
        this.markingMove = markingMove;
    }

    public Position position() {
        return position;
    }

    public boolean isMarkingMove() {
        return markingMove;
    }
}
