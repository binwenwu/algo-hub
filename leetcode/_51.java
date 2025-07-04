import java.util.ArrayList;
import java.util.List;

public class _51 {
    public static void main(String[] args) {

    }

    List<List<String>> res = new ArrayList<>();
    List<String> temp = new ArrayList<>();
    /**
     * 使用三个数组 colUsed、diag1Used 和 diag2Used 分别记录每一列以及两个方向的每条斜线上是否有皇后。
     */
    boolean[] colUsed;
    boolean[] diag1Used; // row - col + n - 1，方向一的斜线为从左上到右下方向，同一条斜线上的每个位置满足行下标与列下标之差相等
    boolean[] diag2Used; // row + col，方向二的斜线为从右上到左下方向，同一条斜线上的每个位置满足行下标与列下标之和相等
    int length;

    public List<List<String>> solveNQueens(int n) {
        res.clear();
        temp.clear();
        length = n;
        colUsed = new boolean[n];
        diag1Used = new boolean[2 * n - 1];
        diag2Used = new boolean[2 * n - 1];
        backtrack(0);
        return res;
    }

    private void backtrack(int row) {
        if (row == length) {
            res.add(new ArrayList<>(temp));
            return;
        }
        for (int col = 0; col < length; col++) {
            int d1 = row - col + length - 1;
            int d2 = row + col;
            if (colUsed[col] || diag1Used[d1] || diag2Used[d2]) {
                continue;
            }

            // 放皇后
            colUsed[col] = diag1Used[d1] = diag2Used[d2] = true;
            temp.add(getLine(col));

            backtrack(row + 1);

            // 回溯
            temp.removeLast();
            colUsed[col] = diag1Used[d1] = diag2Used[d2] = false;
        }
    }

    private String getLine(int index) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(i == index ? 'Q' : '.');
        }
        return sb.toString();
    }

}
