package codefun;

import java.util.Scanner;

public class _4289 {

    static final long MOD = 998244353;

    public static void main(String[] args) {
        // 使用题目要求的 Scanner 进行输入
        Scanner sc = new Scanner(System.in);

        int t = sc.nextInt();
        while (t-- > 0) {
            long k = sc.nextLong();
            if (k == 1) {
                // k=1 只有数字 0 满足条件
                System.out.println(1);
            } else {
                // k>=2 时，等价于求斐波那契数列，计算矩阵快速幂
                long[][] res = power(k - 2);
                System.out.println(res[0][0]);
            }
        }
        sc.close();
    }

    // 2x2 矩阵乘法并取模
    static long[][] multiply(long[][] a, long[][] b) {
        long[][] c = new long[2][2];
        // 拿出一个a矩阵的元素a[i][k]，看看它能对c矩阵的哪些格子产生贡献，我把它全部分发出去
        for (int i = 0; i < 2; i++) {
            for (int k = 0; k < 2; k++) {
                if (a[i][k] == 0)
                    continue; // 意味着当前a矩阵元素对c矩阵没有任何贡献
                for (int j = 0; j < 2; j++) {
                    c[i][j] = (c[i][j] + a[i][k] * b[k][j]) % MOD;
                }
            }
        }
        return c;
    }

    // 矩阵快速幂
    static long[][] power(long p) {
        // 单位矩阵
        long[][] res = { { 1, 0 }, { 0, 1 } };
        // 转移矩阵
        long[][] base = { { 1, 1 }, { 1, 0 } };

        while (p > 0) {
            if ((p & 1) == 1) {
                res = multiply(res, base);
            }
            base = multiply(base, base);
            p >>= 1;
        }
        return res;
    }
}
