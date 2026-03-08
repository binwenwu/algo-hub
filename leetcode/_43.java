public class _43 {

    /**
     * 创建数组 res[m+n]
     * 从后向前遍历两个字符串
     * 每次计算乘积
     * 加到对应位置
     * 处理进位
     * 转换为字符串
     */
    public String multiply(String num1, String num2) {
        int m = num1.length();
        int n = num2.length();

        int[] res = new int[m + n];

        for (int i = m - 1; i >= 0; i--) {
            int a = num1.charAt(i) - '0';

            for (int j = n - 1; j >= 0; j--) {
                int b = num2.charAt(j) - '0';

                int sum = res[i + j + 1] + a * b;

                /**
                 * 乘积最多两位数：
                 * 十位 -> res[i+j]
                 * 个位 -> res[i+j+1]
                 */
                res[i + j + 1] = sum % 10;
                res[i + j] += sum / 10;
            }
        }

        StringBuilder sb = new StringBuilder();
        // 跳过前导0
        int index = 0;
        for (; index < res.length; index++) {
            if (res[index] != 0) {
                break;
            }
        }

        for (; index < res.length; index++) {
            sb.append(res[index]);
        }

        return sb.length() == 0 ? "0" : sb.toString();
    }
}
