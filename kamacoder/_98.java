import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class _98 {

    static List<List<Integer>> result = new ArrayList<>();
    static List<Integer> path = new ArrayList<>();

    private static void dfs(int[][] graph, int x, int n) {
        if (x == n) {
            result.add(new ArrayList<>(path));
            return;
        }
        for (int i = 1; i <= n; i++) {
            if (graph[x][i] == 1) {
                path.add(i);
                dfs(graph, i, n);
                path.remove(path.size() - 1);
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();

        int[][] graph = new int[n + 1][n + 1];
        for (int i = 0; i < m; i++) {
            int start = sc.nextInt();
            int end = sc.nextInt();
            graph[start][end] = 1;
        }

        path.add(1); // 无论什么路径，都已经是从1节点出发
        dfs(graph, 1, n); // 开始遍历

        if (result.isEmpty()) {
            System.out.println(-1);
        }

        for (List<Integer> list : result) {
            for (int i = 0; i < list.size() - 1; i++) {
                System.out.print(list.get(i) + " ");
            }
            System.out.println(list.get(list.size() - 1));
        }

        sc.close();
    }

}
