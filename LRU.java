import java.util.HashMap;
import java.util.Map;

public class LRU {
    class ListNode{

        public int key;
        public int val;
        public ListNode next;
        public ListNode prev;

        public ListNode(int key, int val){
            this.key = key;
            this.val = val;
            next = null;
            prev= null;
        }

    }

    public ListNode head;
    public ListNode tail;
    public int capacity;

    Map<Integer, ListNode> m = new HashMap<>();

    public LRU(int cap){
        head =  new ListNode(-1,-1);
        tail =  new ListNode(-1,-1);
        this.capacity = cap;
        head.next = tail;
        tail.prev = head;
    }

    void add(ListNode newNode){

        m.put(newNode.key, newNode);
        ListNode next = head.next;
        newNode.next = next;
        newNode.prev = head;
        head.next = newNode;
        next.prev = newNode;


    }

    void delete(ListNode node){

        ListNode next = node.next;
        ListNode prev = node.prev;

        prev.next = next;
        next.prev = prev;

        node.next = null;
        node.prev = null;

        m.remove(node.key);


    }

    public int get(int key){
        if(m.containsKey(key)){

            ListNode currNode = m.get(key);
            delete(currNode);
            add(currNode);

            return currNode.val;

        }
        return -1;

    }

    public void put(int key, int value){

        if(m.size()==this.capacity){
            delete(tail.prev);
            add(new ListNode(key, value));
        }
        else if(m.containsKey(key)){
            ListNode node =m.get(key);
            node.val = value;
            delete(node);
            add(node);
        }
        else add(new ListNode(key, value));

    }




}
