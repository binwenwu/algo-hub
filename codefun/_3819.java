package codefun;

import java.util.Scanner;

/**
 * 整体思路：
 * 1. 字符串 s 是交替的 "1010..."，所有相邻字符都不同。
 * 2. 关键性质：删除任何一对相邻不同字符后，新串仍然保持完全交替。
 *    因为 s_{i-1} 和 s_{i+2} 经过 3 次取反后仍然不同。
 * 3. 因此，第 k 步操作时（k 从 0 开始），当前串长为 n-2k，
 *    可选位置为 1 到 n-2k-1（对应固定数组 a[1..n-2k-1]，即 0-indexed a[0..n-2k-2]）。
 * 4. 每一步的选择互相独立（不影响后续可选范围），所以贪心即可：
 *    - 最大化：每步选当前可选范围的最大值
 *    - 最小化：每步选当前可选范围的最小值
 * 5. 预处理前缀最大值和前缀最小值数组，O(n) 求解。
 */
public class _3819 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        while (T-- > 0) {
            int n = sc.nextInt();
            int m = n / 2; // 操作次数
            int[] a = new int[n - 1];
            for (int i = 0; i < n - 1; i++) {
                a[i] = sc.nextInt();
            }

            // 预处理前缀最大值和前缀最小值（0-indexed）
            // prefixMax[j] = max(a[0..j]), prefixMin[j] = min(a[0..j])
            long[] prefixMax = new long[n - 1];
            long[] prefixMin = new long[n - 1];
            prefixMax[0] = a[0];
            prefixMin[0] = a[0];
            for (int i = 1; i < n - 1; i++) {
                prefixMax[i] = Math.max(prefixMax[i - 1], a[i]);
                prefixMin[i] = Math.min(prefixMin[i - 1], a[i]);
            }

            // 第 k 步（k=0..m-1）可选范围为 a[0..n-2-2k]
            // 下标序列：n-2, n-4, ..., 依次递减 2
            long sumMax = 0, sumMin = 0;
            for (int k = 0; k < m; k++) {
                int idx = n - 2 - 2 * k; // 可选范围的右端点（0-indexed）
                sumMax += prefixMax[idx];
                sumMin += prefixMin[idx];
            }

            System.out.println(sumMax + " " + sumMin);
        }
        sc.close();
    }
}
