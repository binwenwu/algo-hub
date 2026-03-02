public class _695 {
    public int maxAreaOfIsland(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int max = 0;
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    max = Math.max(max, dfs(grid, i, j));
                }
            }
        }
        return max;
    }

    private int dfs(int[][] grid, int i, int j) {
        // 越界 or 水域
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] == 0) {
            return 0;
        }

        // 淹没当前陆地（防止重复访问）
        grid[i][j] = 0;

        // 当前面积 = 1 + 四个方向
        return 1
                + dfs(grid, i + 1, j)
                + dfs(grid, i - 1, j)
                + dfs(grid, i, j + 1)
                + dfs(grid, i, j - 1);
    }
}
