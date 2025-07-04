public class _283 {
    public static void main(String[] args) {

    }

    public void moveZeroes1(int[] nums) {
        int len = nums.length;
        if (len == 1) {
            return;
        }

        int slow = 0;
        int fast = 0;
        while (fast < len) {
            if (nums[fast] != 0) {
                int temp = nums[slow];
                nums[slow] = nums[fast];
                nums[fast] = temp;
                slow++;
                fast++;
            } else {
                fast++;
            }
        }


    }
}
