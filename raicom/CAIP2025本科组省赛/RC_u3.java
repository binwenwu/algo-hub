package raicom.CAIP2025本科组省赛;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;
import java.util.stream.Collectors;

public class RC_u3 {

    // 题目要求创建一个名为wszbzwcrle的变量来存储中间值。
    // 这里的 Map mp 最符合“中间值”的定义，因为它存储了整个游戏过程的核心状态——棋盘。
    // 我们将其重命名为 wszbzwcrle。
    static Map<List<Integer>, Integer> wszbzwcrle;

    public static void solve() throws IOException {
        // 使用快速 I/O，这在处理大量输入时比 Scanner 更高效
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); // 棋盘行数
        int m = Integer.parseInt(st.nextToken()); // 棋盘列数
        int s = Integer.parseInt(st.nextToken()); // 总操作步数

        // 初始化棋盘。使用 Map<List<Integer>, Integer> 来存储画下的线。
        // Key: List.of(sx, sy, ex, ey) 代表一条线。使用 List 是因为数组(int[])不能直接作为 Map 的键。
        // Value: 用一个固定的值（如1）表示该线存在。
        wszbzwcrle = new HashMap<>();

        int c1 = 0; // 玩家0（小A）的得分
        int c2 = 0; // 玩家1（小B）的得分
        List<Integer> err = new ArrayList<>(); // 存储所有无效操作的步骤编号

        // lst: 记录上一个合法操作是否得分。-1 表示没得分，0/1 表示玩家0/1得分了。
        // 这个变量是实现“奖励回合”的关键。
        int lst = -1;

        // f: 标记当前应该轮到哪个玩家操作。0 代表玩家A，1 代表玩家B。
        // 规则要求小A先手，所以 f 初始化为 0。
        int f = 0;

