package codefun;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class _4290 {
    // 贪心
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        while (T-- > 0) {
            int n = sc.nextInt();
            int m = sc.nextInt();

            long sum = (long) n * (n + 1) / 2;

            while (m-- > 0) {
                long k = sc.nextLong();

                if (k > sum) {
                    System.out.println(-1);
                    continue;
                }

                List<Integer> res = new ArrayList<>();

                // 贪心选
                for (int i = n; i >= 1; i--) {
                    if (i <= k) {
                        res.add(i);
                        k -= i;
                    }
                    if (k == 0)
                        break;
                }

                // 输出
                System.out.println(res.size());

                for (int i = res.size() - 1; i >= 0; i--) {
                    System.out.print(res.get(i) + " ");
                }
                System.out.println();
            }
        }

        sc.close();
    }
}
