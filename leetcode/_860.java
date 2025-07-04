public class _860 {
    public static void main(String[] args) {

    }

    // 方法一
    public boolean lemonadeChange(int[] bills) {
        int[] sc = new int[3];// 0,1,2 分别代表三种面值的钞票数量
        for (int i = 0; i < bills.length; i++) {
            int curr = bills[i];
            switch (curr) {
                case 5:
                    sc[0]++;
                    break;
                case 10:
                    sc[1]++;
                    if (sc[0] > 0) {
                        sc[0]--;
                        break;
                    } else {
                        return false;
                    }
                case 20:
                    sc[2]++;
                    if (sc[0] > 0 && sc[1] > 0) {//尽量先用1张10块钱 + 1张5块去找零
                        sc[0]--;
                        sc[1]--;
                        break;
                    } else if (sc[0] >= 3) {
                        sc[0] -= 3;
                        break;
                    } else {
                        return false;
                    }
                default:
                    break;
            }
        }

        return true;
    }
}
