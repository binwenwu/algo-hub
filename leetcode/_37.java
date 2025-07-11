public class _37 {

    public static void main(String[] args) {
    }
    
    // 解法1：延用N皇后（_51.java）的思路，采用一维递归
    public void solveSudoku1(char[][] board) {
        // 初始化状态数组
        row = new boolean[9][9];
        col = new boolean[9][9];
        block = new boolean[9][9];

        // 先扫描一次棋盘，记录已放置的数字
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                char c = board[i][j];
                if (c != '.') {
                    int num = c - '1';
                    row[i][num] = true;
                    col[j][num] = true;
                    block[getBlockIndex(i, j)][num] = true;
                }
            }
        }

        backtrack1(board, 0);
    }

    boolean[][] row, col, block;

    private boolean backtrack1(char[][] board, int index) {
        if (index == 81)
            return true; // 找到解

        int i = index / 9;
        int j = index % 9;

        if (board[i][j] != '.') {
            return backtrack1(board, index + 1); // 跳过填好的格子
        }

        for (int num = 0; num < 9; num++) {
            int blockIndex = getBlockIndex(i, j);
            if (row[i][num] || col[j][num] || block[blockIndex][num])
                continue;

            // 放置数字
            board[i][j] = (char) (num + '1');
            row[i][num] = col[j][num] = block[blockIndex][num] = true;

            if (backtrack1(board, index + 1))
                return true;

            // 回溯
            board[i][j] = '.';
            row[i][num] = col[j][num] = block[blockIndex][num] = false;
        }

        return false;
    }


    
    private int getBlockIndex(int i, int j) {
        return (i / 3) * 3 + (j / 3);
    }
}
