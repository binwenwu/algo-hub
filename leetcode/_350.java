import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class _350 {
    public static void main(String[] args) {

    }

    // 我的方法
    public int[] intersect1(int[] nums1, int[] nums2) {
        int[] arr1 = new int[1001];
        int[] arr2 = new int[1001];
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < nums1.length; i++) {
            arr1[nums1[i]]++;
        }

        for (int i = 0; i < nums2.length; i++) {
            arr2[nums2[i]]++;
        }
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != 0 && arr2[i] != 0) {
                int num = Math.min(arr1[i], arr2[i]);
                for (int j = 0; j < num; j++) {
                    list.add(i);
                }
            }
        }

        int[] res = list.stream().mapToInt(Integer::intValue).toArray();

        return res;
    }

    // 采用 HashMap
    public int[] intersect2(int[] nums1, int[] nums2) {
        Map<Integer, Integer> map = new HashMap<>();
        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < nums1.length; i++) {
            map.put(nums1[i], map.getOrDefault(nums1[i], 0) + 1);
        }

        for (int i = 0; i < nums2.length; i++) {
            if (map.get(nums2[i]) > 0) {
                list.add(nums2[i]);
                map.put(nums1[i], map.get(nums2[i]) - 1);
            }
        }

        int[] res = list.stream().mapToInt(Integer::intValue).toArray();

        return res;
    }
}
