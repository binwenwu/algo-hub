package raicom.CAIP2024本科组省赛;

import java.util.Scanner;

public class RC_u2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] points = new int[20];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < 20; j++) {
                int p = sc.nextInt();
                int k = sc.nextInt();
                points[j] = points[j] + getPoint(p) + k;
            }
        }

        for (int i = 0; i < 20; i++) {
            System.out.println((i + 1) + " " + points[i]);
        }

        sc.close();

    }

    public static int getPoint(int rank) {
        if (rank == 1) {
            return 12;
        }
        if (rank == 2) {
            return 9;
        }
        if (rank == 3) {
            return 7;
        }
        if (rank == 4) {
            return 5;
        }
        if (rank == 5) {
            return 4;
        }
        if (rank == 6 || rank == 7) {
            return 3;
        }
        if (rank >= 8 && rank <= 10) {
            return 2;
        }
        if (rank >= 11 && rank <= 15) {
            return 1;
        }
        return 0;


    }
}
