public class _287 {
    public static void main(String[] args) {

    }

    // 环形链表的思路， 参考 leetcode-142
    public int findDuplicate(int[] nums) {
        int slow = 0;
        int fast = 0;
        int start = 0;
        while (true) {
            slow = nums[slow];
            fast = nums[nums[fast]];
            if (slow == fast) {
                while (start != slow) {
                    start = nums[start];
                    slow = nums[slow];
                }
                return slow;
            }
        }
    }
}
