import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class _109 {

    // 并查集内部类
    static class DisJoint {
        private int[] parent;

        // 初始化，每个节点的父节点是它自己
        public DisJoint(int n) {
            parent = new int[n + 1];
            for (int i = 1; i <= n; i++) {
                parent[i] = i;
            }
        }

        // 查找根节点（带路径压缩）
        public int find(int i) {
            if (parent[i] == i) {
                return i;
            }
            parent[i] = find(parent[i]);
            return parent[i];
        }

        // 合并两个集合
        public void join(int i, int j) {
            int rootI = find(i);
            int rootJ = find(j);
            if (rootI != rootJ) {
                parent[rootI] = rootJ;
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        // 存储所有边，以便后续处理
        List<int[]> edges = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            edges.add(new int[] { u, v });
        }
        sc.close();

        int[] result = findRedundantDirectedConnection(N, edges);
        System.out.println(result[0] + " " + result[1]);
    }

    public static int[] findRedundantDirectedConnection(int n, List<int[]> edges) {
        // parentInGraph[i] 记录节点 i 在图中的父节点
        int[] parentInGraph = new int[n + 1];
        int[] candidate1 = null;
        int[] candidate2 = null;

        // 第一次遍历：找出导致节点入度为2的边
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            if (parentInGraph[v] != 0) {
                // 找到入度为2的节点v
                // candidate1是第一条指向v的边
                candidate1 = new int[] { parentInGraph[v], v };
                // candidate2是第二条指向v的边（当前边）
                candidate2 = new int[] { u, v };
                break; // 题目保证最多只有一个节点入度>1
            }
            parentInGraph[v] = u;
        }

        // 第二次遍历：使用并查集检测环
        DisJoint uf = new DisJoint(n);
        int[] cycleEdge = null;

        for (int[] edge : edges) {
            // 如果存在入度为2的节点，我们暂时忽略第二条指向它的边
            // 看看剩下的图是否会构成环
            if (candidate2 != null && edge[0] == candidate2[0] && edge[1] == candidate2[1]) {
                continue;
            }

            int u = edge[0];
            int v = edge[1];
            int rootU = uf.find(u);
            int rootV = uf.find(v);

            // 如果u和v已经在一个连通分量里，说明加入(u,v)会形成环
            if (rootU == rootV) {
                cycleEdge = edge; // 记录下这条导致环的边
                // 注意：这里不break，因为题目要求输出最后出现的边
            } else {
                uf.join(u, v);
            }
        }

        // 最终决策
        if (candidate1 == null) {
            // Case A: 没有入度为2的节点，问题是简单的环。
            // cycleEdge就是我们要找的边。由于循环没有break，它就是最后那条构成环的边。
            return cycleEdge;
        } else {
            // Case B: 存在入度为2的节点
            if (cycleEdge != null) {
                // Sub-case B1: 忽略candidate2后仍然有环，说明环是由candidate1构成的
                return candidate1;
            } else {
                // Sub-case B2: 忽略candidate2后没有环，说明candidate2是多余的
                // 这也满足“输出最后出现的边”的要求，因为candidate2在输入中出现在candidate1之后
                return candidate2;
            }
        }
    }
}
