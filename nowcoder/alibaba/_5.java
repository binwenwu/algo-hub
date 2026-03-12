package nowcoder.alibaba;

import java.util.Arrays;
import java.util.Scanner;

/**
 * https://www.nowcoder.com/exam/test/95052764/detail?pid=30440638
 */
public class _5 {
    // 用来存储每个员工的能力
    static class Node {
        int A; // 推理能力
        int B; // 阅读能力
    }

    static int n;
    static Node[] arr;

    /**
     * 判定函数：是否存在一对员工 i,j
     * 使得：
     * Ai + Aj ≥ T
     * Bi + Bj ≥ T
     */
    static boolean check(int T) {

        // 双指针从数组右侧开始
        int j = n - 1;

        // 当前满足 Aj ≥ T-Ai 的员工里，最大的 Bj
        int maxB = -1;

        // 枚举员工 i
        for (int i = 0; i < n; i++) {

            /*
             * 当 arr[j].A ≥ T - arr[i].A 时
             * 说明：
             * Ai + Aj ≥ T
             * 因此 j 可以作为候选人
             */
            while (j >= 0 && arr[j].A >= T - arr[i].A) {

                // 更新这些候选人中的最大 B
                maxB = Math.max(maxB, arr[j].B);

                j--;
            }

            /*
             * 如果存在某个 j 满足：
             * Bj ≥ T - Bi
             * 
             * 那么：
             * Bi + Bj ≥ T
             * 
             * 同时上面已经保证：
             * Ai + Aj ≥ T
             * 
             * 因此找到合法员工对
             */
            if (maxB >= T - arr[i].B) {
                return true;
            }
        }

        // 没找到合法员工对
        return false;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();

        arr = new Node[n];

        // 读入员工能力
        for (int i = 0; i < n; i++) {
            arr[i] = new Node();
            arr[i].A = sc.nextInt();
            arr[i].B = sc.nextInt();
        }

        /*
         * 按推理能力 A 排序
         * 这样我们就可以利用单调性
         * 用双指针快速找到 Ai + Aj ≥ T 的员工
         */
        Arrays.sort(arr, (a, b) -> a.A - b.A);

        // 二分答案范围
        int l = 0;
        int r = 2000000000;

        int ans = 0;

        while (l <= r) {

            int mid = (l + r) / 2;

            // 如果存在员工对满足 ≥ mid
            if (check(mid)) {

                ans = mid;

                // 尝试更大的答案
                l = mid + 1;

            } else {

                // mid 不可行，缩小范围
                r = mid - 1;
            }
        }

        /*
         * 原题答案是：
         * min((Ai+Aj)/2 , (Bi+Bj)/2)
         * 
         * 我们求的是：
         * min(Ai+Aj , Bi+Bj)
         * 所以最后需要 /2
         */
        System.out.printf("%.1f\n", ans / 2.0);

        sc.close();
    }
}
