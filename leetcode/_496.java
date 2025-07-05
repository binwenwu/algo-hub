import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

public class _496 {
    public static void main(String[] args) {

    }

    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int n1 = nums1.length;
        int n2 = nums2.length;
        int[] res = new int[n1];
        Arrays.fill(res, -1);
        Map<Integer, Integer> map = new HashMap<>();
        Deque<Integer> stack = new ArrayDeque<>();

        for (int i = 0; i < n1; i++) {
            map.put(nums1[i], i);
        }

        for (int i = 0; i < n2; i++) {
            while (!stack.isEmpty() && nums2[i] > nums2[stack.peek()]) {
                int temp = stack.pop();
                if (map.get(nums2[temp]) != null) {
                    res[map.get(nums2[temp])] = nums2[i];
                }
            }
            stack.push(i);
        }

        return res;
    }
}
