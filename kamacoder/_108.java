import java.util.Scanner;

public class _108 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        DisJoint disJoint = new DisJoint(N + 1);
        for (int i = 0; i < N; i++) {
            int p1 = sc.nextInt();
            int p2 = sc.nextInt();
            if (disJoint.isSame(p1, p2)) {
                System.out.println(p1 + " " + p2);
                break;
            } else {
                disJoint.join(p1, p2);
            }
        }
        sc.close();
    }
}

class DisJoint {
    private int[] father;

    public DisJoint(int N) {
        father = new int[N];
        for (int i = 0; i < N; i++) {
            father[i] = i;
        }
    }

    public int find(int n) {
        return n == father[n] ? n : (father[n] = find(father[n]));
    }

    public void join(int n, int m) {
        n = find(n);
        m = find(m);
        if (n == m) {
            return;
        } else {
            father[m] = n;
        }
    }

    public boolean isSame(int n, int m) {
        n = find(n);
        m = find(m);
        return n == m;
    }
}
