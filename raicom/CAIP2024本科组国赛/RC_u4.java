package raicom.CAIP2024本科组国赛;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 注意：此解法基于“先用DFS找到所有路径，再筛选最优”的思路。
 * 对于本题 n<=1000 的数据规模，该方法会因为路径数量过多而导致严重超时和内存溢出。
 * 它仅用于教学和对比，不能通过本题。正确的解法是 Dijkstra 算法。
 */
public class RC_u4 {

    // 邻接矩阵，graph[i][j] 存储 i 到 j 的花销，0表示不通
    private static int[][] graph;
    // 存储城镇热度
    private static int[] heats;
    // 目的地
    private static int t;
    // 存储所有找到的路径
    private static List<List<Integer>> allPaths = new ArrayList<>();
    // 记录当前路径
    private static List<Integer> currentPath = new ArrayList<>();
    // 访问标记，防止在一条路径中重复访问节点
    private static boolean[] visited;

    /**
     * DFS 寻找所有从 s 到 t 的简单路径
     * 
     * @param s 当前所在城市
     */
    private static void dfs(int s) {

        // 如果到达目的地，则保存这条路径
        if (s == t) {
            allPaths.add(new ArrayList<>(currentPath));
            return;
            // 到达终点后，必须回溯，不能在此处 return，因为可能还有其他路径
        }

        
        // 遍历所有可能的下一个城市
        for (int i = 1; i < graph.length; i++) {
            // 如果 s 到 i 有路，并且 i 尚未在当前路径中被访问过
            if (graph[s][i] != 0 && !visited[i]) {
                currentPath.add(i);
                visited[i] = true;
                dfs(i);
                // 回溯：将当前城市从路径中移除，并取消访问标记，以便其他路径可以使用它
                currentPath.removeLast();
                visited[i] = false;
            }
        }

    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int s = sc.nextInt();
        t = sc.nextInt(); // t 设为全局变量，方便dfs访问

        heats = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            heats[i] = sc.nextInt();
        }

        graph = new int[n + 1][n + 1];
        for (int i = 0; i < m; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            int cost = sc.nextInt();
            graph[u][v] = cost;
            graph[v][u] = cost;
        }
        sc.close();

        // --- 步骤 1: 搜索所有路径 ---
        visited = new boolean[n + 1];
        currentPath.add(s);
        visited[s] = true;
        dfs(s);

        // --- 步骤 2: 筛选最优路径 ---
        if (allPaths.isEmpty()) {
            System.out.println("Impossible");
        } else {
            long bestCost = Long.MAX_VALUE;
            int bestMaxHeat = Integer.MAX_VALUE;

            for (List<Integer> path : allPaths) {
                long currentTotalCost = 0;
                int currentMaxHeat = 0;

                // 计算当前路径的总花销和最高热度
                for (int i = 0; i < path.size() - 1; i++) {
                    int u = path.get(i);
                    int v = path.get(i + 1);
                    currentTotalCost += graph[u][v];

                    // 计算途径城市热度（起点和终点都不算）
                    // 题目解释：7->2->5->8，途径2和5，终点8的热度不算
                    // 所以我们只考虑 path 中除了终点 t 以外的所有节点的热度
                    if (v != t) {
                        currentMaxHeat = Math.max(currentMaxHeat, heats[v]);
                    }
                }

                // 比较并更新最优解
                if (currentTotalCost < bestCost) {
                    bestCost = currentTotalCost;
                    bestMaxHeat = currentMaxHeat;
                } else if (currentTotalCost == bestCost && currentMaxHeat < bestMaxHeat) {
                    bestMaxHeat = currentMaxHeat;
                }
            }
            System.out.println(bestCost + " " + bestMaxHeat);
        }
    }
}