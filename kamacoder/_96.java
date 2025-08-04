import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class _96 {
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

        int src = sc.nextInt(); // 起点
        int dst = sc.nextInt(); // 终点
        int k = sc.nextInt(); // 经过的城市数量的限制

        sc.close();

        // 表示从当前节点到原始节点的最小距离
        int[] minDist = new int[n + 1];
        int[] minDistCopy;
 
        // 初始化 minDist 数组
        Arrays.fill(minDist, Integer.MAX_VALUE);
        minDist[src] = 0;

        // 启动循环以松弛所有边 k + 1 次以更新 minDist 数组
        for (int i = 1; i <= k + 1; i++) {
            minDistCopy = Arrays.copyOf(minDist, n + 1);
            for (Edge edge : edges) {
                // 更新 minDist 数组
                if (minDistCopy[edge.from] != Integer.MAX_VALUE && (minDistCopy[edge.from] + edge.val) < minDist[edge.to]) {
                    minDist[edge.to] = minDistCopy[edge.from] + edge.val;
                }
            }
        }

        // 输出结果
        if (minDist[dst] == Integer.MAX_VALUE) {
            System.out.println("unreachable");
        } else {
            System.out.println(minDist[dst]);
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
