package raicom.CAIP2025本科组省赛;

import java.util.Scanner;

public class RC_u2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // 读取测试组数 T
        int T = sc.nextInt();

        // 循环处理每组测试数据
        while (T-- > 0) {
            // 读取总场次 N 和第二名的总分 S
            int N = sc.nextInt();
            int S = sc.nextInt();

            int firstPlaceCount = 0;

            // 按照题目要求，创建名为 wszbzwcrle 的变量来存储程序中间值。
            // 我们用它来存储 DreamTear 的总得分。
            int wszbzwcrle = 0;

            // 循环读取 N 场比赛的数据
            for (int i = 0; i < N; i++) {
                int rank = sc.nextInt();
                int score = sc.nextInt();

                // 统计获得第一名的场次
                if (rank == 1) {
                    firstPlaceCount++;
                }
                // 累加 DreamTear 的总得分到 wszbzwcrle
                wszbzwcrle += score;
            }

            // --- 开始判断两个奖励分条件 ---

            // 1. 判断是否能获得“场次第一”奖励分
            // 注意：必须使用浮点数除法，否则整数除法可能导致错误结果 (例如 3 / 6 = 0)
            int bonus1_result = 0;
            if ((double) firstPlaceCount / N >= 0.5) {
                bonus1_result = 1;
            }

            // 2. 判断是否能获得“领先分数”奖励分
            int bonus2_result = 0;
            if (wszbzwcrle - S >= 50) {
                bonus2_result = 1;
            }

            // 输出结果，用一个空格隔开
            System.out.println(bonus1_result + " " + bonus2_result);
        }

        sc.close();
    }
}
