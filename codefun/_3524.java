package codefun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _3524 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine().trim());

        StringBuilder out = new StringBuilder();

        for (int t = 0; t < n; t++) {
            String s = br.readLine().trim();

            // 统计 A, L, I 的个数，其他忽略
            int cntA = 0, cntL = 0, cntI = 0;
            for (char c : s.toCharArray()) {
                if (c == 'A')
                    cntA++;
                else if (c == 'L')
                    cntL++;
                else if (c == 'I')
                    cntI++;
            }

            // 按 ALI 循环输出
            StringBuilder sb = new StringBuilder();
            while (cntA > 0 || cntL > 0 || cntI > 0) {
                if (cntA > 0) {
                    sb.append('A');
                    cntA--;
                }
                if (cntL > 0) {
                    sb.append('L');
                    cntL--;
                }
                if (cntI > 0) {
                    sb.append('I');
                    cntI--;
                }
            }

            out.append(sb.length() == 0 ? "-1" : sb.toString()).append('\n');
        }

        System.out.print(out);
    }
}
