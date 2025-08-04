import java.util.Arrays;
import java.util.Scanner;

// dijkstra 算法（朴素版）
public class _47 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        final int MAX_VALUE = Integer.MAX_VALUE;
        int N = sc.nextInt();
        int M = sc.nextInt();
        boolean[] visited = new boolean[N + 1];
        int[] minDist = new int[N + 1];
        Arrays.fill(minDist, MAX_VALUE);
        int[][] graph = new int[N + 1][N + 1];

        for (int i = 0; i <= N; i++) {
            Arrays.fill(graph[i], MAX_VALUE);
        }

        for (int i = 0; i < M; i++) {
            int p1 = sc.nextInt();
            int p2 = sc.nextInt();
            int value = sc.nextInt();
            graph[p1][p2] = value;
        }

        sc.close();

        // 0. 设置起始点
        minDist[1] = 0;

        for (int i = 1; i <= N; i++) {
            // 1. 找到距离起点最近的节点
            int curr = -1;
            int minValue = MAX_VALUE;
            for (int j = 1; j <= N; j++) {
                if (!visited[j] && minDist[j] < minValue) {
                    curr = j;
                    minValue = minDist[j];
                }
            }
            if (curr == -1) {
                break;
            }

            // 2. 将其加入路径，并累加距离
            visited[curr] = true;

            // 3. 更新未访问点到起点的最短距离
            for (int j = 1; j <= N; j++) {
                if (!visited[j] && graph[curr][j] != MAX_VALUE && graph[curr][j] + minDist[curr] < minDist[j]) {
                    minDist[j] = graph[curr][j] + minDist[curr];
                }
            }

        }

        if (minDist[N] == MAX_VALUE) {
            System.out.print(-1);
        } else {
            System.out.print(minDist[N]);
        }

    }
}

// dijkstra 算法（堆优化版）
class Main {
    public static void main(String[] args) {

    }
}