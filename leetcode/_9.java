public class _9 {
    public static void main(String[] args) {

    }

    public boolean isPalindrome(int x) {
        char[] arr = (x + "").toCharArray();
        int left = 0;
        int right = arr.length - 1;
        while (left < right) {
            if (arr[left] != arr[right]) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}
