public class _122 {
    public static void main(String[] args) {

    }

    /**
     * 
     * 贪心算法
     * 
     * 
     * 如果想到其实最终利润是可以分解的，那么本题就很容易了！
     * 如何分解呢？
     * 假如第 0 天买入，第 3 天卖出，那么利润为：prices[3] - prices[0]。
     * 相当于(prices[3] - prices[2]) + (prices[2] - prices[1]) + (prices[1] -
     * prices[0])。
     * 
     * 从图中可以发现，其实我们需要收集每天的正利润就可以，收集正利润的区间，就是股票买卖的区间
     * 
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        if (prices.length == 1) {
            return 0;
        }
        int result = 0;
        int temp = 0;

        for (int i = 1; i < prices.length; i++) {
            temp = prices[i] - prices[i - 1];
            if (temp > 0) {
                result += temp;
            }
        }

        return result;
    }

}
