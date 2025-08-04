public class _724 {
    public static void main(String[] args) {

    }

    public int pivotIndex1(int[] nums) {
        int sum = 0;
        for (int i : nums) {
            sum += i;
        }

        int leftSum = 0;
        for (int i = 0; i < nums.length; i++) {
            // 如果左右两边的合相等
            if (leftSum == sum - leftSum - nums[i]) {
                return i;
            }
            leftSum += nums[i];
        }


        return -1;
    }
}