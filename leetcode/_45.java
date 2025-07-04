public class _45 {
    public static void main(String[] args) {

    }

    // 贪心（方式一）
    public int jump1(int[] nums) {
        if (nums.length == 1) {
            return 0;
        }
        int count = 0;
        int currMaxRange = 0;
        int maxRange = 0;

        for (int i = 0; i < nums.length; i++) {
            maxRange = Math.max(maxRange, i + nums[i]);
            if (maxRange >= nums.length - 1) {
                count++;
                break;
            }
            if (i == currMaxRange) {
                currMaxRange = maxRange;
                count++;
            }
        }

        return count;
    }

    // 贪心（方式二）
    public int jump2(int[] nums) {
        int length = nums.length;
        int currMaxRange = 0;
        int maxRange = 0;
        int count = 0;
        for (int i = 0; i < length - 1; i++) {
            maxRange = Math.max(maxRange, i + nums[i]);
            if (i == currMaxRange) {
                currMaxRange = maxRange;
                count++;
            }
        }
        return count;
    }

}
