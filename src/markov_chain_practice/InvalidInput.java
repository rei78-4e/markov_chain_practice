package markov_chain_practice;

// 入力ファイルが存在しないなどの事前エラー
public class InvalidInput extends RuntimeException {
    private static final long serialVersionUID = 1L;

	public InvalidInput(String message) {
        super(message);
    }

    public InvalidInput(String message, Throwable cause) {
        super(message, cause);
    }
}
