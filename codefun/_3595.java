package codefun;

import java.util.Scanner;

public class _3595 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();

        // 关键：即使使用 Scanner 读取，输出也必须使用 StringBuilder 缓冲
        // 否则 20 万次 System.out.print 会直接导致超时
        StringBuilder sb = new StringBuilder();

        while (T-- > 0) {
            int n = sc.nextInt();
            int m = sc.nextInt();

            // 策略复盘：
            // 1. 前 m+2 个数字降序排列： m+2, m+1, ..., 1 （这部分恰好产生 m 个谷值）
            for (int i = m + 2; i >= 1; i--) {
                sb.append(i).append(" ");
            }

            // 2. 剩下的数字升序排列： m+3, m+4, ..., n （这部分产生 0 个谷值）
            for (int i = m + 3; i <= n; i++) {
                sb.append(i).append(" ");
            }

            sb.append("\n"); // 每组数据结束后换行
        }

        // 循环结束后，一次性将拼接好的结果输出到控制台
        System.out.print(sb);

        sc.close();
    }
}
