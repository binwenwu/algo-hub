public class _541 {
    public static void main(String[] args) {

    }

    public String reverseStr1(String s, int k) {
        int length = s.length();
        char[] arr = s.toCharArray();
        int left = 0;
        int right = 0;

        for (int i = 0; i < length; i = i + 2 * k) {
            left = i;
            if (length - i > 2 * k) {
                right = i + k - 1;
            } else if (length - i < k) {
                right = length - 1;
            } else {
                right = i + k - 1;
            }

            while (left < right) {
                char temp = arr[left];
                arr[left] = arr[right];
                arr[right] = temp;
                left++;
                right--;
            }
        }

        return String.valueOf(arr);
    }

}
