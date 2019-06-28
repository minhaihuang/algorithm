package com.hhm.algo.list;

import java.util.ArrayList;
import java.util.List;

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

    public int size(){
        return size;
    }

    /**
     * 删除节点
     * @param index
     * @return
     */
    public Node remove(int index){
        if(index < 0 || index >= size){
            throw new IndexOutOfBoundsException("超出链表节点范围");
        }
        Node removeNode = null;
        // 头部删除
        if(index == 0){
            removeNode = head;
            head = head.next;
        } else if(index == size-1){  // 尾部删除
            Node tempNode = getNode(index - 1);
            removeNode = tempNode.next;
            tempNode.next = null;
            last = tempNode;
        } else { //中间删除
            Node tempNode = getNode(index - 1);
            Node nextNode = tempNode.next.next;
            removeNode = tempNode.next;
            tempNode.next = nextNode;
        }
        return removeNode;
    }

    public void output(){
        if(head != null){
           while (head != null) {
               System.out.println(head.getData());
               head = head.next;
           }
        }
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

    public static void main(String[] args) {
        MyList myList = new MyList();
        myList.insert(3,0);
        myList.insert(5,1);
        myList.insert(6,2);
        myList.insert(4,3);
        myList.insert(2,4);
        myList.insert(9,5);

        System.out.println(myList.size);
        System.out.println("===================");
        myList.output();
    }
}
