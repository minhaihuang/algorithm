package com.hhm.algo.example;

import java.util.Stack;

/**
 * create by huanghaimin。
 * 实现一个栈，该栈带有出栈（pop）,入栈（push），取最小元素（getMin）3个方法。要保证这三个方法的时间复杂度都是O(1)。
 * 步骤：
 * （1）设置两个栈，一个为主栈A，一个为辅助栈B。主栈负责入栈和出栈所有元素。而辅助栈的栈顶元素永远都是当前最小的元素。
 * （2）当第一个元素进入栈A时，让新元素也进入栈B。这个唯一额元素是当前A的最小元素。
 * （3）之后，每当新元素进入栈A时，比较新元素和栈A当前的最小值的大小，若小于栈A当前的最小值，则
 * 新元素进入栈B，此时栈B的栈顶元素就是栈A当前最小值。
 * （4）每当栈A有元素出栈时，如果出栈元素是栈A当前最小值，则让栈B的栈顶元素也出栈。此时
 * 栈B新的栈顶元素正是栈A的第二小元素。
 * （5）当获取最小值时，返回栈B的栈顶元素即可。
 *
 * 时间复杂度是O(1)，最坏情况的空间复杂度为O(n)
 *
 */
public class GetStatckMinData {
    // 主栈
    private static Stack<Integer> mainStack = new Stack<Integer>();
    // 辅助栈
    private static Stack<Integer> minStack = new Stack<Integer>();

    /**
     * 入栈
     * @param data
     */
    public static void push(Integer data){
        // 进入主栈
        mainStack.push(data);
        // 如果辅助栈为空 ，或者辅助栈的栈顶元素大于新元素，则将新元素压入辅助栈
        if(minStack.empty() || data <= minStack.peek()){
            minStack.push(data);
        }
    }

    /**
     * 出栈
     */
    public static Integer pop(){
        // 若出栈元素与辅助栈的栈顶元素值相等，辅助栈出栈。
        if(minStack.peek().equals(mainStack.peek())){
            minStack.pop();
        }
        return mainStack.pop();
    }

    /**
     * 获取最小元素
     * @return
     */
    public static Integer getMin(){
        if(mainStack.empty()){
            throw new RuntimeException("stack is null");
        }
        return minStack.peek();
    }

    public static void main(String[] args) {
        push(4);
        push(9);
        push(7);
        push(3);
        push(8);
        push(5);

        System.out.println(getMin());

        pop();
        pop();
        pop();
        pop();

        System.out.println(getMin());
    }
}
