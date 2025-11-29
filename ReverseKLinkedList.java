class Node{

    public int data;
    public Node next;

    public Node(int data){
        this.data = data;
    }


}



public class ReverseKLinkedList {

    private Node getKthNode(Node head, int k){
        k=k-1;
        Node temp = head;
        while(temp!=null && k>0){
            k--;
            temp=temp.next;
        }
        return temp;
    }

    private Node reverse(Node head){

        Node curr = head;
        Node prev = null;

        while(curr!=null){
            Node next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }

        return prev;
    }

    public void printLinkedList(Node head){
        Node curr = head;

        while(curr!=null){
            System.out.print(curr.data+", ");
            curr=curr.next;
        }
        System.out.println("");
    }

    public Node reverseKLinkedList(Node head,int k){

        Node dummy = new Node(-1);
        dummy.next = head;
        Node groupHead = dummy.next;

        Node prevGroup = dummy;

        while(groupHead!=null){

            Node kthNode = getKthNode(groupHead, k);

            if(kthNode ==null)break;

            Node nextGroup = kthNode.next;
            kthNode.next = null;
            Node reverseHead = reverse(groupHead);

            prevGroup.next = reverseHead;
            groupHead.next = nextGroup;

            prevGroup = groupHead;
            groupHead = nextGroup;







        }

        return dummy.next;
    }

    public static void main(String [] args){
        ReverseKLinkedList obj = new ReverseKLinkedList();

        // Creating the linked list: 1->2->3->4->5
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(5);

        int k = 2;

        // Reversing in groups of k
        Node result = obj.reverseKLinkedList(head, k);

        obj.printLinkedList(result);
    }
}
