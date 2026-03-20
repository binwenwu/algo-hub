import java.util.Arrays;

public class _179 {
    public static void main(String[] args) {
    }

    public String largestNumber(int[] nums) {
        // 1. 将 int 数组转为 String 数组（方便拼接比较）
        String[] strs = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            strs[i] = String.valueOf(nums[i]);
        }

        // 2. 核心：自定义排序规则
        // 排序依据：比较两个字符串拼接后的结果
        // 比如 x="3", y="30"
        // 比较 "330" 和 "303"
        // 如果 y+x > x+y，则 y 应该排在前面
        Arrays.sort(strs, (x, y) -> (y + x).compareTo(x + y));

        // 3. 特殊情况处理：如果排序后第一个是 "0"
        // 说明所有数字都是 0（例如 [0,0]）
        // 直接返回 "0"，避免返回 "0000"
        if (strs[0].equals("0")) {
            return "0";
        }

        // 4. 拼接结果
        StringBuilder res = new StringBuilder();
        for (String s : strs) {
            res.append(s);
        }

        // 5. 返回拼接后的最大数字字符串
        return res.toString();
    }
}
