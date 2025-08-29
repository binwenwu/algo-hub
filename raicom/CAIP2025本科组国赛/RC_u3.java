package raicom.CAIP2025本科组国赛;

import java.util.Scanner;

/**
 * 类似于括号匹配的思路，这里是用 StringBuilder 来模拟栈
 */
public class RC_u3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String pingbi = sc.nextLine();
        char c1 = pingbi.charAt(0);
        char c2 = pingbi.charAt(1);
        String test = sc.nextLine();
        StringBuilder sb = new StringBuilder();
        int sum = 0;
        for (int i = 0; i < test.length(); i++) {
            int length = sb.length();
            char curr = test.charAt(i);
            if (length < 1) {
                sb.append(curr);
                continue;
            }
            char top = sb.charAt(length - 1);
            if (top == c1 && curr == c2) {
                sum++;
                sb.setLength(length - 1);
            } else {
                sb.append(curr);
            }
        }

        System.out.println(sum + " " + sb.toString());
        sc.close();
    }
}
