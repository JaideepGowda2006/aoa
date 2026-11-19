// Experiment 3: Implementation of B-Tree
// Standard B-Tree with search, insert, and traversal operations

public class Exp3_BTree {

    // Node class for B-Tree
    static class BTreeNode {
        int[] keys;             // array of keys
        int t;                  // minimum degree (defines range [t-1, 2t-1])
        BTreeNode[] children;   // array of child pointers
        int n;                  // current number of keys in node
        boolean isLeaf;         // true if node is leaf

        public BTreeNode(int t, boolean isLeaf) {
            this.t = t;
            this.isLeaf = isLeaf;
            this.keys = new int[2 * t - 1];       // maximum 2t-1 keys
            this.children = new BTreeNode[2 * t]; // maximum 2t children
            this.n = 0;
        }

        // in-order traversal to print keys
        public void traverse() {
            int i;
            for (i = 0; i < this.n; i++) {
                // if not leaf, first visit left child
                if (!this.isLeaf) {
                    children[i].traverse();
                }
                System.out.print(keys[i] + " ");
            }
            // print the subtree of the last child
            if (!this.isLeaf) {
                children[i].traverse();
            }
        }

        // search a key in subtree rooted with this node
        public BTreeNode search(int k) {
            int i = 0;
            while (i < n && k > keys[i]) {
                i++;
            }
            // if key found here
            if (i < n && keys[i] == k) {
                return this;
            }
            // if leaf and key not found, return null
            if (isLeaf) {
                return null;
            }
            // search in child
            return children[i].search(k);
        }

        // insert a key in a node that is NOT full
        public void insertNonFull(int k) {
            int i = n - 1;

            if (isLeaf) {
                // shift keys to make space for new key
                while (i >= 0 && keys[i] > k) {
                    keys[i + 1] = keys[i];
                    i--;
                }
                keys[i + 1] = k;
                n++;
            } else {
                // find child which will get new key
                while (i >= 0 && keys[i] > k) {
                    i--;
                }
                // check if found child is full
                if (children[i + 1].n == 2 * t - 1) {
                    splitChild(i + 1, children[i + 1]);
                    if (keys[i + 1] < k) {
                        i++;
                    }
                }
                children[i + 1].insertNonFull(k);
            }
        }

        // split full child node y of this node
        public void splitChild(int i, BTreeNode y) {
            // create new node z to store (t-1) keys of y
            BTreeNode z = new BTreeNode(y.t, y.isLeaf);
            z.n = t - 1;

            // copy last (t-1) keys of y to z
            for (int j = 0; j < t - 1; j++) {
                z.keys[j] = y.keys[j + t];
            }

            // copy children if y is not leaf
            if (!y.isLeaf) {
                for (int j = 0; j < t; j++) {
                    z.children[j] = y.children[j + t];
                }
            }

            y.n = t - 1;

            // create space for new child pointer
            for (int j = n; j >= i + 1; j--) {
                children[j + 1] = children[j];
            }
            children[i + 1] = z;

            // move middle key of y up to this node
            for (int j = n - 1; j >= i; j--) {
                keys[j + 1] = keys[j];
            }
            keys[i] = y.keys[t - 1];
            n++;
        }
    }

    // BTree class
    static class BTree {
        BTreeNode root;
        int t; // minimum degree

        public BTree(int t) {
            this.root = null;
            this.t = t;
        }

        public void traverse() {
            if (root != null) {
                root.traverse();
            }
            System.out.println();
        }

        public BTreeNode search(int k) {
            return (root == null) ? null : root.search(k);
        }

        public void insert(int k) {
            // if tree is empty
            if (root == null) {
                root = new BTreeNode(t, true);
                root.keys[0] = k;
                root.n = 1;
            } else {
                // if root is full, tree grows in height
                if (root.n == 2 * t - 1) {
                    BTreeNode s = new BTreeNode(t, false);
                    s.children[0] = root;
                    s.splitChild(0, root);

                    int i = 0;
                    if (s.keys[0] < k) {
                        i++;
                    }
                    s.children[i].insertNonFull(k);
                    root = s;
                } else {
                    root.insertNonFull(k);
                }
            }
        }
    }

    public static void main(String[] args) {
        long startTime = System.nanoTime();

        // minimum degree t = 3 (max keys per node = 5)
        BTree tree = new BTree(3);

        int[] values = {10, 20, 5, 6, 12, 30, 7, 17};
        System.out.print("Inserting values: ");
        for (int v : values) {
            System.out.print(v + " ");
            tree.insert(v);
        }
        System.out.println();

        System.out.print("Traversal of B-Tree (Sorted Keys): ");
        tree.traverse();

        // searching keys
        int searchKey1 = 12;
        int searchKey2 = 99;
        System.out.println("Searching for " + searchKey1 + ": " + (tree.search(searchKey1) != null ? "Found!" : "Not Found"));
        System.out.println("Searching for " + searchKey2 + ": " + (tree.search(searchKey2) != null ? "Found!" : "Not Found"));

        long endTime = System.nanoTime();
        long timeTaken = endTime - startTime;

        System.out.println("\n--- Complexity & Execution Time ---");
        System.out.println("Execution Time: " + timeTaken + " ns (" + (timeTaken / 1_000_000.0) + " ms)");
        System.out.println("Time Complexity: O(log n) for Search and Insert");
        System.out.println("Space Complexity: O(n) to store keys and pointers");
    }
}
