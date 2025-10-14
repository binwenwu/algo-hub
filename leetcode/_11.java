public class _11 {
    public static void main(String[] args) {

    }

    // 双指针
    public int maxArea1(int[] height) {
        int ans = 0;
        int left = 0;
        int right = height.length - 1;
        while (left < right) {
            int area = (right - left) * Math.min(height[left], height[right]);
            ans = Math.max(ans, area);
            if (height[left] <= height[right]) {
                left++;
            } else {
                right--;
            }
        }

        return ans;
    }

    
}
