package codefun;

import java.util.*;

/**
 * 整体思路：
 * 1. 树是二部图，可以二染色（黑/白），同色节点互不相邻。
 * 2. 为最小化总和，尽量让更多节点取 1，剩余节点取 2。
 * 3. 同色节点可以全取相同值（互不相邻），所以一种颜色全取 1，另一种全取 2。
 * 4. 设两色节点数分别为 b 和 w = n - b：
 * - 方案 A：b 个取 1 + w 个取 2 → 总和 = b + 2w = 2n - b
 * - 方案 B：w 个取 1 + b 个取 2 → 总和 = w + 2b = n + b
 * - 答案 = min(2n - b, n + b) = min(n + w, n + b) = n + min(w, b)
 * 5. 特判 n = 1 时答案为 1。
 * 6. 用 BFS 对树二染色，统计两色节点数。
 */
public class _3820 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        StringBuilder sb = new StringBuilder();

        while (T-- > 0) {
            int n = sc.nextInt();
            int m = sc.nextInt(); // 读取但不使用，因为只需 2 种颜色

            if (n == 1) {
                sb.append(1).append('\n');
                continue;
            }

            // 建邻接表
            List<List<Integer>> adj = new ArrayList<>();
            for (int i = 0; i <= n; i++) {
                adj.add(new ArrayList<>());
            }
            for (int i = 0; i < n - 1; i++) {
                int u = sc.nextInt();
                int v = sc.nextInt();
                adj.get(u).add(v);
                adj.get(v).add(u);
            }

            // BFS 二染色，统计两色节点数
            int[] color = new int[n + 1];
            Arrays.fill(color, -1);
            int[] cnt = new int[2]; // cnt[0] 和 cnt[1] 分别记录两种颜色的节点数
            Queue<Integer> queue = new LinkedList<>();
            color[1] = 0;
            cnt[0]++;
            queue.add(1);

            while (!queue.isEmpty()) {
                int u = queue.poll();
                for (int v : adj.get(u)) {
                    if (color[v] == -1) {
                        color[v] = 1 - color[u];
                        cnt[color[v]]++;
                        queue.add(v);
                    }
                }
            }

            // 答案 = n + min(cnt[0], cnt[1])
            long ans = (long) n + Math.min(cnt[0], cnt[1]);
            sb.append(ans).append('\n');
        }

        System.out.print(sb);
        sc.close();
    }
}
