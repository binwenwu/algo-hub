public class _26 {
    public static void main(String[] args) {

    }
    
    public int removeDuplicates1(int[] nums) {
        int len = nums.length;
        if (len == 1) {
            return 1;
        }
        int slow = 1;
        int fast = 1;
        while (fast < len) {
            if (nums[fast] != nums[fast - 1]) {
                nums[slow] = nums[slow];
                slow++;
                fast++;
            } else {
                fast++;
            }
        }

        return slow;
    }
}
