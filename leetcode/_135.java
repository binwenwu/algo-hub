import java.util.Arrays;

public class _135 {
    public static void main(String[] args) {
        _135 s = new _135();
        int[] ratings = { 1, 2, 2 };
        int candy = s.candy(ratings);
        System.out.println(candy);
    }

    /**
     * 贪心
     * 先从左往右，然后从右往左依次判断
     */
    public int candy(int[] ratings) {
        int result = 0;
        int[] candy = new int[ratings.length];
        Arrays.fill(candy, 1);

        for (int i = 1; i < ratings.length; i++) {
            if (ratings[i] > ratings[i - 1]) {
                candy[i] = candy[i - 1] + 1;
            }
        }

        for (int i = ratings.length - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1]) {
                candy[i] = Math.max(candy[i], candy[i + 1] + 1);
            }
        }

        for (int i = 0; i < candy.length; i++) {
            result += candy[i];
        }

        return result;
    }
}
