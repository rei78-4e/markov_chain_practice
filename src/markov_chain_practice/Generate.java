package markov_chain_practice;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

public class Generate {
    // <HashMap<現在の単語, HashMap<次の単語, 出現確率>>>
    HashMap<String, HashMap<String, Probability>> ProbabilityMap;

    // 乱数生成のため
    static final Random RANDOM = new Random();

    /**
     * 
     * @param leaned_data: 出現回数解析ずみのHashMap
     */
    public Generate(Leaning leaned_data) {
        // 初期化
        this.ProbabilityMap = new HashMap<>();

        var ld = leaned_data.get_map();
        ld.forEach((outk, nextp) -> {
            // 分数化する際の母数のためにsumを計算
            var sum = 0;
            // <次の単語, 回数>
            for (Map.Entry<String, Integer> entry : nextp.entrySet()) {
                sum += entry.getValue();
            }

            // 確率保存用に一時変数
            HashMap<String, Probability> new_map = new HashMap<>();
            for (Map.Entry<String, Integer> entry : nextp.entrySet()) {
                // 出現した単語の重複を含めた総数を母数としてその単語(entry.getValue())の出現回数を分子とする
                // 全体における次の単語の出現確率を規定できる
                new_map.put(entry.getKey(), new Probability(entry.getValue(), sum));
            }

            // 生成した確率群を保存
            this.ProbabilityMap.put(outk, new_map);
        });
    }

    /**
     * 次の単語を計算済みの確率マップから乱数を用いて生成
     * 
     * @param k: 現在の単語
     * @return: 次の単語
     */
    public String Next(String k) {
        var start = this.ProbabilityMap.get(k);
        var w = get_sum(start);
        var pivot = RANDOM.nextLong(w);

        var cSum = 0L;
        for (Entry<String, Probability> p : start.entrySet()) {
            cSum += p.getValue().get_num();
            if (pivot < cSum) {
                return p.getKey();
            }
        }
        return ".";
    }

    /**
     * 指定回数、Next()を用いて単語を生成して表示する
     * 
     * @param k: 現在の単語
     * @param c: 生成する単語数
     */
    public void loopShow(String k, int c) {
        if (c <= 0 || k.equals(".")) {
            System.out.printf(".\n");
            return;
        }
        System.out.printf("%s ", k);
        loopShow(this.Next(k), c - 1);
    }

    // 先頭の値の分母を取得して返す
    long get_sum(HashMap<String, Probability> m) {
        long den = 0;
        for (Map.Entry<String, Probability> entry : m.entrySet()) {
            den = entry.getValue().get_den();
            // 同一単語からの次単語予測群における分母は同一のため1つ目で十分
            break;
        }
        return den;

    }

    // debug用のプリンタ
    public void show() {
        this.ProbabilityMap.forEach((k, v) -> {
            System.out.printf("%s\n", k);
            v.forEach((kk, vv) -> {
                System.out.printf("  %s:\n  %s\n", kk, vv.toString());
            });
        });
    }
}
