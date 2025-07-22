import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

// 普通BFS的解法
public class _107 {

    private static boolean[][] graph;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        graph = new boolean[N + 1][N + 1];

        for (int i = 0; i < M; i++) {
            int p1 = sc.nextInt();
            int p2 = sc.nextInt();
            graph[p1][p2] = true;
            graph[p2][p1] = true;
        }

        int source = sc.nextInt();
        int destination = sc.nextInt();

        Queue<Integer> queue = new LinkedList<>();
        queue.add(source);

        boolean[] visited = new boolean[N + 1];

        boolean has = false;
        while (!queue.isEmpty()) {
            int curr = queue.poll();
            if (visited[curr]) {
                continue;
            }
            visited[curr] = true;
            if (curr == destination) {
                has = true;
                break;
            }
            for (int i = 1; i <= N; i++) {
                if (graph[curr][i] && !visited[i]) {
                    queue.add(i);
                }
            }
        }

        if (has) {
            System.out.print(1);
        } else {
            System.out.print(0);
        }
        sc.close();
    }
}

// 并查集的写法
class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        DisJoint disJoint = new DisJoint(N + 1);
        for (int i = 0; i < M; i++) {
            disJoint.join(sc.nextInt(), sc.nextInt());
        }

        if (disJoint.isSame(sc.nextInt(), sc.nextInt())) {
            System.out.print(1);
        } else {
            System.out.print(0);
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
        if (n == father[n]) {
            return n;
        } else {
            return father[n] = find(father[n]);
        }
    }

    public void join(int n, int m) {
        n = find(n);
        m = find(m);
        if (n == m)
            return;
        father[m] = n;
    }

    public boolean isSame(int n, int m) {
        n = find(n);
        m = find(m);
        return n == m;
    }
}