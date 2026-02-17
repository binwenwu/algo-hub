public class _79 {
    public static void main(String[] args) {

    }

    int[][] used;

    public boolean exist(char[][] board, String word) {
        used = new int[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (backtrack(board, word, 0, i, j)) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean backtrack(char[][] board, String word, int curr, int x, int y) {

        if (curr == word.length()) {
            return true;
        }

        if (x < 0 || y < 0 || x >= board.length || y >= board[0].length) {
            return false;
        }
        if (board[x][y] != word.charAt(curr) || used[x][y] >= 1) {
            return false;
        }

        used[x][y]++;
        boolean isTrue = backtrack(board, word, curr + 1, x + 1, y) || backtrack(board, word, curr + 1, x - 1, y)
                || backtrack(board, word, curr + 1, x, y + 1) || backtrack(board, word, curr + 1, x, y - 1);
        used[x][y]--;
        return isTrue;
    }
}
