package codefun;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class _3558 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[] res = new int[n + 1];
        int[] vote = new int[n + 1];
        int maxVote = 0;
        List<Integer> currMaxIndexList = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            int v = sc.nextInt();
            vote[v]++;
            if (vote[v] > maxVote) {
                maxVote = vote[v];
                currMaxIndexList.clear();
                currMaxIndexList.add(v);
                res[v]++;
            } else if (vote[v] == maxVote) {
                currMaxIndexList.add(v);
                for (int j : currMaxIndexList) {
                    res[j]++;
                }
            } else {
                for (int j : currMaxIndexList) {
                    res[j]++;
                }
            }
        }

        for (int i = 1; i <= n; i++) {
            System.out.print(res[i] + " ");
        }

        sc.close();
    }
}
