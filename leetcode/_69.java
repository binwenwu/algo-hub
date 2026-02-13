/**
 * 给你一个非负整数 x ，计算并返回 x 的 算术平方根 。
 * 由于返回类型是整数，结果只保留 整数部分 ，小数部分将被 舍去 。
 * 注意：不允许使用任何内置指数函数和算符，例如 pow(x, 0.5) 或者 x ** 0.5 。
 */
public class _69 {
    public static void main(String[] args) {
        _69 s = new _69();
        int x = 4;
        System.out.println(x + "的算数平方根（去掉小数）是：" + s.mySqrt1(x));
    }

    // 我一开始就想到了用二分查找，但是一开始我用的是int数组，导致溢出，
    // 应该使用long数组，但是用数组的方式会导致内存占用非常大，实际上不需要定义数组，可以循环
    // 体内边定义边比较
    public int mySqrt1(int x) {
        long[] nums = new long[1 << 16];

        for (int i = 0; i < nums.length; i++) {
            nums[i] = (long) i * i;
        }
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int middle = left + ((right - left) >> 1);
            if (nums[middle] > x) {
                right = middle - 1;
            } else if (nums[middle] < x) {
                left = middle + 1;
            } else {
                return middle;
            }
        }
        return left - 1;
    }

    // 二分查找，不使用数组，直接在循环体内进行比较
    public int mySqrt2(int x) {
        int left = 0;
        int right = 1 << 16;
        while (left <= right) {
            int middle = left + ((right - left) >> 1);
            long temp = (long) middle * middle;
            if (temp > x) {
                right = middle - 1;
            } else if (temp < x) {
                left = middle + 1;
            } else {
                return middle;
            }
        }

        return left - 1;
    }

    // 袖珍计算器算法
    public int mySqrt3(int x) {
        if (x == 0) {
            return 0;
        }
        int ans = (int) Math.exp(0.5 * Math.log(x));
        return (long) (ans + 1) * (ans + 1) <= x ? ans + 1 : ans;
    }

    // 牛顿迭代
    public int mySqrt4(int x) {
        return -1;
    }
}
