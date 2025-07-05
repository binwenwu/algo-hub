import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class _503 {
    public static void main(String[] args) {
        _503 s = new _503();
        int[] nums = { 1, 2, 1 };
        int[] res = s.nextGreaterElements1(nums);
        System.out.println(Arrays.toString(res));
    }


    // 方法一
    public int[] nextGreaterElements1(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        Arrays.fill(res, -1);
        Deque<Integer> stack = new ArrayDeque<>();

        for (int i = 0; i < 2 * n; i++) {
            while (!stack.isEmpty() && nums[i % n] > nums[stack.peek()]) {
                int temp = stack.pop();
                res[temp] = nums[i % n];
            }
            stack.push(i % n);
        }

        return res;
    }
}
