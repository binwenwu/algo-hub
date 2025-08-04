import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

// 判断有没有负权回路，就多松弛一次，看 minDist 发不发生变化
public class _95 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        List<Edge> edges = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            int from = sc.nextInt();
            int to = sc.nextInt();
            int val = sc.nextInt();
            edges.add(new Edge(from, to, val));
        }

        sc.close();

        // 表示从当前节点到原始节点的最小距离
        int[] minDist = new int[n + 1];

        // 初始化 minDist 数组
        Arrays.fill(minDist, Integer.MAX_VALUE);
        minDist[1] = 0;

        // 启动循环以松弛所有边 n 次以更新 minDist 数组

        // 用来标记第 n 次松弛的时候，minDist是否发生了变化
        boolean flag = false;
        for (int i = 1; i <= n; i++) {
            for (Edge edge : edges) {
                // 更新 minDist 数组
                if (i < n) {
                    if (minDist[edge.from] != Integer.MAX_VALUE && (minDist[edge.from] + edge.val) < minDist[edge.to]) {
                        minDist[edge.to] = minDist[edge.from] + edge.val;
                    }
                } else {
                    if (minDist[edge.from] != Integer.MAX_VALUE && (minDist[edge.from] + edge.val) < minDist[edge.to]) {
                        flag = true;
                    }
                }
            }
        }

        // 输出结果
        if (flag) {
            System.out.println("circle");
        } else if (minDist[n] == Integer.MAX_VALUE) {
            System.out.println("unconnected");
        } else {
            System.out.println(minDist[n]);
        }
    }
}

class Edge {
    int from;
    int to;
    int val;

    public Edge(int from, int to, int val) {
        this.from = from;
        this.to = to;
        this.val = val;
    }
}
