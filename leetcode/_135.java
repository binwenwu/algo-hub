public class _135 {
    public static void main(String[] args) {
        _135 s = new _135();
        int[] ratings = { 1, 2, 2 };
        int candy = s.candy1(ratings);
        System.out.println(candy);
    }

    // 暴力解法
    public int candy1(int[] ratings) {
        if (ratings.length == 1) {
            return 1;
        }

        int result = 0;
        int[] candy = new int[ratings.length];
        for (int i = 0; i < ratings.length; i++) {
            candy[i] = 1;
        }

        boolean replay = true;

        while (true) {
            replay = false;
            for (int i = 0; i < ratings.length - 1; i++) {
                if (ratings[i] > ratings[i + 1]) {
                    while (candy[i] <= candy[i + 1]) {
                        replay = true;
                        candy[i]++;
                    }
                } else if (ratings[i] < ratings[i + 1]) {
                    while (candy[i] >= candy[i + 1]) {
                        replay = true;
                        candy[i + 1]++;
                    }
                }
            }
            if (!replay) {
                break;
            }
        }

        for (int i = 0; i < candy.length; i++) {
            result += candy[i];
        }

        return result;
    }

    // 贪心（方式一）
    public int candy2(int[] ratings) {
        if (ratings.length == 1) {
            return 1;
        }

        int result = 0;
        int[] candy = new int[ratings.length];
        for (int i = 0; i < ratings.length; i++) {
            candy[i] = 1;
        }

        for (int i = 0; i < ratings.length - 1; i++) {
            if (ratings[i] < ratings[i + 1]) {
                candy[i + 1] = candy[i] + 1;
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
