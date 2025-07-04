import java.util.Scanner;

public class _46 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int M = sc.nextInt();
        int N = sc.nextInt();
        int[] spaces = new int[M];
        int[] values = new int[M];
        for (int i = 0; i < M; i++) {
            spaces[i] = sc.nextInt();
        }
        for (int i = 0; i < M; i++) {
            values[i] = sc.nextInt();
        }

        int[][] dp = new int[M + 1][N + 1];
        for (int i = 1; i <= M; i++) {
            for (int j = 1; j <= N; j++) {
                if (spaces[i - 1] > j) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - spaces[i - 1]] + values[i - 1]);
                }
            }
        }

        System.out.println(dp[M][N]);
        sc.close();
    }
}
