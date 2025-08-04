public class _922 {
    public static void main(String[] args) {

    }

    public int[] sortArrayByParityII1(int[] nums) {
        int[] numsCopy = new int[nums.length];
        int a = 0;
        int b = 1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] % 2 == 0) {
                numsCopy[a] = nums[i];
                a += 2;
            } else {
                numsCopy[b] = nums[i];
                b += 2;
            }
        }

        return numsCopy;
    }


    // 方法二：双指针
    public int[] sortArrayByParityII2(int[] nums) {
        int n = nums.length;
        int j = 1;
        for (int i = 0; i < n; i += 2) {
            if (nums[i] % 2 == 1) {
                while (nums[j] % 2 == 1) {
                    j += 2;
                }
                swap(nums, i, j);
            }
        }
        return nums;

    }

    public void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

}
