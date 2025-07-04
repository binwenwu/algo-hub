public class _59 {
    public static void main(String[] args) {
        _59 s = new _59();
        s.generateMatrix1(2);
    }

    // 比较容易忘记，n为奇数的时候，循环内会遗漏对最中间那个值的赋值，也就是最后一个绘制元素 n * n
    public int[][] generateMatrix1(int n) {
        int[][] maxtrix = new int[n][n];
        int i = 0, j = 0;
        int loop = 1;
        int offset = 1;
        while (loop <= (n * n) / 2 * 2) {
            while (j < n - offset) {
                maxtrix[i][j] = loop;
                j++;
                loop++;
            }

            while (i < n - offset) {
                maxtrix[i][j] = loop;
                i++;
                loop++;
            }

            while (j >= offset) {
                maxtrix[i][j] = loop;
                j--;
                loop++;
            }

            while (i >= offset) {
                maxtrix[i][j] = loop;
                i--;
                loop++;
            }

            i++;
            j++;
            offset++;

        }

        if (n % 2 != 0) {
            maxtrix[i][j] = loop;
        }
        return maxtrix;
    }

}
