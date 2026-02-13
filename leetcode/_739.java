import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class _739 {
    public static void main(String[] args) {
        _739 s = new _739();
        int[] temperatures = { 73, 74, 75, 71, 69, 72, 76, 73 };
        int[] result = s.dailyTemperatures(temperatures);
        System.out.println(Arrays.toString(result));
    }

    public int[] dailyTemperatures(int[] temperatures) {
        int[] result = new int[temperatures.length];
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < temperatures.length; i++) {
            while (!stack.isEmpty() && temperatures[i] > temperatures[stack.peek()]) {
                int index = stack.pop();
                result[index] = i - index;
            }
            stack.push(i);
        }

        return result;
    }
}
