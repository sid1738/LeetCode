public class reverseLinkedList {

    static class Node {
        public int data;
        public Node next;

        public Node(int data){
            this.data = data;
            next = null;
        }
    }

    public Node reverseList(Node head){
        if(head==null || head.next == null){
            return head;
        }

        Node newNode = reverseList(head.next);
        Node front = head.next;
        head.next = null;
        front.next = head;

        return newNode;

    }

    public void solve(){



    }
    public static void printLinkedList(Node head) {
        Node temp = head;
        while (temp != null) {
            System.out.print(temp.data + " ");
            temp = temp.next;
        }
        System.out.println();
    }
    public static void main(String []args){

        reverseLinkedList obj = new reverseLinkedList();
        Node head = new Node(1);
        head.next = new Node(3);
        head.next.next = new Node(2);
        head.next.next.next = new Node(5);
        head.next.next.next.next = new Node(1);

        Node node = obj.reverseList(head);
        printLinkedList(node);

    }
}
