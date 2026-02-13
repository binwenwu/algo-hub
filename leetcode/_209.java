public class _209 {
    public static void main(String[] args) {
        _209 s = new _209();
        int[] nums = { 2, 3, 1, 2, 4, 3 };
        int target = 7;
        int minSize = s.minSubArrayLen(target, nums);
        System.out.println("minSize: " + minSize);
    }

    // 滑动窗口
    public int minSubArrayLen(int target, int[] nums) {
        int minSize = nums.length + 1;
        int sum = 0;
        int left = 0;
        for (int right = 0; right < nums.length; right++) {
            sum += nums[right];
            while (sum >= target) {
                minSize = Math.min(minSize, right - left + 1);
                sum -= nums[left];
                left++;
            }
        }
        return minSize == nums.length + 1 ? 0 : minSize;
    }

}
