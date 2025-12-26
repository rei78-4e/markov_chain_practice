package markov_chain_practice;

import java.util.HashMap;
import java.util.Map;

public class Generate {
    HashMap<String, HashMap<String, Probability>> ProbabilityMap;

    public Generate(Leaning leaned_data) {
        var ld = leaned_data.get_map();
        this.ProbabilityMap = new HashMap<>();
        ld.forEach((outk, nextp) -> {
            var sum = 0;
            for (Map.Entry<String,Integer> entry : nextp.entrySet()) {
               sum += entry.getValue(); 
            }
            HashMap<String, Probability> new_map = new HashMap<>();
            for (Map.Entry<String,Integer> entry : nextp.entrySet()) {
               new_map.put(entry.getKey(), new Probability(entry.getValue(), sum));
            }
            
            this.ProbabilityMap.put(outk, new_map);
        });
    }

    public void show() {
        this.ProbabilityMap.forEach((k, v) -> {
            System.out.printf("%s\n", k);
            v.forEach((kk, vv) -> {
                System.out.printf("  %s:\n  %s\n", kk, vv.toString());
            });
        });
    }
}
