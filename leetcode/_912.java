public class _912 {
    public static void main(String[] args) {

    }

    public int[] sortArray(int[] nums) {
        quicksort(nums, 0, nums.length - 1);
        return nums;
    }

    public void quicksort(int[] nums, int l, int r) {
        if (l >= r) {
            return;
        }

        int x = nums[l], i = l - 1, j = r + 1;
        while (true) {
            i++;
            while (nums[i] < x) {
                i++;
            }
            j--;
            while (nums[j] > x) {
                j--;
            }
            if (i >= j) {
                break;
            }
            int tmp = nums[i];
            nums[i] = nums[j];
            nums[j] = tmp;
        }

        quicksort(nums, l, j);
        quicksort(nums, j + 1, r);
    }
}
