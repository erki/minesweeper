package at.erki.training.minesweeper.domain.model.exceptions;

public class GameOverException extends RuntimeException {
    
    public GameOverException(String message) {
        super(message);
    }

}
