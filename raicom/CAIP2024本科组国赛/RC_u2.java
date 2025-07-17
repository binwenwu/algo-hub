package raicom.CAIP2024本科组国赛;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RC_u2 {

    // 使用一个静态数组来存储排名到分数的映射，方便查询
    // 索引0是占位符，索引1对应第1名，以此类推
    private static final int[] RANK_POINTS = {
            0, 25, 21, 18, 16, 15, 14, 13, 12, 11, 10,
            9, 8, 7, 6, 5, 4, 3, 2, 1, 0
    };

    // 自定义一个类来封装队伍信息，便于排序
    static class Team {
        int id;
        int score;

        public Team(int id, int score) {
            this.id = id;
            this.score = score;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt(); // 读取比赛轮数

        // 数组大小为31， чтобы我们可以直接使用队伍编号1-30作为索引
        int[] totalScores = new int[31];
        // 记录哪些队伍实际参加了比赛
        boolean[] participated = new boolean[31];

        // 循环处理N轮比赛
        for (int i = 0; i < N; i++) {
            // 每轮有20支队伍
            for (int j = 0; j < 20; j++) {
                int teamId = sc.nextInt();
                int rank = sc.nextInt();

                // 累加分数
                totalScores[teamId] += RANK_POINTS[rank];
                // 标记该队伍已参赛
                participated[teamId] = true;
            }
        }
        sc.close();

        // 将参赛队伍的数据整理到List中，以便排序
        List<Team> teams = new ArrayList<>();
        for (int i = 1; i <= 30; i++) {
            if (participated[i]) {
                teams.add(new Team(i, totalScores[i]));
            }
        }

        // 使用Lambda表达式进行自定义排序
        // 1. 按分数降序
        // 2. 如果分数相同，按队伍ID升序
        teams.sort((t1, t2) -> {
            if (t1.score != t2.score) {
                // 分数不同，按分数降序排列 (t2 - t1)
                return t2.score - t1.score;
            } else {
                // 分数相同，按ID升序排列 (t1 - t2)
                return t1.id - t2.id;
            }
        });

        // 输出最终排序结果
        for (Team team : teams) {
            System.out.println(team.id + " " + team.score);
        }
    }
}
