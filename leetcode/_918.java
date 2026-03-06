public class _918 {
    /**
     * 1. 普通最大子数组 → Kadane
     * 2. 环形子数组 → 等价于 total - 最小子数组
     * 3. 最终结果
     * 4. 但要注意全负数特判
     * - 这里全负判断，采用判断最大子数组（不算环）是不是小于零来判读的，
     * - 如果小于零，说明全部元素都是小于零的，这个时候直接返回当前的最大子数组即可
     */
    public int maxSubarraySumCircular(int[] nums) {
        int total = 0;
        int maxSum = Integer.MIN_VALUE;
        int s1 = 0;
        int minSum = Integer.MAX_VALUE;
        int s2 = 0;

        for (int num : nums) {

            s1 += num;
            maxSum = Math.max(maxSum, s1);
            if (s1 < 0) {
                s1 = 0;
            }

            s2 += num;
            minSum = Math.min(minSum, s2);
            if (s2 > 0) {
                s2 = 0;
            }

            total += num;
        }

        // 全负数
        if (maxSum < 0)
            return maxSum;

        return Math.max(maxSum, total - minSum);
    }
}
