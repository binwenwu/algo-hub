package nowcoder.alibaba;

import java.util.Arrays;
import java.util.Scanner;

/**
 * https://www.nowcoder.com/exam/test/95052764/detail?pid=30440638
 */
public class _1 {
    static class Node {
        int x;
        int y;

        Node() {

        }

        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    /**
     * 先按 x 排序， 其中为了防止 x 相等时错误选择， x 相等时，需按 y 降序
     * (1,2) (1,3)
     * 这种 x 相同但 y 增长的非法情况被 LIS 选中
     * 
     * 排序完后，就只要从 y 序列中找最长递增子序列即可
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        while (T-- > 0) {
            int n = sc.nextInt();
            Node[] xy = new Node[n];

            Arrays.fill(xy, new Node());

            for (int i = 0; i < n; i++) {
                xy[i] = new Node(sc.nextInt(), 0);
            }
            for (int i = 0; i < n; i++) {
                xy[i].y = sc.nextInt();
            }

            Arrays.sort(xy, (Node a, Node b) -> {
                if (a.x == b.x) {
                    return b.y - a.y;
                }
                return a.x - b.x;
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

            int[] dp = new int[n]; // dp[k] = 长度为 k+1 的递增子序列的最小结尾，因为结尾更小，更容易接新数
            int len = 0;

            for (int i = 0; i < n; i++) {

                int y = xy[i].y;

                int l = 0;
                int r = len;

                while (l < r) {
                    int mid = (l + r) / 2;

                    if (dp[mid] < y) {
                        l = mid + 1;
                    } else {
                        r = mid;
                    }
                }

                // 如果能接， l 就会是 len，都则 l 指向的是第一个 >= 当前数的位置
                dp[l] = y;

                // 如果能接，len++
                if (l == len) {
                    len++;
                }
            }

            System.out.println(len);
        }

        sc.close();
    }
}
