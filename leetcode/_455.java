import java.util.Arrays;

public class _455 {
    public static void main(String[] args) {

    }

    // 先喂饱大胃口的
    public int findContentChildren1(int[] g, int[] s) {
        int sum = 0;
        Arrays.sort(g);
        Arrays.sort(s);
        int i = g.length - 1;
        int j = s.length - 1;
        for (; i >= 0 && j >= 0;) {
            if (g[i] <= s[j]) {
                sum++;
                i--;
                j--;
            } else {
                i--;
            }
        }

        return sum;
    }

    // 先喂饱小胃口的
    public int findContentChildren2(int[] g, int[] s) {
        int sum = 0;
        Arrays.sort(g);
        Arrays.sort(s);
        int i = 0;
        int j = 0;
        for (; i < g.length && j < s.length;) {
            if (g[i] <= s[j]) {
                sum++;
                i++;
                j++;
            } else {
                j++;
            }
        }

        return sum;
    }

}
