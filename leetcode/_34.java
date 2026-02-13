import java.util.Arrays;

/**
 * 给定一个按照升序排列的整数数组 nums，和一个目标值
 * target。找出给定目标值在数组中的开始位置和结束位置。 如果数组中不存在目标值
 * target，返回 [-1, -1]。
 */
public class _34 {
    public static void main(String[] args) {
        _34 s = new _34();
        int[] nums = { 1, 3, 4, 5, 5, 6, 8, 8, 8, 12, 15 };
        int target = 8;
        System.out.println(Arrays.toString(s.searchRange(nums, target)));
    }

    public int[] searchRange(int[] nums, int target) {
        if (nums == null) {
            return new int[] { -1, -1 };
        }

        int length = nums.length;
        if (length == 0 || nums[0] > target || nums[length - 1] < target) {
            return new int[] { -1, -1 };
        }

        int left = getLeftBorder(nums, target);
        if (left == -1) {
            return new int[] { -1, -1 };
        }

        int right = getRightBorder(nums, target);

        return new int[] { left, right };
    }

    private int getLeftBorder(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        int res = -1;
        while (left <= right) {
            int middle = left + ((right - left) >> 1);
            if (nums[middle] > target) {
                right = middle - 1;
            } else if (nums[middle] < target) {
                left = middle + 1;
            } else {
                res = middle;
                right = middle - 1;
            }
        }
        return res;
    }

    private int getRightBorder(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        int res = -1;
        while (left <= right) {
            int middle = left + ((right - left) >> 1);
            if (nums[middle] > target) {
                right = middle - 1;
            } else if (nums[middle] < target) {
                left = middle + 1;
            } else {
                res = middle;
                left = middle + 1;
            }
        }
        return res;
    }
}
