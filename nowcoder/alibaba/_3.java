package nowcoder.alibaba;

import java.util.Scanner;

/**
 * https://www.nowcoder.com/exam/test/95052764/detail?pid=30440638
 */
public class _3 {
    static final long MOD = 1000000007;

    /**
     * 
     * n 个节点能组成多少种二叉树：G(n) = Σ (G(i) * G(n-1-i))
     * 含义：
     * 选一个节点做根
     * 左子树有 i 个节点
     * 右子树有 n-1-i 个节点
     * 
     * 现在加上高度限制：dp[n][h] = 节点数为 n，高度 ≤ h 的二叉树数量
     * dp[n][h] = Σ (dp[i][h-1] * dp[n-1-i][h-1])
     */
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();

        long[][] dp = new long[n + 1][m + 1];

        // 空树
        for (int h = 0; h <= m; h++) {
            dp[0][h] = 1;
        }

        // 枚举顺序必须保证在计算当前状态时，依赖的状态已经计算完成
        // 枚举高度
        for (int h = 1; h <= m; h++) {

            // 枚举节点数
            for (int nodes = 1; nodes <= n; nodes++) {

                long sum = 0;

                // 枚举左子树节点
                for (int left = 0; left < nodes; left++) {

                    int right = nodes - 1 - left;

                    // 因为取模预算符合交换率，所以可以这样计算，如果sum累加完再取模，可能会出现累加过程中，就越界的情况
                    sum = (sum + dp[left][h - 1] * dp[right][h - 1]) % MOD;
                }

                dp[nodes][h] = sum;
            }
        }

        System.out.println(dp[n][m]);

        sc.close();
    }
}
