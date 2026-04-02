package codefun;

import java.util.*;

public class _3596 {
    static int[] s;
    static List<List<Integer>> adj;
    static int[][] dp;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(), k = sc.nextInt();
        s = new int[n + 1];
        for (int i = 1; i <= n; i++) s[i] = sc.nextInt();
        adj = new ArrayList<>();
        for (int i = 0; i <= n; i++) adj.add(new ArrayList<>());
        for (int i = 0; i < n - 1; i++) {
            int u = sc.nextInt(), v = sc.nextInt();
            adj.get(u).add(v);
            adj.get(v).add(u);
        }
        int lo = 0, hi = 100;
        while (lo < hi) {
            int mid = (lo + hi) >>> 1;
            if (check(n, mid, k)) hi = mid;
            else lo = mid + 1;
        }
        System.out.println(lo);
        sc.close();
    }

    static boolean check(int n, int c, int k) {
        dp = new int[n + 1][101];
        dfs(1, -1, c);
        int res = Integer.MAX_VALUE;
        for (int v = 0; v <= 100; v++) res = Math.min(res, dp[1][v]);
        return res <= k;
    }

    static void dfs(int u, int par, int c) {
        for (int v = 0; v <= 100; v++) dp[u][v] = v != s[u] ? 1 : 0;
        for (int ch : adj.get(u)) {
            if (ch == par) continue;
            dfs(ch, u, c);
            int[] best = new int[101];
            for (int v = 0; v <= 100; v++) {
                best[v] = Integer.MAX_VALUE;
                for (int cv = Math.max(0, v - c); cv <= Math.min(100, v + c); cv++) {
                    best[v] = Math.min(best[v], dp[ch][cv]);
                }
            }
            for (int v = 0; v <= 100; v++) dp[u][v] += best[v];
        }
    }
}
