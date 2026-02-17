public class _121 {
    public static void main(String[] args) {

    }

    public int maxProfit(int[] prices) {
        int profit = 0;
        int cost = Integer.MAX_VALUE;
        for (int price : prices) {
            cost = Math.min(cost, price);
            profit = Math.max(profit, price - cost);
        }

        return profit;
    }

}
