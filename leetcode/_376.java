public class _376 {
    public static void main(String[] args) {

    }

    // 自己写的贪心算法
    public int wiggleMaxLength1(int[] nums) {
        if (nums.length == 1) {
            return 1;
        }

        int preDiff = 0;
        int maxLength = 0;
        int preIndex = 0;

        for (int i = 1; i < nums.length; i++) {
            if (preDiff == 0) {
                preDiff = nums[i] - nums[preIndex];
                preIndex = i;
                if (preDiff != 0) {
                    maxLength++;
                }
            } else {
                int diff = nums[i] - nums[preIndex];
                if (diff * preDiff < 0) {
                    preDiff = diff;
                    preIndex = i;
                    maxLength++;
                } else {
                    preIndex = i;
                }
            }
        }

        return maxLength + 1;
    }

    /*
     * 
     * ✅ 精简思路
     * 初始化 preDiff = 0（记录前一个差值）
     * 遍历数组，从第 1 项开始
     * 每次计算 diff = nums[i] - nums[i - 1]
     * 如果 diff 和 preDiff 符号相反（diff * preDiff < 0），说明是“摆动”，count++
     * 如果 preDiff == 0，首次非零也算“开始摆动”
     * 然后更新 preDiff = diff
     */
    public int wiggleMaxLength2(int[] nums) {
        if (nums.length == 1) {
            return 1;
        }

        int preDiff = 0;
        int count = 1; // 至少有一个元素

        for (int i = 1; i < nums.length; i++) {
            int diff = nums[i] - nums[i - 1];
            if ((diff > 0 && preDiff <= 0) || (diff < 0 && preDiff >= 0)) {
                count++;
                preDiff = diff;
            }
        }

        return count;
    }

}
