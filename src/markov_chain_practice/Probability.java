package markov_chain_practice;

public class Probability {
    long num;
    long den;
    
    public Probability(long n, long d) {
        this.num = n;
        this.den = d;
    }

    public long get_num() {
        return this.num;
    }

    public long get_den() {
        return this.den;
    }

    @Override
    public String toString() {
        return String.format("%d/%d", this.num, this.den);
    }
}
