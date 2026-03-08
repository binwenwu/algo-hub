public class _123 {
    /**
     * buy1 = 第一次买入后的最大收益
     * sell1 = 第一次卖出后的最大收益
     * buy2 = 第二次买入后的最大收益
     * sell2 = 第二次卖出后的最大收益
     */
    public int maxProfit(int[] prices) {

        int buy1 = Integer.MIN_VALUE;
        int sell1 = 0;

        int buy2 = Integer.MIN_VALUE;
        int sell2 = 0;

        for (int price : prices) {

            buy1 = Math.max(buy1, -price); // 要么之前买过，要么今天买，取最大值

            sell1 = Math.max(sell1, buy1 + price); // 要么之前卖过，要么今天卖，取最大值

            buy2 = Math.max(buy2, sell1 - price); // 同理

            sell2 = Math.max(sell2, buy2 + price); // 同理
        }

        return sell2;
    }
}
