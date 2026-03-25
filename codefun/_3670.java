package codefun;

import java.util.*;

/**
 * 完美操作
 *
 * 思路（模拟）：
 * 用一个数组 original 保存原始字符，再用 result 保存修改后的字符。
 * - i = 1：翻转大小写
 * - i >= 2：比较 s_i 与 原始的 s_{i-1} 的大小写：
 *     - 大小写不同：翻转 s_i 的大小写
 *     - 大小写相同：将 s_i 替换为字母表中下一个同大小写字母（z→a, Z→A）
 */
public class _3670 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        String s = sc.next();

        char[] original = s.toCharArray(); // 保存原始字符（用于比较）
        char[] result = s.toCharArray();   // 保存修改后的字符

        // i = 0（对应下标1）：翻转大小写
        result[0] = toggleCase(original[0]);

        for (int i = 1; i < n; i++) {
            // 比较当前字符 s_i 与 原始的 s_{i-1} 的大小写
            boolean curIsUpper = Character.isUpperCase(original[i]);
            boolean prevIsUpper = Character.isUpperCase(original[i - 1]);

            if (curIsUpper != prevIsUpper) {
                // 大小写不同：翻转
                result[i] = toggleCase(original[i]);
            } else {
                // 大小写相同：替换为下一个同大小写字母
                result[i] = nextChar(original[i]);
            }
        }

        System.out.println(new String(result));
        sc.close();
    }

    // 翻转大小写
    private static char toggleCase(char c) {
        return Character.isUpperCase(c) ? Character.toLowerCase(c) : Character.toUpperCase(c);
    }

    // 字母表中下一个同大小写字母，z→a, Z→A
    private static char nextChar(char c) {
        if (c == 'z') return 'a';
        if (c == 'Z') return 'A';
        return (char)(c + 1);
    }
}
