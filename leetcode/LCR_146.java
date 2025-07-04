import java.util.Arrays;

public class LCR_146 {
    public static void main(String[] args) {
        int[][] array = new int[][] { { 2,3 }};
        LCR_146 s = new LCR_146();
        int[] res = s.spiralArray1(array);
        System.out.println(Arrays.toString(res));

    }

    public int[] spiralArray1(int[][] array) {
        int x_length = array.length;
        if (x_length == 0) {
            return new int[0];
        }
        int y_length = array[0].length;
        int count = x_length * y_length;
        int index = 0;
        int[] res = new int[count];
        int right = y_length - 1;
        int bottom = x_length - 1;
        int left = 0;
        int top = 0;
        int i = 0, j = 0;
        while (index < count) {
            for (j = left; j <= right; j++) {
                res[index++] = array[top][j];
            }
            top++;
            if (index == count) {
                break;
            }
            for (i = top; i <= bottom; i++) {
                res[index++] = array[i][right];
            }
            right--;
            if (index == count) {
                break;
            }
            for (j = right; j >= left; j--) {
                res[index++] = array[bottom][j];
            }
            bottom--;
            if (index == count) {
                break;
            }
            for (i = bottom; i >= top; i--) {
                res[index++] = array[i][left];
            }
            left++;
            if (index == count) {
                break;
            }
        }

        return res;
    }

}
