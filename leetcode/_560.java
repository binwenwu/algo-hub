import java.util.HashMap;
import java.util.Map;

public class _560 {
    public static void main(String[] args) {

    }

    // 前缀和 + 哈希表优化
    public int subarraySum(int[] nums, int k) {
        int res = 0;
        int pre = 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        for (int i = 0; i < nums.length; i++) {
            pre += nums[i];
            if (map.containsKey(pre - k)) {
                res += map.get(pre - k);
            }

            map.put(pre, map.getOrDefault(pre, 0) + 1);
        }

        return res;
    }

}
