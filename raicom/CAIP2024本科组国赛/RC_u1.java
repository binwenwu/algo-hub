package raicom.CAIP2024本科组国赛;

import java.util.Scanner;

public class RC_u1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 使用StringBuilder来拼接所有输入行
        StringBuilder sb = new StringBuilder();
        while (sc.hasNextLine()) {
            sb.append(sc.nextLine());
            // 在行之间加一个非字母数字字符（如空格），确保跨行的关键词能被正确分隔
            sb.append(" ");
        }
        sc.close();

        String allText = sb.toString();

        // 1. 提取关键词
        // 使用正则表达式切分字符串，分隔符是一个或多个非字母数字的字符
        String[] potentialKeywords = allText.split("[^a-zA-Z0-9]+");
        
        long totalSuspicionScore = 0;
        long totalLength = 0;
        long keywordCount = 0;

        // 遍历所有提取出的部分
        for (String keyword : potentialKeywords) {
            // split可能会产生空字符串，需要忽略
            if (keyword.isEmpty()) {
                continue;
            }

            // 2. 分析关键词并计算分数
            int score = calculateScore(keyword);

            // 3. 累加统计数据
            totalSuspicionScore += score;
            totalLength += keyword.length();
            keywordCount++;
        }

        // 4. 输出结果
        System.out.println(totalSuspicionScore);
        System.out.println(totalLength + " " + keywordCount);
    }

    /**
     * 计算单个关键词的可疑分数
     * 
     * @param keyword 待分析的关键词
     * @return 该关键词的可疑分数
     */
    private static int calculateScore(String keyword) {
        boolean hasLower = false;
        boolean hasUpper = false;
        boolean hasDigit = false;

        for (char c : keyword.toCharArray()) {
            if (Character.isLowerCase(c)) {
                hasLower = true;
            } else if (Character.isUpperCase(c)) {
                hasUpper = true;
            } else if (Character.isDigit(c)) {
                hasDigit = true;
            }
            // 优化：如果三种类型都已找到，可以提前结束循环
            if (hasLower && hasUpper && hasDigit) {
                break;
            }
        }

        if (hasLower && hasUpper && hasDigit) {
            return 5;
        } else if ((hasLower && hasDigit) || (hasUpper && hasDigit)) {
            return 3;
        } else if (hasLower && hasUpper) {
            return 1;
        } else {
            return 0; // 其他情况不加分
        }
    }
}
