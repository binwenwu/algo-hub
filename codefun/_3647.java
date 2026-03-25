package codefun;

import java.util.*;

public class _3647 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        while (T-- > 0) {
            int n = sc.nextInt();
            int odd = 0;
            int even = 0;
            for (int i = 0; i < n; i++) {
                int x = sc.nextInt();
                if (x % 2 == 0)
                    even++;
                else
                    odd++;
            }
            if (odd == 0 || even == 0)
                System.out.println(0);
            else
                System.out.println(Math.min(odd, even));
        }
        sc.close();
    }
}
