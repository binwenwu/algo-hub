public class _134 {
    public static void main(String[] args) {

    }

    // 暴力解法
    public int canCompleteCircuit1(int[] gas, int[] cost) {
        int n = gas.length;
        for (int i = 0; i < n; i++) {
            int rest = gas[i] - cost[i]; // 当前剩余油量
            int index = (i + 1) % n;
            while (rest > 0 && index != i) { // 模拟从第 i 个加油站出发
                rest += gas[index] - cost[index];
                index = (index + 1) % n;
            }
            if (rest >= 0 && index == i) {
                return i; // 成功绕一圈
            }
        }
        return -1; // 无法绕一圈
    }

    // 贪心（方式一）
    public int canCompleteCircuit2(int[] gas, int[] cost) {
        int Sum = 0;
        int temp = 0;
        int result = 0;
        for (int i = 0; i < gas.length; i++) {
            temp += (gas[i] - cost[i]);
            Sum += (gas[i] - cost[i]);
            if (temp < 0) {
                temp = 0;
                result = i + 1;
            }
        }
        if (Sum < 0) {
            return -1;
        }
        return result;
    }

         

}
