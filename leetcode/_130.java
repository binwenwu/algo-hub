public class _130 {

    /**
     * 从边界出发 DFS/BFS
     * 
     * 1. 把所有连到边界的 O 标记成临时字符（比如 #）
     * 2. 遍历整个棋盘
     *      剩下的 O → 变成 X
     *      把 # 还原成 O
     */
    public void solve(char[][] board) {
        int m = board.length;
        int n = board[0].length;

        // 1. 从四条边出发 DFS
        for (int i = 0; i < m; i++) {
            dfs(board, i, 0);
            dfs(board, i, n - 1);
        }

        for (int j = 0; j < n; j++) {
            dfs(board, 0, j);
            dfs(board, m - 1, j);
        }

        // 2. 遍历棋盘做转换
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 'O') {
                    board[i][j] = 'X'; // 被包围
                } else if (board[i][j] == '#') {
                    board[i][j] = 'O'; // 还原安全区
                }
            }
        }
    }

    private void dfs(char[][] board, int i, int j) {
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length || board[i][j] != 'O') {
            return;
        }

        board[i][j] = '#'; // 标记为安全

        dfs(board, i + 1, j);
        dfs(board, i - 1, j);
        dfs(board, i, j + 1);
        dfs(board, i, j - 1);
    }
}
