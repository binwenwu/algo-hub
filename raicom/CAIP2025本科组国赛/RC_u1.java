package raicom.CAIP2025本科组国赛;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


// 自己的解法，得分：20/20
public class RC_u1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        int A1 = sc.nextInt();
        int A2 = sc.nextInt();
        int B1 = sc.nextInt();
        int B2 = sc.nextInt();
        int B3 = sc.nextInt();
        Map<Type, Integer> map1 = new HashMap<>();
        Map<Type, Integer> map2 = new HashMap<>();
        for (int i = 0; i < N; i++) {
            int day = sc.nextInt();
            int type = sc.nextInt();
            Type t = new Type(type, day);
            if (type == 1) {
                map1.put(t, map1.getOrDefault(t, 0) + A1);
                map2.put(t, map2.getOrDefault(t, 0) + B1);
            } else if (type == 2) {
                map2.put(t, map2.getOrDefault(t, 0) + B2);
            } else {
                map1.put(t, map1.getOrDefault(t, 0) - A2);
                map2.put(t, map2.getOrDefault(t, 0) + B3);
            }
        }

        for (int i = 0; i < M; i++) {
            int day = sc.nextInt();
            int type = sc.nextInt();
            Type t = new Type(type, day);
            if (type == 1) {
                map2.put(t, map2.getOrDefault(t, 0) / 2);
            } else if (type == 2) {
                map2.put(t, map2.getOrDefault(t, 0) / 2);
            } else {
                map2.put(t, map2.getOrDefault(t, 0) / 2);
            }
        }

        int score1 = 0;
        int score2 = 0;
        for (int s : map1.values()) {
            score1 += s;
        }

        for (int s : map2.values()) {
            score2 += s;
        }
        
        score2 = -score2;

        System.out.println(score1 + " " + score2);

        sc.close();
    }
}

class Type {
    int type;
    int day;

    public Type(int type, int day) {
        this.type = type;
        this.day = day;
    }

    @Override
    public int hashCode() {
        return (type + " " + day).hashCode();
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Type)) {
            return false;
        }
        Type score = (Type) object;
        return score.type == this.type && score.day == this.day;
    }
}