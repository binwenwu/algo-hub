package codefun;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class _3526 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(); // 积木总数，编号 1~n
        int m = sc.nextInt(); // 目标积木数量
        int[] target = new int[m]; // 目标堆叠区状态（从底到顶）
        for (int i = 0; i < m; i++) {
            target[i] = sc.nextInt();
        }

        List<String> ops = new ArrayList<>();
        int targetIdx = 0; // 指向目标数组中下一个需要匹配的位置

        // 按顺序从预备区取积木 1, 2, ..., n
        for (int block = 1; block <= n && targetIdx < m; block++) {
            // 每取一块积木都是一次 In 操作
            ops.add("In");

            if (block == target[targetIdx]) {
                // 当前积木正是目标序列中需要的，保留在栈中，匹配下一个目标
                targetIdx++;
            } else {
                // 当前积木不需要，立即弹出丢弃
                ops.add("Out");
            }
        }

        System.out.println(String.join(",", ops));
        sc.close();
    }
}
