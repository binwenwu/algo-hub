public class _201 {

    /**
     * 
     * 
     * 只有高位公共前缀才能保留下来,后面的位会变化,后面的位不断变化从而使得left逐渐增加到right
     * 
     * 我们通过右移，将两个数字压缩为它们的公共前缀。在迭代过程中，我们计算执行的右移操作数。
     * 将得到的公共前缀左移相同的操作数得到结果。
     * 
     */
    public int rangeBitwiseAnd(int left, int right) {

        int shift = 0;

        while (left < right) {
            left >>= 1;
            right >>= 1;
            shift++;
        }

        return left << shift;
    }
}
