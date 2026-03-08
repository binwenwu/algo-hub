public class _66 {

    /**
     * 从后往前加
     * 遇9变0
     * 遇非9,直接++，并结束
     * 如果是全9，需要新建数组扩容
     */
    public int[] plusOne(int[] digits) {

        for (int i = digits.length - 1; i >= 0; i--) {

            if (digits[i] < 9) {
                digits[i]++;
                return digits;
            }

            digits[i] = 0;
        }

        int[] res = new int[digits.length + 1];
        res[0] = 1;

        return res;
    }
}
