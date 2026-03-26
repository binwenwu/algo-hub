package codefun;

import java.util.Scanner;

public class _3616 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int height[] = new int[n];
        for (int i = 0; i < n; i++) {
            height[i] = sc.nextInt();
        }

        int right2left[] = new int[n];
        for (int i = 1; i < n; i++) {
            int temp = right2left[i - 1] + height[i - 1] - height[i];
            right2left[i] = temp < 0 ? 0 : temp;
        }
        int left2right[] = new int[n];
        for (int i = n - 2; i >= 0; i--) {
            int temp = left2right[i + 1] + height[i + 1] - height[i];
            left2right[i] = temp < 0 ? 0 : temp;
        }

        for (int i = 0; i < n; i++) {
            System.out.print(Math.min(right2left[i], left2right[i]) + " ");
        }

        sc.close();
    }
}
