public class _289 {

    /**
     * 如何在“原地”更新，同时还能读取“旧状态”？
     * 状态编码法：
     * | 原状态 | 新状态 | 编码 |
     * | ----- | --- | -- |
     * | 0 → 0 | 死→死 | 0 |
     * | 1 → 1 | 活→活 | 1 |
     * | 1 → 0 | 活→死 | -1 |
     * | 0 → 1 | 死→活 | 2 |
     * 
     * 判断“旧状态”时：
     * if (Math.abs(board[x][y]) == 1)
     * 表示原来是活
     */
    public void gameOfLife(int[][] board) {
        int m = board.length;
        int n = board[0].length;

        int[][] dirs = {
                { -1, -1 }, { -1, 0 }, { -1, 1 },
                { 0, -1 }, { 0, 1 },
                { 1, -1 }, { 1, 0 }, { 1, 1 }
        };

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                int liveNeighbors = 0;

                for (int[] d : dirs) {
                    int x = i + d[0];
                    int y = j + d[1];

                    if (x >= 0 && x < m && y >= 0 && y < n
                            && Math.abs(board[x][y]) == 1) {
                        liveNeighbors++;
                    }
                }

                // 活细胞
                if (board[i][j] == 1) {
                    if (liveNeighbors < 2 || liveNeighbors > 3) {
                        board[i][j] = -1; // 活 → 死
                    }
                }
                // 死细胞
                else {
                    if (liveNeighbors == 3) {
                        board[i][j] = 2; // 死 → 活
                    }
                }
            }
        }

        // 第二遍更新最终状态
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] > 0)
                    board[i][j] = 1;
                else
                    board[i][j] = 0;
            }
        }
    }
}
