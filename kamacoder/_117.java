import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;


// 拓扑排序
public class _117 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        List<List<Integer>> list = new ArrayList<>();
        int[] inDegree = new int[N];
        for (int i = 0; i < N; i++) {
            list.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            int f1 = sc.nextInt();
            int f2 = sc.nextInt();
            list.get(f1).add(f2);
            inDegree[f2]++;
        }

        Queue<Integer> queue = new LinkedList<>();

        for (int i = 0; i < N; i++) {
            if (inDegree[i] == 0) {
                queue.add(i);
            }
        }

        List<Integer> result = new ArrayList<>();

        while (!queue.isEmpty()) {
            int f = queue.poll();
            result.add(f);
            for (int file : list.get(f)) {
                inDegree[file]--;
                if (inDegree[file] == 0) {
                    queue.add(file);
                }
            }
        }

        if (result.size() == N) {
            for (int i = 0; i < N - 1; i++) {
                System.out.print(result.get(i) + " ");
            }
            System.out.print(result.get(result.size() - 1));
        } else {
            System.out.print(-1);
        }

        sc.close();
    }
}
