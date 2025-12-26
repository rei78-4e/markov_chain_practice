package markov_chain_practice;

import java.util.HashMap;

public class Leaning {
    HashMap<String, HashMap<String, Integer>> WordsMap;

    public Leaning(String[] input) {
        this.WordsMap = new HashMap<>();
        this.collect_analyzer(input);
    }

    public HashMap<String, HashMap<String, Integer>> get_map() {
        return this.WordsMap;
    }


    void collect_analyzer(String[] words) {
        for (int i = 0; i < words.length - 1; i++) {
            this.WordsMap.computeIfAbsent(words[i], k -> new HashMap<>()).merge(words[i+1].trim(), 1, Integer::sum);
        }
    }

    void calcProbability() {
        
    }

    void test_show() {
        System.out.println(this.WordsMap);
    }
}
