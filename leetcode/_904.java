import java.util.HashMap;
import java.util.Map;

public class _904 {
  public static void main(String[] args) {
    _904 s = new _904();
    int[] fruits = { 3, 3, 3, 1, 2, 1, 1, 2, 3, 3, 4 };
    int maxSize = s.totalFruit1(fruits);
    System.out.println("maxSize: " + maxSize);
  }

  /**
   * 暴力解法，我是想用一种滑动窗口的想法来解决这个问题。
   * 思路：left 代表从哪里开始采摘，每个开始的left，都会消耗掉一个篮子，
   * 然后一直采摘到遇到不相等的元素，就再消耗掉一个篮子，
   * 这时我们继续往后摘，采摘到，直到遇到两个篮子都装不了的元素为止；
   *
   * 但是这种解法实际上时间复杂度为O(n^2)，会超时
   */
  public int totalFruit1(int[] fruits) {
    int basket_1 = -1;
    int basket_2 = -1;
    int maxsize = 0;
    int right = 0;
    for (int left = 0; left < fruits.length; left++) {
      right = left;
      basket_1 = fruits[right];
      right++;
      while (right < fruits.length && fruits[right] == basket_1) {
        right++;
      }
      if (right < fruits.length) {
        basket_2 = fruits[right];
        right++;
        while (right < fruits.length &&
            (fruits[right] == basket_2 || fruits[right] == basket_1)) {
          right++;
        }
      }
      maxsize = (right - left) > maxsize ? right - left : maxsize;
    }
    return maxsize;
  }

  // 方法2：滑动窗口, 不使用 hashmap
  public int totalFruit2(int[] fruits) {
    int lastFruit = -1, secondLastFruit = -1;
    int lastFruitCount = 0;
    int currMax = 0, max = 0;

    for (int fruit : fruits) {
      if (fruit == lastFruit || fruit == secondLastFruit) {
        currMax += 1;
      } else {
        currMax = lastFruitCount + 1; // 只保留上一种连续的 + 当前这个
      }

      if (fruit == lastFruit) {
        lastFruitCount += 1;
      } else {
        lastFruitCount = 1;
        secondLastFruit = lastFruit;
        lastFruit = fruit;
      }

      max = Math.max(max, currMax);
    }

    return max;
  }

  // 方法3：滑动窗口，官方题解
  public int totalFruit3(int[] fruits) {
    Map<Integer, Integer> count = new HashMap<>();
    int left = 0, maxLen = 0;
    for (int right = 0; right < fruits.length; right++) {
      count.put(fruits[right], count.getOrDefault(fruits[right], 0) + 1);
      while (count.size() > 2) {
        count.put(fruits[left], count.get(fruits[left]) - 1);
        if (count.get(fruits[left]) == 0) {
          count.remove(fruits[left]);
        }
        left++;
      }
      maxLen = Math.max(maxLen, right - left + 1);
    }
    return maxLen;
  }
}
