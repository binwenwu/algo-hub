import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class _94 {
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

        // 启动循环以松弛所有边 n - 1 次以更新 minDist 数组
        for (int i = 1; i < n; i++) {
            for (Edge edge : edges) {
                // 更新 minDist 数组
                if (minDist[edge.from] != Integer.MAX_VALUE && (minDist[edge.from] + edge.val) < minDist[edge.to]) {
                    minDist[edge.to] = minDist[edge.from] + edge.val;
                }
            }
        }

        // 输出结果
        if (minDist[n] == Integer.MAX_VALUE) {
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

class Main {
    public static void main(String[] args) {
        // Input processing
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        List<List<Edge>> graph = new ArrayList<>();

        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            int from = sc.nextInt();
            int to = sc.nextInt();
            int val = sc.nextInt();
            graph.get(from).add(new Edge(from, to, val));
        }

        // Declare the minDist array to record the minimum distance form current node to
        // the original node
        int[] minDist = new int[n + 1];
        Arrays.fill(minDist, Integer.MAX_VALUE);
        minDist[1] = 0;

        // Declare a queue to store the updated nodes instead of traversing all nodes
        // each loop for more efficiency
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(1);

        // Declare a boolean array to record if the current node is in the queue to

        while (!queue.isEmpty()) {
            int curNode = queue.poll();
            for (Edge edge : graph.get(curNode)) {
                if (minDist[edge.to] > minDist[edge.from] + edge.val) { // Start relaxing the edge
                    minDist[edge.to] = minDist[edge.from] + edge.val;
                    if (!queue.contains(edge.to)) { // Don't add the node if it's already in the queue
                        queue.offer(edge.to);
                    }
                }
            }
        }

        // Outcome printing
        if (minDist[n] == Integer.MAX_VALUE) {
            System.out.println("unconnected");
        } else {
            System.out.println(minDist[n]);
        }
    }
}
