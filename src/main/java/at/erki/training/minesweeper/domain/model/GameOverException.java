package at.erki.training.minesweeper.domain.model;

public class GameOverException extends RuntimeException {
    
    public GameOverException(String message) {
        super(message);
    }

}
