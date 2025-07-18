import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

// 自己想的方法一
public class _104 {

    private static int[][] directions = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } }; // 上, 下, 左, 右

    private static int[][] island;
    private static int maxArea = 1;
    private static int temp = 1;
    private static boolean[][] visited;

    private static void dfs(int x, int y) {
        for (int i = 0; i < 4; i++) {
            int nextX = x + directions[i][0];
            int nextY = y + directions[i][1];
            if (!visited[nextX][nextY] && island[nextX][nextY] == 1) {
                temp++;
                visited[nextX][nextY] = true;
                dfs(nextX, nextY);
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        island = new int[N + 2][M + 2];

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                island[i][j] = sc.nextInt();
            }
        }

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                if (island[i][j] == 0) {
                    visited = new boolean[N + 2][M + 2];
                    temp = 1;
                    visited[i][j] = true;
                    dfs(i, j);
                    maxArea = Math.max(maxArea, temp);
                }
            }
        }

        // 因为有可能一开始就全是陆地，所以得针对陆地区域再dfs一次
        visited = new boolean[N + 2][M + 2];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                if (island[i][j] == 1) {
                    temp = 1;
                    visited[i][j] = true;
                    dfs(i, j);
                    maxArea = Math.max(maxArea, temp);
                }
            }
        }

        System.out.println(maxArea);

        sc.close();
    }
}

// 方法二
class Main {
    // 该方法采用 DFS
    // 定义全局变量
    // 记录每次每个岛屿的面积
    static int count;
    // 对每个岛屿进行标记
    static int mark;
    // 定义二维数组表示四个方位
    static int[][] dirs = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };

    // DFS 进行搜索，将每个岛屿标记为不同的数字
    public static void dfs(int[][] grid, int x, int y, boolean[][] visited) {
        // 当遇到边界，直接return
        if (x < 0 || x >= grid.length || y < 0 || y >= grid[0].length)
            return;
        // 遇到已经访问过的或者遇到海水，直接返回
        if (visited[x][y] || grid[x][y] == 0)
            return;

        visited[x][y] = true;
        count++;
        grid[x][y] = mark;

        // 继续向下层搜索
        dfs(grid, x, y + 1, visited);
        dfs(grid, x, y - 1, visited);
        dfs(grid, x + 1, y, visited);
        dfs(grid, x - 1, y, visited);
    }

    public static void main(String[] args) {
        // 接收输入
        Scanner sc = new Scanner(System.in);
        int m = sc.nextInt();
        int n = sc.nextInt();

        int[][] grid = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                grid[i][j] = sc.nextInt();
            }
        }

        // 初始化mark变量，从2开始（区别于0水，1岛屿）
        mark = 2;

        // 定义二位boolean数组记录该位置是否被访问
        boolean[][] visited = new boolean[m][n];

        // 定义一个HashMap，记录某片岛屿的标记号和面积
        HashMap<Integer, Integer> getSize = new HashMap<>();
        

        // 定义一个HashSet，用来判断某一位置水四周是否存在不同标记编号的岛屿
        HashSet<Integer> set = new HashSet<>();

        // 定义一个boolean变量，看看DFS之后，是否全是岛屿
        boolean isAllIsland = true;

        // 遍历二维数组进行DFS搜索，标记每片岛屿的编号，记录对应的面积
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0)
                    isAllIsland = false;
                if (grid[i][j] == 1) {
                    count = 0;
                    dfs(grid, i, j, visited);
                    getSize.put(mark, count);
                    mark++;
                }
            }
        }

        int result = 0;
        if (isAllIsland)
            result = m * n;

        // 对标记完的grid继续遍历，判断每个水位置四周是否有岛屿，并记录下四周不同相邻岛屿面积之和
        // 每次计算完一个水位置周围可能存在的岛屿面积之和，更新下result变量
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) {
                    set.clear();
                    // 当前水位置变更为岛屿，所以初始化为1
                    int curSize = 1;

                    for (int[] dir : dirs) {
                        int curRow = i + dir[0];
                        int curCol = j + dir[1];

                        if (curRow < 0 || curRow >= m || curCol < 0 || curCol >= n)
                            continue;
                        int curMark = grid[curRow][curCol];
                        // 如果当前相邻的岛屿已经遍历过或者HashMap中不存在这个编号，继续搜索
                        if (set.contains(curMark) || !getSize.containsKey(curMark))
                            continue;
                        set.add(curMark);
                        curSize += getSize.get(curMark);
                    }

                    result = Math.max(result, curSize);
                }
            }
        }

        // 打印结果
        System.out.println(result);
    }
}