public class _415 {

    /**
     * 从低位开始，并记录进位
     */
    public String addStrings(String num1, String num2) {

        int i = num1.length() - 1;
        int j = num2.length() - 1;

        int carry = 0;

        StringBuilder res = new StringBuilder();

        while (i >= 0 || j >= 0) {

            int n1 = i >= 0 ? num1.charAt(i) - '0' : 0;
            int n2 = j >= 0 ? num2.charAt(j) - '0' : 0;

            int sum = n1 + n2 + carry;

            res.append(sum % 10);

            carry = sum / 10;

            i--;
            j--;
        }
        
        if (carry > 0) {
            res.append(carry);
        }

        return res.reverse().toString();
    }
}
