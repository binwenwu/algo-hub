import java.util.Scanner;

// 暴力解法
public class _106 {

    private static int[][] directions = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
    private static int[][] island;
    private static int result = 0;

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
                if (island[i][j] == 1) {
                    int temp = 4;
                    for (int q = 0; q < 4; q++) {
                        int nextX = i + directions[q][0];
                        int nextY = j + directions[q][1];
                        if (island[nextX][nextY] == 1) {
                            temp--;
                        }
                    }
                    result += temp;
                }
            }
        }

        System.out.println(result);

        sc.close();
    }
}
