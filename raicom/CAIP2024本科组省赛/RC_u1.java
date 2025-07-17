package raicom.CAIP2024本科组省赛;

import java.util.Scanner;

public class RC_u1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int W = sc.nextInt();
        int res1 = 0;
        int res2 = 0;
        for (int i = 0; i < N; i++) {
            int temp = sc.nextInt();
            if (temp >= 35) {
                if ((i + W) % 7 != 4) {
                    res1++;
                } else {
                    res2++;
                }
            }
        }

        System.out.println(res1 + " " + res2);

        sc.close();
    }
}
