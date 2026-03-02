public class _134 {
    public static void main(String[] args) {

    }

    /**
     * 贪心
     * 
     * 每当油量为负，就说明从 start 到 i 没法成功到达
     * 也能说明 start - i 之间的节点也没法作为 start，因为假如是公 start + 1 出发的，
     * start 是能到 start + 1 的，之前带着 start 到 start + 1 的剩余油量都没法到 i，则直接从 start + 1 开始，更没法到
     *
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
