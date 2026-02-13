/**
 * 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
 * 
 * 请必须使用时间复杂度为 O(log n) 的算法。
 */
public class _35 {
    public static void main(String[] args) {
        _35 solution = new _35();
        int[] nums = { 1, 3, 5, 6 };
        int target = 7;
        int index = solution.searchInsert(nums, target);
        System.out.println("index: " + index);
    }

    /**
     * 二分查找的思路：
     * 因为二分查找退出循环后，如果没有找到对应的元素，
     * 那么此时 left 指向数组中第一个大于 target 的位置，
     * 而 right 指向数组中最后一个小于 target 的位置；
     */
    public int searchInsert(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int middle = left + ((right - left) >> 1);
            if (nums[middle] > target) {
                right = middle - 1;
            } else if (nums[middle] < target) {
                left = middle + 1;
            } else {
                return middle;
            }
        }
        return left;
    }
}
