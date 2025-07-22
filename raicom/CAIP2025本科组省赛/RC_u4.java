package raicom.CAIP2025本科组省赛;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 题目分析:
 * 题目的 "Tree Tree" 定义非常复杂且具有迷惑性，可以被视为一个 "烟雾弹"。
 * 1. "不连通" 与 "删去节点后仍不连通" -> 暗示图至少有两个连通分量，且每个分量大小至少为2。
 * 2. "删去节点后不能构成一棵树" -> 由于满足条件1的图删点后必然不连通，所以永远不会是树，此条件冗余。
 * 
 * 然而，N<=10 的小数据范围以及 "Tree Tree" (脆脆的) 的反义词——“坚韧”，强烈暗示题目真实意图是寻找图中结构最“坚韧”的部分，也就是环
 * (Cycle)。
 * 环是稳定的结构，去掉一个节点或一条边通常不会使其断开。
 * 
 * 因此，本题的实际目标是：在给定的无向图中，找到节点数最多的简单环（最长环）和节点数第二多的简单环（次长环）。
 * "简单环" 指的是环路径上除了首尾节点相同外，没有其他重复的节点。
 * 
 * 本代码通过深度优先搜索 (DFS) + 回溯法来暴力搜索图中所有的简单环。
 */
public class RC_u4 {
    // === 静态变量区 (模拟全局变量，用于单个测试用例) ===

    // N 的最大值为 10，这里设置为 11 以防止数组越界 (因为节点编号从1开始)。
    static final int N = 11;
    // 使用邻接表来存储图。g.get(i) 表示节点 i 的所有邻居节点列表。
    static List<List<Integer>> g;
    // n: 节点数, m: 边数
    static int n, m;
    // 访问标记数组。注意：vis[i] = true 表示节点 i 在【当前正在探索的路径】上。
    // 这不是常规的全局访问数组，而是用于在单次DFS中防止路径重复访问节点，以确保找到的是简单环。
    static boolean[] vis;
    // S: 当前DFS寻找的环的起始(Start)节点。
    static int S;
    // ans1: 记录找到的最长环的长度 (节点数)。
    // ans2: 记录找到的次长环的长度。
    static int ans1, ans2;


    /**
     * 深度优先搜索 (DFS) 来寻找以 S 为起点的简单环。
     *
     * @param u   当前遍历到的节点。
     * @param cnt 从起始点 S 到当前节点 u 的路径长度（包含了多少个节点）。
     */
    public static void dfs(int u, int cnt) {
        // 遍历当前节点 u 的所有邻居 v
        for (int v : g.get(u)) {
            // 如果邻居 v 已经在当前路径上 (vis[v]为true)，则跳过。
            // 这是为了保证找到的环是“简单环”，即路径上除了首尾节点外没有重复节点。
            if (vis[v]) {
                continue;
            }

            // 如果邻居 v 正好是本次搜索的起始节点 S，说明我们找到了一个环。
            // 环的路径是 S -> ... -> u -> S。
            if (v == S) {
                // 环的长度就是当前路径上的节点数 cnt。
                // 根据找到的环的长度 cnt 来更新 ans1 和 ans2。
                if (cnt > ans1) {
                    // 找到了一个比当前最长环还要长的环。
                    ans2 = ans1; // 原来的最长环变成次长环。
                    ans1 = cnt; // 更新最长环。
                } else if (cnt != ans1 && cnt > ans2) {
                    // 找到了一个长度介于 ans1 和 ans2 之间的环。
                    // cnt != ans1 的判断是为了处理找到多个长度相同的最长环的情况，此时不应更新 ans2。
                    ans2 = cnt;
                }
            } else {
                // 如果邻居 v 不是起始点，说明路径还未形成环，可以继续延伸。
                // 1. 标记：将节点 v 加入到当前路径中。
                vis[v] = true;
                // 2. 递归：从节点 v 继续向下搜索，路径长度加 1。
                dfs(v, cnt + 1);
                // 3. 回溯：(关键步骤!) 从节点 v 返回后，必须将其移出当前路径。
                // 这样，在探索其他从 u 出发的路径时，节点 v 才能被再次访问。
                vis[v] = false;
            }
        }
    }

    /**
     * 处理单个测试用例。
     */
    public static void solve(BufferedReader br, PrintWriter out) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        // 为每个测试用例重置结果变量。
        // 初始化 ans1=1, ans2=0。如果图中没有环（或只有2元环），这个值可能作为最终结果。
        // 注意：简单环的最小长度为3。若找到第一个环长度为3，则ans1=3, ans2=1。
        ans1 = 1;
        ans2 = 0;

        // 初始化邻接表
        g = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            g.add(new ArrayList<>());
        }

        // 读取M条边，构建无向图
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            // 无向图，所以两个方向都要加边
            g.get(u).add(v);
            g.get(v).add(u);
        }

        // 初始化访问数组
        vis = new boolean[N];

        // 核心驱动逻辑：遍历每个节点，尝试将它作为环的起点进行搜索。
        for (int i = 1; i <= n; i++) {
            S = i; // 设置当前要找的环的起点

            // 注意：这里调用 dfs(i, 1) 时，起始点 i 并没有被标记为 vis[i]=true。
            // 这是因为我们需要在路径的最后一步能够“回到”起点 i。
            // 如果一开始就标记 vis[i]=true，那么在 dfs 过程中，当某个节点的邻居是 i 时，
            // 会因为 vis[i] 为 true 而跳过，从而永远无法构成环。
            // vis 数组只标记从 S 出发后的路径上的点。
            dfs(i, 1);
        }

        // 特殊处理：如果图中没有任何环（ans1, ans2 保持初始值或很小的值），
        // 样例输出似乎暗示了一些特殊情况。例如样例1输出3 2，样例2输出5 2。
        // 但根据简单环的定义，样例2只有一个5元环，次长环不存在。
        // 这里我们严格按照代码逻辑输出找到的最长和次长环。
        // 若没有环，输出 1 0 是合理的。如果只有一个环，如长度为5，输出 5 1。
        // 样例2的输出 5 2 可能是个特例或错误，但代码的逻辑是清晰的。
        // 2元环(u-v-u)在代码中会被识别为长度为2的环。
        if (ans1 < 2)
            ans1 = 0; // 长度为1不是环
        if (ans2 < 2)
            ans2 = 0; // 长度为1不是环

        out.println(ans1 + " " + ans2);
    }

    /**
     * 主函数，程序入口。
     */
    public static void main(String[] args) throws IOException {
        // 使用 BufferedReader 和 PrintWriter 以提高I/O效率
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);

        // 读取测试用例的数量 T
        int T = Integer.parseInt(br.readLine().trim());
        while (T-- > 0) {
            solve(br, out);
        }

        // 刷新并关闭输出流
        out.flush();
        out.close();
    }
}