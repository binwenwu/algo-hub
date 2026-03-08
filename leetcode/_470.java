public class _470 {
    /**
     * 1 调用 rand7()
     * 2 再调用 rand7()
     * 3 生成 1~49
     * 4 如果 <=40 就返回
     * 5 如果 >40 重新生成
     */
    public int rand10() {
        while (true) {
            int num = (rand7() - 1) * 7 + rand7(); // 1~49

            if (num <= 40) {
                return num % 10 + 1;
            }
        }
    }

    private int rand7() {
        return -1;
    }
}
