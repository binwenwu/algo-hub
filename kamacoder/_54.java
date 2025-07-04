import java.util.Scanner;

public class _54 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        String res = "";
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) >= 'a' && s.charAt(i) <= 'z') {
                res = res + s.charAt(i);
            } else {
                res = res + "number";
            }
        }

        System.out.println(res);

        sc.close();
    }
}
