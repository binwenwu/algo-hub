import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class _103 {
    static int[][] directions = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } }; // 上, 下, 左, 右
    static int[][] island;
    static boolean[][] visited1;
    static boolean[][] visited2;

    private static void dfs(int x, int y, boolean[][] visited) {
        for (int i = 0; i < 4; i++) {
            int nextX = x + directions[i][0];
            int nextY = y + directions[i][1];
            if (nextX < 0 || nextX >= island.length || nextY < 0 || nextY >= island[0].length) {
                continue;
            }
            if (!visited[nextX][nextY] && island[nextX][nextY] > island[x][y]) {
                visited[nextX][nextY] = true;
                dfs(nextX, nextY, visited);
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int m = sc.nextInt();
        int n = sc.nextInt();
        island = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                island[i][j] = sc.nextInt();
            }
        }

        visited1 = new boolean[m][n];
        visited2 = new boolean[m][n];

        for (int i = 0; i < m; i++) {
            if (!visited1[i][0]) {
                visited1[i][0] = true;
                dfs(i, 0, visited1);
            }
            if (!visited2[i][n - 1]) {
                visited2[i][n - 1] = true;
                dfs(i, n - 1, visited2);
            }
        }

        for (int i = 0; i < n; i++) {
            if (!visited1[0][i]) {
                visited1[0][i] = true;
                dfs(0, i, visited1);
            }
            if (!visited2[m - 1][i]) {
                visited2[m - 1][i] = true;
                dfs(m - 1, i, visited2);
            }
        }

        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (visited1[i][j] && visited2[i][j]) {
                    result.add(Arrays.asList(i, j));
                }
            }
        }

        for (int i = 0; i < result.size() - 1; i++) {
            System.out.println(result.get(i).get(0) + " " + result.get(i).get(1));
        }

        System.out.print(result.get(result.size() - 1).get(0) + " " + result.get(result.size() - 1).get(1));

        sc.close();
    }
}
