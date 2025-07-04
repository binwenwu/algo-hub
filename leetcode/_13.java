public class _13 {
    public static void main(String[] args) {

    }

    public int romanToInt(String s) {
        int temp = -1;
        int res = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            char c = s.charAt(i);
            int n = getNumber(c);
            if (n > temp) {
                res = res + n;
            } else {
                res = res - n;
            }
            temp = n;
        }
        return res;
    }

    private int getNumber(char c) {
        switch (c) {
            case 'I':
                return 1;
            case 'V':
                return 5;
            case 'X':
                return 10;
            case 'L':
                return 50;
            case 'C':
                return 100;
            case 'D':
                return 500;
            case 'M':
                return 1000;
        }
        return -1;
    }
}
