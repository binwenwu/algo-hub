public class _55 {
    public static void main(String[] args) {

    }

    public boolean canJump(int[] nums) {
        if (nums.length == 1) {
            return true;
        }

        int maxRange = 0;

        for (int i = 0; i <= maxRange; i++) {
            maxRange = Math.max(maxRange, i + nums[i]);
            if (maxRange >= nums.length - 1) {
                return true;
            }
        }

        return false;
    }

}
