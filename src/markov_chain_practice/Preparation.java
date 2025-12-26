package markov_chain_practice;

import java.nio.file.Files;
import java.nio.file.Paths;


public class Preparation {
    String[] input;

    public Preparation(String[] p) throws Exception {
        try {
        this.input = this.Parse(this.get_some_input(p));
        System.out.printf("loaded %d words\n\n", this.input.length);
        } catch (Exception e) {
            throw e;
        }
    }

    public String[] get_input() {
        return this.input;
    }

    String get_some_input(String[] pathes) throws Exception {
        String res = new String();
        for (var p : pathes) {
            res += get_input(p);
        }

        return res;
    }

    /// this function is possibly return error if failed read input file
    String get_input(String path) throws Exception{
        var p = Paths.get(path);
        
        // check file exist
        if (!Files.isRegularFile(p)) {
            throw new InvalidInput(String.format("invalid input: %s", p));
        }

        try {
            return Files.readString(p).replace(".", "");
        } catch (Exception e) {
           throw e; 
        }
    }

    String[] Parse(String input) throws Exception {
        return input.split(" ");
    }
}
