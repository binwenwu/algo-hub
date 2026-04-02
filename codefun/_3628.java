package codefun;

import java.util.Scanner;

// 二分
public class _3628 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long n = sc.nextLong(), m = sc.nextLong(), k = sc.nextLong();

        long low = 1, high = m;
        while (low < high) {
            long mid = low + (high - low + 1) / 2;
            if (minTotal(mid, k - 1, n - k) <= m) {
                low = mid;
            } else {
                high = mid - 1;
            }
        }
        System.out.println(low);
        sc.close();
    }

    static long minTotal(long x, long left, long right) {
        return x + sideSum(left, x) + sideSum(right, x);
    }

    // cnt 个孩子，距离依次为 1..cnt，每人分到 max(1, x-d)，求最小总和
    static long sideSum(long cnt, long x) {
        if (cnt == 0)
            return 0;
        if (x - 1 >= cnt) { // 不够减
            return cnt * x - cnt * (cnt + 1) / 2;
        }
        return (x - 1) * x / 2 + cnt - x + 1; // 够减
    }
}
