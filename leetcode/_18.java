import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class _18 {
    public static void main(String[] args) {
        _18 s = new _18();
        int[] nums = { 1000000000, 1000000000, 1000000000, 1000000000 };
        int target = -294967296;
        List<List<Integer>> fourSum1 = s.fourSum1(nums, target);
        System.out.println(fourSum1);

    }

    // 仿照15做的，但是时间太长，需要做剪枝处理
    public List<List<Integer>> fourSum1(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);

        for (int i = 0; i < nums.length; i++) {

            // 剪枝处理
            if (nums[i] > target && nums[i] >= 0) {
                return result;
            }


            // 去重 a
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }


            outer:
            for (int j = i + 1; j < nums.length; j++) {

                if (nums[i] + nums[j] > target && nums[i] + nums[j] >= 0) {
                    break outer;
                }

                // 去重 b
                if (j > i + 1 && nums[j] == nums[j - 1]) {
                    continue;
                }

                // c 和 d
                int left = j + 1;
                int right = nums.length - 1;

                while (left < right) {
                    long sum = (long) nums[i] + nums[j] + nums[left] + nums[right];
                    if (sum > target) {
                        right--;
                    } else if (sum < target) {
                        left++;
                    } else {
                        result.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));
                        // 去重 c
                        while (left < right && nums[left] == nums[left + 1]) {
                            left++;
                        }
                        // 去重 d
                        while (left < right && nums[right] == nums[right - 1]) {
                            right--;
                        }
                        left++;
                        right--;
                    }
                }
            }
        }

        return result;
    }
}
