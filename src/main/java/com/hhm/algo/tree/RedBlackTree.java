package com.hhm.algo.tree;

/**
 * 红黑树
 性质：
 1. 节点是红色或黑色。
 2. 根节点是黑色。
 3. 每个红色节点的两个子节点都是黑色。(从每个叶子到根的所有路径上不能有两个连续的红色节点)
 4. 从任一节点到其每个叶子的所有路径都包含相同数目的黑色节点。
 5. 空白的叶子节点都是黑色的

 左旋转：
 想象一个罗盘向左旋转，把目标节点A的右节点B替换当前节点，调整后，目标节点A变成节点B的左节点，同时把B的左节点变成A的右节点。其他的节点还是原先的级别。
 右旋转：
 想象一个罗盘向右旋转。把目标节点A的左节点B替换当前节点，调整后，目标节点A变成节点B的右节点，同时把B的右节点变成A的左节点。其他的节点还是原先的级别。

 */
public class RedBlackTree {
    private Node tree;

    public static void main(String[] args) {
        RedBlackTree redBlackTree = new RedBlackTree();
        redBlackTree.insert(4);
        redBlackTree.insert(6);
        redBlackTree.insert(3);
        redBlackTree.insert(7);
        redBlackTree.insert(5);

        Node tree = redBlackTree.getTree();
        redBlackTree.prePrintTree(tree);
        System.out.println("=================左旋=================");
        redBlackTree.leftRotate(6);
        redBlackTree.prePrintTree(redBlackTree.getTree());
        System.out.println("=================右旋=================");
        redBlackTree.rightRotate(7);
        redBlackTree.prePrintTree(redBlackTree.getTree());
    }

    /**
     * 前序遍历树
     */
    public void prePrintTree(Node node){
        if(node == null){
            System.out.println("null");
            return;
        }
        System.out.println(node.getKey()+","+node.isRed);
        prePrintTree(node.left);
        prePrintTree(node.right);
    }

    private Node getNode(Integer key){
      Node temp = tree;
      while (!key.equals(temp.getKey())) { //若证书不在-127至128中间时，==不等
          Integer tempKey = temp.getKey();
          if(key > tempKey){
              temp = temp.right;
          }else {
              temp = temp.left;
          }
      }
      return temp;
    }

    /**
     * 跳转二叉树，修复插入后破坏红黑树的情况
     * @param node
     */
    private void fixInsert(Node node){
        Node parent = node.parent;
        if(parent == null){ // 根节点
            node.isRed = false; // 根节点设为黑色
            return;
        }
        // 父亲是黑色的，返回
        if(!parent.isRed){
            return;
        }
        Node uncle = null;
        if(parent.parent.left == parent){ // 叔叔为右孩子
            uncle = parent.parent.right;
        } else {
            uncle = parent.parent.left;
        }
        /** case1: 父亲为红色，叔叔也为红色 **/
        if(uncle.isRed){
            fixCase1(node,parent,uncle);
        }
    }

    /**
     * case 1：F为红，U为红。父亲是红色，且叔叔节点也为红色
     * 操作：将F以及U设置为黑，G设为红，并将G看成新插入的节点（即下一轮的S），递归操作。
     * 原理：这个操作实际是想将红色往根处移动。将红色往上移了一层，并不会打破红黑树的特性，不断的把红色往上移动，
     *      当移动到根时，直接将根设置为黑色，就完全符合红黑树的性质了
     */
    private void fixCase1(Node node, Node parent, Node uncle){
        Node grandFather = parent.parent;
        // 祖父设为红色
        grandFather.isRed = true;
        // 叔叔和父亲都设为黑色
        parent.isRed = false;
        uncle.isRed = false;

        //用祖父作为新的起点递归
        fixInsert(grandFather);
    }

