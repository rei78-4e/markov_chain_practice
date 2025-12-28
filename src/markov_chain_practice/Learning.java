package markov_chain_practice;

import java.util.HashMap;

public class Learning {
    // HashMap<現在の単語, HashMap<次の単語, 出現回数>>
    HashMap<String, HashMap<String, Integer>> WordsMap;

    // 初期化と関数呼び出し
    public Learning(String[] input) {
        this.WordsMap = new HashMap<>();
        this.collect_analyzer(input);
    }

    // ゲッター
    public HashMap<String, HashMap<String, Integer>> get_map() {
        return this.WordsMap;
    }

    /**
     * 
     * @param words: 単語単位のString配列
     */
    void collect_analyzer(String[] words) {
        for (int i = 0; i < words.length - 1; i++) {
            this.WordsMap.computeIfAbsent(words[i], k -> new HashMap<>()).merge(words[i + 1].trim(), 1, Integer::sum);
        }
    }
}
