import java.util.HashSet;
import java.util.Set;

public class _128 {
    public static void main(String[] args) {

    }

    public int longestConsecutive(int[] nums) {
        Set<Integer> set = new HashSet<>();
        int ans = 0;
        // 先将nums转为set进行去重
        for (int num : nums) {
            set.add(num);
        }
        for (int num : set) {
            if (set.contains(num - 1)) {
                continue;
            }
            int y = num + 1;
            while (set.contains(y)) {
                y++;
            }
            ans = Math.max(ans, y - num);
        }

        return ans;
    }

    // 优化
    public int longestConsecutive2(int[] nums) {
        Set<Integer> set = new HashSet<>();
        int ans = 0;
        // 先将nums转为set进行去重
        for (int num : nums) {
            set.add(num);
        }
        int len = set.size();
        for (int num : set) {
            if (set.contains(num - 1)) {
                continue;
            }
            int y = num + 1;
            while (set.contains(y)) {
                y++;
            }
            ans = Math.max(ans, y - num);
            if (ans >= len / 2) {
                return ans;
            }
        }

        return ans;
    }
}
