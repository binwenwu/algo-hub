/**
 *
 * 给定一个含有 n 个正整数的数组和一个正整数 target 。
 * 找出该数组中满足其总和大于等于 target 的长度最小的子数组
 * [numsl, numsl+1, ..., numsr-1, numsr]
 * ，并返回其长度。如果不存在符合条件的子数组，返回 0 。
 */
public class _209 {
  public static void main(String[] args) {
    _209 s = new _209();
    int[] nums = { 2, 3, 1, 2, 4, 3 };
    int target = 7;
    int minSize = s.minSubArrayLen1(target, nums);
    System.out.println("minSize: " + minSize);
  }

  // 暴力解法，嵌套循环遍历
  public int minSubArrayLen1(int target, int[] nums) {
    int minSize = nums.length + 1;
    for (int i = 0; i < nums.length; i++) {
      int sum = 0;
      for (int j = i; j < nums.length; j++) {
        sum += nums[j];
        if (sum >= target) {
          if ((j - i + 1) < minSize) {
            minSize = j - i + 1;
          }
        }
      }
    }
    return minSize == nums.length + 1 ? 0 : minSize;
  }

  // 方法2： 滑动窗口，有点像双指针
  public int minSubArrayLen2(int target, int[] nums) {
    int minSize = nums.length + 1;
    int sum = 0;
    int left = 0;
    for (int right = 0; right < nums.length; right++) {
      sum += nums[right];
      while (sum >= target) {
        if ((right - left + 1) < minSize) {
          minSize = right - left + 1;
        }
        // 只要 sum 还大于等于 target，就不断将左指针右移，同时减去当前
        // left对应的元素值
        sum -= nums[left];
        left++;
      }
    }
    return minSize == nums.length + 1 ? 0 : minSize;
  }

  // 方法3：前缀和 + 二分查找
  public int minSubArrayLen3(int target, int[] nums) {
    return -1;
  }
}
