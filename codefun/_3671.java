package codefun;

import java.util.*;

/**
 * 构造数组使异或对和最小
 *
 * 思路（构造 + 贪心）：
 * 要让所有数对的异或和尽可能小，就需要让这些数在二进制表示上尽可能相似。
 * 异或运算中，相同位为 0，不同位为 1，因此高位相同、只在低位有差异的数对异或值最小。
 *
 * 构造方法：找到最小的 2 的幂 P 使得 P >= n，然后取连续的 n 个数 [P, P+1, ..., P+n-1]。
 * - 这 n 个数的最高位都相同（都是 P 对应的那一位为 1）
 * - 低位差异被限制在 [0, n-1] 范围内，保证异或值尽可能小
 * - 元素天然两两不同
 */
public class _3671 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        // 找最小的 2 的幂 P >= n
        int P = 1;
        while (P < n) P <<= 1;

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            if (i > 0) sb.append(' ');
            sb.append(P + i);
        }
        System.out.println(sb);
        sc.close();
    }
}
