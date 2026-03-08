import java.util.List;

public class _120 {
    /**
     * dp[i][j] = triangle[i][j] + min(dp[i+1][j], dp[i+1][j+1])
     * 
     * 这意味着：从底往上计算最方便
     * 
     * 可以直接复用一维数组
     */
    public int minimumTotal(List<List<Integer>> triangle) {

        int n = triangle.size();
        int[] dp = new int[n + 1];

        for (int i = n - 1; i >= 0; i--) {

            for (int j = 0; j <= i; j++) {

                dp[j] = Math.min(dp[j], dp[j + 1]) + triangle.get(i).get(j);

            }
        }

        return dp[0];
    }
}
