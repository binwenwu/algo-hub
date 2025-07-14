package raicom.CAIP2024本科组省赛;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class RC_u5 {

    static class Job implements Comparable<Job> {
        int t, d, p;

        public Job(int t, int d, int p) {
            this.t = t;
            this.d = d;
            this.p = p;
        }

        // 实现Comparable接口，用于排序
        // 按截止日期 d 从小到大排序
        @Override
        public int compareTo(Job other) {
            return this.d - other.d;
        }
    }

    public static void main(String[] args) throws IOException {
        // 使用 BufferedReader 加速输入
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(reader.readLine());
        while (T-- > 0) {
            solve(reader);
        }
    }

    public static void solve(BufferedReader reader) throws IOException {
        int N = Integer.parseInt(reader.readLine());
        Job[] jobs = new Job[N];
        int maxD = 0;
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(reader.readLine());
            int t = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int p = Integer.parseInt(st.nextToken());
            jobs[i] = new Job(t, d, p);
            if (d > maxD) {
                maxD = d;
            }
        }

        // 1. 按截止日期排序
        Arrays.sort(jobs);

        // 2. DP数组，dp[j]表示花费总时间为j能获得的最大报酬
        // 数组大小可以是 N*t_max，但更紧凑的是 max_d
        // 题目中 d_i <= 5000, N <= 5000, t_i <= 5000
        // maxD 最大可达5000，dp数组大小开到5001即可
        int[] dp = new int[maxD + 1];

        // 3. 状态转移
        for (int i = 0; i < N; i++) {
            int t = jobs[i].t;
            int d = jobs[i].d;
            int p = jobs[i].p;

            // 逆序遍历，这是0/1背包的核心
            for (int j = d; j >= t; j--) {
                dp[j] = Math.max(dp[j], dp[j - t] + p);
            }
        }

        // 4. 找到全局最大值
        int maxProfit = 0;
        for (int profit : dp) {
            if (profit > maxProfit) {
                maxProfit = profit;
            }
        }
        System.out.println(maxProfit);
    }
}
