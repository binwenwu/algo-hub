import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * 很多人一开始都会想： 从每个格子往外流，看能不能到两个海
 * ❌ 这样会超时。
 * 
 * 正确思路（反向思维）： 从海岸往内陆“逆流而上”
 * 
 * 因为水能从高流向低，那么反过来：
 * 如果从海出发，只要下一格高度 ≥ 当前高度，就能逆流爬上去。
 */
public class _417 {
    int m, n;
    int[][] heights;
    boolean[][] pacific;
    boolean[][] atlantic;

    int[][] dirs = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        this.heights = heights;
        m = heights.length;
        n = heights[0].length;

        pacific = new boolean[m][n];
        atlantic = new boolean[m][n];

        // 1. 从 Pacific 边界开始
        for (int i = 0; i < m; i++) {
            dfs(i, 0, pacific);
            dfs(i, n - 1, atlantic);
        }

        for (int j = 0; j < n; j++) {
            dfs(0, j, pacific);
            dfs(m - 1, j, atlantic);
        }

        // 2. 找交集
        List<List<Integer>> res = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (pacific[i][j] && atlantic[i][j]) {
                    res.add(Arrays.asList(i, j));
                }
            }
        }

        return res;
    }

    private void dfs(int i, int j, boolean[][] visited) {
        visited[i][j] = true;

        for (int[] d : dirs) {
            int x = i + d[0];
            int y = j + d[1];

            if (x >= 0 && x < m && y >= 0 && y < n && !visited[x][y] && heights[x][y] >= heights[i][j]) {
                dfs(x, y, visited);
            }
        }
    }
}
