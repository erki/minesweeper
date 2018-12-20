package at.erki.training.minesweeper.domain.model;

import java.util.Optional;

public final class Position {

    public final int x;
    public final int y;

    private Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public static Position of(int x, int y) {
        if(x < 0 || y < 0) {
            throw new IllegalArgumentException("Negative numbers are not allowed!");
        }
        return new Position(x, y);
    }

    Optional<Position> adjustBy(int deltaX, int deltaY) {
        if(x + deltaX < 0 || y + deltaY < 0) {
            return Optional.empty();
        }
        return Optional.of(new Position(x + deltaX, y + deltaY));
    }
    
    boolean inBounds(int upperBound) {
        return 0 <= x && x < upperBound && 0 <= y && y < upperBound;
    }

}
