import java.util.HashMap;
import java.util.Map;

public class _560 {
    public static void main(String[] args) {

    }

    // 暴力解法
    public int subarraySum1(int[] nums, int k) {
        int res = 0;
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            int sum = 0;
            for (int j = i; j < len; j++) {
                sum = sum + nums[j];
                if (sum == k) {
                    res++;
                }
            }
        }

        return res;
    }

    // 前缀和 + 哈希表优化
    public int subarraySum2(int[] nums, int k) {
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
