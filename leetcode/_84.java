import java.util.ArrayDeque;
import java.util.Deque;

public class _84 {
    public static void main(String[] args) {

    }

    public int largestRectangleArea1(int[] heights) {
        // 处理边界情况
        if (heights == null || heights.length == 0) {
            return 0;
        }

        int n = heights.length;

        int[] extendedHeights = new int[n + 1];
        System.arraycopy(heights, 0, extendedHeights, 0, n);
        extendedHeights[n] = 0; // 在末尾设置高度为0的哨兵

        // 使用 Deque 作为栈，存储的是 extendedHeights 的索引
        Deque<Integer> stack = new ArrayDeque<>();

        int maxArea = 0;

        for (int r = 0; r < extendedHeights.length; r++) {

            // 当栈不为空，且当前柱子高度小于等于栈顶柱子高度时，
            // 说明以栈顶柱子为高的矩形的右边界已经找到，就是 r。
            while (!stack.isEmpty() && extendedHeights[r] < extendedHeights[stack.peek()]) {
                // 弹出栈顶，这个就是要计算面积的柱子的索引
                int mid = stack.pop();
                int h = extendedHeights[mid]; // 矩形的高度

                // 左边界 l 是弹出后，新的栈顶。
                // 如果栈为空，说明左边没有更矮的了，左边界视为 -1。
                int l = stack.isEmpty() ? -1 : stack.peek(); // 或者就是在开头也添加一个哨兵，这样就不用判断这个了，如解法二

                // 计算宽度 (右边界 - 左边界 - 1)
                int w = r - l - 1;

                // 更新最大面积
                maxArea = Math.max(maxArea, h * w);
            }

            // 将当前柱子的索引压入栈中
            stack.push(r);
        }

        return maxArea;
    }

    public int largestRectangleArea2(int[] heights) {
        // 处理边界情况
        if (heights == null || heights.length == 0) {
            return 0;
        }

        int n = heights.length;

        int[] extendedHeights = new int[n + 2];
        System.arraycopy(heights, 0, extendedHeights, 1, n);
        extendedHeights[0] = 0;
        extendedHeights[n + 1] = 0; // 在末尾设置高度为0的哨兵

        // 使用 Deque 作为栈，存储的是 extendedHeights 的索引
        Deque<Integer> stack = new ArrayDeque<>();

        int maxArea = 0;

        for (int r = 0; r < extendedHeights.length; r++) {

            // 当栈不为空，且当前柱子高度小于等于栈顶柱子高度时，
            // 说明以栈顶柱子为高的矩形的右边界已经找到，就是 r。
            while (!stack.isEmpty() && extendedHeights[r] <= extendedHeights[stack.peek()]) {
                // 弹出栈顶，这个就是要计算面积的柱子的索引
                int mid = stack.pop();
                int h = extendedHeights[mid]; // 矩形的高度

                // 左边界 l 是弹出后，新的栈顶。
                // 如果栈为空，说明左边没有更矮的了，左边界视为 -1。
                int l = stack.peek(); // 或者就是在开头也添加一个哨兵，这样就不用判断这个了，如解法二

                // 计算宽度 (右边界 - 左边界 - 1)
                int w = r - l - 1;

                // 更新最大面积
                maxArea = Math.max(maxArea, h * w);
            }

            // 将当前柱子的索引压入栈中
            stack.push(r);
        }

        return maxArea;
    }
}
