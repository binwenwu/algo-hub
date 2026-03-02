public class _26 {
    public static void main(String[] args) {

    }

    // 双指针
    public int removeDuplicates(int[] nums) {
        int slow = 1, fast = 1;
        while (fast < nums.length) {
            if (nums[fast] != nums[slow - 1]) {
                nums[slow] = nums[fast];
                slow++;
                fast++;
            } else {
                fast++;
            }
        }
        return slow;
    }
}
