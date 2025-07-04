
public class _416 {
    public static void main(String[] args) {
        _416 s = new _416();
        int[] nums = { 1, 5, 11, 5 };
        System.out.println(s.canPartition1(nums));
    }

    // 动态规划
    public boolean canPartition1(int[] nums) {
        if (nums.length < 2) {
            return false;
        }
        int sum = 0;
        int max = 0;
        for (int i : nums) {
            sum += i;
            max = Math.max(max, i);
        }
        if ((sum % 2) != 0) {
            return false;
        }
        sum = sum / 2;
        if (max > sum) {
            return false;
        }

        boolean[][] dp = new boolean[nums.length + 1][sum + 1];
        for (int i = 0; i <= nums.length; i++) {
            dp[i][0] = true;
        }

        for (int i = 1; i <= nums.length; i++) {
            for (int j = 1; j <= sum; j++) {
                if (nums[i - 1] > j) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i - 1][j] || dp[i - 1][j - nums[i - 1]];
                }
            }
        }

        return dp[nums.length][sum];
    }

    /**
     * 动态规划（空间优化）
     * 优化的时候看dp公式，如果只依赖于上一行，
     * 则倒序遍历，如果还有依赖于当前行，则正序遍历
     * 
     * @param nums
     * @return
     */
    public boolean canPartition2(int[] nums) {
        if (nums.length < 2) {
            return false;
        }
        int sum = 0;
        int max = 0;
        for (int i : nums) {
            sum += i;
            max = Math.max(max, i);
        }
        if ((sum % 2) != 0) {
            return false;
        }
        sum = sum / 2;
        if (max > sum) {
            return false;
        }

        boolean[] dp = new boolean[sum + 1];
        dp[0] = true;

        for (int i = 1; i <= nums.length; i++) {
            for (int j = sum; j >= 1; j--) {
                if (nums[i - 1] <= j) {
                    dp[j] = dp[j] || dp[j - nums[i - 1]];
                }
            }
        }

        return dp[sum];
    }

}
