public class _191 {

    /**
     * 每执行一次循环，就消掉一个1。
     */
    public int hammingWeight(int n) {

        int count = 0;

        while (n != 0) {
            n &= (n - 1);
            count++;
        }

        return count;
    }
}
