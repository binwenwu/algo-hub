/**
 * 367
 */
public class _367 {
    public static void main(String[] args) {
    }

    // 二分法
    public boolean isPerfectSquare(int num) {
        int left = 0;
        int right = 1 << 16;
        boolean res = false;
        while (left <= right) {
            int middle = left + ((right - left) >> 1);
            long temp = (long) middle * middle;
            if (temp > num) {
                right = middle - 1;
            } else if (temp < num) {
                left = middle + 1;
            } else {
                return !res;
            }
        }

        return res;
    }
}
