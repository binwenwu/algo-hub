public class _75 {
    public static void main(String[] args) {

    }

    /**
     * 我们可以考虑对数组进行两次遍历：
     * 在第一次遍历中，我们将数组中所有的 0 交换到数组的头部，
     * 在第二次遍历中，我们将数组中所有的 1 交换到头部的 0，
     * 之后，此时，所有的 2 都出现在数组的尾部，这样我们就完成了排序；
     */
    public void sortColors(int[] nums) {
        int n = nums.length;
        int ptr = 0;
        for (int i = 0; i < n; ++i) {
            if (nums[i] == 0) {
                int temp = nums[i];
                nums[i] = nums[ptr];
                nums[ptr] = temp;
                ++ptr;
            }
        }
        for (int i = ptr; i < n; ++i) {
            if (nums[i] == 1) {
                int temp = nums[i];
                nums[i] = nums[ptr];
                nums[ptr] = temp;
                ++ptr;
            }
        }
    }

}
