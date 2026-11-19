// Experiment 4: Implementation of B+ Tree
// In B+ Tree:
// 1. All actual data/keys are stored in the leaf nodes.
// 2. Leaf nodes are linked sequentially using 'next' pointers (great for range queries).
// 3. Internal nodes only hold routing/guide keys.

import java.util.ArrayList;
import java.util.Collections;

public class Exp4_BPlusTree {

    // Maximum number of keys a node can hold before splitting
    private static final int ORDER = 3;

    static class Node {
        boolean isLeaf;
        ArrayList<Integer> keys;
        ArrayList<Node> children; // for internal nodes
        Node next;                 // only for leaf nodes (linked list)

        public Node(boolean isLeaf) {
            this.isLeaf = isLeaf;
            this.keys = new ArrayList<>();
            this.children = isLeaf ? null : new ArrayList<>();
            this.next = null;
        }
    }

    private Node root;

    public Exp4_BPlusTree() {
        this.root = new Node(true); // initially root is an empty leaf
    }

    // search for a key in B+ tree
    public boolean search(int key) {
        Node curr = root;
        // traverse down to leaf
        while (!curr.isLeaf) {
            int i = 0;
            while (i < curr.keys.size() && key >= curr.keys.get(i)) {
                i++;
            }
            curr = curr.children.get(i);
        }
        // search inside the leaf
        return curr.keys.contains(key);
    }

    // insert a key into B+ tree
    public void insert(int key) {
        Node leaf = findLeaf(root, key);
        insertIntoSortedList(leaf.keys, key);

        // if leaf overflows, split it
        if (leaf.keys.size() > ORDER) {
            splitLeaf(leaf);
        }
    }

    // helper to find leaf node where key should go
    private Node findLeaf(Node curr, int key) {
        if (curr.isLeaf) return curr;
        int i = 0;
        while (i < curr.keys.size() && key >= curr.keys.get(i)) {
            i++;
        }
        return findLeaf(curr.children.get(i), key);
    }

    // helper to insert in sorted order
    private void insertIntoSortedList(ArrayList<Integer> list, int key) {
        list.add(key);
        Collections.sort(list);
    }

    // split leaf node
    private void splitLeaf(Node leaf) {
        int mid = leaf.keys.size() / 2;

        Node newLeaf = new Node(true);
        // move second half keys to new leaf
        newLeaf.keys.addAll(leaf.keys.subList(mid, leaf.keys.size()));
        leaf.keys.subList(mid, leaf.keys.size()).clear();

        // link leaf nodes
        newLeaf.next = leaf.next;
        leaf.next = newLeaf;

        int promotedKey = newLeaf.keys.get(0);

        // if splitting the root
        if (leaf == root) {
            Node newRoot = new Node(false);
            newRoot.keys.add(promotedKey);
            newRoot.children.add(leaf);
            newRoot.children.add(newLeaf);
            root = newRoot;
        } else {
            insertIntoParent(root, leaf, promotedKey, newLeaf);
        }
    }

    // insert promoted key and right child into parent node
    private void insertIntoParent(Node curr, Node leftChild, int key, Node rightChild) {
        if (curr == null || curr.isLeaf) return;

        // check if this is the direct parent
        int idx = curr.children.indexOf(leftChild);
        if (idx != -1) {
            curr.keys.add(idx, key);
            curr.children.add(idx + 1, rightChild);

            if (curr.keys.size() > ORDER) {
                splitInternal(curr);
            }
            return;
        }

        // otherwise recurse downwards to find parent
        for (Node child : curr.children) {
            insertIntoParent(child, leftChild, key, rightChild);
        }
    }

    // split internal node
    private void splitInternal(Node internal) {
        int mid = internal.keys.size() / 2;
        int promotedKey = internal.keys.get(mid);

        Node newInternal = new Node(false);
        newInternal.keys.addAll(internal.keys.subList(mid + 1, internal.keys.size()));
        newInternal.children.addAll(internal.children.subList(mid + 1, internal.children.size()));

        internal.keys.subList(mid, internal.keys.size()).clear();
        internal.children.subList(mid + 1, internal.children.size()).clear();

        if (internal == root) {
            Node newRoot = new Node(false);
            newRoot.keys.add(promotedKey);
            newRoot.children.add(internal);
            newRoot.children.add(newInternal);
            root = newRoot;
        } else {
            insertIntoParent(root, internal, promotedKey, newInternal);
        }
    }

    // print linked leaves (shows all stored keys sequentially)
    public void printLeaves() {
        Node curr = root;
        while (!curr.isLeaf) {
            curr = curr.children.get(0);
        }
        System.out.print("Leaf Linked List: ");
        while (curr != null) {
            System.out.print(curr.keys + " -> ");
            curr = curr.next;
        }
        System.out.println("null");
    }

    public static void main(String[] args) {
        long startTime = System.nanoTime();

        Exp4_BPlusTree bpt = new Exp4_BPlusTree();
        int[] data = {10, 20, 5, 6, 12, 30, 7, 17};

        System.out.print("Inserting keys into B+ Tree: ");
        for (int x : data) {
            System.out.print(x + " ");
            bpt.insert(x);
        }
        System.out.println();

        // print all keys across leaf linked list
        bpt.printLeaves();

        // search tests
        int test1 = 12;
        int test2 = 25;
        System.out.println("Search " + test1 + ": " + (bpt.search(test1) ? "Found!" : "Not Found"));
        System.out.println("Search " + test2 + ": " + (bpt.search(test2) ? "Found!" : "Not Found"));

        long endTime = System.nanoTime();
        long timeTaken = endTime - startTime;

        System.out.println("\n--- Complexity & Execution Time ---");
        System.out.println("Execution Time: " + timeTaken + " ns (" + (timeTaken / 1_000_000.0) + " ms)");
        System.out.println("Time Complexity: O(log n) for Search and Insert");
        System.out.println("Time Complexity for Range Search: O(log n + k)  [k = number of elements]");
        System.out.println("Space Complexity: O(n)");
    }
}
