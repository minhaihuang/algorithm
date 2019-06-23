package com.hhm.algo.example;

/**
 * j校验链表是否有环。
 * 解法，使用两个指针。让他们同时指向这个链表的头节点。
 * 然后开始一个大循环，在循环体中，然指针p1每次向后移动1个节点，让指针p2每次向后移动2个节点，
 * 然后比较两个指向的节点是否相同。如果相同，则可以判断出链表是否有环，如果不同，则下一次循环。
 *
 * 原理就像是两个人在一个操场上跑步，一个人跑得快，一个人跑的慢，总会相遇。
 *
 * 时间复杂度是o(n)，由于没有使用额外的临时数据，所以空间复杂度为O(1)
 * create by huanghaimin
 */
public class CheckIsListCycle {
    public static void main(String[] args) {
        Node root = new Node(5);
        Node node1 = new Node(3);
        Node node2 = new Node(7);
        Node node3 = new Node(2);
        Node node4 = new Node(6);
        root.setNext(node1);
        node1.setNext(node2);
        node2.setNext(node3);
        node3.setNext(node4);
        node4.setNext(node1);

        System.out.println(isCycle(root));
    }

    /**
     * 判断链表是否有环
     * @param root
     * @return
     */
    public static boolean isCycle(Node root){
        Node p1 = root;
        Node p2 = root;
        while (p1.next != null && p2.next != null) {
            p1 = p1.next;
            p2 = p2.next.next;
            if(p1 == p2){
                return true;
            }
        }
        return false;
    }

    private static class Node{
        private int data;
        private Node next;

        Node(int data){
            this.data = data;
        }

        public int getData() {
            return data;
        }

        public void setData(int data) {
            this.data = data;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }
}
