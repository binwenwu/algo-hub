public class _53 {
    public static void main(String[] args) {

    }

    /*
     * 贪心算法
     * 局部最优：当前“连续和”为负数的时候立刻放弃，
     * 从下一个元素重新计算“连续和”，因为负数加上下一个元素 “连续和”只会越来越小。
     */
    public int maxSubArray(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }

        int maxSum = Integer.MIN_VALUE;
        int count = 0;

        for (int i = 0; i < nums.length; i++) {
            count += nums[i];
            if (count > maxSum) {
                maxSum = count;
            }
            if (count <= 0) {
                count = 0;
            }
        }
        return maxSum;
    }

}
