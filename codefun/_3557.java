package codefun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _3557 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine().trim());

        int ans = 0;
        for (int i = 0; i < n; i++) {
            br.readLine(); // 读取长度（不需要用）
            String s = br.readLine().trim();

            if (isLiked(s))
                ans++;
        }

        System.out.println(ans);
    }

    static boolean isLiked(String s) {
        int len = s.length();

        // 条件1：首字母大写
        if (!Character.isUpperCase(s.charAt(0)))
            return false;

        // 条件2：末字母小写
        if (!Character.isLowerCase(s.charAt(len - 1)))
            return false;

        // 条件3：不能同时存在某字母的大小写
        boolean[] has = new boolean[26]; // 出现了大小写

        for (char c : s.toCharArray()) {
            int idx = 0;
            if (Character.isLowerCase(c)) {
                idx = c - 'a';
            } else {
                idx = c - 'A';
            }
            if (has[idx]) {
                return false;
            }
            has[idx] = true;
        }

        return true;
    }
}
