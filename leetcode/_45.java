public class _45 {
    public static void main(String[] args) {
        _45 s = new _45();
        System.out.println(s.jump(new int[] { 1, 0 }));
    }

    // 贪心
    public int jump(int[] nums) {
        int currMaxRange = 0;
        int maxRange = 0;
        int count = 0;
        /**
         * 当我们已经到达最后一个位置时，不需要再跳了，
         * 如果遍历到 nums.length，可能会多算一次跳跃次数
         */
        for (int i = 0; i < nums.length - 1; i++) {
            maxRange = Math.max(maxRange, i + nums[i]);
            if (i == currMaxRange) {
                currMaxRange = maxRange;
                count++;
            }
        }
        return count;
    }

}
