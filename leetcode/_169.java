import java.util.Arrays;

public class _169 {
    public static void main(String[] args) {

    }

    // 排序法
    public int majorityElement1(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length >> 1];
    }

    // 摩尔投票
    public int majorityElement2(int[] nums) {
        int x = 0, votes = 0;
        for (int num : nums) {
            if (votes == 0)
                x = num;
            if (num == x) {
                votes++;
            } else {
                votes--;
            }
        }
        return x;
    }
}
