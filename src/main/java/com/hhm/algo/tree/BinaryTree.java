package com.hhm.algo.tree;

import java.util.*;

/**
 * create by huanghaimin
 * 二叉树。
 * 包括构建二叉树，以及二叉树的前序、中序、后序、层次遍历
 */
public class BinaryTree {
    public static void main(String[] args) {
        LinkedList<Integer> intputList = new LinkedList<Integer>(Arrays.asList(new Integer[]{1,2,4,null,null,5,null,null,3,null,6}));
        TreeNode node = createBinaryTree(intputList);
        //preOrderTraveral(node);
        //inOrderTraveral(node);
        // postOrderTraveral(node);
        // levelOrderTraversal(node);
        preOrderTraveralWithStack(node);
    }

    /**
     * 前序遍历
     * @param treeNode
     */
    public static void preOrderTraveral(TreeNode treeNode){
        if(treeNode == null){
            System.out.println("null");
            return;
        }
        System.out.println(treeNode.getData());
        preOrderTraveral(treeNode.left);
        preOrderTraveral(treeNode.right);
    }

    /**
     * 中序遍历
     * @param treeNode
     */
    public static void inOrderTraveral(TreeNode treeNode){
        if(treeNode == null){
            System.out.println("null");
            return;
        }
        inOrderTraveral(treeNode.left);
        System.out.println(treeNode.getData());
        inOrderTraveral(treeNode.right);
    }

    /**
     * 后序遍历
     * @param treeNode
     */
    public static void postOrderTraveral(TreeNode treeNode){
        if(treeNode == null){
            System.out.println("null");
            return;
        }
        postOrderTraveral(treeNode.left);
        postOrderTraveral(treeNode.right);
        System.out.println(treeNode.getData());
    }

    /**
     * 构建二叉树
     * @param inputList
     * @return
     */
    public static TreeNode createBinaryTree(LinkedList<Integer> inputList){
        TreeNode node = null;
        if(inputList == null || inputList.isEmpty()){
            return null;
        }
        // 拿到首节点
        Integer data = inputList.removeFirst();
        if(data != null){
            node = new TreeNode(data);
            node.left = createBinaryTree(inputList);
            node.right = createBinaryTree(inputList);
        }
        return  node;
    }

    /**
     * 二叉树非递归前序遍历
     */
    public static void preOrderTraveralWithStack(TreeNode root){
        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode treeNode = root;
        while (treeNode != null || !stack.isEmpty()) {
            // 迭代访问节点的左孩子节点，并入栈
            while (treeNode != null){
                System.out.println(treeNode.getData());
                stack.push(treeNode);
                treeNode = treeNode.left;
            }
            // 如果节点没有左孩子节点，则弹出栈顶节点，访问节点右孩子
            if(!stack.isEmpty()){
                treeNode = stack.pop();
                treeNode = treeNode.right;
            }
        }
    }

    /**
     * 层次遍历。利用队列作为辅助。
     * @param root
     */
    public static void levelOrderTraversal(TreeNode root){
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        // 根节点入队
        queue.offer(root);
        while (!queue.isEmpty()) {
            // 节点出队
            TreeNode node = queue.poll();
            System.out.println(node.getData());
            // 左孩子入队
            if(node.left != null){
                queue.offer(node.left);
            }
            // 右孩子入队
            if(node.right != null){
                queue.offer(node.right);
            }
        }
    }

    private static class TreeNode{
        int data;
        TreeNode left;
        TreeNode right;

        public TreeNode(int data) {
            this.data = data;
        }

        public int getData() {
            return data;
        }

        public void setData(int data) {
            this.data = data;
        }

        public TreeNode getLeft() {
            return left;
        }

        public void setLeft(TreeNode left) {
            this.left = left;
        }

        public TreeNode getRight() {
            return right;
        }

        public void setRight(TreeNode right) {
            this.right = right;
        }
    }
}
