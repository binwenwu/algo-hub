import java.util.Scanner;

public class _100 {
    static int[][] directions = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } }; // 上, 下, 左, 右
    static int[][] island;
    static boolean[][] visited;

    static int temp = 0;
    static int max = 0;

    private static void dfs(int x, int y) {
        for (int i = 0; i < 4; i++) {
            int nextX = x + directions[i][0];
            int nextY = y + directions[i][1];
            if (nextX < 0 || nextX >= island.length || nextY < 0 || nextY >= island[0].length) {
                continue;
            }
            if (!visited[nextX][nextY] && island[nextX][nextY] == 1) {
                temp++;
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
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                island[i][j] = sc.nextInt();
            }
        }
        visited = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!visited[i][j] && island[i][j] == 1) {
                    temp = 1;
                    visited[i][j] = true;
                    dfs(i, j);
                    max = Math.max(max, temp);
                }
            }
        }

        System.out.println(max);

        sc.close();
    }
}
