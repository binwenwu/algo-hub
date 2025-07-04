import java.util.HashSet;
import java.util.Set;

public class _202 {
    public static void main(String[] args) {

    }

    public boolean isHappy1(int n) {
        if (n == -1 || n == 1) {
            return true;
        }

        Set<Integer> set = new HashSet<>();

        String num = "" + n;

        while (true) {
            int sum = 0;
            for (int i = 0; i < num.length(); i++) {
                int temp = Integer.valueOf(num.charAt(i) + "");
                sum = sum + temp * temp;
            }
            if (sum == 1) {
                return true;
            }

            if (set.contains(sum)) {
                return false;
            }

            set.add(sum);

            num = "" + sum;
        }
    }

    // 其实可以不用字符串String来获取每一位，而是采用对10进行取模来依次获取每一位，每取完一位，就除10
    public boolean isHappy2(int n) {
        if (n == -1 || n == 1) {
            return true;
        }

        Set<Integer> set = new HashSet<>();
        
        while (true) {
            int sum = 0;
            while (n > 0) {
                int temp = n % 10;
                sum = sum + temp * temp;
                n = n / 10;
            }
            
            if (sum == 1) {
                return true;
            }

            if (set.contains(sum)) {
                return false;
            }

            set.add(sum);

            n = sum;
        }
    }
}
