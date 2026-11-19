# Exp 4: B+ Tree Implementation
# Keys in leaves, leaves linked together

import time

ORDER = 3

class Node:
    def __init__(self, leaf=True):
        self.leaf = leaf
        self.keys = []
        self.children = []
        self.next = None

class BPlusTree:
    def __init__(self):
        self.root = Node(True)

    def search(self, key):
        curr = self.root
        while not curr.leaf:
            i = 0
            while i < len(curr.keys) and key >= curr.keys[i]:
                i += 1
            curr = curr.children[i]
        return key in curr.keys

    def insert(self, key):
        leaf = self._find_leaf(self.root, key)
        leaf.keys.append(key)
        leaf.keys.sort()

        if len(leaf.keys) > ORDER:
            self._split_leaf(leaf)

    def _find_leaf(self, curr, key):
        if curr.leaf:
            return curr
        i = 0
        while i < len(curr.keys) and key >= curr.keys[i]:
            i += 1
        return self._find_leaf(curr.children[i], key)

    def _split_leaf(self, leaf):
        mid = len(leaf.keys) // 2
        new_leaf = Node(True)
        new_leaf.keys = leaf.keys[mid:]
        leaf.keys = leaf.keys[:mid]

        new_leaf.next = leaf.next
        leaf.next = new_leaf
        promo_key = new_leaf.keys[0]

        if leaf == self.root:
            new_root = Node(False)
            new_root.keys = [promo_key]
            new_root.children = [leaf, new_leaf]
            self.root = new_root
        else:
            self._insert_parent(self.root, leaf, promo_key, new_leaf)

    def _insert_parent(self, curr, left, key, right):
        if not curr or curr.leaf:
            return
        if left in curr.children:
            idx = curr.children.index(left)
            curr.keys.insert(idx, key)
            curr.children.insert(idx + 1, right)
            return
        for c in curr.children:
            self._insert_parent(c, left, key, right)

    def print_leaves(self):
        curr = self.root
        while not curr.leaf:
            curr = curr.children[0]
        print("Leaves:", end=" ")
        while curr:
            print(curr.keys, end=" -> ")
            curr = curr.next
        print("null")

if __name__ == "__main__":
    start_time = time.perf_counter()

    bpt = BPlusTree()
    arr = [10, 20, 5, 6, 12, 30, 7, 17]

    print("Inserted:", " ".join(map(str, arr)))
    for x in arr:
        bpt.insert(x)

    bpt.print_leaves()
    print("Search 12:", "Found" if bpt.search(12) else "Not Found")
    print("Search 25:", "Found" if bpt.search(25) else "Not Found")

    end_time = time.perf_counter()
    print(f"\nExecution Time: {(end_time - start_time) * 1e9:.0f} ns")
    print("Time Complexity: O(log n)")
    print("Space Complexity: O(n)")
