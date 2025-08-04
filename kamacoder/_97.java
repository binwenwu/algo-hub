import java.util.Arrays;
import java.util.Scanner;

public class _97 {

    // 使用一个较大的数来表示无穷大，题目中权值范围是[1,10000]，所以这里选择：10001
    private static final int INF = 10001;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 1. 读取景点的数量 N 和道路的数量 M
        int n = sc.nextInt();
        int m = sc.nextInt();

        // 2. 初始化距离矩阵 dist
        // 节点编号从 1 到 N，所以数组大小为 (N+1) x (N+1) 以方便索引
        int[][] dist = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            Arrays.fill(dist[i], INF);
            // 从一个景点到其自身的距离为 0
            dist[i][i] = 0;
        }

        // 3. 读取 M 条道路信息，填充距离矩阵
        for (int i = 0; i < m; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            int w = sc.nextInt();
            // 道路是双向的，所以 u->v 和 v->u 的距离都要更新
            // 如果两点间已存在更短的路径，则保留更短的
            dist[u][v] = Math.min(dist[u][v], w);
            dist[v][u] = Math.min(dist[v][u], w);
        }

        // 4. 应用 Floyd-Warshall 算法
        // 依次将每个顶点 k 作为中转点
        for (int k = 1; k <= n; k++) {
            // 遍历所有可能的起点 i
            for (int i = 1; i <= n; i++) {
                // 遍历所有可能的终点 j
                for (int j = 1; j <= n; j++) {
                    // 如果从 i 到 k 和从 k 到 j 的路径都存在
                    if (dist[i][k] != INF && dist[k][j] != INF) {
                        // 尝试通过中转点 k 来缩短 i 到 j 的距离
                        dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                    }
                }
            }
        }

        // 5. 读取观景计划的数量 Q
        int q = sc.nextInt();

        // 6. 处理每个观景计划
        for (int i = 0; i < q; i++) {
            int start = sc.nextInt();
            int end = sc.nextInt();

            int result = dist[start][end];

            // 如果结果仍然是无穷大，说明两点不连通
            if (result == INF) {
                System.out.println(-1);
            } else {
                System.out.println(result);
            }
        }

        sc.close();
    }
}
