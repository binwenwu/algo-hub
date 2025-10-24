import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class _15 {
    public static void main(String[] args) {

    }

    // 双指针的方法
    public List<List<Integer>> threeSum2(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                return result;
            }

            // 去重 a
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            // b 和 c
            int left = i + 1;
            int right = nums.length - 1;

            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (sum > 0) {
                    right--;
                } else if (sum < 0) {
                    left++;
                } else {
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    // 去重 b
                    while (left < right && nums[left] == nums[left + 1]) {
                        left++;
                    }
                    left++;
                    // 去重 c
                    while (left < right && nums[right] == nums[right - 1]) {
                        right--;
                    }                    
                    right--;
                }
            }

        }

        return result;
    }

    public List<List<Integer>> threeSum3(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        Arrays.sort(nums);
        int len = nums.length;
        for (int i = 0; i < len - 2; i++) {
            if (nums[i] > 0) {
                return ans; // 因为排序过了，所以第一个不能 > 0;
            }

            // 先对 a 去重，第一次的时候不用，从i = 1开始就需要了
            if (i >= 1 && nums[i] == nums[i - 1]) {
                continue;
            }
            // 这里可以加两个优化
            /**
             * 优化一：如果 nums[i] 与后面最小的两个数相加 nums[i]+nums[i+1]+nums[i+2]>0，那么后面不可能存在三数之和等于
             * 0，break 外层循环。
             * 
             * 优化二：如果 nums[i] 与后面最大的两个数相加 nums[i]+nums[n−2]+nums[n−1]<0，那么内层循环不可能存在三数之和等于
             * 0，但继续枚举，nums[i] 可以变大，所以后面还有机会找到三数之和等于 0，continue 外层循环。
             */
            if (nums[i] + nums[i + 1] + nums[i + 2] > 0) {
                break;
            }
            if (nums[i] + nums[len - 1] + nums[len - 2] < 0) {
                continue;
            }

            // 开始双指针
            int left = i + 1;
            int right = nums.length - 1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (sum > 0) {
                    right--;
                } else if (sum < 0) {
                    left++;
                } else {
                    ans.add(List.of(nums[i], nums[left], nums[right]));
                    // 去重 b
                    while (left < right && nums[left] == nums[left + 1]) {
                        left++;
                    }
                    left++;
                    // 去重 c
                    while (left < right && nums[right] == nums[right - 1]) {
                        right--;
                    }
                    right--;

                }
            }
        }
        return ans;
    }

}
