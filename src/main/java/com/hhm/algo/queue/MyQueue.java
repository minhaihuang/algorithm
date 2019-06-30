package com.hhm.algo.queue;

/**
 * create by huanghaimin
 * 循环队列。
 * 循环队列不但充分利用了数组的空间，还避免了数组元素的整体移动的麻烦。
 * 入队和出队的时间复杂度均为O(1)
 *
 */
public class MyQueue {
    // 数组
    private int[] array;
    // 头指针
    private int front;
    // 尾指针
    private int rear;

    public MyQueue(int capacity){
        this.array = new int[capacity];
    }

    /**
     * 入队
     * @param element
     */
    public void enQueue(int element){
        if((rear+1)%array.length == front){
            throw new IndexOutOfBoundsException("队列已满");
        }
        array[rear] = element;
        rear = (rear+1)%array.length;
    }

    /**
     * 出队
     * @return
     */
    public Integer deQueue(){
        if(rear == front){
            throw new RuntimeException("队列已空");
        }
        int deQueueEle = array[front];
        front = (front+1)%array.length;
        return deQueueEle;
    }

    public void output(){
        for(int i = front; i != rear; i = (i+1)%array.length){
            System.out.println(array[i]);
        }
    }

    public static void main(String[] args) {
        MyQueue myQueue = new MyQueue(6);
        myQueue.enQueue(3);
        myQueue.enQueue(5);
        myQueue.enQueue(6);
        myQueue.enQueue(8);
        myQueue.enQueue(1);
        myQueue.deQueue();
        myQueue.deQueue();
        myQueue.deQueue();
        myQueue.enQueue(2);
        myQueue.enQueue(4);
        myQueue.enQueue(9);
        myQueue.output();
    }
}
