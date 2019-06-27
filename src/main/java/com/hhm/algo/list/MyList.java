package com.hhm.algo.list;

/**
 * 链表
 * 时间复杂度
 查询：O(n)；插入O(1)；若不考虑之前的查找操作，更新和删除的时间复杂度都为O(1)
 */
public class MyList {
    // 头节点
    private Node head;
    // 尾节点
    private Node last;
    // 链表的实际长度
    private int size;

    public void insert(int data,int index){
        if(index < 0 && index > size){
            throw new IndexOutOfBoundsException("超出链表节点范围");
        }
        Node insertNode = new Node(data);
        // 头部插入
        if(size == 0){
            head = insertNode;
            last = insertNode;
        } else if(index == size){ // 尾部插入
            last.next = insertNode;
            last = insertNode;
        } else { //中间插入
            Node prevNode = getNode(index - 1);
            Node nextNode = prevNode.next;
            prevNode.next = insertNode;
            insertNode.next = nextNode;
        }
        size++;
    }

    public Node getNode(Integer index){
        if(index < 0 && index >= size){
            throw new IndexOutOfBoundsException("超出链表节点范围");
        }
        if(index == 0){
            return head;
        }
        if(index == size-1){
            return last;
        }
        Node temp = head;
        for(int i = 0 ; i < index; i++){
            temp = temp.next;
        }
        return temp;
    }

    private class Node{
        private int data;
        private Node next;
        public int getData() {
            return data;
        }

        public Node(int data) {
            this.data = data;
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
