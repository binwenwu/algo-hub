package codefun;

import java.util.*;

// 贪心：每次配对一个奇数和一个偶数，直到无法配对为止
// 关键性质：奇 * 偶 = 奇，奇 * 奇 = 偶，偶 * 偶 = 偶
// 因此只有一奇一偶配对才能产生奇数乘积，最大配对数 = min(奇数个数, 偶数个数)
public class _3647 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        while (T-- > 0) {
            int n = sc.nextInt();
            int odd = 0;   // 奇数个数
            int even = 0;  // 偶数个数
            for (int i = 0; i < n; i++) {
                int x = sc.nextInt();
                if (x % 2 == 0)
                    even++;
                else
                    odd++;
            }
            // 全奇或全偶时无法配出奇数和，否则答案为较少一方的个数
            if (odd == 0 || even == 0)
                System.out.println(0);
            else
                System.out.println(Math.min(odd, even));
        }
        sc.close();
    }
}
