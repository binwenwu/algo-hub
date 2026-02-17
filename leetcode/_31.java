public class _31 {
    public static void main(String[] args) {

    }

    /**
     * 1. 从右往左找到第一个小于右边的数 nums[i]；
     * 2. 如果找不到，说明整个num已经是最大字段序了，直接反转全部编程最小字典序
     * 3. 如果找到了，从右往左找到第一个比 nums[i] 大的数 nums[j]，然后交换，交换后，把 i + 1 开始到结尾的降序列反转，编程升序列
     * 
     * @param nums
     */
    public void nextPermutation(int[] nums) {
        int i = nums.length - 2;
        while (i >= 0 && nums[i] >= nums[i + 1]) {
            i--;
        }
        if (i >= 0) {
            int j = nums.length - 1;
            while (j >= 0 && nums[j] <= nums[i]) {
                j--;
            }
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }
        reverse(nums, i + 1, nums.length - 1);
    }

    private void reverse(int[] nums, int left, int right) {
        while (left < right) {
            int temp = nums[left];
            nums[left] = nums[right];
            nums[right] = temp;
            left++;
            right--;
        }
    }
}
