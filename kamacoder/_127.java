import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class _127 {
    // 骑士的 8 个移动方向
    private static final int[][] dir = { { -2, -1 }, { -2, 1 }, { -1, 2 }, { 1, 2 }, { 2, 1 }, { 2, -1 }, { 1, -2 },
            { -1, -2 } };

    private static boolean[][] visited;
    // 优先队列（Open Set），根据 f 值从小到大排序
    private static PriorityQueue<Node> openSet = new PriorityQueue<>(new Comparator<Node>() {
        @Override
        public int compare(Node o1, Node o2) {
            return Double.compare(o1.f, o2.f);
        }
    });

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        while (n-- > 0) {
            int startX = sc.nextInt();
            int startY = sc.nextInt();
            int endX = sc.nextInt();
            int endY = sc.nextInt();
            // 方法名虽然叫 bfs，但实现的是 A* 算法
            openSet.clear();
            int res = aStar(startX, startY, endX, endY);
            System.out.println(res);
        }
        sc.close();
    }

    /**
     * 计算启发式函数 h(n) 的值，即当前点到终点的欧几里得距离。
     * 
     * @param x1 当前点 x 坐标
     * @param y1 当前点 y 坐标
     * @param x2 终点 x 坐标
     * @param y2 终点 y 坐标
     * @return 欧几里得距离
     */
    private static double heuristic(int x1, int y1, int x2, int y2) {
        return Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
    }

    /**
     * 使用 A* 算法计算最短路径
     * 
     * @param startX 起点 x
     * @param startY 起点 y
     * @param endX   终点 x
     * @param endY   终点 y
     * @return 最短步数
     */
    private static int aStar(int startX, int startY, int endX, int endY) {
        // 如果起点和终点相同，直接返回 0
        if (startX == endX && startY == endY) {
            return 0;
        }

        visited = new boolean[1001][1001];

        // 1. 创建起始节点
        double h_start = heuristic(startX, startY, endX, endY);
        Node startNode = new Node(startX, startY, 0, h_start);
        openSet.add(startNode);

        // 2. A* 搜索循环
        while (!openSet.isEmpty()) {
            // 2.1 从 Open Set 中取出 f 值最小的节点
            Node currentNode = openSet.poll();

            // 2.2 如果当前节点是终点，搜索成功
            if (currentNode.x == endX && currentNode.y == endY) {
                return currentNode.g; // 返回实际步数
            }

            // 2.3 标记当前坐标位置已访问
            if (visited[currentNode.x][currentNode.y]) {
                continue; // 如果已处理过，则跳过
            }

            visited[currentNode.x][currentNode.y] = true;

            // 2.4 遍历当前节点的所有邻居
            for (int i = 0; i < 8; i++) {
                int nextX = currentNode.x + dir[i][0];
                int nextY = currentNode.y + dir[i][1];

                if (nextX < 1 || nextX > 1000 || nextY < 1 || nextY > 1000) {
                    continue; // 如果越界，则忽略这个邻居
                }

                // 如果邻居节点已访问
                if (visited[nextX][nextY]) {
                    continue;
                }

                // 创建邻居节点并加入 Open Set
                int g_new = currentNode.g + 1;
                double h_new = heuristic(nextX, nextY, endX, endY);
                Node neighborNode = new Node(nextX, nextY, g_new, h_new);
                openSet.add(neighborNode);
            }
        }

        return -1; // 正常情况下，在无限棋盘上总能找到路径
    }
}

/**
 * A* 算法的节点类
 * 包含坐标 (x, y)
 * g: 从起点到当前节点的实际成本（步数）
 * h: 从当前节点到终点的启发式估计成本（欧几里得距离）
 * f: g + h，节点的总评估值
 */
class Node {
    int x;
    int y;
    int g; // 实际成本
    double h; // 启发式成本，使用 double 保证精度
    double f; // 总评估成本

    public Node(int x, int y, int g, double h) {
        this.x = x;
        this.y = y;
        this.g = g;
        this.h = h;
        this.f = g + h; // f = g + h
    }
}