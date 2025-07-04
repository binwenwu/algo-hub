import java.util.ArrayList;
import java.util.List;

public class _136 {
    public static void main(String[] args) {

    }

    public int singleNumber1(int[] nums) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (!list.contains(nums[i])) {
                list.add(nums[i]);
            } else {
                list.remove(Integer.valueOf(nums[i]));
            }
        }

        return list.get(0);
    }

    // 位运算的方法
    public int singleNumber2(int[] nums) {
        int single = 0;
        for (int num : nums) {
            single ^= num;
        }
        return single;
    }
}
