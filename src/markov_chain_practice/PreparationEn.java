package markov_chain_practice;

import java.nio.file.Path;

public class PreparationEn extends Preparation {
    public PreparationEn(Path[] p) throws Exception {
        super(p);
    }

    /**
     * @return: spaceで分割した値
     */
    @Override
    public String[] Parse() {
        return this.input.split("\\s+");
    }

}
