import java.util.Scanner;

public class _44 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int[][] land = new int[n][m];

        int[] x_sum = new int[n]; // 记录x方向前缀累计合
        int[] y_sum = new int[m]; // 记录y方向前缀累计合
        int temp_sum = 0;
        int minDifference = Integer.MAX_VALUE;
        int tempDifference = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                land[i][j] = scanner.nextInt();
                temp_sum += land[i][j];
            }
            x_sum[i] = temp_sum;
        }

        temp_sum = 0;
        for (int j = 0; j < m; j++) {
            for (int i = 0; i < n; i++) {
                temp_sum += land[i][j];
            }
            y_sum[j] = temp_sum;
        }

        for (int i = 1; i <= m - 1; i++) {
            tempDifference = Math.abs(y_sum[m - 1] - 2 * y_sum[i - 1]);
            minDifference = Math.min(minDifference, tempDifference);
        }

        for (int j = 1; j <= n - 1; j++) {
            tempDifference = Math.abs(x_sum[n - 1] - 2 * x_sum[j - 1]);
            minDifference = Math.min(minDifference, tempDifference);
        }

        System.out.println(minDifference);

        scanner.close();
    }
}
