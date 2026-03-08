public class _137 {

    /**
     * 
     * 出现3次的数
     * 在每个二进制位上
     * 1 的数量一定是 3 的倍数
     * 
     * 所以只出现两次的第 i 个二进制位就是数组中所有元素的第 i 个二进制位之和除以 3 后的余数
     */
    public int singleNumber(int[] nums) {
        int ans = 0;
        for (int i = 0; i < 32; ++i) {
            int total = 0;
            for (int num : nums) {
                total += ((num >> i) & 1);
            }
            if (total % 3 != 0) {
                ans |= (1 << i);
            }
        }
        return ans;
    }

}
