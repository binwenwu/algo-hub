import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class _99 {
    static int[][] directions = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } }; // 上, 下, 左, 右
    static int[][] island;
    static boolean[][] visted;

    private static void dfs(int x, int y) {
        for (int i = 0; i < 4; i++) {
            int nextX = x + directions[i][0];
            int nextY = y + directions[i][1];
            if (nextX < 0 || nextX >= island.length || nextY < 0 || nextY >= island[0].length) {
                continue;
            }
            if (!visted[nextX][nextY] && island[nextX][nextY] == 1) {
                visted[nextX][nextY] = true;
                dfs(nextX, nextY);
            }
        }
    }

    private static void bfs(int x, int y) {
        Queue<int[]> queue = new LinkedList<int[]>();// 定义坐标队列，没有现成的pair类，在下面自定义了
        queue.add(new int[]{x, y});
        visted[x][y] = true;// 遇到入队直接标记为优先，
        // 否则出队时才标记的话会导致重复访问，比如下方节点会在右下顺序的时候被第二次访问入队
        while (!queue.isEmpty()) {
            int curX = queue.peek()[0];
            int curY = queue.poll()[1];// 当前横纵坐标
            for (int i = 0; i < 4; i++) {
                // 顺时针遍历新节点next，下面记录坐标
                int nextX = curX + directions[i][0];
                int nextY = curY + directions[i][1];
                if (nextX < 0 || nextX >= island.length || nextY < 0 || nextY >= island[0].length) {
                    continue;
                } // 去除越界部分
                if (!visted[nextX][nextY] && island[nextX][nextY] == 1) {
                    queue.add(new int[]{nextX, nextY});
                    visted[nextX][nextY] = true;// 逻辑同上
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        island = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                island[i][j] = sc.nextInt();
            }
        }
        visted = new boolean[n][m];

        int result = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!visted[i][j] && island[i][j] == 1) {
                    result++;
                    visted[i][j] = true;
                    dfs(i, j);
                    // bfs(i, j);
                }
            }
        }

        System.out.println(result);

        sc.close();
    }
}
