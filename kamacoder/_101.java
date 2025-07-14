import java.util.Scanner;

public class _101 {
    static int[][] directions = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } }; // 上, 下, 左, 右
    static int[][] island;

    private static void dfs(int x, int y) {
        for (int i = 0; i < 4; i++) {
            int nextX = x + directions[i][0];
            int nextY = y + directions[i][1];
            if (nextX < 0 || nextX >= island.length || nextY < 0 || nextY >= island[0].length) {
                continue;
            }
            if (island[nextX][nextY] == 1) {
                island[nextX][nextY] = 0;
                dfs(nextX, nextY);
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        island = new int[n][m];
        int sum = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                island[i][j] = sc.nextInt();
            }
        }

        for (int i = 0; i < n; i++) {
            if (island[i][0] == 1) {
                island[i][0] = 0;
                dfs(i, 0);
            }
            if (island[i][m - 1] == 1) {
                island[i][m - 1] = 0;
                dfs(i, m - 1);
            }
        }

        for (int i = 0; i < m; i++) {
            if (island[0][i] == 1) {
                island[0][i] = 0;
                dfs(0, i);
            }
            if (island[n - 1][i] == 1) {
                island[n - 1][i] = 0;
                dfs(n - 1, i);
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (island[i][j] == 1) {
                    sum++;
                }
            }
        }

        System.out.println(sum);

        sc.close();
    }
}
