package codefun;

import java.util.Scanner;

/**
 * 数学岛表达式计算
 *
 * 题意：表达式由小写字母和一个数字字符组成。
 *       小写字母 a~z 对应数字 0~25；
 *       数字 1,2,3,4 对应运算符 +, -, ×, ÷（÷ 为向下取整除法）。
 *       运算符左右的字母各自按顺序转换为数字后拼接成一个整数，再做运算。
 *       结果为负数时返回绝对值；除数为 0 时返回 -1。
 *
 * 思路：
 * 1. 遍历字符串，找到唯一的数字字符（运算符），记录位置
 * 2. 将运算符左侧的字母逐个转换为数字，拼接成整数（字符串拼接后转 long）
 * 3. 右侧同理
 * 4. 根据运算符计算结果，处理特殊情况（除数为 0、结果为负）
 */
public class _4233 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String formula = sc.nextLine();
        sc.close();
        System.out.println(solution(formula));
    }

    public static int solution(String formula) {
        // 找到运算符的位置
        int opIdx = -1;
        char opChar = ' ';
        for (int i = 0; i < formula.length(); i++) {
            char c = formula.charAt(i);
            if (c >= '1' && c <= '4') {
                opIdx = i;
                opChar = c;
                break;
            }
        }

        // 左侧字母转数字拼接
        StringBuilder leftSb = new StringBuilder();
        for (int i = 0; i < opIdx; i++) {
            leftSb.append(formula.charAt(i) - 'a');
        }
        long left = Long.parseLong(leftSb.toString());

        // 右侧字母转数字拼接
        StringBuilder rightSb = new StringBuilder();
        for (int i = opIdx + 1; i < formula.length(); i++) {
            rightSb.append(formula.charAt(i) - 'a');
        }
        long right = Long.parseLong(rightSb.toString());

        // 根据运算符计算
        long result;
        switch (opChar) {
            case '1': // +
                result = left + right;
                break;
            case '2': // -
                result = left - right;
                break;
            case '3': // ×
                result = left * right;
                break;
            case '4': // ÷
                if (right == 0) return -1;
                // 向下取整除法（Java 的整数除法对正数是向下取整，
                // 但对负数是向零取整，需要用 Math.floorDiv）
                result = Math.floorDiv(left, right);
                break;
            default:
                result = 0;
        }

        // 结果为负数返回绝对值
        return (int) Math.abs(result);
    }
}
