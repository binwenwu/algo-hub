

public class _136 {
    public static void main(String[] args) {

    }

    // 异或运算的方法
    public int singleNumber(int[] nums) {
        int single = 0;
        for (int num : nums) {
            single ^= num;
        }
        return single;
    }
}
