import java.util.Scanner;

public class _55 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int k = Integer.valueOf(sc.nextLine());
        String s = sc.nextLine();
        String s1 = s.substring(0, s.length() - k);
        String s2 = s.substring(s.length() - k, s.length());
        System.out.println(s2 + s1);
        sc.close();
    }
}
