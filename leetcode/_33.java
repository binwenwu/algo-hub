public class _33 {
    public static void main(String[] args) {

    }

    public int search(int[] nums, int target) {
        int minIndex = findMin(nums);
        int firstIndex = find(nums, target, 0, minIndex - 1);
        if (firstIndex == -1) {
            return find(nums, target, minIndex, nums.length - 1);
        } else {
            return firstIndex;
        }

    }

    public int findMin(int[] nums) {
        int n = nums.length;
        int left = 0;
        int right = n - 1;
        while (left < right) {
            int mid = left + ((right - left) >> 1);
            if (nums[mid] < nums[right]) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    private int find(int[] nums, int target, int left, int right) {
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (nums[mid] == target) {
                return mid;
            }

            if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return -1;
    }
}
