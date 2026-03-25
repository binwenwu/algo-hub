package codefun;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 将数组分成两个非空组 b 和 c，使得 b 的任意非空子集之积 不是 c 的任意非空子集之积的整数倍。
 *
 * 整体思路：
 * 把最小元素单独放 b，其余放 c。
 * 由于元素互不相同且 >= 1，c 中每个元素都严格大于 min，
 * 所以 c 的任何非空子集之积 > min = b 的唯一子集积，
 * 一个更大的数不可能整除一个更小的正整数，因此条件自动满足。
 */
public class _3818 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();
        while (T-- > 0) {
            int n = sc.nextInt();
            int[] a = new int[n];
            for (int i = 0; i < n; i++) {
                a[i] = sc.nextInt();
            }

            // 排序，最小元素放 b，其余放 c
            Arrays.sort(a);

            // 输出 b
            System.out.println(1);
            System.out.println(a[0]);

            // 输出 c
            System.out.println(n - 1);
            StringBuilder sb = new StringBuilder();
            for (int i = 1; i < n; i++) {
                if (i > 1) sb.append(' ');
                sb.append(a[i]);
            }
            System.out.println(sb);
        }

        sc.close();
    }
}
