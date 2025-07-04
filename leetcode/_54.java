import java.util.ArrayList;
import java.util.List;

public class _54 {
    public static void main(String[] args) {

    }

    public List<Integer> spiralOrder1(int[][] matrix) {
        List<Integer> res = new ArrayList<>();
        int x_length = matrix.length;
        if (x_length == 0) {
            return res;
        }
        int y_length = matrix[0].length;
        int length = x_length * y_length;
        int index = 0;
        int right = y_length - 1;
        int bottom = x_length - 1;
        int left = 0;
        int top = 0;
        while (index < length) {
            for (int j = left; j <= right; j++) {
                res.add(matrix[top][j]);
                index++;
            }
            top++;
            if (index == length) {
                break;
            }
            for (int i = top; i <= bottom; i++) {
                res.add(matrix[i][right]);
                index++;
            }
            right--;
            if (index == length) {
                break;
            }
            for (int j = right; j >= left; j--) {
                res.add(matrix[bottom][j]);
                index++;
            }
            bottom--;
            if (index == length) {
                break;
            }
            for (int i = bottom; i >= top; i--) {
                res.add(matrix[i][left]);
                index++;
            }
            left++;
            if (index == length) {
                break;
            }
        }
        return res;
    }
}
