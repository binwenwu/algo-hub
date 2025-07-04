import java.util.Arrays;

public class _1005 {
    public static void main(String[] args) {

    }

    
    public int largestSumAfterKNegations(int[] nums, int k) {
        int result = 0;
        Arrays.sort(nums);
        // 先尽量把小的负数变成正数
        for (int i = 0; i < nums.length; i++) {
            if (k > 0) {
                if (nums[i] < 0) {
                    nums[i] = -nums[i];
                    k--;
                }
            }
            result += nums[i];
        }

        // 这里需要再排序一次，因为可能出现之前最小的负数变成正数后不是最小了
        Arrays.sort(nums);

        // 如果 k 还有剩余，那就把最小的反复变换
        if (k % 2 == 1) {
            result = result - 2 * nums[0];
        }

        return result;
    }
}
