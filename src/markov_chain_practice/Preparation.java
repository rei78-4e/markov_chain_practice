package markov_chain_practice;

import java.nio.file.Files;
import java.nio.file.Paths;


public class Preparation {
    String[] input;

    public void Ready(String path) throws Exception {
        try {
        this.input = this.Parse(this.get_input(path));
        } catch (Exception e) {
            throw e;
        }
    }

    /// this function is possibily return error if failed read input file
    String get_input(String path) throws Exception{
        var p = Paths.get(path);
        
        // check file exist
        if (!Files.isRegularFile(p)) {
            throw new InvalidInput(String.format("invalid input: %s", p));
        }

        try {
            var lines = Files.readString(p);
            return lines;
        } catch (Exception e) {
           throw e; 
        }
    }

    String[] Parse(String input) throws Exception {
        var words = input.split("");
        return words;
    }
}
