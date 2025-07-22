package raicom.CAIP2025本科组省赛;

import java.util.Scanner;

public class RC_u1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 读取参会者总数 N
        int n = sc.nextInt();

        // 循环处理 N 位参会者的信息
        for (int i = 0; i < n; i++) {
            int m = sc.nextInt(); // 月份
            int d = sc.nextInt(); // 日期
            int c = sc.nextInt(); // 缴纳的金额

            // 1. 首先检查是否超过了截止日期 (7月11日)
            // 如果月份大于7，或者月份是7但日期大于11，则为太迟
            if (m > 7 || (m == 7 && d > 11)) {
                System.out.println("Too late!");
                // 跳过当前循环，处理下一位参会者
                continue;
            }

            // 按照题目要求，创建名为 wszbzwcrle 的变量来存储中间值
            // 我们用它来存储应缴的正确金额
            int wszbzwcrle;

            // 2. 如果没有太迟，则判断应缴金额
            // 早鸟期：6月20日及以前
            if (m < 6 || (m == 6 && d <= 20)) {
                wszbzwcrle = 1800;
            } else {
                // 标准期：6月20日之后，7月11日及以前
                wszbzwcrle = 2000;
            }

            // 3. 比较实际缴纳金额与应缴金额
            if (c < wszbzwcrle) {
                System.out.println("Need more!");
            } else if (c > wszbzwcrle) {
                System.out.println("^_^");
            } else { // c == wszbzwcrle
                System.out.println("Ok!");
            }
        }

        // 关闭扫描器
        sc.close();
    }
}
