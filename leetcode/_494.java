public class _494 {
    public static void main(String[] args) {

    }

    public int findTargetSumWays(int[] nums, int target) {
        int sum = 0;
        for (int i : nums) {
            sum += i;
        }

        if ((sum + target) % 2 != 0 || (sum + target) < 0) {
            return 0;
        }

        target = (sum + target) / 2;

        int[][] dp = new int[nums.length + 1][target + 1];

        dp[0][0] = 1;
        for (int i = 1; i <= nums.length; i++) {
            if (nums[i - 1] == 0) {
                dp[i][0] = 2;
            } else {
                dp[i][0] = 1;
            }
        }

        for (int i = 1; i <= nums.length; i++) {
            for (int j = 0; j <= target; j++) {
                if (nums[i - 1] > j) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i - 1][j] + dp[i - 1][j - nums[i - 1]];
                }
            }
        }

        return dp[nums.length][target];

    }

    public int findTargetSumWays2(int[] nums, int target) {
        int sum = 0;
        for (int i : nums) {
            sum += i;
        }

        if ((sum + target) % 2 != 0 || (sum + target) < 0) {
            return 0;
        }

        target = (sum + target) / 2;

        int[][] dp = new int[nums.length + 1][target + 1];

        dp[0][0] = 1;

        for (int i = 1; i <= nums.length; i++) {
            for (int j = 0; j <= target; j++) {
                if (nums[i - 1] > j) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i - 1][j] + dp[i - 1][j - nums[i - 1]];
                }

            }
        }

        return dp[nums.length][target];
    }
}
