import java.util.Arrays;

public class _27 {
  public static void main(String[] args) {
    _27 s = new _27();
    int[] nums = { 0, 1, 2, 2, 3, 0, 4, 2 };
    int val = 2;
    int res = s.removeElement1(nums, val);
    System.out.println("res: " + res);
    System.out.println(Arrays.toString(nums));
  }

  // 暴力解法，从头开始找，找到就跟后面的元素交换，实际上就是双指针优化的方法
  public int removeElement1(int[] nums, int val) {
    int exchange = nums.length - 1;
    int i = 0;

    while (i <= exchange) {
      if (nums[i] == val) {
        // 交换前确保 exchange 没有越界
        if (exchange < 0)
          break;

        // 跳过所有等于 val 的右端元素
        while (exchange >= i && nums[exchange] == val) {
          exchange--;
        }

        // 如果还有可以交换的元素
        if (exchange >= i) {
          int temp = nums[i];
          nums[i] = nums[exchange];
          nums[exchange] = temp;
          exchange--;
        }
      }
      i++;
    }

    // 返回的是有效元素的数量，即第一个等于 val 的索引位置
    return exchange + 1;
  }

  // 双指针
  public int removeElement2(int[] nums, int val) {
    int n = nums.length - 1;
    int left = 0;
    int right = 0;
    while (right <= n) {
      if (nums[right] != val) {
        nums[left] = nums[right]; // 可以直接覆盖，不需要交换
        right++;
        left++;
      } else {
        right++;
      }
    }
    return left;
  }

  /**
   * 双指针优化
   * 如果左指针 left 指向的元素等于 val，此时将右指针 right
   * 指向的元素复制到左指针 left 的位置，然后右指针 right
   * 左移一位。如果赋值过来的元素恰好也等于 val，可以继续把右指针 right
   * 指向的元素的值赋值过来（左指针 left 指向的等于 val
   * 的元素的位置继续被覆盖），直到左指针指向的元素的值不等于 val 为止。 当左指针
   * left 和右指针 right 重合的时候，左右指针遍历完数组中所有的元素。
   */
  public int removeElement3(int[] nums, int val) {
    int left = 0;
    int right = nums.length - 1;
    while (left <= right) {
      if (nums[left] == val) {
        nums[left] = nums[right];
        right--;
      } else {
        left++;
      }
    }
    return left;
  }

  // 相向双指针，其实跟我一开始想到的暴力解法是一样的，但是可以不交换元素，而是覆盖元素
  public int removeElement4(int[] nums, int val) {
    int left = 0;
    int right = nums.length - 1;
    while (right >= 0 && nums[right] == val)
      right--; // 将right移到从右数第一个值不为val的位置
    while (left <= right) {
      if (nums[left] == val) { // left位置的元素需要移除
        // 将right位置的元素移到left（覆盖），right位置移除
        nums[left] = nums[right];
        right--;
      }
      left++;
      while (right >= 0 && nums[right] == val)
        right--;
    }
    return left;
  }
}
