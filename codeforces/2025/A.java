import java.util.Scanner;

public class A {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int q = scanner.nextInt();
        scanner.nextLine(); // 消费换行符

        for (int i = 0; i < q; i++) {
            String s = scanner.nextLine();
            String t = scanner.nextLine();

            System.out.println(solve(s, t));
        }

        scanner.close();
    }

    public static int solve(String s, String t) {
        // 找到最长公共前缀
        int commonPrefixLength = 0;
        int minLength = Math.min(s.length(), t.length());

        for (int i = 0; i < minLength; i++) {
            if (s.charAt(i) == t.charAt(i)) {
                commonPrefixLength++;
            } else {
                break;
            }
        }

        // 策略1：直接分别输入两个字符串
        int strategy1 = s.length() + t.length();

        // 策略2：利用公共前缀
        int strategy2;
        if (commonPrefixLength == 0) {
            // 没有公共前缀，策略2就是策略1
            strategy2 = strategy1;
        } else {
            // 输入公共前缀 + 复制 + 完成剩余部分
            strategy2 = commonPrefixLength + 1 + (s.length() - commonPrefixLength) + (t.length() - commonPrefixLength);
        }

        return Math.min(strategy1, strategy2);
    }
}
