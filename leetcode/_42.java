import java.util.ArrayDeque;
import java.util.Deque;

public class _42 {
    public static void main(String[] args) {
        _42 s = new _42();
        int[] height = { 0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1 };
        int res = s.trap(height);
        System.out.println(res);
    }

    // 这个写法比较符合单调栈的惯用模板
    public int trap(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }

        int n = height.length;
        // 栈中存储的是索引，对应的高度是单调递减的
        Deque<Integer> stack = new ArrayDeque<>();
        int totalWater = 0;

        for (int i = 0; i < n; i++) {
            // 当遇到一个比栈顶高的柱子时，说明可能形成了凹槽
            while (!stack.isEmpty() && height[i] > height[stack.peek()]) {
                // 坑底的索引
                int bottomIndex = stack.pop();

                // 如果弹出后栈为空，说明左边没有墙，无法存水
                if (stack.isEmpty()) {
                    break;
                }

                // 左墙的索引就是新的栈顶
                int leftWallIndex = stack.peek();

                // 计算宽度
                int width = i - leftWallIndex - 1;

                // 计算高度，取左右墙中较矮的那个
                int boundedHeight = Math.min(height[leftWallIndex], height[i]) - height[bottomIndex];

                // 累加雨水
                totalWater += width * boundedHeight;
            }
            // 当前柱子索引入栈
            stack.push(i);
        }

        return totalWater;
    }

    public int trap2(int[] height) {
        int n = height.length;
        if (n <= 2) {
            return 0;
        }
        int res = 0;
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(0);
        for (int i = 1; i < n; i++) {
            if (height[i] < height[stack.peek()]) {
                stack.push(i);
            } else if (height[i] == height[stack.peek()]) {
                stack.pop();
                stack.push(i);
            } else {
                while (!stack.isEmpty() && height[i] > height[stack.peek()]) {
                    int mid = stack.pop();
                    if (!stack.isEmpty()) {
                        int left = stack.peek();
                        int rain = (i - left - 1) * (Math.min(height[i], height[left]) - height[mid]);
                        if (rain > 0) {
                            res = res + (i - left - 1) * (Math.min(height[i], height[left]) - height[mid]);
                        }

                    }
                }
                stack.push(i);
            }
        }

        return res;
    }

    public int trap3(int[] height) {
        int leftMax = 0;
        int rightMax = 0;
        int left = 0;
        int right = height.length - 1;
        int ans = 0;
        while (left < right) {
            leftMax = Math.max(leftMax, height[left]);
            rightMax = Math.max(rightMax, height[right]);
            if (height[left] < height[right]) {
                ans += leftMax - height[left];
                ++left;
            } else {
                ans += rightMax - height[right];
                --right;
            }
        }

        return ans;
    }
}