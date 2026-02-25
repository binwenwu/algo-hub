public class _80 {
    public static void main(String[] args) {

    }

    public int removeDuplicates(int[] nums) {
        int slow = 2, fast = 2;
        while (fast < nums.length) {
            if (nums[fast] != nums[slow - 2]) {
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
