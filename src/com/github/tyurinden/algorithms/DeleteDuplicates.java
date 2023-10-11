package com.github.tyurinden.algorithms;

/**
 * Удалить дубликаты из односвязного отсортированного списка: head = [1,1,2,3,3] -> head = [1,2,3]
 * <p>
 * Сложность: O(n)
 **/
public class DeleteDuplicates {
    public static void main(String[] args) {
        var n3 = new ListNode(3);
        var n33 = new ListNode(3, n3);
        var n22 = new ListNode(2, n33);
        var n2 = new ListNode(2, n22);
        var n1 = new ListNode(1, n2);

        var head = new ListNode(1, n1);

        printList(head);

        deleteDuplicates(head);
        System.out.println();
        printList(head);

    }

    private static ListNode deleteDuplicates(ListNode head) {
        if (head.next == null) {
            return head;
        }

        ListNode current = head;
        ListNode next = head.next;

        while (next != null) {
            while (next != null && current.val == next.val) {
                next = next.next;
            }

            if (next != null) {
                current.next = next;
                current = next;
                next = next.next;
            } else {
                current.next = next; //null
            }
        }

        return head;
    }

    private static void printList(ListNode head) {
        while (head != null) {
            System.out.print("{" + head.val + "} ");
            head = head.next;
        }
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }

        @Override
        public String toString() {
            return "ListNode{" +
                   "val=" + val +
                   '}';
        }
    }
}
