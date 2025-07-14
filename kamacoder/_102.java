import java.util.Scanner;

public class _102 {
    static int[][] directions = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } }; // 上, 下, 左, 右
    static int[][] island;
    static boolean[][] visited;

    private static void dfs(int x, int y) {
        for (int i = 0; i < 4; i++) {
            int nextX = x + directions[i][0];
            int nextY = y + directions[i][1];
            if (nextX < 0 || nextX >= island.length || nextY < 0 || nextY >= island[0].length) {
                continue;
            }
            if (island[nextX][nextY] == 1 && !visited[nextX][nextY]) {
                visited[nextX][nextY] = true;
                dfs(nextX, nextY);
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        island = new int[n][m];
        visited = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                island[i][j] = sc.nextInt();
            }
        }

        for (int i = 0; i < n; i++) {
            if (island[i][0] == 1 && !visited[i][0]) {
                visited[i][0] = true;
                dfs(i, 0);
            }
            if (island[i][m - 1] == 1 && !visited[i][m - 1]) {
                visited[i][m - 1] = true;
                dfs(i, m - 1);
            }
        }

        for (int i = 0; i < m; i++) {
            if (island[0][i] == 1 && !visited[0][i]) {
                visited[0][i] = true;
                dfs(0, i);
            }
            if (island[n - 1][i] == 1 && !visited[n - 1][i]) {
                visited[n - 1][i] = true;
                dfs(n - 1, i);
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (island[i][j] == 1 && !visited[i][j]) {
                    island[i][j] = 0;
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m - 1; j++) {
                System.out.print(island[i][j] + " ");
            }
            System.out.println(island[i][m - 1]);
        }

        sc.close();
    }
}
