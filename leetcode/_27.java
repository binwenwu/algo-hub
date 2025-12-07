import java.util.Arrays;

public class _27 {
    public static void main(String[] args) {
        _27 s = new _27();
        int[] nums = { 0, 1, 2, 2, 3, 0, 4, 2 };
        int val = 2;
        int res = s.removeElement(nums, val);
        System.out.println("res: " + res);
        System.out.println(Arrays.toString(nums));
    }

    // 双指针
    public int removeElement(int[] nums, int val) {
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

}
