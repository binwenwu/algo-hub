public class _69 {
    public static void main(String[] args) {
        _69 s = new _69();
        int x = 1;
        System.out.println(x + "的算数平方根（去掉小数）是：" + s.mySqrt(x));
    }

    // 二分法
    public int mySqrt(int x) {
        // r 可以优化成 x / 2 + 1，因为：sqrt(x) ≤ x/2 + 1,当然，写 r = x 也可以
        int l = 0, r = x / 2 + 1, ans = -1;
        while (l <= r) {
            int mid = l + ((r - l) >> 1);
            if ((long) mid * mid <= x) {
                ans = mid;
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return ans;
    }
}
