import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.PriorityQueue;

public class _239 {
    public static void main(String[] args) {
        _239 s = new _239();
        int[] nums = { 1, 3, 1, 2, 0, 5 };
        int k = 3;
        int[] res = s.maxSlidingWindow1(nums, k);
        System.out.println(Arrays.toString(res));
    }

    // 单调队列
    public int[] maxSlidingWindow1(int[] nums, int k) {
        int len = nums.length;
        int[] res = new int[len - k + 1];
        Deque<Integer> stack = new ArrayDeque<>();

        for (int i = 0; i < k; i++) {
            while (!stack.isEmpty() && nums[i] > stack.getLast()) {
                stack.removeLast();
            }
            stack.offerLast(nums[i]);
        }

        res[0] = stack.getFirst();

        for (int i = k; i < len; i++) {

            // 这个地方非常容易漏，这里是判断双端队列的最下面是不是nums[i-k]，如果是，就应该移除掉了
            // 或者可以在stack中不存储值，而是存储数组索引，这样就可以直接比较索引了
            if (nums[i - k] == stack.getFirst()) {
                stack.removeFirst();
            }

            while (!stack.isEmpty() && nums[i] > stack.getLast()) {
                stack.removeLast();
            }

            stack.offerLast(nums[i]);
            res[i - k + 1] = stack.getFirst();
        }

        return res;
    }

    // 优先队列
    public int[] maxSlidingWindow2(int[] nums, int k) {
        PriorityQueue<int[]> queue = new PriorityQueue<>((arr1, arr2) -> {
            return arr1[0] != arr2[0] ? arr2[0] - arr1[0] : arr1[0] - arr2[0];
        });

        for (int i = 0; i < k; i++) {
            queue.offer(new int[] { nums[i], i });
        }

        int[] res = new int[nums.length - k + 1];
        res[0] = queue.peek()[0];
        for (int i = k; i < nums.length; i++) {
            queue.offer(new int[] { nums[i], i });
            while (queue.peek()[1] <= i - k) {
                queue.poll();
            }
            res[i - k + 1] = queue.peek()[0];
        }

        return res;
    }
}
