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

        redBlackTree.prePrintTree(redBlackTree.tree);
        System.out.println("=================左旋=================");
        redBlackTree.leftRotate(redBlackTree.tree);
        redBlackTree.prePrintTree(redBlackTree.getTree());
        System.out.println("=================右旋=================");
        redBlackTree.rightRotate(redBlackTree.tree);
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


    private void insertFix(Node node) {
        Node father, grandFather;
        while ((father = node.getParent()) != null && father.isRed) {
            grandFather = father.getParent();
            if (grandFather.getLeft() == father) {  //F为G左儿子的情况，如之前的分析
                Node uncle = grandFather.getRight();
                if (uncle != null && uncle.isRed) {
                    father.isRed = false;
                    uncle.isRed = false;
                    grandFather.isRed = true;
                    node = grandFather;
                    continue;
                }
                if (node == father.getRight()) {
                    leftRotate(father);
                    Node tmp = node;
                    node = father;
                    father = tmp;
                }
                father.isRed = false;
                grandFather.isRed = true;
                rightRotate(grandFather);
            } else {                               //F为G的右儿子的情况，对称操作
                Node uncle = grandFather.getLeft();
                if (uncle != null && uncle.isRed) {
                    father.isRed = false;
                    uncle.isRed = false;
                    grandFather.isRed = true;
                    node = grandFather;
                    continue;
                }
                if (node == father.getLeft()) {
                    rightRotate(father);
                    Node tmp = node;
                    node = father;
                    father = tmp;
                }
                father.isRed = false;
                grandFather.isRed = true;
                leftRotate(grandFather);
            }
        }
        tree.isRed = false;
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
        insertFix(newNode);
        return tree;
    }

    /**
     * 左旋。设目标节点为x，x的父节点为xP，x的右孩子为xR，xR的左孩子节点为XRL。
     * 则左旋后，xP为xR的父节点，x为xR的左节点，xRL为x的右节点。
     */
    public void leftRotate(Node node){
        Node right = node.getRight();
        Node parent = node.getParent();
        if (parent == null) {
            tree = right;
            right.setParent(null);
        } else {
            if (parent.getLeft() != null && parent.getLeft() == node) {
                parent.setLeft(right);
            } else {
                parent.setRight(right);
            }
            right.setParent(parent);
        }
        node.setParent(right);
        node.setRight(right.getLeft());
        if (right.getLeft() != null) {
            right.getLeft().setParent(node);
        }
        right.setLeft(node);
    }

    /**
     * 右旋。设目标节点为x，x的父节点为xP，x的左孩子为xL，xL的右孩子节点为XLR。
     * 则左旋后，xP为xL的父节点，x为xL的右节点，xLR为x的左节点。
     */
    public void rightRotate(Node node){
        Node left = node.getLeft();
        Node parent = node.getParent();
        if (parent == null) {
            tree = left;
            left.setParent(null);
        } else {
            if (parent.getLeft() != null && parent.getLeft() == node) {
                parent.setLeft(left);
            } else {
                parent.setRight(left);
            }
            left.setParent(parent);
        }
        node.setParent(left);
        node.setLeft(left.getRight());
        if (left.getRight() != null) {
            left.getRight().setParent(node);
        }
        left.setRight(node);
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
