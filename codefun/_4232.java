package codefun;

import java.util.*;

/**
 * 信息熵计算
 *
 * 题意：给定用户的新闻阅读历史数据（字典格式），计算每个用户的信息熵。
 * 信息熵公式：H = -Σ(pi * log2(pi))，其中 pi 是用户阅读第 i 类新闻的概率。
 * 结果保留 3 位小数。
 *
 * 思路：
 * 1. 解析输入的字典字符串，提取每个用户及其各类别的阅读次数
 * 2. 对每个用户，计算总阅读次数，求出各类别的概率 pi
 * 3. 用公式计算信息熵，保留 3 位小数
 * 4. 按输入格式输出结果
 */
public class _4232 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String line = sc.nextLine().trim();
        sc.close();

        // 去掉最外层的大括号
        line = line.substring(1, line.length() - 1).trim();

        // 用 LinkedHashMap 保持插入顺序
        LinkedHashMap<String, Double> result = new LinkedHashMap<>();

        // 解析每个用户的数据
        // 格式: "user1": {"sports":10,"technology":20,"entertainment":30}, "user2":{...}
        // 按 "}" 分割后处理每个用户
        int i = 0;
        while (i < line.length()) {
            // 找到用户名（在引号内）
            int q1 = line.indexOf('"', i);
            if (q1 == -1) break;
            int q2 = line.indexOf('"', q1 + 1);
            String userId = line.substring(q1 + 1, q2);

            // 找到该用户对应的 { }
            int braceStart = line.indexOf('{', q2);
            int braceEnd = line.indexOf('}', braceStart);
            String inner = line.substring(braceStart + 1, braceEnd);

            // 解析内部的 key:value 对，提取阅读次数
            List<Integer> counts = new ArrayList<>();
            String[] pairs = inner.split(",");
            for (String pair : pairs) {
                // 格式: "sports":10
                String[] kv = pair.split(":");
                if (kv.length == 2) {
                    counts.add(Integer.parseInt(kv[1].trim()));
                }
            }

            // 计算信息熵
            int total = 0;
            for (int c : counts) total += c;

            double entropy = 0.0;
            for (int c : counts) {
                if (c > 0) {
                    double pi = (double) c / total;
                    entropy -= pi * (Math.log(pi) / Math.log(2));
                }
            }

            // 保留 3 位小数
            entropy = Math.round(entropy * 1000.0) / 1000.0;
            result.put(userId, entropy);

            // 移动到下一个用户
            i = braceEnd + 1;
        }

        // 按题目要求的格式输出
        StringBuilder sb = new StringBuilder();
        sb.append('{');
        int idx = 0;
        for (Map.Entry<String, Double> entry : result.entrySet()) {
            if (idx > 0) sb.append(", ");
            // 格式: "user1": {"entropy": 1.459}
            sb.append('"').append(entry.getKey()).append("\": {\"entropy\": ")
              .append(String.format("%.3f", entry.getValue())).append('}');
            idx++;
        }
        sb.append('}');
        System.out.println(sb.toString());
    }
}
