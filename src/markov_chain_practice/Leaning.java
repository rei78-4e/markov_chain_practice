package markov_chain_practice;

import java.util.HashMap;

public class Leaning {
    HashMap<String, HashMap<String, Integer>> WordsMap;

    public Leaning(String input) {
        this.collect(input.split(" "));
    }


    void collect(String[] words) {
        for (int i = 0; i < words.length; i++) {
            this.WordsMap.computeIfAbsent(words[i], k -> new HashMap<>()).merge(words[i+1], 1, Integer::sum);
        }
    }

    void test_show() {
        System.out.println(this.WordsMap);
    }
}
