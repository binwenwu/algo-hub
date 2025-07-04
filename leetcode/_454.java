import java.util.HashMap;
import java.util.Map;

public class _454 {
    public static void main(String[] args) {

    }

    public int fourSumCount1(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        Map<Integer, Integer> map = new HashMap<>();
        int res = 0;
        int n = nums1.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                map.put(nums1[i] + nums2[j], map.getOrDefault(nums1[i] + nums2[j], 0) + 1);
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int temp = nums3[i] + nums4[j];
                if (map.get(0 - temp) != null) {
                    res = res + map.get(0 - temp);
                }
            }
        }

        return res;
    }
}
