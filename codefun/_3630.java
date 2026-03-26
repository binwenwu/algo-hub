package codefun;

import java.util.*;

// 逐轮模拟
// 消消乐模拟：H×5 网格，水平连续 3+ 相同数字消除，石头下落，重复直到无可消除
// 求所有被消除石头的数字之和
public class _3630 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        while (T-- > 0) {
            int H = sc.nextInt();
            int[][] graph = new int[H][5];
            int total = 0;
            for (int i = 0; i < H; i++) {
                for (int j = 0; j < 5; j++) {
                    graph[i][j] = sc.nextInt();
                }
            }
            while (true) {
                boolean has = false;
                for (int i = 0; i < H; i++) {
                    int left = 0;
                    while (left < 5) {
                        if (graph[i][left] == 0) {
                            left++;
                        } else {
                            int right = left;
                            while (right < 5 && graph[i][right] == graph[i][left]) {
                                right++;
                            }
                            if (right - left >= 3) {
                                has = true;
                                total += (graph[i][left] * (right - left));
                                for (int j = left; j < right; j++) {
                                    graph[i][j] = 0;
                                }
                                break;
                            } else {
                                left = right;
                            }
                        }
                    }
                }
                if (!has) {
                    break;
                }

                for (int i = 0; i < 5; i++) {
                    int slow = H - 1;
                    int fast = H - 1;
                    while (fast >= 0) {
                        if (graph[fast][i] != 0) {
                            int temp = graph[slow][i];
                            graph[slow][i] = graph[fast][i];
                            graph[fast][i] = temp;
                            slow--;
                            fast--;
                        } else {
                            fast--;
                        }
                    }
                }
            }
            System.out.println(total);

        }
        sc.close();
    }
}