    /**
     * 插入
     * @param
     */
    public Node insert(Integer key){
        // 头节点为空的情况
        if(tree == null){
            tree = new Node(key,false);
            return tree;
        }
        Node temp = tree;
        Node newNode = new Node(key,true);
        while (temp != null){
            Integer hData = temp.getKey();
            if(hData > key){
                if(temp.left == null){
                    temp.left = newNode;
                    temp.left.setParent(temp);
                    break;
                }else{
                    temp = temp.left;
                }
            }
            if(hData < key){
                if(temp.right == null){
                    temp.right = newNode;
                    temp.right.setParent(temp);
                    break;
                }else{
                    temp = temp.right;
                }
            }
        }
        fixInsert(newNode);
        return tree;
    }

    /**
     * 左旋。设目标节点为x，x的父节点为xP，x的右孩子为xR，xR的左孩子节点为XRL。
     * 则左旋后，xP为xR的父节点，x为xR的左节点，xRL为x的右节点。
     */
    public void leftRotate(Integer key){
        Node node = getNode(key);
        Node node_parent = node.parent;
        Node right = node.right;
        if(right == null){ // 右孩子没有，不用转了
            return;
        }
        Node right_left = null;
        //获取到右孩子节点，它将是替换target
        if(right != null){
            // 获取到右孩子节点的左孩子节点，它将成为target的右孩子节点
            right_left = right.left;
        }
        // 开始左移

        /**将当前节点的父节点与当前节点的右孩子关联**/
        if(node_parent.left == node){
            node_parent.left = right;
        }else {
            node_parent.right = right;
        }
        right.parent = node_parent;
        /**使当前节点成为右孩子的左节点**/
        right.left = node;
        node.parent = right;
        /**使右孩子节点的左孩子成为当前节点的右孩子**/
        node.right = right_left;
        if(right_left != null){
            right_left.parent = node;
        }

        if(right.parent == null){
            tree = right;
        }
    }

    /**
     * 右旋。设目标节点为x，x的父节点为xP，x的左孩子为xL，xL的右孩子节点为XLR。
     * 则左旋后，xP为xL的父节点，x为xL的右节点，xLR为x的左节点。
     */
    public void rightRotate(Integer key){
        Node node = getNode(key);
        Node node_parent = node.parent;
        Node left = node.left;
        if(left == null){ // 左孩子没有，不用转了
            return;
        }
        Node left_right = null;
        //获取到左孩子节点
        if(left != null){
            // 获取到左孩子节点的右孩子节点，它将成为target的左孩子节点
            left_right = left.right;
        }
        // 开始右移
        /**将当前节点的父节点与当前节点的左孩子关联**/
        if(node_parent.left == node){
            node_parent.left = left;
        }else {
            node_parent.right = left;
        }
        left.parent = node_parent;

        /**使当前节点成为左孩子的右节点**/
        left.right = node;
        node.parent = left;
        /**使左孩子节点的右孩子成为当前节点的左孩子**/
        node.left = left_right;
        if(left_right != null){
            left_right.parent = node;
        }

        if(left.parent == null){
            tree = left;
        }
    }

    /**
     * 节点数据结构
     */
    private class Node{
        private Integer key;
        private Node left;
        private Node right;
        private Node parent;
        private boolean isRed;

        public Node(Integer key, boolean isRed) {
            this.key = key;
            this.isRed = isRed;
        }

        public Integer getKey() {
            return key;
        }

        public void setKey(Integer key) {
            this.key = key;
        }

        public Node getLeft() {
            return left;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public Node getRight() {
            return right;
        }

        public void setRight(Node right) {
            this.right = right;
        }

        public Node getParent() {
            return parent;
        }

        public void setParent(Node parent) {
            this.parent = parent;
        }

        public boolean isRed() {
            return isRed;
        }

        public void setRed(boolean red) {
            isRed = red;
        }
    }

    public Node getTree() {
        return tree;
    }

    public void setTree(Node tree) {
        this.tree = tree;
    }
}
