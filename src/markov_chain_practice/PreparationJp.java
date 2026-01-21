package markov_chain_practice;

import com.atilika.kuromoji.ipadic.Token;
import com.atilika.kuromoji.ipadic.Tokenizer;
import java.util.List;
import java.util.stream.Collectors;
import java.nio.file.Path;

public class PreparationJp extends Preparation{

	public PreparationJp (Path[] p) throws Exception {

		super(p);
	}
	
	public String[] Parse() {
        return this.input.split("\\s+");
    }

	
	
	
	public class SimpleSplitter {
	    public static void main(String[] args) {
	        Tokenizer tokenizer = new Tokenizer();
	        String text = "Javaで日本語を分割するのは簡単です。";

	        // 分割してList<String>に変換
	        List<String> words = tokenizer.tokenize(text).stream()
	                                      .map(Token::getSurface)
	                                      .collect(Collectors.toList());

	        // 結果: [Java, で, 日本語, を, 分割, する, の, は, 簡単, です, 。]
	        System.out.println(words);
	    }
	}

}
