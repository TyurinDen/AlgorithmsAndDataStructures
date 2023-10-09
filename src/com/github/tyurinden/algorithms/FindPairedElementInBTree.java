package com.github.tyurinden.algorithms;

import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * Поиск двух элементов в бинарном дереве, которые в сумме дают заданное число
 * <p>
 * После получения элементов дерева в виде сета итерируемся по сету и для каждого элемента ищем парный элемент в дереве
 * Сложность: O(n*log(n))
 * <p>
 * Также можно итерироваться по дереву и искать парный элемент в сете, что даст сложность O(log(n))
 **/
public class FindPairedElementInBTree {
    public static void main(String[] args) {
        var n9 = new TreeNode(9);
        var n4 = new TreeNode(4);
        var n6 = new TreeNode(6);
        var n3n = new TreeNode(-3);

        var n0 = new TreeNode(0, n3n, null);
        var n7 = new TreeNode(7, n6, n9);
        var n3 = new TreeNode(3, null, n4);
        var n2 = new TreeNode(2, n0, n3);
        var root = new TreeNode(5, n2, n7);

        //        System.out.println(findPairedElement(root, 10));
        //        System.out.println(findPairedElement(root, 13));
        //        System.out.println(findPairedElement(root, 22));
        //        System.out.println(findPairedElement(root, 3));

        System.out.println(formSetFromBTreeIteratively(root));
    }

    private static boolean findPairedElement(TreeNode root, int k) {
        HashSet<Integer> treeElements = new HashSet<>();

        formSetFromBTreeRecursively(root, treeElements);
        System.out.println("treeElements = " + treeElements);

        for (Integer treeElement : treeElements) {
            if (findElementInBTree(root, k - treeElement) != null) {
                return true;
            }
        }
        return false;
    }

    private static Set<Integer> formSetFromBTreeIteratively(TreeNode root) {
        Set<Integer> treeElms;
        LinkedList<TreeNode> queue;

        // Обрабатываем текущий узел, при наличии левого поддерева добавляем его в стек для последующей обработки.
        // Переходим к узлу правого поддерева. Если правого узла нет, переходим к верхнему узлу из стека.
        if (root == null) {
            return Collections.emptySet();
        }
        queue = new LinkedList<>();
        treeElms = new HashSet<>();

        TreeNode node = root;
        while (!queue.isEmpty() || node != null) {

            while (node != null) {
                treeElms.add(node.value);
                System.out.println("added to set: " + node.value);

                if (node.left != null) {
                    queue.push(node);
                    System.out.println("pushed node = " + node);
                }
                node = node.right;
            }

            if (!queue.isEmpty()) {
                node = queue.pop().left;
                System.out.println("popped node = " + node);
            }
        }

        return treeElms;
    }

    private static void formSetFromBTreeRecursively(TreeNode root, HashSet<Integer> set) {
        if (root != null) {
            set.add(root.value);
            if (root.left != null) {
                formSetFromBTreeRecursively(root.left, set);
            }
            if (root.right != null) {
                formSetFromBTreeRecursively(root.right, set);
            }
        }

    }

    private static TreeNode findElementInBTree(TreeNode root, int k) {
        if (root != null) {
            if (root.value == k) {
                return root;
            }
            if (k > root.value) {
                return findElementInBTree(root.right, k);
            }
            return findElementInBTree(root.left, k);
        }

        return null;
    }

    private static class TreeNode {
        int value;
        TreeNode left;
        TreeNode right;

        public TreeNode(int value) {
            this.value = value;
        }

        public TreeNode(int value, TreeNode left, TreeNode right) {
            this.value = value;
            this.left = left;
            this.right = right;
        }

        @Override
        public String toString() {
            return "TreeNode{" +
                   "value=" + value +
                   '}';
        }
    }
}
