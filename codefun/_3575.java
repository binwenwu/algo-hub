package codefun;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class _3575 {

    // 枚举每一对 (r位置, d位置)，中间有多少个 e，就贡献多少个 2*(d位置 - r位置)
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine().trim());
        String s = br.readLine().trim();

        // 预处理 e 的前缀和
        int[] prefE = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            prefE[i] = prefE[i - 1] + (s.charAt(i - 1) == 'e' ? 1 : 0);
        }

        // 收集 r 和 d 的位置（下标从1开始）
        List<Integer> rPos = new ArrayList<>();
        List<Integer> dPos = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            char c = s.charAt(i - 1);
            if (c == 'r')
                rPos.add(i);
            else if (c == 'd')
                dPos.add(i);
        }

        long ans = 0;
        for (int r : rPos) {
            for (int d : dPos) {
                if (r < d) {
                    // r 和 d 之间（不含端点）e 的个数
                    int eCnt = prefE[d - 1] - prefE[r];
                    ans += 2L * (d - r) * eCnt;
                }
            }
        }

        System.out.println(ans);
    }
}
