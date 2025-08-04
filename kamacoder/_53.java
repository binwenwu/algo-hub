import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

// Prim 算法
public class _53 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        final int MAX_VALUE = 10001;
        int V = sc.nextInt();
        int E = sc.nextInt();
        boolean[] isInTree = new boolean[V + 1];
        int[] minTree = new int[V + 1];
        Arrays.fill(minTree, MAX_VALUE);
        int[][] graph = new int[V + 1][V + 1];
        int[] parent = new int[V + 1];
        Arrays.fill(parent, -1);
        for (int i = 0; i <= V; i++) {
            Arrays.fill(graph[i], MAX_VALUE);
        }

        for (int i = 0; i < E; i++) {
            int p1 = sc.nextInt();
            int p2 = sc.nextInt();
            int value = sc.nextInt();
            graph[p1][p2] = value;
            graph[p2][p1] = value;
        }

        sc.close();

        // 0. 设置一个起始点
        int totalValue = 0;
        minTree[1] = 0;

        for (int i = 1; i <= V; i++) {
            // 1. 找到距离最小生成树最近的节点
            int curr = -1;
            int minValue = MAX_VALUE;
            for (int j = 1; j <= V; j++) {
                if (!isInTree[j] && minTree[j] < minValue) {
                    curr = j;
                    minValue = minTree[j];
                }
            }
            if (curr == -1) {
                break;
            }

            // 2. 将其加入最小生成树，并累加距离
            isInTree[curr] = true;
            totalValue = totalValue + minValue;

            // 3. 更新最小生成树
            for (int j = 1; j <= V; j++) {
                if (!isInTree[j] && graph[curr][j] < minTree[j]) {
                    minTree[j] = graph[curr][j];
                    parent[j] = curr;
                }
            }

        }
        System.out.println(totalValue);

        System.out.println("最小生成树：");
        for (int i = 1; i <= V; i++) {
            System.out.println(i + " -> " + parent[i]);
        }

    }
}

// kruskal 算法
class Main2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int V = sc.nextInt();
        int E = sc.nextInt();
        List<Edge> edges = new ArrayList<>();
        for (int i = 0; i < E; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            int value = sc.nextInt();
            edges.add(new Edge(u, v, value));
        }
        sc.close();

        // 1. 将所有边按照权重大小进行排序
        Collections.sort(edges, (e1, e2) -> {
            return e1.value - e2.value;
        });

        // 2. 初始化并查集
        DisJoint disJoint = new DisJoint(V + 1);

        int totalValue = 0;
        int totalEdge = 0;

        // 3. 将没有加入的边进行加入
        for (Edge edge : edges) {
            if (!disJoint.isSame(edge.u, edge.v)) {
                totalValue = totalValue + edge.value;
                disJoint.join(edge.u, edge.v);
                totalEdge = totalEdge + 1;
            }

            // 如果已经找到了 v-1 条边，可以提前退出
            if (totalEdge == V - 1) {
                break;
            }
        }
        System.out.println(totalValue);

    }
}

class Edge {
    int u;
    int v;
    int value;

    public Edge(int u, int v, int value) {
        this.u = u;
        this.v = v;
        this.value = value;
    }
}

class DisJoint {
    private int[] father;

    public DisJoint(int N) {
        father = new int[N];
        for (int i = 0; i < N; i++) {
            father[i] = i;
        }
    }

    public int find(int n) {
        return n == father[n] ? n : (father[n] = find(father[n]));
    }

    public void join(int n, int m) {
        n = find(n);
        m = find(m);
        if (n == m) {
            return;
        } else {
            father[m] = n;
        }
    }

    public boolean isSame(int n, int m) {
        return find(n) == find(m);
    }
}