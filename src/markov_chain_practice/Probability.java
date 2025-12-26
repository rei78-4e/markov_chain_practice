// 確率保存のための分数Class

package markov_chain_practice;

public class Probability {
    // 分子
    long num;
    // 分母
    long den;

    /**
     * 
     * @param n: 分子
     * @param d: 分母
     */
    public Probability(long n, long d) {
        this.num = n;
        this.den = d;
    }

    // ゲッター
    public long get_num() {
        return this.num;
    }

    public long get_den() {
        return this.den;
    }

    // Debug用に表示系を
    @Override
    public String toString() {
        return String.format("%d/%d", this.num, this.den);
    }
}
