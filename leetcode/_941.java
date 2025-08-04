public class _941 {
    public static void main(String[] args) {

    }

    public boolean validMountainArray(int[] arr) {
        if (arr.length < 3) {
            return false;
        }

        int i = 0;
        for (; i < arr.length - 1; i++) {
            if (arr[i + 1] <= arr[i]) {
                break;
            }
        }
        if (i == 0 || i == arr.length - 1) {
            return false;
        }

        for (; i < arr.length - 1; i++) {
            if (arr[i + 1] >= arr[i]) {
                return false;
            }
        }
        return true;
    }
}
