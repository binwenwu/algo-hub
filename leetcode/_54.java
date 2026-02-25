import java.util.ArrayList;
import java.util.List;

public class _54 {
    public static void main(String[] args) {

    }

    public List<Integer> spiralOrder(int[][] matrix) {
        int[][] dirs = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };
        List<Integer> ans = new ArrayList<>();
        int m = matrix.length;
        int n = matrix[0].length;
        int x = 0;
        int y = 0;
        int k = 0;
        for (int i = 0; i < n * m; i++) {
            ans.add(matrix[x][y]);
            matrix[x][y] = Integer.MAX_VALUE;
            int nextX = x + dirs[k][0];
            int nextY = y + dirs[k][1];
            if (nextX < 0 || nextX > m || nextY < 0 || nextY > n || matrix[nextX][nextY] == Integer.MAX_VALUE) {
                k = (k + 1) % 4;
            }
            x += dirs[k][0];
            y += dirs[k][1];
        }
        return ans;
    }

}
