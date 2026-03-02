public class _463 {
    public int islandPerimeter(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int perimeter = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    perimeter += 4;

                    // 上方有陆地，减2
                    if (i > 0 && grid[i - 1][j] == 1)
                        perimeter -= 2;

                    // 左方有陆地，减2
                    if (j > 0 && grid[i][j - 1] == 1)
                        perimeter -= 2;
                }
            }
        }
        return perimeter;
    }
}
