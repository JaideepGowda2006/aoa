// Exp 3: B-Tree Implementation
// Insertion, Search, and In-order Traversal

public class Exp3_BTree {

    static class Node {
        int[] keys;
        Node[] children;
        int count;
        boolean leaf;

        Node(int t, boolean leaf) {
            this.leaf = leaf;
            this.keys = new int[2 * t - 1];
            this.children = new Node[2 * t];
            this.count = 0;
        }

        // print keys in order
        void traverse() {
            int i;
            for (i = 0; i < count; i++) {
                if (!leaf) children[i].traverse();
                System.out.print(keys[i] + " ");
            }
            if (!leaf) children[i].traverse();
        }

        // search key
        Node search(int k) {
            int i = 0;
            while (i < count && k > keys[i]) i++;
            if (i < count && keys[i] == k) return this;
            if (leaf) return null;
            return children[i].search(k);
        }

        // insert when node is not full
        void insertNonFull(int k, int t) {
            int i = count - 1;
            if (leaf) {
                while (i >= 0 && keys[i] > k) {
                    keys[i + 1] = keys[i];
                    i--;
                }
                keys[i + 1] = k;
                count++;
            } else {
                while (i >= 0 && keys[i] > k) i--;
                i++;
                if (children[i].count == 2 * t - 1) {
                    splitChild(i, children[i], t);
                    if (keys[i] < k) i++;
                }
                children[i].insertNonFull(k, t);
            }
        }

        // split full child node
        void splitChild(int i, Node y, int t) {
            Node z = new Node(t, y.leaf);
            z.count = t - 1;

            for (int j = 0; j < t - 1; j++) z.keys[j] = y.keys[j + t];
            if (!y.leaf) {
                for (int j = 0; j < t; j++) z.children[j] = y.children[j + t];
            }
            y.count = t - 1;

            for (int j = count; j >= i + 1; j--) children[j + 1] = children[j];
            children[i + 1] = z;

            for (int j = count - 1; j >= i; j--) keys[j + 1] = keys[j];
            keys[i] = y.keys[t - 1];
            count++;
        }
    }

    static class BTree {
        Node root = null;
        int t;

        BTree(int t) {
            this.t = t;
        }

        void insert(int k) {
            if (root == null) {
                root = new Node(t, true);
                root.keys[0] = k;
                root.count = 1;
            } else {
                if (root.count == 2 * t - 1) {
                    Node s = new Node(t, false);
                    s.children[0] = root;
                    s.splitChild(0, root, t);
                    int i = (s.keys[0] < k) ? 1 : 0;
                    s.children[i].insertNonFull(k, t);
                    root = s;
                } else {
                    root.insertNonFull(k, t);
                }
            }
        }

        void traverse() {
            if (root != null) root.traverse();
            System.out.println();
        }

        boolean search(int k) {
            return root != null && root.search(k) != null;
        }
    }

    public static void main(String[] args) {
        long startTime = System.nanoTime();

        BTree tree = new BTree(3);
        int[] arr = {10, 20, 5, 6, 12, 30, 7, 17};

        System.out.print("Inserted: ");
        for (int x : arr) {
            System.out.print(x + " ");
            tree.insert(x);
        }
        System.out.println();

        System.out.print("Traversal: ");
        tree.traverse();

        System.out.println("Search 12: " + (tree.search(12) ? "Found" : "Not Found"));
        System.out.println("Search 99: " + (tree.search(99) ? "Found" : "Not Found"));

        long endTime = System.nanoTime();
        System.out.println("\nExecution Time: " + (endTime - startTime) + " ns");
        System.out.println("Time Complexity: O(log n)");
        System.out.println("Space Complexity: O(n)");
    }
}
