package raicom.CAIP2024本科组省赛;

import java.util.*;

public class RC_u3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        sc.nextLine(); // 消耗掉换行符

        // 使用你原来的方法，用一个-padded的二维数组来简化边界处理（这样就避免了越界的判断），这很不错
        char[][] grid = new char[N + 2][M + 2];
        for (int i = 1; i <= N; i++) {
            String line = sc.nextLine();
            for (int j = 1; j <= M; j++) {
                grid[i][j] = line.charAt(j - 1);
            }
        }

        // 步骤 1: 找到那个唯一不对劲的'w'
        int problemR = -1, problemC = -1; // 用-1作为未找到的标记

        outerLoop: for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                if (grid[i][j] == 'w') {
                    boolean hasHeater = false;
                    // 检查8个邻居
                    for (int dr = -1; dr <= 1; dr++) {
                        for (int dc = -1; dc <= 1; dc++) {
                            // 跳过中心点本身
                            if (dr == 0 && dc == 0)
                                continue;
                            if (grid[i + dr][j + dc] == 'm') {
                                hasHeater = true;
                                break; // 找到了暖炉，这个'w'是正常的
                            }
                        }
                        if (hasHeater)
                            break; // 跳出外层邻居循环
                    }

                    // 如果检查完8个邻居都没有暖炉，这就是我们要找的那个'w'
                    if (!hasHeater) {
                        problemR = i;
                        problemC = j;
                        break outerLoop; // 题目保证最多一个，找到就可以退出了
                    }
                }
            }
        }

        // 步骤 2: 如果没有找到问题水豚，说明初始状态合法，不需要加暖炉
        if (problemR == -1) {
            System.out.println("Too cold!");
            sc.close();
            return;
        }

        // 步骤 3: 寻找并测试可能的解决方案
        List<int[]> solutions = new ArrayList<>();
        // 遍历问题水豚(problemR, problemC)的8个邻居
        for (int dr = -1; dr <= 1; dr++) {
            for (int dc = -1; dc <= 1; dc++) {
                if (dr == 0 && dc == 0)
                    continue;

                int candidateR = problemR + dr; // 候选暖炉位置的行
                int candidateC = problemC + dc; // 候选暖炉位置的列

                // 候选位置必须是空格 '.'
                if (grid[candidateR][candidateC] == '.') {
                    // **核心修正**: 检查在这个位置放暖炉是否会产生新问题
                    boolean isSafe = true;
                    // 检查候选位置(candidateR, candidateC)的所有8个邻居
                    for (int ndr = -1; ndr <= 1; ndr++) {
                        for (int ndc = -1; ndc <= 1; ndc++) {
                            if (ndr == 0 && ndc == 0)
                                continue;

                            int neighborR = candidateR + ndr;
                            int neighborC = candidateC + ndc;

                            if (grid[neighborR][neighborC] == 'c') {
                                isSafe = false; // 如果邻居有'c'，则此位置不安全
                                break;
                            }
                        }
                        if (!isSafe)
                            break;
                    }

                    // 如果这个位置是安全的，就加入解决方案列表
                    if (isSafe) {
                        solutions.add(new int[] { candidateR, candidateC });
                    }
                }
            }
        }

        // 步骤 4: 输出结果
        if (solutions.isEmpty()) {
            System.out.println("Too cold!");
        } else {
            // 按要求排序：先按行从小到大，行相同则按列从小到大
            solutions.sort((a, b) -> {
                if (a[0] != b[0]) {
                    return a[0] - b[0];
                } else {
                    return a[1] - b[1];
                }
            });

            for (int[] sol : solutions) {
                System.out.println(sol[0] + " " + sol[1]);
            }
        }

        sc.close();
    }
}