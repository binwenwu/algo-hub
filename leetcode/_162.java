public class _162 {
    // 二分
    public int findPeakElement(int[] nums) {
        int left = 0, right = nums.length - 1;

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] > nums[mid + 1]) {
                right = mid; // 这里不加1，因为mid还可能是峰
            } else {
                left = mid + 1; // 这里加1，因为mid已经不可能是峰了
            }
        }

        return left;
    }
}
