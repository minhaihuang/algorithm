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

    /**
     * 节点数据结构
     * @param <T>
     */
    private class Node<T>{
        private T value;
        private Node<T> left;
        private Node<T> right;
        private Node<T> parent;

        public T getValue() {
            return value;
        }

        public void setValue(T value) {
            this.value = value;
        }

        public Node<T> getLeft() {
            return left;
        }

        public void setLeft(Node<T> left) {
            this.left = left;
        }

        public Node<T> getRight() {
            return right;
        }

        public void setRight(Node<T> right) {
            this.right = right;
        }

        public Node<T> getParent() {
            return parent;
        }

        public void setParent(Node<T> parent) {
            this.parent = parent;
        }
    }
}
