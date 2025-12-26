package markov_chain_practice;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

public class Generate {
    HashMap<String, HashMap<String, Probability>> ProbabilityMap;
    static final Random RANDOM = new Random();

    public Generate(Leaning leaned_data) {
        var ld = leaned_data.get_map();
        this.ProbabilityMap = new HashMap<>();
        ld.forEach((outk, nextp) -> {
            var sum = 0;
            for (Map.Entry<String, Integer> entry : nextp.entrySet()) {
                sum += entry.getValue();
            }
            HashMap<String, Probability> new_map = new HashMap<>();
            for (Map.Entry<String, Integer> entry : nextp.entrySet()) {
                new_map.put(entry.getKey(), new Probability(entry.getValue(), sum));
            }

            this.ProbabilityMap.put(outk, new_map);
        });
    }

    public String Next(String k) {
        var start = this.ProbabilityMap.get(k);
        // System.out.printf("%s ", k);
        var w = get_sum(start);
        var pivot = RANDOM.nextLong(w);

        // System.out.printf("total w: %d\npivot: %d\n\n", w, pivot);
        // System.out.println(start);

        var cSum = 0L;
        for (Entry<String, Probability> p: start.entrySet())  {
            cSum += p.getValue().get_num();
            if (pivot < cSum) {
            	return p.getKey();
            }
        }
        return ".";
    }

    public void loopShow(String k, int c) {
        if (c <= 0 || k.equals(".")) {
            return;
        }
        System.out.printf("%s ",k);
        loopShow(this.Next(k), c - 1);
    }

    public void show() {
        this.ProbabilityMap.forEach((k, v) -> {
            System.out.printf("%s\n", k);
            v.forEach((kk, vv) -> {
                System.out.printf("  %s:\n  %s\n", kk, vv.toString());
            });
        });
    }

    long get_sum(HashMap<String, Probability> m) {
        long den = 0;
        for (Map.Entry<String, Probability> entry : m.entrySet()) {
            den = entry.getValue().get_den();
            break;
        }
        return den;

    }
}
