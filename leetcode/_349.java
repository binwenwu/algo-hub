import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class _349 {
    public static void main(String[] args) {

    }


    // 自己一开始的解法
    public int[] intersection1(int[] nums1, int[] nums2) {
        Map<Integer, Integer> map = new HashMap(); // 其实可以把 map 换成 set
        Set<Integer> set = new HashSet<>();

        for (int i = 0; i < nums1.length; i++) {
            map.put(nums1[i], 1);
        }

        for (int i = 0; i < nums2.length; i++) {
            if (map.get(nums2[i]) != null) {
                set.add(nums2[i]);
            }
        }

        int[] res = new int[set.size()];
        int i = 0;
        for (Integer num : set) {
            res[i++] = num;
        }

        return res;
    }


}
