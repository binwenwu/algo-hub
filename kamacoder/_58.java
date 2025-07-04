import java.util.Scanner;

public class _58 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] array = new int[n];
        int a = 0;
        int b = 0;
        int sum = 0;
        int[] p = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = scanner.nextInt();
            sum += array[i];
            p[i] = sum;
        }

        while (scanner.hasNextInt()) {
            a = scanner.nextInt();
            b = scanner.nextInt();
            if (a == 0) {
                System.out.println(p[b]);
            } else {
                System.out.println(p[b] - p[a - 1]);
            }
        }

        scanner.close();
    }
}