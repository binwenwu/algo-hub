public class _79 {
    public static void main(String[] args) {

    }

    public boolean exist(char[][] board, String word) {
        int m = board.length, n = board[0].length;
        int[][] visited = new int[m][n]; // 记录访问次数
        char[] words = word.toCharArray();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (dfs(board, words, visited, i, j, 0))
                    return true;
            }
        }
        return false;
    }

    boolean dfs(char[][] board, char[] word, int[][] visited, int i, int j, int k) {
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length)
            return false;
        if (board[i][j] != word[k])
            return false;
        if (visited[i][j] >= 1) // 每个单元格最多使用一次
            return false;

        if (k == word.length - 1)
            return true;

        visited[i][j]++; // 标记使用
        boolean res = dfs(board, word, visited, i + 1, j, k + 1)
                || dfs(board, word, visited, i - 1, j, k + 1)
                || dfs(board, word, visited, i, j + 1, k + 1)
                || dfs(board, word, visited, i, j - 1, k + 1);
        visited[i][j]--; // 回溯还原
        return res;
    }
}
