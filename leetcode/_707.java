public class _707 {
    public static void main(String[] args) {
        MyLinkedList3 myLinkedList = new MyLinkedList3();
        myLinkedList.addAtHead(1);
        myLinkedList.addAtTail(3);
        myLinkedList.addAtIndex(1,2);
        int i = myLinkedList.get(1);
        System.out.println(i);
        myLinkedList.deleteAtIndex(1);
        int j = myLinkedList.get(1);
        System.out.println(j);
    }
}

// 方法1：单向链表，不用虚拟头节点
class MyLinkedList {
    class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }

    ListNode head = null;
    int size = 0;

    public MyLinkedList() {
    }

    public int get(int index) {
        if (index >= size) {
            return -1;
        }
        ListNode temp = head;
        while (index > 0) {
            temp = temp.next;
            index--;
        }
        return temp.val;
    }

    public void addAtHead(int val) {
        ListNode newNode = new ListNode(val);
        newNode.next = head;
        head = newNode;
        size++;
    }

    public void addAtTail(int val) {
        if (size == 0) {
            addAtHead(val);
        } else {
            ListNode newNode = new ListNode(val);
            ListNode temp = head;
            for (int i = 0; i < size - 1; i++) {
                temp = temp.next;
            }
            temp.next = newNode;
            size++;
        }
    }

    public void addAtIndex(int index, int val) {
        if (index > size) {
            return;
        } else if (index == 0) {
            addAtHead(val);
        } else if (index == size) {
            if (size == 0) {
                addAtHead(val);
            } else {
                addAtTail(val);
            }
        } else {
            ListNode newNode = new ListNode(val);
            ListNode temp = head;
            for (int i = 0; i < index - 1; i++) {
                temp = temp.next;
            }
            newNode.next = temp.next;
            temp.next = newNode;
            size++;
        }

    }

    public void deleteAtIndex(int index) {
        if (index >= size) {
            return;
        } else if (index == 0) {
            head = head.next;
            size--;
        } else {
            ListNode temp = head;
            for (int i = 0; i < index - 1; i++) {
                temp = temp.next;
            }
            temp.next = temp.next.next;
            size--;
        }
    }
}

// 方法2：单向链表，使用虚拟头节点
class MyLinkedList2 {
    class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }

    ListNode head = null;
    int size = 0;

    public MyLinkedList2() {
        // 虚拟头节点
        head = new ListNode(0);
    }

    public int get(int index) {
        if (index >= size) {
            return -1;
        }
        ListNode temp = head;
        while (index >= 0) {
            temp = temp.next;
            index--;
        }
        return temp.val;
    }

    public void addAtHead(int val) {
        ListNode newNode = new ListNode(val);
        newNode.next = head.next;
        head.next = newNode;
        size++;
    }

    public void addAtTail(int val) {
        ListNode newNode = new ListNode(val);
        ListNode temp = head;
        for (int i = 0; i < size; i++) {
            temp = temp.next;
        }
        temp.next = newNode;
        size++;
    }

    public void addAtIndex(int index, int val) {
        if (index > size) {
            return;
        }

        ListNode newNode = new ListNode(val);
        ListNode temp = head;
        for (int i = 0; i < index; i++) {
            temp = temp.next;
        }
        newNode.next = temp.next;
        temp.next = newNode;
        size++;

    }

    public void deleteAtIndex(int index) {
        if (index >= size) {
            return;
        }
        ListNode temp = head;
        for (int i = 0; i < index; i++) {
            temp = temp.next;
        }
        temp.next = temp.next.next;
        size--;

    }
}

// 方法3：双向链表，使用虚拟头、尾节点
class MyLinkedList3 {  

    class ListNode{
        int val;
        ListNode next, prev;
        ListNode(int val){
            this.val = val;
        }
    }

    //记录链表中元素的数量
    private int size;
    //记录链表的虚拟头结点和尾结点
    private ListNode head, tail;
    
    public MyLinkedList3() {
        //初始化操作
        this.size = 0;
        this.head = new ListNode(0);
        this.tail = new ListNode(0);
        //这一步非常关键，否则在加入头结点的操作中会出现null.next的错误！！！
        this.head.next = tail;
        this.tail.prev = head;
    }
    
    public int get(int index) {
        //判断index是否有效
        if(index < 0 || index >= size){
            return -1;
        }
        ListNode cur = head;
        //判断是哪一边遍历时间更短
        if(index >= size / 2){
            //tail开始
            cur = tail;
            for(int i = 0; i < size - index; i++){
                cur = cur.prev;
            }
        }else{
            for(int i = 0; i <= index; i++){
                cur = cur.next; 
            }
        }
        return cur.val;
    }
    
    public void addAtHead(int val) {
        //等价于在第0个元素前添加
        addAtIndex(0, val);
    }
    
    public void addAtTail(int val) {
        //等价于在最后一个元素(null)前添加
        addAtIndex(size, val);
    }
    
    public void addAtIndex(int index, int val) {
        //判断index是否有效
        if(index < 0 || index > size){
            return;
        }

        //找到前驱
        ListNode pre = head;
        for(int i = 0; i < index; i++){
            pre = pre.next;
        }
        //新建结点
        ListNode newNode = new ListNode(val);
        newNode.next = pre.next;
        pre.next.prev = newNode;
        newNode.prev = pre;
        pre.next = newNode;
        size++;
        
    }
    
    public void deleteAtIndex(int index) {
        //判断index是否有效
        if(index < 0 || index >= size){
            return;
        }

        //删除操作
        ListNode pre = head;
        for(int i = 0; i < index; i++){
            pre = pre.next;
        }
        pre.next.next.prev = pre;
        pre.next = pre.next.next;
        size--;
    }
}
