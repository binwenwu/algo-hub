package codefun;

import java.io.PrintWriter;
import java.util.Scanner;

/**
 * 整体思路：预处理 + 动态规划
 *
 * 题意：给定正整数 x，将其分解为若干个大于 1 的正整数之积（p1 * p2 * ... * pk = x），
 * 使得各部分的"权值"（即正因子个数 τ(pi)）之和最大。
 *
 * 1. 预处理约数个数 tau[i]：
 * - 用倍数筛法（类似埃氏筛），对每个 i，将 i 的所有倍数 j 的 tau[j]++。
 * - 时间复杂度 O(N log N)，得到 1~MAX_X 每个数的因子个数。
 *
 * 2. 动态规划求最优拆分 dp[i]：
 * - dp[i] 表示整数 i 在最优拆分下的最大权值和。
 * - 初始值：dp[i] = tau[i]（不拆分，直接取自身的因子个数）。
 * - 转移：枚举 i 的所有因子对 (a, b)，其中 a * b = i 且 a > 1, b > 1，
 * 则 dp[i] = max(dp[i], dp[a] + dp[b])。
 * - 由于 dp[a] 和 dp[b] 已经是各自的最优解，递推天然包含了多次拆分的情况。
 * - 从小到大递推，保证子问题已求解。
 *
 * 3. 查询：O(1) 直接输出 dp[x]。
 *
 * 关键观察：拆分成质数之积时，每个质数 p 的 τ(p) = 2，
 * 因此答案的下界是 2 * (质因子个数之和)。DP 保证找到全局最优。
 */
public class _4231 {
    // 题目给出 x 的最大值为 2 * 10^5
    static final int MAX_X = 200000;

    // tau[i] 存储整数 i 的约数个数
    static int[] tau = new int[MAX_X + 1];

    // dp[i] 存储整数 i 经过最优拆解后的最大权值和
    static int[] dp = new int[MAX_X + 1];

    public static void main(String[] args) {

        // --- 预处理阶段开始 ---

        // 1. 预处理出 1 到 MAX_X 所有数字的正因子数量 tau[i]
        // 采用倍数筛法，时间复杂度 O(N log N)
        for (int i = 1; i <= MAX_X; i++) {
            for (int j = i; j <= MAX_X; j += i) {
                tau[j]++;
            }
        }

        // 2. 动态规划计算最优权值和 dp[i]
        // 从小到大计算，保证计算后面的数值时，前面的状态已经是最优的
        for (int i = 2; i <= MAX_X; i++) {
            // 初始策略：不拆分自身
            dp[i] = tau[i];

            // 尝试将 i 拆分为两个大于 1 的正整数 a 和 b (a * b = i)
            // 只需要枚举到 sqrt(i) 即可找到所有因子对，因为继续拆分得到的因子会重复，如 6 = 2*3 = 3*2
            for (int j = 2; j * j <= i; j++) {
                if (i % j == 0) {
                    int a = j;
                    int b = i / j;

                    // 转移方程：当前最优解 vs 拆分后的两部分的最优解之和
                    dp[i] = Math.max(dp[i], dp[a] + dp[b]);

                }
            }
        }
        // --- 预处理阶段结束 ---

        // --- 处理输入查询 ---
        Scanner sc = new Scanner(System.in);
        // 使用 PrintWriter 加速输出，避免在大规模循环中 println 导致 TLE
        PrintWriter out = new PrintWriter(System.out);

        if (sc.hasNextInt()) {
            int t = sc.nextInt();
            while (t-- > 0) {
                int x = sc.nextInt();
                // 预处理过以后，每次查询只需 O(1) 的时间
                out.println(dp[x]);
            }
        }

        out.flush(); // 刷新输出流，保证内容全部打印
        sc.close();
    }
}
