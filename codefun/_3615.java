package codefun;

import java.util.Scanner;

/**
 * 题意：长度为 n 的二进制串中，最大连续1的长度严格小于 r 的方案数
 *
 * 思路：二维 DP
 * - dp[i][j] = 长度为 i 的合法串中，末尾恰好有 j 个连续1 的方案数 (0 <= j <= r-1)
 * - 转移：
 * dp[i][0] = sum(dp[i-1][j]) for j=0..r-2 （第i位填0，前面任意合法串）
 * dp[i][j] = dp[i-1][j-1] (j>=1) （第i位填1，延续前面的连续1）
 * - 初始化：dp[0][0] = 1
 * - 答案：sum(dp[n][j]) for j=0..r-2
 * - 当 r > n 时，答案 = 2^n
 *
 * 时间复杂度 O(n * min(r, n))
 */
public class _3615 {
    static final long MOD = 1_000_000_007;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        while (T-- > 0) {
            int n = sc.nextInt();
            long r = sc.nextLong();
            if (r > n) {
                // 所有长度为n的串都合法
                System.out.println(power(2, n));
            } else {
                int R = (int) r; // r <= n <= 1000，可以安全转 int
                // dp[i][j] = 长度为 i 的合法串中，末尾恰好有 j 个连续1 的方案数
                long[][] dp = new long[n + 1][R];
                dp[0][0] = 1; // 长度0的空串，末尾0个连续1

                for (int i = 1; i <= n; i++) {
                    // 第i位填0：dp[i][0] = sum(dp[i-1][j]) for j=0..R-1
                    long total = 0;
                    for (int j = 0; j < R; j++) {
                        total = (total + dp[i - 1][j]) % MOD;
                    }
                    dp[i][0] = total;
                    // 第i位填1：dp[i][j] = dp[i-1][j-1] (j >= 1)
                    for (int j = 1; j < R; j++) {
                        dp[i][j] = dp[i - 1][j - 1];
                    }
                }

                // 答案 = sum(dp[n][j]) for j=0..R-1
                long ans = 0;
                for (int j = 0; j < R; j++) {
                    ans = (ans + dp[n][j]) % MOD;
                }
                System.out.println(ans);
            }
        }
        sc.close();
    }

    // 快速幂计算 2^exp
    static long power(long base, long exp) {
        base %= MOD;
        long result = 1;
        while (exp > 0) {
            if ((exp & 1) == 1)
                result = result * base % MOD;
            base = base * base % MOD;
            exp >>= 1;
        }
        return result;
    }
}
