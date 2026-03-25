package codefun;

import java.util.*;

/**
 * 题意：
 * - 一维网格长度 n，初始全白
 * - 前 m 秒，第 t 秒结束前手动将 a_t 染黑
 * - 每秒开始时，所有黑格同时向左右扩散 1 格
 * - t > m 后扩散继续但不再手动染
 * - 求最早全黑的时刻 T
 *
 * 思路：二分答案 T
 * - 种子 i 在时刻 i 放置于 a_i，在时刻 T(≥i) 覆盖区间 [a_i+i-T, a_i-i+T]
 * - 预先将种子按 (a_i+i) 排序，则左端点有序
 * - check 时 O(m) 扫描合并区间，判断是否覆盖 [1, n]
 */
public class _3649 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        StringBuilder sb = new StringBuilder();

        while (T-- > 0) {
            int n = sc.nextInt();
            int m = sc.nextInt();
            int[] a = new int[m];
            for (int i = 0; i < m; i++) {
                a[i] = sc.nextInt();
            }

            // seeds[i] = {a[i] + (i+1), a[i] - (i+1), i+1}
            // 即种子在第 (i+1) 秒放置于 a[i]
            // 在时刻 t 覆盖: 左端点 = (a[i]+(i+1)) - t, 右端点 = (a[i]-(i+1)) + t
            // 按 a[i]+(i+1) 排序 → 左端点有序
            int[][] seeds = new int[m][3];
            for (int i = 0; i < m; i++) {
                seeds[i][0] = a[i] + (i + 1); // key: 决定左端点排序
                seeds[i][1] = a[i] - (i + 1); // base: 决定右端点
                seeds[i][2] = i + 1; // 放置时刻
            }
            Arrays.sort(seeds, (x, y) -> x[0] - y[0]);

            // 二分答案 t
            // 下界: 0, 上界: n + m（一定够）
            long lo = 1, hi = (long) n + m;
            while (lo < hi) {
                long mid = (lo + hi) / 2;
                if (check(seeds, n, mid)) {
                    hi = mid;
                } else {
                    lo = mid + 1;
                }
            }
            sb.append(lo).append('\n');
        }

        System.out.print(sb);
        sc.close();
    }

    /**
     * 检查在时刻 t 时是否所有格子 [1, n] 都被覆盖
     */
    static boolean check(int[][] seeds, int n, long t) {
        // 当前已覆盖到的最右位置
        long covered = 0; // 表示 [1, covered] 已被覆盖

        for (int[] seed : seeds) {
            int time = seed[2]; // 放置时刻
            if (time > t)
                continue; // 这个种子还没放下

            long left = (long) seed[0] - t; // a_i + i - t
            long right = (long) seed[1] + t; // a_i - i + t

            // 裁剪到 [1, n]
            if (left > n || right < 1)
                continue;
            if (left < 1)
                left = 1;
            if (right > n)
                right = n;

            // 如果当前区间的左端点 > covered + 1，说明有间隙
            if (left > covered + 1) {
                return false;
            }
            // 更新 covered
            if (right > covered) {
                covered = right;
            }
        }

        return covered >= n;
    }
}

/**
 * 暴力 BFS 模拟法（仅适用于小数据，n 很大时会 TLE/MLE），类似 leetcode 腐烂的橘子的做法
 *
 * 思路：
 * - Set 存所有白色位置 {1, 2, ..., n}，代表还没被染色
 * - Queue 存当前刚刚被标记成黑色的位置
 * - 每秒：
 * 1. 取出队列中本轮所有黑格，向左右扩散（若邻居是白色则染黑）
 * 2. 手动将 a_t 染黑（若 t ≤ m）
 * 3. 若 Set 为空，全黑，返回当前时刻
 */
class Solution {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        StringBuilder sb = new StringBuilder();

        while (T-- > 0) {
            int n = sc.nextInt();
            int m = sc.nextInt();
            int[] a = new int[m];
            for (int i = 0; i < m; i++) {
                a[i] = sc.nextInt();
            }
            sb.append(solve(n, m, a)).append('\n');
        }

        System.out.print(sb);
        sc.close();
    }

    static int solve(int n, int m, int[] a) {
        // white: 存放所有还是白色的位置
        Set<Integer> white = new HashSet<>();
        for (int i = 1; i <= n; i++) {
            white.add(i);
        }

        // queue: 存放刚刚被染成黑色的位置（用于下一轮扩散）
        Queue<Integer> queue = new LinkedList<>();

        for (int t = 1;; t++) {
            // —— 第 t 秒开始：先扩散 ——
            // 取出本轮队列中的所有元素（上一轮新染黑的），向左右扩散
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int pos = queue.poll();
                // 检查左邻居
                if (pos - 1 >= 1 && white.contains(pos - 1)) {
                    white.remove(pos - 1);
                    queue.add(pos - 1);
                }
                // 检查右邻居
                if (pos + 1 <= n && white.contains(pos + 1)) {
                    white.remove(pos + 1);
                    queue.add(pos + 1);
                }
            }

            // —— 然后手动染色 a_t（若 t ≤ m）——
            if (t <= m) {
                int at = a[t - 1]; // 第 t 秒手动染的位置
                if (white.contains(at)) {
                    white.remove(at);
                    queue.add(at);
                }
            }

            // —— 判断是否全黑 ——
            if (white.isEmpty()) {
                return t;
            }
        }
    }
}
