import java.util.ArrayList;
import java.util.List;

public class _260 {
    public static void main(String[] args) {
        _260 s = new _260();
        int[] nums = { 1, 2, 1, 3, 2, 5 };
        s.singleNumber1(nums);
    }

    public int[] singleNumber1(int[] nums) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (!list.contains(nums[i])) {
                list.add(nums[i]);
            } else {
                list.remove(Integer.valueOf(nums[i]));
            }
        }

        return new int[] { list.get(0), list.get(1) };
    }

    // 位运算的方法
    public int[] singleNumber2(int[] nums) {
        int xorsum = 0;
        for (int num : nums) {
            xorsum ^= num;
        }
        // 防止溢出
        int lsb = (xorsum == Integer.MIN_VALUE ? xorsum : xorsum & (-xorsum));
        int type1 = 0, type2 = 0;
        for (int num : nums) {
            if ((num & lsb) != 0) {
                type1 ^= num;
            } else {
                type2 ^= num;
            }
        }
        return new int[] { type1, type2 };
    }

}
