public class _67 {

    /**
     * 从后往前加
     * carry 控制进位
     * 最后 reverse
     */
    public String addBinary(String a, String b) {

        StringBuilder sb = new StringBuilder();

        int i = a.length() - 1;
        int j = b.length() - 1;
        int carry = 0;

        while (i >= 0 || j >= 0 || carry > 0) {

            int num1 = i >= 0 ? a.charAt(i) - '0' : 0;
            i--;
            int num2 = j >= 0 ? b.charAt(j) - '0' : 0;
            j--;

            sb.append((num1 + num2 + carry) % 2);
            carry = (num1 + num2 + carry) / 2;
        }

        return sb.reverse().toString();
    }
}
