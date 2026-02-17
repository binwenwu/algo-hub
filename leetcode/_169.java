import java.util.Arrays;

public class _169 {
    public static void main(String[] args) {

    }

    // 排序法
    public int majorityElement1(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length >> 1];
    }

    /**
     * 摩尔投票
     * 从第一个数开始假设是众数，然后开始遍历，每遇到等于假定众数的，vote++，否则vote--；
     * 当 vote=0 时，不论假定的众数是不是最终的众数，都不会抵消超过一半的实际众数，不会影响右边序列的众数结果，所以可以重新假定众数；
     * 最后一轮假定的众数，就是最终答案；
     */
    public int majorityElement2(int[] nums) {
        int x = nums[0], votes = 0;
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
