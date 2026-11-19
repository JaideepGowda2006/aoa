# Experiment 3: Implementation of B-Tree
# B-Tree with Search, Insert, and In-order Traversal

import time

class BTreeNode:
    def __init__(self, t, is_leaf=True):
        self.t = t                      # minimum degree
        self.is_leaf = is_leaf          # boolean: is leaf or not
        self.keys = []                  # list of keys (max 2*t - 1)
        self.children = []              # list of child pointers (max 2*t)

    # in-order traversal to print keys in sorted order
    def traverse(self):
        for i in range(len(self.keys)):
            if not self.is_leaf:
                self.children[i].traverse()
            print(self.keys[i], end=" ")
        if not self.is_leaf:
            self.children[len(self.keys)].traverse()

    # search key in subtree rooted at this node
    def search(self, k):
        i = 0
        while i < len(self.keys) and k > self.keys[i]:
            i += 1
        
        # if key is found
        if i < len(self.keys) and self.keys[i] == k:
            return self
        
        # if key not found and node is leaf
        if self.is_leaf:
            return None
        
        # recurse in child
        return self.children[i].search(k)

    # insert into node that is not full
    def insert_non_full(self, k):
        i = len(self.keys) - 1

        if self.is_leaf:
            # find location and insert in sorted order
            self.keys.append(None)
            while i >= 0 and self.keys[i] > k:
                self.keys[i + 1] = self.keys[i]
                i -= 1
            self.keys[i + 1] = k
        else:
            # find appropriate child
            while i >= 0 and self.keys[i] > k:
                i -= 1
            i += 1

            # if child is full, split it first
            if len(self.children[i].keys) == (2 * self.t - 1):
                self.split_child(i, self.children[i])
                if self.keys[i] < k:
                    i += 1
            self.children[i].insert_non_full(k)

    # split child y of this node
    def split_child(self, i, y):
        t = self.t
        z = BTreeNode(t, y.is_leaf)

        # z takes last (t - 1) keys of y
        z.keys = y.keys[t:]
        promoted_key = y.keys[t - 1]
        y.keys = y.keys[:t - 1]

        # z takes last t children of y if not leaf
        if not y.is_leaf:
            z.children = y.children[t:]
            y.children = y.children[:t]

        # insert z as child of this node
        self.children.insert(i + 1, z)
        # insert promoted key into this node
        self.keys.insert(i, promoted_key)


class BTree:
    def __init__(self, t):
        self.root = None
        self.t = t

    def traverse(self):
        if self.root is not None:
            self.root.traverse()
        print()

    def search(self, k):
        return None if self.root is None else self.root.search(k)

    def insert(self, k):
        # if tree is empty
        if self.root is None:
            self.root = BTreeNode(self.t, is_leaf=True)
            self.root.keys.append(k)
        else:
            # if root is full, tree height increases
            if len(self.root.keys) == (2 * self.t - 1):
                s = BTreeNode(self.t, is_leaf=False)
                s.children.append(self.root)
                s.split_child(0, self.root)

                i = 0
                if s.keys[0] < k:
                    i += 1
                s.children[i].insert_non_full(k)
                self.root = s
            else:
                self.root.insert_non_full(k)


if __name__ == "__main__":
    start_time = time.perf_counter()

    # minimum degree t = 3 (max keys per node = 5)
    btree = BTree(3)
    values = [10, 20, 5, 6, 12, 30, 7, 17]

    print("Inserting values:", values)
    for v in values:
        btree.insert(v)

    print("B-Tree In-order Traversal (Sorted): ", end="")
    btree.traverse()

    # searching
    print("Searching for 12:", "Found!" if btree.search(12) else "Not Found")
    print("Searching for 99:", "Found!" if btree.search(99) else "Not Found")

    end_time = time.perf_counter()
    elapsed_ms = (end_time - start_time) * 1000

    print("\n--- Complexity & Execution Time ---")
    print(f"Code Execution Time: {elapsed_ms:.4f} ms")
    print("Time Complexity: O(log n) for Search and Insert")
    print("Space Complexity: O(n) to store keys and pointers")
