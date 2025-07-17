package raicom.CAIP2024本科组国赛;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RC_u3 {

    // 静态变量保持你的原始设计
    private static int[] numbers; // 输入的 n 个数字
    private static int[] arr; // 存放所有 n! 个排列生成的数字
    private static int n; // 输入的数字个数
    private static int N; // 目标小组的大小 (n! / 2)
    private static int nCount = 0; // 用于填充 arr 数组的计数器
    private static List<Integer> nTemp = new ArrayList<>(); // 临时列表，两个回溯函数共用
    private static boolean[] usedDigits = new boolean[10]; // backtrack1 使用，标记数字是否被用过
    private static long sum; // 目标平方和，使用 long 更安全
    private static List<Integer> result = null; // 存放最终结果，初始化为 null 作为找到解的标志

    // 计算阶乘
    public static int factorial(int num) {
        if (num < 0)
            throw new IllegalArgumentException("n must be >= 0");
        int result = 1;
        for (int i = 2; i <= num; i++) {
            result *= i;
        }
        return result;
    }

    /**
     * 回溯法一：生成所有 n! 个数字的全排列
     */
    private static void backtrack1() {
        if (nTemp.size() == n) {
            // 将列表中的数字拼接成一个整数
            int currentNumber = 0;
            for (Integer digit : nTemp) {
                currentNumber = currentNumber * 10 + digit;
            }
            arr[nCount] = currentNumber;
            nCount++;
            return;
        }

        for (int i = 0; i < numbers.length; i++) {
            // 使用数字本身作为索引来标记，这要求数字是唯一的，题目已保证
            if (!usedDigits[numbers[i]]) {
                usedDigits[numbers[i]] = true; // 选择
                nTemp.add(numbers[i]);
                backtrack1(); // 递归
                nTemp.removeLast(); // 回溯
                usedDigits[numbers[i]] = false;
            }
        }
    }

    /**
     * 回溯法二：从 arr 数组中寻找符合条件的子集（组合）
     * 
     * @param startIndex 从 arr 数组的哪个索引开始选择
     * @param currentSum 当前已选数字的平方和
     */
    private static void backtrack2(int startIndex, long currentSum) {
        // 如果已经找到解，就没必要继续搜索了
        if (result != null) {
            return;
        }

        // 剪枝：如果当前平方和已经超过目标，此路不通
        if (currentSum > sum) {
            return;
        }

        // 成功找到解的条件
        if (nTemp.size() == N) {
            if (currentSum == sum) {
                // 找到了！保存结果并返回
                result = new ArrayList<>(nTemp);
            }
            return;
        }

        // 如果剩下的元素数量不足以凑齐 N 个，也无需继续
        if ((arr.length - startIndex) < (N - nTemp.size())) {
            return;
        }

        // 核心循环：从 startIndex 开始，避免重复组合
        for (int i = startIndex; i < arr.length; i++) {
            // 选择当前数字 arr[i]
            int currentNum = arr[i];
            nTemp.add(currentNum);

            // 递归进入下一层，注意下一层的起始位置是 i + 1
            backtrack2(i + 1, currentSum + (long) currentNum * currentNum);

            // 回溯，撤销选择
            nTemp.removeLast();

            // 如果在刚才的递归中已经找到了解，就直接结束当前循环
            if (result != null) {
                return;
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        numbers = new int[n];
        for (int i = 0; i < n; i++) {
            numbers[i] = sc.nextInt();
        }
        sc.close();

        // --- 步骤 1: 生成所有数字 ---
        int totalCount = factorial(n);
        arr = new int[totalCount];
        backtrack1();

        // --- 步骤 2: 计算目标和与目标数量 ---
        long totalSumOfSquares = 0;
        for (int a : arr) {
            // 使用 long 转换避免乘法溢出
            totalSumOfSquares += (long) a * a;
        }
        sum = totalSumOfSquares / 2;
        N = totalCount / 2;

        // --- 步骤 3: 寻找子集 ---
        nTemp.clear(); // 清空 nTemp 以便 backtrack2 复用
        backtrack2(0, 0); // 从索引0开始，当前和为0

        // --- 步骤 4: 输出结果 ---
        if (result != null) {
            for (Integer num : result) {
                System.out.println(num);
            }
        }
    }
}