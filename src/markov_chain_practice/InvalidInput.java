package markov_chain_practice;

public class InvalidInput extends RuntimeException {
    public InvalidInput(String message) {
        super(message);
    }

    public InvalidInput(String message, Throwable cause) {
        super(message, cause);
    }
}
