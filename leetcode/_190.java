public class _190 {

    /**
     * 每次取最低位
     * 结果左移补上
     * 循环32次
     */
    public int reverseBits(int n) {

        int res = 0;

        for (int i = 0; i < 32; i++) {
            res <<= 1;
            res |= (n & 1);
            n >>= 1;
        }

        return res;
    }
}
