import java.util.Arrays;

public class _977 {
    public static void main(String[] args) {
        _977 s = new _977();
        int[] nums = { -4, -1, 0, 3, 10 };
        int[] res = s.sortedSquares1(nums);
        System.out.println(Arrays.toString(res));
    }

    // 暴力解法
    public int[] sortedSquares1(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            nums[i] = nums[i] * nums[i];
        }
        Arrays.sort(nums);
        return nums;
    }

    // 双指针法，这里的关键就是数组平方的最大值就在数组两端
    /**
     * 数组其实是有序的， 只不过负数平方之后可能成为最大数了。
     * 那么数组平方的最大值就在数组的两端，不是最左边就是最右边，不可能是中间。
     * 此时可以考虑双指针法了，i指向起始位置，j指向终止位置。
     * 定义一个新数组result，和A数组一样的大小，让k指向result数组终止位置。
     * 如果A[i] * A[i] < A[j] * A[j] 那么result[k--] = A[j] * A[j]; 。
     * 如果A[i] * A[i] >= A[j] * A[j] 那么result[k--] = A[i] * A[i]; 。
     */
    public int[] sortedSquares2(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        int[] result = new int[nums.length];
        int index = nums.length - 1;
        while (left <= right) {
            if (nums[left] * nums[left] > nums[right] * nums[right]) {
                result[index] = nums[left] * nums[left];
                index--;
                left++;
            } else {
                result[index] = nums[right] * nums[right];
                index--;
                right--;
            }
        }
        return result;
    }
}
