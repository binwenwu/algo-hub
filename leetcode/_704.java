/**
 * 二分查找：
 *
 * 给定一个 n 个元素有序的（升序）整型数组 nums 和一个目标值 target
 * ，写一个函数搜索 nums 中的 target，如果目标值存在返回下标，否则返回 -1。
 */
public class _704 {
  public static void main(String[] args) {
    _704 solution = new _704();
    int[] nums = { 1, 5, 6, 12, 67, 100 };
    int target = 12;
    int index = solution.search1(nums, target);
    System.out.println("index: " + index);
  }

  // 左闭右闭的写法
  public int search1(int[] nums, int target) {
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
    return -1;
  }

  // 做闭右开的写法
  public int search2(int[] nums, int target) {
    int left = 0;
    int right = nums.length;
    while (left < right) {
      int middle = left + ((right - left) >> 1);
      if (nums[middle] > target) {
        right = middle;
      } else if (nums[middle] < target) {
        left = middle + 1;
      } else {
        return middle;
      }
    }
    return -1;
  }
}
