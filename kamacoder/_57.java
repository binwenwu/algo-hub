import java.util.Scanner;

public class _57 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();

        if (n < 3) {
            if (m >= 2) {
                System.out.println(n);
            } else {
                System.out.println(1);
            }
        } else {
            if (m < 2) {
                System.out.println(1);
            } else {
                int[] dp = new int[n + 1];
                dp[0] = 1;
                dp[1] = 1;
                dp[2] = 2;
                for (int i = 3; i <= n; i++) {
                    for (int j = i - 1; j >= 0 && j >= i - m; j--) {
                        dp[i] = dp[i] + dp[j];
                    }
                }

                System.out.println(dp[n]);
            }
        }

        sc.close();
    }
}
