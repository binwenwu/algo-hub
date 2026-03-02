public class _88 {
    public static void main(String[] args) {

    }

    // 逆向双指针
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = m - 1; // nums1 有效部分尾
        int j = n - 1; // nums2 尾
        int k = m + n - 1; // 填充位置

        while (i >= 0 && j >= 0) {
            if (nums1[i] > nums2[j]) {
                nums1[k--] = nums1[i--];
            } else {
                nums1[k--] = nums2[j--];
            }
        }

        // 如果 nums2 还有剩
        while (j >= 0) {
            nums1[k--] = nums2[j--];
        }
    }
}
