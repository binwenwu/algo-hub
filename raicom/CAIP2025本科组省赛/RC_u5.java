package raicom.CAIP2025本科组省赛;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class RC_u5 {

    // 静态变量区
    static int n, m; // 地图的行和列
    static char[][] grid; // 存储地图
    static int[][][] dist; // 核心数据结构: dist[x][y][s] 存储从终点到状态(x,y,s)的最短距离
    static int tx, ty; // 目标格的坐标

    // 方向数组，方便模拟滚动
    // dx[0..3] -> 下, 上, 右, 左
    static int[] dx = { 1, -1, 0, 0 };
    static int[] dy = { 0, 0, 1, -1 };

    // 状态类，用于在队列中存储状态信息
    static class State {
        int x, y, s; // s=0:竖立, s=1:水平, s=2:垂直

        State(int x, int y, int s) {
            this.x = x;
            this.y = y;
            this.s = s;
        }
    }

    // 检查某个位置是否在地图边界内
    static boolean inBounds(int x, int y) {
        return x >= 0 && x < n && y >= 0 && y < m;
    }

    // 反向BFS函数
    static void bfs() {
        // 1. 初始化
        dist = new int[n][m][3];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                Arrays.fill(dist[i][j], -1); // -1 表示不可达
            }
        }

        Queue<State> q = new ArrayDeque<>();

        // 2. 将终点状态入队
        dist[tx][ty][0] = 0; // 终点到自身的距离是0
        q.offer(new State(tx, ty, 0));

        // 3. BFS主循环
        while (!q.isEmpty()) {
            State curr = q.poll();
            int d = dist[curr.x][curr.y][curr.s];

            // === 从当前状态 curr 尝试滚动到其他状态 next ===

            if (curr.s == 0) { // 如果当前是竖立状态 (在 curr.x, curr.y)
                // 尝试向四个方向滚动
                // 滚向右边 -> 变为水平，占据 (curr.x, curr.y+1), (curr.x, curr.y+2)
                if (inBounds(curr.x, curr.y + 2) && grid[curr.x][curr.y + 1] != '0' && grid[curr.x][curr.y + 2] != '0'
                        && dist[curr.x][curr.y + 1][1] == -1) {
                    dist[curr.x][curr.y + 1][1] = d + 1;
                    q.offer(new State(curr.x, curr.y + 1, 1));
                }
                // 滚向左边 -> 变为水平，占据 (curr.x, curr.y-2), (curr.x, curr.y-1)
                if (inBounds(curr.x, curr.y - 2) && grid[curr.x][curr.y - 2] != '0' && grid[curr.x][curr.y - 1] != '0'
                        && dist[curr.x][curr.y - 2][1] == -1) {
                    dist[curr.x][curr.y - 2][1] = d + 1;
                    q.offer(new State(curr.x, curr.y - 2, 1));
                }
                // 滚向下边 -> 变为垂直，占据 (curr.x+1, curr.y), (curr.x+2, curr.y)
                if (inBounds(curr.x + 2, curr.y) && grid[curr.x + 1][curr.y] != '0' && grid[curr.x + 2][curr.y] != '0'
                        && dist[curr.x + 1][curr.y][2] == -1) {
                    dist[curr.x + 1][curr.y][2] = d + 1;
                    q.offer(new State(curr.x + 1, curr.y, 2));
                }
                // 滚向上边 -> 变为垂直，占据 (curr.x-2, curr.y), (curr.x-1, curr.y)
                if (inBounds(curr.x - 2, curr.y) && grid[curr.x - 2][curr.y] != '0' && grid[curr.x - 1][curr.y] != '0'
                        && dist[curr.x - 2][curr.y][2] == -1) {
                    dist[curr.x - 2][curr.y][2] = d + 1;
                    q.offer(new State(curr.x - 2, curr.y, 2));
                }

            } else if (curr.s == 1) { // 如果当前是水平状态 (在 curr.x, curr.y 和 curr.x, curr.y+1)
                // 滚向右边 -> 变为竖立，在 (curr.x, curr.y+2)
                if (inBounds(curr.x, curr.y + 2) && grid[curr.x][curr.y + 2] != '0' && grid[curr.x][curr.y + 2] != '2'
                        && dist[curr.x][curr.y + 2][0] == -1) {
                    dist[curr.x][curr.y + 2][0] = d + 1;
                    q.offer(new State(curr.x, curr.y + 2, 0));
                }
                // 滚向左边 -> 变为竖立，在 (curr.x, curr.y-1)
                if (inBounds(curr.x, curr.y - 1) && grid[curr.x][curr.y - 1] != '0' && grid[curr.x][curr.y - 1] != '2'
                        && dist[curr.x][curr.y - 1][0] == -1) {
                    dist[curr.x][curr.y - 1][0] = d + 1;
                    q.offer(new State(curr.x, curr.y - 1, 0));
                }
                // 滚向下边 -> 保持水平，在 (curr.x+1, curr.y), (curr.x+1, curr.y+1)
                if (inBounds(curr.x + 1, curr.y + 1) && grid[curr.x + 1][curr.y] != '0'
                        && grid[curr.x + 1][curr.y + 1] != '0' && dist[curr.x + 1][curr.y][1] == -1) {
                    dist[curr.x + 1][curr.y][1] = d + 1;
                    q.offer(new State(curr.x + 1, curr.y, 1));
                }
                // 滚向上边 -> 保持水平，在 (curr.x-1, curr.y), (curr.x-1, curr.y+1)
                if (inBounds(curr.x - 1, curr.y + 1) && grid[curr.x - 1][curr.y] != '0'
                        && grid[curr.x - 1][curr.y + 1] != '0' && dist[curr.x - 1][curr.y][1] == -1) {
                    dist[curr.x - 1][curr.y][1] = d + 1;
                    q.offer(new State(curr.x - 1, curr.y, 1));
                }

            } else { // curr.s == 2, 如果当前是垂直状态 (在 curr.x, curr.y 和 curr.x+1, curr.y)
                // 滚向右边 -> 保持垂直，在 (curr.x, curr.y+1), (curr.x+1, curr.y+1)
                if (inBounds(curr.x + 1, curr.y + 1) && grid[curr.x][curr.y + 1] != '0'
                        && grid[curr.x + 1][curr.y + 1] != '0' && dist[curr.x][curr.y + 1][2] == -1) {
                    dist[curr.x][curr.y + 1][2] = d + 1;
                    q.offer(new State(curr.x, curr.y + 1, 2));
                }
                // 滚向左边 -> 保持垂直，在 (curr.x, curr.y-1), (curr.x+1, curr.y-1)
                if (inBounds(curr.x + 1, curr.y - 1) && grid[curr.x][curr.y - 1] != '0'
                        && grid[curr.x + 1][curr.y - 1] != '0' && dist[curr.x][curr.y - 1][2] == -1) {
                    dist[curr.x][curr.y - 1][2] = d + 1;
                    q.offer(new State(curr.x, curr.y - 1, 2));
                }
                // 滚向下边 -> 变为竖立，在 (curr.x+2, curr.y)
                if (inBounds(curr.x + 2, curr.y) && grid[curr.x + 2][curr.y] != '0' && grid[curr.x + 2][curr.y] != '2'
                        && dist[curr.x + 2][curr.y][0] == -1) {
                    dist[curr.x + 2][curr.y][0] = d + 1;
                    q.offer(new State(curr.x + 2, curr.y, 0));
                }
                // 滚向上边 -> 变为竖立，在 (curr.x-1, curr.y)
                if (inBounds(curr.x - 1, curr.y) && grid[curr.x - 1][curr.y] != '0' && grid[curr.x - 1][curr.y] != '2'
                        && dist[curr.x - 1][curr.y][0] == -1) {
                    dist[curr.x - 1][curr.y][0] = d + 1;
                    q.offer(new State(curr.x - 1, curr.y, 0));
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        grid = new char[n][m];
        for (int i = 0; i < n; i++) {
            grid[i] = br.readLine().toCharArray();
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == '3') {
                    tx = i;
                    ty = j;
                }
            }
        }

        // 执行一次性的反向BFS
        bfs();

        // 处理Q个查询
        int Q = Integer.parseInt(br.readLine());
        while (Q-- > 0) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1; // 1-based to 0-based
            int y = Integer.parseInt(st.nextToken()) - 1;
            int s = Integer.parseInt(st.nextToken());

            // 直接查表输出结果
            out.println(dist[x][y][s]);
        }

        out.flush();
        out.close();
    }
}
