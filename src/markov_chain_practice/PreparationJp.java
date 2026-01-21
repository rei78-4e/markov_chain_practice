package markov_chain_practice;

import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

import com.atilika.kuromoji.ipadic.Token;
import com.atilika.kuromoji.ipadic.Tokenizer;

public class PreparationJp extends Preparation {

    public PreparationJp(Path[] p) throws Exception {

        super(p);
    }

    public String[] Parse() {
        Tokenizer tokenizer = new Tokenizer();
        String text = this.input;

        // 分割してList<String>に変換
        List<String> words = tokenizer.tokenize(text).stream()
<<<<<<< HEAD
                                      .map(Token::getSurface)
                                      .collect(Collectors.toList());
        System.out.println(words);
        
		return words.toArray(String[]::new);
=======
                .map(Token::getSurface)
                .collect(Collectors.toList());

        return words.toArray(String[]::new);
>>>>>>> 7a645c00b155db9654aa8d5fd79c8d677ab1341a
    }

}
