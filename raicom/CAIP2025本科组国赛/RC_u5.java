package raicom.CAIP2025本科组国赛;

import java.util.Scanner;

public class RC_u5 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int K = sc.nextInt();
        int[][] steps = new int[N][3];
        for (int i = 0; i < N; i++) {
            steps[i][0] = sc.nextInt();
            steps[i][1] = sc.nextInt();
            steps[i][2] = sc.nextInt();
        }
        sc.close();
    }
}