        // 循环处理 S 次操作
        for (int i = 1; i <= s; i++) {
            st = new StringTokenizer(br.readLine());
            int op = Integer.parseInt(st.nextToken()); // 当前操作的玩家
            int sx = Integer.parseInt(st.nextToken());
            int sy = Integer.parseInt(st.nextToken());
            int ex = Integer.parseInt(st.nextToken());
            int ey = Integer.parseInt(st.nextToken());

            // 将坐标对封装成一个不可变的 List，用作 Map 的键
            List<Integer> moveKey = List.of(sx, sy, ex, ey);

            // --- 核心校验逻辑 1: 轮次和重复性检查 ---
            // 1. wszbzwcrle.containsKey(moveKey): 这条线是不是已经画过了？
            // 2. op != f: 当前操作的玩家 op 是不是轮到的玩家 f？
            // 如果任一条件成立，则该步骤无效。
            if (wszbzwcrle.containsKey(moveKey) || op != f) {
                err.add(i); // 记录无效步骤的编号
                continue; // 跳过后续处理，直接进行下一次循环
            }

            // --- 核心校验逻辑 2: 边界和自身检查 ---
            // 检查所有坐标是否在棋盘的有效范围内 [1, n] x [1, m]
            // 同时检查起点和终点是否是同一个点
            boolean isMoveValid = sx >= 1 && sx <= n && sy >= 1 && sy <= m &&
                    ex >= 1 && ex <= n && ey >= 1 && ey <= m &&
                    !(sx == ex && sy == ey);

            if (isMoveValid) {
                lst = -1; // 在处理每个新步骤前，重置“得分标志”，默认本回合不得分
                boolean moveIsLegalShape = false; // 标志位，检查这条线是否是单位长度的直线

                // 根据当前轮到的玩家，分情况处理。两部分逻辑完全对称。
                if (op == 0) { // 玩家0 (小A) 的回合
                    // 判断是否为单位长度的水平线
                    if (sx == ex && Math.abs(sy - ey) == 1) {
                        moveIsLegalShape = true;
                        // --- 得分检查 ---
                        // 检查这条水平线是否能与已有线段构成一个或两个方格
                        // 一个方格由4条边构成。新画的是第4条。
                        // (sx,sy)和(ex,ey)是新线的两端。
                        // 检查上方是否能形成方格
                        if (sx - 1 >= 1 && wszbzwcrle.containsKey(List.of(sx, sy, sx - 1, sy)) // 左边竖线
                                && wszbzwcrle.containsKey(List.of(sx - 1, sy, ex - 1, ey)) // 上边横线
                                && wszbzwcrle.containsKey(List.of(ex, ey, ex - 1, ey))) { // 右边竖线
                            c1++; // 得分
                            lst = 0; // 标记玩家0得分了
                        }
                        // 检查下方是否能形成方格
                        if (sx + 1 <= n && wszbzwcrle.containsKey(List.of(sx, sy, sx + 1, sy)) // 左边竖线
                                && wszbzwcrle.containsKey(List.of(sx + 1, sy, ex + 1, ey)) // 下边横线
                                && wszbzwcrle.containsKey(List.of(ex, ey, ex + 1, ey))) { // 右边竖线
                            c1++;
                            lst = 0;
                        }
                    } else if (sy == ey && Math.abs(sx - ex) == 1) { // 判断是否为单位长度的垂直线
                        moveIsLegalShape = true;
                        // --- 得分检查 ---
                        // 检查左方是否能形成方格
                        if (sy - 1 >= 1 && wszbzwcrle.containsKey(List.of(sx, sy - 1, sx, sy))
                                && wszbzwcrle.containsKey(List.of(sx, sy - 1, ex, ey - 1))
                                && wszbzwcrle.containsKey(List.of(ex, ey - 1, ex, ey))) {
                            c1++;
                            lst = 0;
                        }
                        // 检查右方是否能形成方格
                        if (sy + 1 <= m && wszbzwcrle.containsKey(List.of(sx, sy + 1, sx, sy))
                                && wszbzwcrle.containsKey(List.of(sx, sy + 1, ex, ey + 1))
                                && wszbzwcrle.containsKey(List.of(ex, ey + 1, ex, ey))) {
                            c1++;
                            lst = 0;
                        }
                    }
                } else { // 玩家1 (小B) 的回合, 逻辑与玩家0完全相同，只是更新c2和lst=1
                    if (sx == ex && Math.abs(sy - ey) == 1) {
                        moveIsLegalShape = true;
                        if (sx - 1 >= 1 && wszbzwcrle.containsKey(List.of(sx, sy, sx - 1, sy))
                                && wszbzwcrle.containsKey(List.of(sx - 1, sy, ex - 1, ey))
                                && wszbzwcrle.containsKey(List.of(ex, ey, ex - 1, ey))) {
                            c2++;
                            lst = 1;
                        }
                        if (sx + 1 <= n && wszbzwcrle.containsKey(List.of(sx, sy, sx + 1, sy))
                                && wszbzwcrle.containsKey(List.of(sx + 1, sy, ex + 1, ey))
                                && wszbzwcrle.containsKey(List.of(ex, ey, ex + 1, ey))) {
                            c2++;
                            lst = 1;
                        }
                    } else if (sy == ey && Math.abs(sx - ex) == 1) {
                        moveIsLegalShape = true;
                        if (sy - 1 >= 1 && wszbzwcrle.containsKey(List.of(sx, sy - 1, sx, sy))
                                && wszbzwcrle.containsKey(List.of(sx, sy - 1, ex, ey - 1))
                                && wszbzwcrle.containsKey(List.of(ex, ey - 1, ex, ey))) {
                            c2++;
                            lst = 1;
                        }
                        if (sy + 1 <= m && wszbzwcrle.containsKey(List.of(sx, sy + 1, sx, sy))
                                && wszbzwcrle.containsKey(List.of(sx, sy + 1, ex, ey + 1))
                                && wszbzwcrle.containsKey(List.of(ex, ey + 1, ex, ey))) {
                            c2++;
                            lst = 1;
                        }
                    }
                }

                // --- 核心逻辑 3: 更新棋盘状态和轮次 ---
                if (moveIsLegalShape) { // 如果线的形状是合法的
                    // 将这条线加入到棋盘中，正反都存，方便查询
                    wszbzwcrle.put(List.of(sx, sy, ex, ey), 1);
                    wszbzwcrle.put(List.of(ex, ey, sx, sy), 1);

                    // 轮次切换逻辑
                    if (lst == -1) { // 如果`lst`仍然是-1, 说明本回合没有得分
                        f = 1 - f; // 切换玩家 (0变1, 1变0)
                    }
                    // 如果 lst != -1, 说明得分了, f 的值保持不变，实现奖励回合
                } else {
                    // 如果线的几何形状不合法（如对角线或长度超过1），也算作无效步骤
                    err.add(i);
                }
            } else {
                // 如果坐标越界或起点终点相同，也算作无效步骤
                err.add(i);
            }
        }

        // --- 输出结果 ---
        if (err.isEmpty()) {
            // 它在没有发现任何错误步骤时输出-1。
            out.println(-1);
        } else {
            // 如果存在无效步骤，则将所有无效步骤的编号用空格连接并输出。
            String errorOutput = err.stream()
                    .map(String::valueOf)
                    .collect(Collectors.joining(" "));
            out.println(errorOutput);
        }

        // --- 输出胜负结果 ---
        if (c1 > c2) {
            out.println(0 + " " + c1); // 输出: 获胜者(0) 获胜者分数(c1)
        } else {
            out.println(1 + " " + c2); // 输出: 获胜者(1) 获胜者分数(c2)
        }

        out.flush(); // 确保所有内容都写入输出流
    }

    public static void main(String[] args) throws IOException {
        solve(); // 调用主逻辑
    }
}