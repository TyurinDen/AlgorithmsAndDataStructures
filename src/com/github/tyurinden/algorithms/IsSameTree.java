package com.github.tyurinden.algorithms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Сравниваем два бинарных дерева. Деревья должны совпадать по структуре и по значениям в узлах
 * <p>
 * Сложность: O(n)
 * <p>
 **/
public class IsSameTree {
    public static void main(String[] args) {
        var n2 = new TreeNode(2);
        var n3 = new TreeNode(3);

        var p = new TreeNode(1, n2, n3);
        var q = new TreeNode(1, n2, n3);
        System.out.println("isSameTree(p, q) = " + isSameTreeIteratively(p, q));
        System.out.println("isSameTree(p, q) Recursively = " + isSameTreeRecursively(p, q));

        p = new TreeNode(1, n2, n3);
        q = new TreeNode(1, n3, n2);
        System.out.println("isSameTree(p, q) = " + isSameTreeIteratively(p, q));
        System.out.println("isSameTree(p, q) Recursively = " + isSameTreeRecursively(p, q));

        p = new TreeNode(1, n2, null);
        q = new TreeNode(1, null, n2);
        System.out.println("isSameTree(p, q) = " + isSameTreeIteratively(p, q));
        System.out.println("isSameTree(p, q) Recursively = " + isSameTreeRecursively(p, q));
    }

    private static boolean isSameTreeRecursively(TreeNode p, TreeNode q) {
        //моя реализация через рекурсию
        if (p == null & q == null) { //p and q is null -> nodes are the same
            return true;
        }

        if (p != null & q != null) { //p and q both are not null
            if (p.val == q.val) { //nodes are the same
                return isSameTreeRecursively(p.left, q.left) && isSameTreeRecursively(p.right, q.right);
            }
        }

        return false;
    }

    private static boolean isSameTree(TreeNode p, TreeNode q) {
        //реализация через рекурсию Евгения Сулейманова
        if (p == null & q == null) { //p or q is null -> nodes are the same
            return true;
        }

        if (p == null || q == null) { //p or q is null -> nodes are not the same
            return false;
        }

        if (p.val != q.val) { //p and q both are not null
            return false;
        }

        return isSameTreeRecursively(p.left, q.left) && isSameTreeRecursively(p.right, q.right);
    }

    //реализация без рекурсии. обходим каждое дерево и собираем все ноды в листы, после чего бежим по листам и сравниваем элементы
    //работает и даже показала неплохой результат на литкод, но оч многословно, хотя оч понятно
    //можно чуть оптимизировать, если при заполнении второго листа сразу же сравнивать ноды с первым
    private static boolean isSameTreeIteratively(TreeNode p, TreeNode q) {
        List<TreeNode> pNodes = getAllNodesAsList(p);
        List<TreeNode> qNodes = getAllNodesAsList(q);

        if (pNodes.size() != qNodes.size()) {
            return false;
        }

        for (int i = 0; i < pNodes.size(); i++) {
            var pNode = pNodes.get(i);
            var qNode = qNodes.get(i);

            if (pNode.val != qNode.val) {
                return false;
            }
            if (pNode.left == null & qNode.left != null) {
                return false;
            }
            if (pNode.left != null & qNode.left == null) {
                return false;
            }
            if (pNode.right == null & qNode.right != null) {
                return false;
            }
            if (pNode.right != null & qNode.right == null) {
                return false;
            }
        }

        return true;
    }

    private static List<TreeNode> getAllNodesAsList(TreeNode root) {
        List<TreeNode> nodes;
        LinkedList<TreeNode> queue;

        if (root == null) {
            return Collections.emptyList();
        }
        queue = new LinkedList<>();
        nodes = new ArrayList<>();

        TreeNode node = root;
        while (!queue.isEmpty() || node != null) {

            while (node != null) {
                nodes.add(node);
                if (node.left != null) {
                    queue.push(node);
                }
                node = node.right;
            }

            if (!queue.isEmpty()) {
                node = queue.pop().left;
            }
        }

        return nodes;
    }

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }

        public TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }

        @Override
        public String toString() {
            return "TreeNode{" +
                   "value=" + val +
                   '}';
        }
    }
}
