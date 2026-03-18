package nowcoder.alibaba;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * https://www.nowcoder.com/exam/test/95338879/detail?pid=30440590
 */
public class _6 {
    /**
     * 流程：
     * 
     * 遍历每个物品 i
     * 计算它的 key（差分向量）
     * 同时计算它的“相反向量”（negKey）
     * 用 HashMap 统计：
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();

        int[][] arr = new int[n][k];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < k; j++) {
                arr[i][j] = sc.nextInt();
            }
        }

        Map<String, Integer> map = new HashMap<>();
        int res = 0;

        for (int i = 0; i < n; i++) {
            // 构造 key 和 negKey
            StringBuilder key = new StringBuilder();
            StringBuilder negKey = new StringBuilder();

            for (int j = 1; j < k; j++) {
                int diff = arr[i][j] - arr[i][0];
                key.append(diff).append("#");
                negKey.append(-diff).append("#");
            }

            String k1 = key.toString();
            String k2 = negKey.toString();

            // 查找是否存在互补
            res += map.getOrDefault(k2, 0);

            // 当前加入 map
            map.put(k1, map.getOrDefault(k1, 0) + 1);
        }

        System.out.println(res);

        sc.close();

    }
}
