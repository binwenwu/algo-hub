import java.util.Scanner;

public class _104 {

    private static int[][] directions = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } }; // 上, 下, 左, 右

    private static int[][] island;
    private static int maxArea = 0;

    private static void dfs(int x, int y) {
        for (int i = 0; i < 4; i++) {
            
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        island = new int[N + 2][M + 2];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                island[i][j] = sc.nextInt();
            }
        }

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                if (island[i][j] == 0) {
                    dfs(i, j);
                }
            }
        }

        sc.close();
    }
}
