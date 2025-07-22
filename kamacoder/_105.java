import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;

public class _105 {

    // 自己的方法，BFS
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean[][] graph;
        int N = sc.nextInt();
        int K = sc.nextInt();
        graph = new boolean[K + 1][K + 1];
        graph[1][1] = true;
        for (int i = 0; i < K; i++) {
            int p1 = sc.nextInt();
            int p2 = sc.nextInt();
            graph[p1][p2] = true;
        }

        Set<Integer> visited = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();
        queue.add(1);

        int result = 0;

        while (!queue.isEmpty()) {
            int curr = queue.poll();
            for (int i = 1; i <= N; i++) {
                if (graph[curr][i] && !visited.contains(i)) {
                    queue.add(i);
                    result++;
                    visited.add(i);
                }
            }
        }

        if (result == N) {
            System.out.print(1);
        } else {
            System.out.print(-1);
        }

        sc.close();
    }
}
