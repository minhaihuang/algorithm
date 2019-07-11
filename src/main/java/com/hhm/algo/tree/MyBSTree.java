package com.hhm.algo.tree;

/**
 * 二叉查找树。
 （1）若左子树不为空，则左子树上所有节点的值均小于根节点的值
 （2）若右子树不为空，则右子树上所有节点的值均大于根节点的值
 （3）左右子树也都是查找二叉树/二叉排序树。
 */
public class MyBSTree {
    private static TreeNode treeNode = null;

    public static void main(String[] args) {
        MyBSTree.insert(10);
        MyBSTree.insert(3);
        MyBSTree.insert(5);
        MyBSTree.insert(1);
        MyBSTree.insert(2);
        MyBSTree.insert(9);
        MyBSTree.insert(12);
        MyBSTree.insert(8);
        MyBSTree.insert(11);
//        output(SearchTree.treeNode);
//
        System.out.println("==============测试查找节点======================");
        TreeNode treeNode = getTreeNodeByData(MyBSTree.getTreNode(),5);
        System.out.println(treeNode.getParent().getData());
        System.out.println("==============测试查找最小节点=====================");
        System.out.println(minTreeNode(MyBSTree.getTreNode()).getData());
        System.out.println("================测试删除节点====================");
        TreeNode deleteNode = remove(10);
        output(MyBSTree.treeNode);
    }

    public static TreeNode getTreNode(){
        return treeNode;
    }

    /**
     * 前序遍历打印树节点
     */
    public static void output(TreeNode node){
        if(node == null){
            System.out.println("null");
            return;
        }
        System.out.println(node.getData());
        output(node.leftChild);
        output(node.rightChild);
    }

    /**
     * 查询
     * @param data
     * @return
     */
    public static TreeNode getTreeNodeByData(TreeNode node, Integer data){
        if(data == null || node == null){
            return null;
        }
        if(node.getData() == data){
            return node;
        }
        // 从右字数查找
        if(node.getData() < data){
            return getTreeNodeByData(node.rightChild,data);
        }else{
            return  getTreeNodeByData(node.leftChild,data);
        }
    }

    /**
     * 删除节点
     * @param data
     * @return
     */
    public static TreeNode remove(Integer data){
        if(treeNode == null || data == null){
            return null;
        }
        TreeNode node = getTreeNodeByData(treeNode,data);
        if(node == null){
            return null;
        }
        TreeNode deleteNode = null;
        /**没有孩子节点或者只有一个孩子节点都是删除其本身**/
        if(node.leftChild == null || node.rightChild == null){
            deleteNode = node;
        } else { /**有两个孩子的情况**/
            deleteNode = minTreeNode(node.rightChild);
        }
        removeNode(deleteNode);
        if(deleteNode != node){
            node.data = deleteNode.data;
        }

        return node;
    }

    public static void removeNode(TreeNode node){
        TreeNode parent = node.parent;
        // 没有孩子节点
        if(node.leftChild == null && node.rightChild == null){
            if(parent.leftChild == node){
                parent.leftChild = null;
            }else {
                parent.rightChild = null;
            }
        }

        /**只有一个孩子的情况**/
        // 左孩子不为空，把父节点跟左孩子连接，即可以删除该节点
        if(node.leftChild != null && node.rightChild == null){
            if(parent.leftChild == node){
                parent.leftChild = node.leftChild;
            }else {
                parent.rightChild = node.leftChild;
            }
        }
        // 右孩子不为空，把父节点跟左孩子连接，即可以删除该节点
        if(node.rightChild != null && node.leftChild == null){
            if(parent.leftChild == node){
                parent.leftChild = node.rightChild;
            }else {
                parent.rightChild = node.rightChild;
            }
        }
    }

    /**
     * 找到最小节点。
     * @param node
     * @return
     */
    public static TreeNode minTreeNode(TreeNode node){
        if(node == null){
            return null;
        }
        while (node.leftChild != null){
            node = node.leftChild;
        }
        return node;
    }

    /**
     * 插入
     * @param
     */
    public static void insert(Integer data){
        // 头节点为空的情况
       if(treeNode == null){
           treeNode = new TreeNode(data);
           return;
       }
       TreeNode temp = treeNode;
       while (temp != null){
           Integer hData = temp.getData();
           if(hData > data){
               if(temp.leftChild == null){
                   temp.leftChild = new TreeNode(data);
                   temp.leftChild.setParent(temp);
                   return;
               }else{
                   temp = temp.leftChild;
               }
           }
           if(hData < data){
               if(temp.rightChild == null){
                   temp.rightChild = new TreeNode(data);
                   temp.rightChild.setParent(temp);
                   return;
               }else{
                   temp = temp.rightChild;
               }
           }
       }
    }
    /**
     * 节点
     */
    private static class TreeNode{
        private Integer data;
        private TreeNode leftChild;
        private TreeNode rightChild;
        private TreeNode parent;

        public TreeNode(Integer data) {
            this.data = data;
        }

        public Integer getData() {
            return data;
        }

        public void setData(Integer data) {
            this.data = data;
        }

        public TreeNode getLeftChild() {
            return leftChild;
        }

        public void setLeftChild(TreeNode leftChild) {
            this.leftChild = leftChild;
        }

        public TreeNode getRightChild() {
            return rightChild;
        }

        public void setRightChild(TreeNode rightChild) {
            this.rightChild = rightChild;
        }

        public TreeNode getParent() {
            return parent;
        }

        public void setParent(TreeNode parent) {
            this.parent = parent;
        }
    }
}
