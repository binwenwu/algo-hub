package codefun;

import java.util.Scanner;

public class _4288 {
    static final int MAX = 200000;

    // 动态维护数组 + 按约数统计
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int q = sc.nextInt();

        int[] a = new int[n + 1];
        int[] cnt = new int[MAX + 1];

        // 读入数组
        for (int i = 1; i <= n; i++) {
            a[i] = sc.nextInt();
            cnt[a[i]]++;
        }


        // 处理操作
        while (q-- > 0) {
            int op = sc.nextInt();

            if (op == 1) {
                int k = sc.nextInt();
                int ans = 0;

                // 枚举 k 的倍数
                for (int j = k; j <= MAX; j += k) {
                    ans += cnt[j];
                }

                System.out.println(ans);

            } else {
                int idx = sc.nextInt();
                int x = sc.nextInt();

                // 更新
                cnt[a[idx]]--;
                a[idx] = x;
                cnt[x]++;
            }
        }

        sc.close();
    }
}
