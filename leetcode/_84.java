import java.util.ArrayDeque;
import java.util.Deque;

public class _84 {
    public static void main(String[] args) {

    }

    // 双指针写法
    public int largestRectangleArea1(int[] heights) {
        int length = heights.length;
        int[] minLeftIndex = new int[length];
        int[] minRightIndex = new int[length];
        // 记录每个柱子左边第一个小于该柱子的下标
        minLeftIndex[0] = -1;
        for (int i = 1; i < length; i++) {
            int t = i - 1;
            // 这里不是用if，而是不断向左寻找的过程
            while (t >= 0 && heights[t] >= heights[i])
                t = minLeftIndex[t];
            minLeftIndex[i] = t;
        }
        // 记录每个柱子右边第一个小于该柱子的下标
        minRightIndex[length - 1] = length;
        for (int i = length - 2; i >= 0; i--) {
            int t = i + 1;
            while (t < length && heights[t] >= heights[i])
                t = minRightIndex[t];
            minRightIndex[i] = t;
        }
        // 求和
        int result = 0;
        for (int i = 0; i < length; i++) {
            // 以每个柱子为“最矮柱子”，然后向左右两边扩展，看看它能延伸到多远（直到遇到比它更矮的柱子）
            int sum = heights[i] * (minRightIndex[i] - minLeftIndex[i] - 1);
            result = Math.max(sum, result);
        }
        return result;
    }

    // 单调栈精简写法
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
