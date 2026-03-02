import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// 邻接矩阵
public class _98 {

    static List<List<Integer>> result = new ArrayList<>();
    static List<Integer> path = new ArrayList<>();
    static int[][] graph;
    static int n;

    private static void dfs(int x) {
        if (x == n) {
            result.add(new ArrayList<>(path));
            return;
        }
        for (int i = 1; i <= n; i++) {
            if (graph[x][i] == 1) {
                path.add(i);
                dfs(i);
                path.remove(path.size() - 1);
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        int m = sc.nextInt();

        graph = new int[n + 1][n + 1];
        for (int i = 0; i < m; i++) {
            int start = sc.nextInt();
            int end = sc.nextInt();
            graph[start][end] = 1;
        }

        path.add(1); // 无论什么路径，都已经是从1节点出发
        dfs(1); // 开始遍历

        if (result.isEmpty()) {
            System.out.println(-1);
        } else {
            for (List<Integer> list : result) {
                for (int i = 0; i < list.size() - 1; i++) {
                    System.out.print(list.get(i) + " ");
                }
                System.out.println(list.get(list.size() - 1));
            }
        }

        sc.close();
    }

}

// 邻接表
class Solution {

    static List<List<Integer>> graph = new ArrayList<>();
    static List<List<Integer>> res = new ArrayList<>();
    static List<Integer> path = new ArrayList<>();
    static int n;

    static void dfs(int x) {
        if (x == n) {
            res.add(new ArrayList<>(path));
            return;
        }

        for (int next : graph.get(x)) {
            path.add(next);
            dfs(next);
            path.remove(path.size() - 1);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int m;
        n = sc.nextInt();
        m = sc.nextInt();

        // 初始化邻接表
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        // 建图
        for (int i = 0; i < m; i++) {
            int s = sc.nextInt();
            int t = sc.nextInt();
            graph.get(s).add(t);
        }

        path.add(1);
        dfs(1);

        if (res.isEmpty()) {
            System.out.println(-1);
        } else {
            for (List<Integer> p : res) {
                for (int i = 0; i < p.size(); i++) {
                    if (i == p.size() - 1)
                        System.out.print(p.get(i));
                    else
                        System.out.print(p.get(i) + " ");
                }
                System.out.println();
            }
        }

        sc.close();
    }
}