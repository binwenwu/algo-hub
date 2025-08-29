package raicom.CAIP2025本科组国赛;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// 自己的解法，得分：19/20
public class RC_u2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        int Q = sc.nextInt();
        char[][] bag = new char[N + 1][M + 1];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                bag[i][j] = '.';
            }
        }

        for (int q = 0; q < Q; q++) {
            int n = sc.nextInt();
            int m = sc.nextInt();
            sc.nextLine();
            if (n > N || m > M) {
                System.out.println(-1 + " " + -1);
                continue;
            }

            char[][] thing = new char[n][m];
            int left = m - 1;
            int top = n - 1;
            for (int i = 0; i < n; i++) {
                String s = sc.nextLine();
                for (int j = 0; j < m; j++) {
                    char c = s.charAt(j);
                    thing[i][j] = c;
                    if (c == '*') {
                        left = j < left ? j : left;
                        top = i < top ? i : top;
                    }
                }
            }

            boolean find = true;

            int i = 1;
            int j = 1;

            out1: for (i = 1; i <= N; i++) {
                for (j = 1; j <= M; j++) {
                    find = true;
                    out2: for (int x = top; x < n; x++) {
                        for (int y = left; y < m; y++) {
                            if (thing[x][y] == '*') {
                                if (i + x - top < 1 || i + x - top > N || j + y - left < 1 || j + y - left > M) {
                                    find = false;
                                    break out2;
                                }
                                if (bag[i + x - top][j + y - left] == '*') {
                                    find = false;
                                    break out2;
                                }
                            }
                        }
                    }
                    if (find) {
                        break out1;
                    }
                }
            }

            if (!find) {
                System.out.println(-1 + " " + -1);
                continue;
            }

            for (int y = left; y < m; y++) {
                if (thing[top][y] == '*') {
                    System.out.println(i + " " + (j + y - left));
                    break;
                }
            }

            for (int x = top; x < n; x++) {
                for (int y = left; y < m; y++) {
                    if (thing[x][y] == '*') {
                        bag[i + x - top][j + y - left] = '*';
                    }
                }
            }

        }
        sc.close();
    }
}


/** 
 * 对每个物品，利用一个 List：relativeStarPositions 来存储每个物品相对于首方格的偏移信息
 * 然后循环体内就可以直接去遍历链表，而不是用2层循环进行遍历 
 */
class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt(); // Backpack rows
        int M = sc.nextInt(); // Backpack cols
        int Q = sc.nextInt(); // Number of items

        // 初始化背包
        char[][] backpack = new char[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                backpack[i][j] = '.';
            }
        }

        // 处理每个项目查询
        for (int q = 0; q < Q; q++) {
            int n = sc.nextInt(); // Item bounding box rows
            int m = sc.nextInt(); // Item bounding box cols

            List<int[]> starPositions = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                String row = sc.next();
                for (int j = 0; j < m; j++) {
                    if (row.charAt(j) == '*') {
                        starPositions.add(new int[] { i, j });
                    }
                }
            }

            boolean placed = false;
            // 问题描述所要求的中间变量

            if (starPositions.isEmpty()) {
                System.out.println("-1 -1");
                continue;
            }

            // Find the 首方格 and 计算物品别的位置相对首方格的偏移信息
            int[] firstStar = starPositions.get(0);
            List<int[]> relativeStarPositions = new ArrayList<>();
            for (int[] pos : starPositions) {
                relativeStarPositions.add(new int[] { pos[0] - firstStar[0], pos[1] - firstStar[1] });
            }

            int finalR = -1, finalC = -1;

            // 搜索最佳放置位置（最顶部，然后最左侧）
            // //循环试图将首方格放在背包[r][c]
            searchLoop: for (int r = 0; r < N; r++) {
                for (int c = 0; c < M; c++) {

                    // 检查第一颗星的目标位置是否可用
                    if (backpack[r][c] == '*') {
                        continue;
                    }

                    boolean canPlaceHere = true;
                    // 检查物品的所有其他部分是否合适
                    for (int[] relPos : relativeStarPositions) {
                        int checkR = r + relPos[0];
                        int checkC = c + relPos[1];

                        // 检查边界
                        if (checkR < 0 || checkR >= N || checkC < 0 || checkC >= M) {
                            canPlaceHere = false;
                            break;
                        }
                        // 检查是否碰撞
                        if (backpack[checkR][checkC] == '*') {
                            canPlaceHere = false;
                            break;
                        }
                    }

                    if (canPlaceHere) {
                        // 找到最佳位置，放置物品
                        placed = true;
                        finalR = r;
                        finalC = c;

                        for (int[] relPos : relativeStarPositions) {
                            backpack[r + relPos[0]][c + relPos[1]] = '*';
                        }

                        // Break out of the search loops
                        break searchLoop;
                    }
                }
            }

            if (placed) {
                System.out.println((finalR + 1) + " " + (finalC + 1));
            } else {
                System.out.println("-1 -1");
            }
        }
        sc.close();
    }
}
