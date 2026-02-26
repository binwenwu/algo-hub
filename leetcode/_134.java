public class _134 {
    public static void main(String[] args) {

    }

    /**
     * 贪心
     */
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int total = 0; // 全局油量差
        int current = 0; // 当前起点累计油量
        int start = 0; // 起点

        for (int i = 0; i < gas.length; i++) {
            int diff = gas[i] - cost[i];
            total += diff;
            current += diff;

            // 当前起点失败
            if (current < 0) {
                start = i + 1;
                current = 0;
            }
        }

        return total >= 0 ? start : -1;
    }

}
