public class _1049 {
    public static void main(String[] args) {
        _1049 s = new _1049();
        int[] stones = { 2, 7, 4, 1, 8, 1 };
        s.lastStoneWeightII1(stones);

    }

    public int lastStoneWeightII1(int[] stones) {
        int n = stones.length;
        if (n < 2) {
            return stones[0];
        }

        int sum = 0;
        for (int i : stones) {
            sum += i;
        }

        int target = sum / 2;

        int[] dp = new int[target + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = target; j >= stones[i - 1]; j--) {
                dp[j] = Math.max(dp[j], dp[j - stones[i - 1]] + stones[i - 1]);
            }
        }
        int result = sum - dp[target] - dp[target];
        return result >= 0 ? result : -1 * result;
    }

}
