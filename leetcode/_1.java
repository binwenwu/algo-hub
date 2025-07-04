import java.util.HashMap;
import java.util.Map;

public class _1 {
    public static void main(String[] args) {

    }

    // 暴力解法
    public int[] twoSum1(int[] nums, int target) {
        int length = nums.length;
        int[] arr = new int[2];
        for (int i = 0; i < length; i++) {
            for (int j = i + 1; j < length; j++) {
                int sum = nums[i] + nums[j];
                if (sum == target) {
                    arr[0] = i;
                    arr[1] = j;
                }
            }
        }
        return arr;
    }

    /**
     * hashmap 解法
     * 
     * 这样我们创建一个哈希表，对于每一个 x，我们首先查询哈希表中是否存在 target - x，然后将 x 插入到哈希表中
     */
    public int[] twoSum2(int[] nums, int target) {
        Map<Integer, Integer> hashtable = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; ++i) {
            if (hashtable.containsKey(target - nums[i])) {
                return new int[] { hashtable.get(target - nums[i]), i };
            }
            hashtable.put(nums[i], i);
        }
        return new int[0];
    }

    // 回溯算法练习
    public void backtracking() {

    }

}
