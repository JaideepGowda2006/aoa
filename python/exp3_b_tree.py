# Exp 3: B-Tree Implementation
# Insertion, Search, and In-order Traversal

import time

class Node:
    def __init__(self, t, leaf=True):
        self.t = t
        self.leaf = leaf
        self.keys = []
        self.children = []

    def traverse(self):
        for i in range(len(self.keys)):
            if not self.leaf:
                self.children[i].traverse()
            print(self.keys[i], end=" ")
        if not self.leaf:
            self.children[-1].traverse()

    def search(self, k):
        i = 0
        while i < len(self.keys) and k > self.keys[i]:
            i += 1
        if i < len(self.keys) and self.keys[i] == k:
            return self
        if self.leaf:
            return None
        return self.children[i].search(k)

    def insert_non_full(self, k, t):
        i = len(self.keys) - 1
        if self.leaf:
            self.keys.append(None)
            while i >= 0 and self.keys[i] > k:
                self.keys[i + 1] = self.keys[i]
                i -= 1
            self.keys[i + 1] = k
        else:
            while i >= 0 and self.keys[i] > k:
                i -= 1
            i += 1
            if len(self.children[i].keys) == 2 * t - 1:
                self.split_child(i, self.children[i], t)
                if self.keys[i] < k:
                    i += 1
            self.children[i].insert_non_full(k, t)

    def split_child(self, i, y, t):
        z = Node(t, y.leaf)
        z.keys = y.keys[t:]
        promoted = y.keys[t - 1]
        y.keys = y.keys[:t - 1]

        if not y.leaf:
            z.children = y.children[t:]
            y.children = y.children[:t]

        self.children.insert(i + 1, z)
        self.keys.insert(i, promoted)

class BTree:
    def __init__(self, t):
        self.root = None
        self.t = t

    def insert(self, k):
        if not self.root:
            self.root = Node(self.t, True)
            self.root.keys.append(k)
        else:
            if len(self.root.keys) == 2 * self.t - 1:
                s = Node(self.t, False)
                s.children.append(self.root)
                s.split_child(0, self.root, self.t)
                i = 1 if s.keys[0] < k else 0
                s.children[i].insert_non_full(k, self.t)
                self.root = s
            else:
                self.root.insert_non_full(k, self.t)

    def traverse(self):
        if self.root:
            self.root.traverse()
        print()

    def search(self, k):
        return self.root.search(k) is not None if self.root else False

if __name__ == "__main__":
    start_time = time.perf_counter()

    tree = BTree(3)
    arr = [10, 20, 5, 6, 12, 30, 7, 17]

    print("Inserted:", " ".join(map(str, arr)))
    for x in arr:
        tree.insert(x)

    print("Traversal:", end=" ")
    tree.traverse()

    print("Search 12:", "Found" if tree.search(12) else "Not Found")
    print("Search 99:", "Found" if tree.search(99) else "Not Found")

    end_time = time.perf_counter()
    print(f"\nExecution Time: {(end_time - start_time) * 1e9:.0f} ns")
    print("Time Complexity: O(log n)")
    print("Space Complexity: O(n)")
