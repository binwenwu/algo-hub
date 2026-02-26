public class _274 {

    /**
     * 计数排序：
     * 使用一个cnt数组来表示引用次数为i的论文数cnt[i]
     * 因为h指数不可能大于n，所以引用次数 >n 的，等价于引用次数为n，所以cnt数组的大小定义为 n + 1
     * 然后因为要找最大的h，所以从后往前遍历，第二个循环中，每次s累加完后，s表示引用次数为i的论文数
     * 所以只要满足 s >= i 时，就说明找到了最大的h
     */
    public int hIndex(int[] citations) {
        int n = citations.length;
        int[] count = new int[n + 1];
        for (int c : citations) {
            if (c >= n) {
                count[n]++;
            } else {
                count[c]++;
            }
        }
        int s = 0;
        for (int i = n; i >= 0; i--) {
            s += count[i];
            if (s >= i) {
                return i;
            }
        }

        return 0;
    }
}
