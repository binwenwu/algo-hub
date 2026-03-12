package nowcoder.alibaba;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * https://www.nowcoder.com/exam/test/95052764/detail?pid=30440638
 */
public class _4 {
    static int[] dx = { 1, -1, 0, 0 };
    static int[] dy = { 0, 0, 1, -1 };

    static class Node {
        int x, y, k, dist;

        Node(int x, int y, int k, int dist) {
            this.x = x;
            this.y = y;
            this.k = k;
            this.dist = dist;
        }
    }

    /**
     * BFS + 多一维状态（飞行器使用次数）
     */
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();
        sc.nextLine();
        char[][] grid = new char[n][m];

        int sx = 0, sy = 0;

        for (int i = 0; i < n; i++) {
            String s = sc.nextLine();
            for (int j = 0; j < m; j++) {
                grid[i][j] = s.charAt(j);
                if (grid[i][j] == 'S') {
                    sx = i;
                    sy = j;
                }
            }
        }

        sc.close();

        boolean[][][] visited = new boolean[n][m][6]; // k = 0,1,2,3,4,5，表示已经使用飞行器的次数

        Queue<Node> q = new LinkedList<>();

        q.offer(new Node(sx, sy, 0, 0));
        visited[sx][sy][0] = true;

        while (!q.isEmpty()) {

            Node cur = q.poll();

            int x = cur.x;
            int y = cur.y;
            int k = cur.k;
            int dist = cur.dist;

            if (grid[x][y] == 'E') {
                System.out.println(dist);
                return;
            }

            // 四方向移动
            for (int d = 0; d < 4; d++) {

                int nx = x + dx[d];
                int ny = y + dy[d];

                if (nx < 0 || nx >= n || ny < 0 || ny >= m)
                    continue;

                if (grid[nx][ny] == '#')
                    continue;

                if (visited[nx][ny][k]) {
                    continue;
                }

                visited[nx][ny][k] = true;
                q.offer(new Node(nx, ny, k, dist + 1));

            }

            // 对称飞行
            if (k < 5) {

                /**
                 * 数组一般是 0-index，所以代码里要变成：
                 * x' = n - 1 - x
                 * y' = m - 1 - y
                 */
                int nx = n - 1 - x;
                int ny = m - 1 - y;

                if (grid[nx][ny] != '#' && !visited[nx][ny][k + 1]) {

                    visited[nx][ny][k + 1] = true;
                    q.offer(new Node(nx, ny, k + 1, dist + 1));
                }
            }
        }

        System.out.println(-1);

    }
}
