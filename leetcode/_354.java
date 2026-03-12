import java.util.Arrays;

public class _354 {

    /**
     * 先按宽度排序， 其中为了防止宽度相等时错误选择， 宽度相等时，需按高度降序
     * (1,2) (1,3)
     * 这种宽度相同但高度增长的非法情况被 LIS 选中
     * 
     * 排序完后，就只要从高度序列中找最长递增子序列即可
     */
    public int maxEnvelopes(int[][] envelopes) {

        if (envelopes.length == 0)
            return 0;

        // 1 排序
        Arrays.sort(envelopes, (a, b) -> {
            if (a[0] == b[0]) {
                return b[1] - a[1]; // 高度降序
            }
            return a[0] - b[0]; // 宽度升序
        });

        /**
         * 传统 DP 会超时,采用 贪心 + 二分 可以降低时间复杂度
         * 
         * 同样长度的序列
         * 保留结尾最小的
         * 
         * 能接就接，接了后，len++
         * 不能接就替换
         * 替换第一个 >= 当前数的位置
         * 
         */
        int[] dp = new int[envelopes.length]; // dp[k] = 长度为 k+1 的递增子序列的最小结尾，因为结尾更小，更容易接新数
        int len = 0;

        for (int[] e : envelopes) {

            int h = e[1];

            int l = 0;
            int r = len;

            while (l < r) {
                int mid = (l + r) / 2;

                if (dp[mid] < h) {
                    l = mid + 1;
                } else {
                    r = mid;
                }
            }

            // 如果能接， l 就会是 len，都则 l 指向的是第一个 >= 当前数的位置
            dp[l] = h;

            // 如果能接，len++
            if (l == len) {
                len++;
            }
        }

        return len;
    }
}
