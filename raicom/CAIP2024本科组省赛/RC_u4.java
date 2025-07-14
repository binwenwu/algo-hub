package raicom.CAIP2024本科组省赛;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;

public class RC_u4 {

    static List<Integer>[] adj;
    static boolean[] visited;
    static int N;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        while (T-- > 0) {
            solve(sc);
        }
    }

    public static void solve(Scanner sc) {
        N = sc.nextInt();
        int M = sc.nextInt();

        adj = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            adj[u].add(v);
            adj[v].add(u);
        }

        visited = new boolean[N + 1];
        int octopusCount = 0;
        int cycleSize = 0;

        for (int i = 1; i <= N; i++) {
            if (!visited[i]) {
                // 发现新连通分量，开始分析
                List<Integer> componentNodes = new ArrayList<>();
                long degreeSum = 0;

                // 1. 找出连通分量的所有节点和边
                Queue<Integer> q = new LinkedList<>();
                q.add(i);
                visited[i] = true;

                while (!q.isEmpty()) {
                    int u = q.poll();
                    componentNodes.add(u);
                    degreeSum += adj[u].size();

                    for (int v : adj[u]) {
                        if (!visited[v]) {
                            visited[v] = true;
                            q.add(v);
                        }
                    }
                }

                int vc = componentNodes.size(); // 分量顶点数
                long ec = degreeSum / 2; // 分量边数

                // 2. 判断是否为章鱼图 (V > 2 且 V == E)
                if (vc > 2 && vc == ec) {
                    octopusCount++;
                    // 3. 计算环的大小
                    cycleSize = calculateCycleSize(componentNodes);
                }
            }
        }

        if (octopusCount == 1) {
            System.out.println("Yes " + cycleSize);
        } else {
            System.out.println("No " + octopusCount);
        }
    }

    // 使用拓扑排序思想剪枝，计算环的大小
    private static int calculateCycleSize(List<Integer> nodes) {
        if (nodes.isEmpty()) {
            return 0;
        }

        Map<Integer, Integer> degrees = new HashMap<>();
        Queue<Integer> q = new LinkedList<>();

        // 初始化度和队列
        for (int node : nodes) {
            degrees.put(node, adj[node].size());
            if (adj[node].size() == 1) {
                q.add(node);
            }
        }

        int prunedCount = 0;
        while (!q.isEmpty()) {
            int u = q.poll();
            prunedCount++;

            for (int v : adj[u]) {
                // v 必须是分量内的点
                if (degrees.containsKey(v)) {
                    degrees.put(v, degrees.get(v) - 1);
                    if (degrees.get(v) == 1) {
                        q.add(v);
                    }
                }
            }
        }

        return nodes.size() - prunedCount;
    }
}
