package codefun;

import java.util.Scanner;

public class _3525 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(); // 云的总数
        int[] c = new int[n];
        for (int i = 0; i < n; i++) {
            c[i] = sc.nextInt(); // 读取每朵云的类型：0 为积云（安全），1 为雷云（危险）
        }
        int jumps = 0; // 跳跃次数计数器
        int i = 0; // 当前所在位置的云的索引，从0开始
        // 贪心算法：每次尽可能跳2步，因为跳2步比跳1步能减少总跳数
        // 只有当向前2步不是安全的（是雷云或超出范围）时，才跳1步
        while (i < n - 1) {
            // 检查是否可以跳2步：不越界且目标云是积云（0）
            if (i + 2 < n && c[i + 2] == 0) {
                i += 2; // 跳2步
            } else {
                i += 1; // 否则跳1步（题目保证一定可以到达终点）
            }
            jumps++; // 每跳一次，跳跃次数加1
        }
        System.out.println(jumps); // 输出最小跳跃次数
        sc.close(); // 关闭扫描器
    }
}