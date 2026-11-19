// Exp 4: B+ Tree Implementation
// Keys are stored in leaves, and leaves are linked together

import java.util.ArrayList;
import java.util.Collections;

public class Exp4_BPlusTree {
    static final int ORDER = 3; // max keys before split

    static class Node {
        boolean leaf;
        ArrayList<Integer> keys = new ArrayList<>();
        ArrayList<Node> children = new ArrayList<>();
        Node next = null; // linked list pointer for leaves

        Node(boolean leaf) {
            this.leaf = leaf;
        }
    }

    static Node root = new Node(true);

    // search for key
    static boolean search(int key) {
        Node curr = root;
        while (!curr.leaf) {
            int i = 0;
            while (i < curr.keys.size() && key >= curr.keys.get(i)) i++;
            curr = curr.children.get(i);
        }
        return curr.keys.contains(key);
    }

    // insert key
    static void insert(int key) {
        Node leaf = findLeaf(root, key);
        leaf.keys.add(key);
        Collections.sort(leaf.keys);

        if (leaf.keys.size() > ORDER) {
            splitLeaf(leaf);
        }
    }

    static Node findLeaf(Node curr, int key) {
        if (curr.leaf) return curr;
        int i = 0;
        while (i < curr.keys.size() && key >= curr.keys.get(i)) i++;
        return findLeaf(curr.children.get(i), key);
    }

    static void splitLeaf(Node leaf) {
        int mid = leaf.keys.size() / 2;
        Node newLeaf = new Node(true);
        newLeaf.keys.addAll(leaf.keys.subList(mid, leaf.keys.size()));
        leaf.keys.subList(mid, leaf.keys.size()).clear();

        // link leaf nodes
        newLeaf.next = leaf.next;
        leaf.next = newLeaf;

        int promoKey = newLeaf.keys.get(0);

        if (leaf == root) {
            Node newRoot = new Node(false);
            newRoot.keys.add(promoKey);
            newRoot.children.add(leaf);
            newRoot.children.add(newLeaf);
            root = newRoot;
        } else {
            insertParent(root, leaf, promoKey, newLeaf);
        }
    }

    static void insertParent(Node curr, Node left, int key, Node right) {
        if (curr == null || curr.leaf) return;

        int idx = curr.children.indexOf(left);
        if (idx != -1) {
            curr.keys.add(idx, key);
            curr.children.add(idx + 1, right);
            return;
        }
        for (Node c : curr.children) insertParent(c, left, key, right);
    }

    static void printLeaves() {
        Node curr = root;
        while (!curr.leaf) curr = curr.children.get(0);
        System.out.print("Leaves: ");
        while (curr != null) {
            System.out.print(curr.keys + " -> ");
            curr = curr.next;
        }
        System.out.println("null");
    }

    public static void main(String[] args) {
        long startTime = System.nanoTime();

        int[] arr = {10, 20, 5, 6, 12, 30, 7, 17};
        System.out.print("Inserted: ");
        for (int x : arr) {
            System.out.print(x + " ");
            insert(x);
        }
        System.out.println();

        printLeaves();
        System.out.println("Search 12: " + (search(12) ? "Found" : "Not Found"));
        System.out.println("Search 25: " + (search(25) ? "Found" : "Not Found"));

        long endTime = System.nanoTime();
        System.out.println("\nExecution Time: " + (endTime - startTime) + " ns");
        System.out.println("Time Complexity: O(log n)");
        System.out.println("Space Complexity: O(n)");
    }
}
