import java.util.*;

public class _380 {
    public static void main(String[] args) {

    }
}

/**
 * 变长数组 + 哈希表
 * 插入时：
 * 1. 先利用哈希表判断存在性，若不存在，往变长数组末尾进行插入，并向哈希表存入下标
 * 删除时：
 * 1. 先利用哈希表判断存在性，若存在，且得到下标为 index, 则将变长数组的最后一个元素 last 移动到下标 index 处，在哈希表中将 last
 * 的下标更新为 index
 * 2. 在变长数组中删除最后一个元素，然后在哈希表中删除val
 * 
 * 获取随机元素操作时，由于变长数组中的所有元素的下标都连续，因此随机选取一个下标，返回变长数组中该下标处的元素。
 * 
 */
class RandomizedSet {
    List<Integer> nums;
    Map<Integer, Integer> map;
    Random random;

    public RandomizedSet() {
        nums = new ArrayList<Integer>();
        map = new HashMap<Integer, Integer>();
        random = new Random();
    }

    public boolean insert(int val) {
        if (map.containsKey(val)) {
            return false;
        }
        int index = nums.size();
        nums.add(val);
        map.put(val, index);
        return true;
    }

    public boolean remove(int val) {
        if (!map.containsKey(val)) {
            return false;
        }
        int index = map.get(val);
        int last = nums.get(nums.size() - 1);
        nums.set(index, last);
        map.put(last, index);
        nums.remove(nums.size() - 1);
        map.remove(val);
        return true;
    }

    public int getRandom() {
        int randomIndex = random.nextInt(nums.size());
        return nums.get(randomIndex);
    }
}
