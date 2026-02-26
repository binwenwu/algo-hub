public class _36 {

    /**
     * 我们需要三个判重结构：
     * 
     * 行：row[9][9]
     * 列：col[9][9]
     * 宫：box[9][9]
     * 
     * 含义： row[i][j] 表示：第 i 行 是否出现过数字 j+1
     */
    public boolean isValidSudoku(char[][] board) {
        boolean[][] row = new boolean[9][9];
        boolean[][] col = new boolean[9][9];
        boolean[][] box = new boolean[9][9];

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                char c = board[i][j];
                if (c == '.')
                    continue;

                int num = c - '0';
                int boxIndex = (i / 3) * 3 + j / 3;

                // 判重
                if (row[i][num - 1] || col[j][num - 1] || box[boxIndex][num - 1]) {
                    return false;
                }

                row[i][num - 1] = true;
                col[j][num - 1] = true;
                box[boxIndex][num - 1] = true;
            }
        }
        return true;
    }
}
