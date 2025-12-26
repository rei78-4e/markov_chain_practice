package markov_chain_practice;

public class Probability {
    long num;
    long den;
    
    public Probability(long n, long d) {
        this.num = n;
        this.den = d;
    }

    @Override
    public String toString() {
        return String.format("%d / %d", this.num, this.den);
    }
}
