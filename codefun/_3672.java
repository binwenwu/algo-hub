package codefun;

import java.util.*;

/**
 * 删边使所有连通块节点数为偶数
 *
 * 思路：
 * 1. 若 n 为奇数，无法分成若干偶数块，答案为 0。
 * 2. 以节点 1 为根 DFS，计算每个子树大小 sz[v]。
 *    对于非根节点 v，若 sz[v] 为偶数，则 v 到其父节点的边"可删"：
 *    删后 v 的子树大小为偶数，剩余部分 = n - sz[v] 也是偶数（因为 n 是偶数）。
 * 3. 设可删边数量为 cnt，从中选恰好 k 条，答案为 C(cnt, k) mod (10^9+7)。
 * 4. 用费马小定理求逆元来计算组合数。
 */
public class _3672 {
    static final long MOD = 1_000_000_007;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();

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
        sc.close();

        // n 为奇数时无解
        if (n % 2 == 1) {
            System.out.println(0);
            return;
        }

        // 用迭代 DFS 计算子树大小，避免递归栈溢出（n 最大 2×10^5）
        int[] sz = new int[n + 1];
        int[] parent = new int[n + 1];
        Arrays.fill(parent, -1);
        int[] order = new int[n]; // DFS 序
        boolean[] visited = new boolean[n + 1];
        int top = 0;

        // BFS 序（等价于 DFS 的拓扑序）用于从叶子向根累加子树大小
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(1);
        visited[1] = true;
        while (!stack.isEmpty()) {
            int u = stack.pop();
            order[top++] = u;
            for (int v : adj.get(u)) {
                if (!visited[v]) {
                    visited[v] = true;
                    parent[v] = u;
                    stack.push(v);
                }
            }
        }

        // 从后往前（叶 → 根）累加子树大小
        Arrays.fill(sz, 1);
        for (int i = top - 1; i >= 1; i--) {
            int v = order[i];
            sz[parent[v]] += sz[v];
        }

        // 统计可删边数量：非根节点 v，sz[v] 为偶数
        int cnt = 0;
        for (int v = 2; v <= n; v++) {
            if (sz[v] % 2 == 0) {
                cnt++;
            }
        }

        // 答案 = C(cnt, k)，若 k > cnt 则为 0
        System.out.println(comb(cnt, k));
    }

    // 组合数 C(n, k) mod MOD
    static long comb(int n, int k) {
        if (k < 0 || k > n) return 0;
        if (k == 0 || k == n) return 1;
        // C(n, k) = n! / (k! * (n-k)!)
        long[] fac = new long[n + 1];
        fac[0] = 1;
        for (int i = 1; i <= n; i++) {
            fac[i] = fac[i - 1] * i % MOD;
        }
        long denom = fac[k] * fac[n - k] % MOD;
        return fac[n] % MOD * modInverse(denom, MOD) % MOD;
    }

    // 费马小定理求逆元：a^(-1) = a^(p-2) mod p
    static long modInverse(long a, long mod) {
        return power(a, mod - 2, mod);
    }

    // 快速幂
    static long power(long base, long exp, long mod) {
        long result = 1;
        base %= mod;
        while (exp > 0) {
            if ((exp & 1) == 1) {
                result = result * base % mod;
            }
            exp >>= 1;
            base = base * base % mod;
        }
        return result;
    }
}
