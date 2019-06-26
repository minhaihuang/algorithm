package com.hhm.algo.array;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * 数组操作。
 * 包括尾部插入，中间插入，以及超范围插入。
 * 时间复杂度
 * 查询：O(1)；插入O(n)；更新O(1)；删除O(n)
 */
public class MyArray {
    private Integer[] array;
    // 记录当前有几个元素
    private int size;

    /**
     * 初始化一个指定大小的数组
     * @param capacity
     */
    public MyArray(int capacity){
        this.array = new Integer[capacity];
    }

    /**
     * 数组扩容
     */
    public void reSize(){
        Integer[] newArray = new Integer[array.length+10];
        // 将旧元素添加到新数组
        System.arraycopy(array,0,newArray,0,array.length);
        this.array = newArray;
    }

    /**
     * 插入操作
     * @param element 插入的元素
     * @param index 插入的下标
     */
    public void insert(int element, int index){
        // 判断访问下班是否超出范围
        if(index < 0 || index > size){
            throw new IndexOutOfBoundsException("超出数组实际元素范围！");
        }
        // 扩容
        if(size >= array.length){
            reSize();
        }
        // 从右往左循环，将元素逐个右挪1位
        for(int i = size-1; i >= index ; i--){
            array[i+1] = array[i];
        }
        // 腾出的位置放入新元素
        array[index] = element;
        size++;
    }

    public void outout(){
        for(int i = 0; i < array.length; i++){
            System.out.println(array[i]);
        }
    }

    public static void main(String[] args) {
        MyArray myArray = new MyArray(5);
        myArray.insert(3,0);
        myArray.insert(7,1);
        myArray.insert(9,2);
        myArray.insert(5,3);
        myArray.insert(6,4);

        myArray.outout();
    }
}
