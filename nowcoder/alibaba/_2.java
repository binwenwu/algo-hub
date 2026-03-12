package nowcoder.alibaba;

import java.util.Scanner;

/**
 * https://www.nowcoder.com/exam/test/95052764/detail?pid=30440638
 * 
 * ASn−1​=Sn​+BSn−2​ 可推得 Sn​=ASn−1​−BSn−2​
 */
public class _2 {
    static final long MOD = 1000000007;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();

        while (T-- > 0) {

            long A = sc.nextLong();
            long B = sc.nextLong();
            int n = sc.nextInt();

            long[] dp = new long[n + 1];

            dp[0] = 2;
            dp[1] = A % MOD;

            for (int i = 2; i <= n; i++) {
                dp[i] = (A * dp[i - 1] % MOD - B * dp[i - 2] % MOD + MOD) % MOD;
            }

            System.out.println(dp[n]);
        }

        sc.close();
    }
}
