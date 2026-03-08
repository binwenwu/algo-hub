public class _188 {
    public int maxProfit(int k, int[] prices) {

        int n = prices.length;

        if (n == 0)
            return 0;

        /**
         * 如果 k >= n/2
         * 说明可以无限次交易
         * 因为最多交易次数：n/2
         * 
         * 这时问题演化成跟 leetcode 122 一样了，我们只需要所有上涨区间都买
         */
        if (k >= n / 2) {

            int profit = 0;

            for (int i = 1; i < n; i++) {

                if (prices[i] > prices[i - 1])
                    profit += prices[i] - prices[i - 1];

            }

            return profit;
        } else {
            /**
             * | 状态 | 含义 |
             * | ----------- | ------------------ |
             * | dp[i][j][0] | 第 i 天，完成 j 次交易，不持股 |
             * | dp[i][j][1] | 第 i 天，完成 j 次交易，持股 |
             * 
             * dp[i][j][0] =
             * max(
             * dp[i-1][j][0], // 今天不卖
             * dp[i-1][j][1] + price // 今天卖
             * )
             * 
             * dp[i][j][1] =
             * max(
             * dp[i-1][j][1], // 今天不买
             * dp[i-1][j-1][0] - price // 今天买, j - 1 是因为买入会消耗一次交易机会
             * )
             * 
             * 另外，我们发现，dp的第一个维度可以去掉，我们直接进行复用
             */
            int[][] dp = new int[k + 1][2];

            for (int j = 0; j <= k; j++)
                dp[j][1] = -prices[0];

            for (int price : prices) {

                for (int j = 1; j <= k; j++) {

                    dp[j][0] = Math.max(dp[j][0], dp[j][1] + price);

                    dp[j][1] = Math.max(dp[j][1], dp[j - 1][0] - price);
                }
            }

            return dp[k][0];
        }
    }
}
