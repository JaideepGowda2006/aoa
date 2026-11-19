# Experiment 4: Implementation of B+ Tree
# In B+ Tree:
# 1. All actual keys/records are stored in leaf nodes.
# 2. Leaves are linked sequentially as a linked list (next pointer).
# 3. Internal nodes act purely as a search index/router.

import time

ORDER = 3  # maximum keys per node before splitting

class BPlusNode:
    def __init__(self, is_leaf=True):
        self.is_leaf = is_leaf
        self.keys = []
        self.children = [] if not is_leaf else None
        self.next = None  # pointer to next leaf node

class BPlusTree:
    def __init__(self):
        self.root = BPlusNode(is_leaf=True)

    def search(self, key):
        curr = self.root
        # traverse down to the leaf node
        while not curr.is_leaf:
            i = 0
            while i < len(curr.keys) and key >= curr.keys[i]:
                i += 1
            curr = curr.children[i]
        
        # search inside leaf
        return key in curr.keys

    def insert(self, key):
        leaf = self._find_leaf(self.root, key)
        # insert key into leaf in sorted order
        leaf.keys.append(key)
        leaf.keys.sort()

        # split if leaf overflows
        if len(leaf.keys) > ORDER:
            self._split_leaf(leaf)

    def _find_leaf(self, curr, key):
        if curr.is_leaf:
            return curr
        i = 0
        while i < len(curr.keys) and key >= curr.keys[i]:
            i += 1
        return self._find_leaf(curr.children[i], key)

    def _split_leaf(self, leaf):
        mid = len(leaf.keys) // 2

        new_leaf = BPlusNode(is_leaf=True)
        new_leaf.keys = leaf.keys[mid:]
        leaf.keys = leaf.keys[:mid]

        # link leaf nodes together
        new_leaf.next = leaf.next
        leaf.next = new_leaf

        promoted_key = new_leaf.keys[0]

        if leaf == self.root:
            new_root = BPlusNode(is_leaf=False)
            new_root.keys = [promoted_key]
            new_root.children = [leaf, new_leaf]
            self.root = new_root
        else:
            self._insert_into_parent(self.root, leaf, promoted_key, new_leaf)

    def _insert_into_parent(self, curr, left_child, key, right_child):
        if curr is None or curr.is_leaf:
            return

        if left_child in curr.children:
            idx = curr.children.index(left_child)
            curr.keys.insert(idx, key)
            curr.children.insert(idx + 1, right_child)

            if len(curr.keys) > ORDER:
                self._split_internal(curr)
            return

        for child in curr.children:
            self._insert_into_parent(child, left_child, key, right_child)

    def _split_internal(self, internal):
        mid = len(internal.keys) // 2
        promoted_key = internal.keys[mid]

        new_internal = BPlusNode(is_leaf=False)
        new_internal.keys = internal.keys[mid + 1:]
        new_internal.children = internal.children[mid + 1:]

        internal.keys = internal.keys[:mid]
        internal.children = internal.children[:mid + 1]

        if internal == self.root:
            new_root = BPlusNode(is_leaf=False)
            new_root.keys = [promoted_key]
            new_root.children = [internal, new_internal]
            self.root = new_root
        else:
            self._insert_into_parent(self.root, internal, promoted_key, new_internal)

    def print_leaves(self):
        curr = self.root
        while not curr.is_leaf:
            curr = curr.children[0]
        
        print("Leaf Linked List: ", end="")
        while curr:
            print(curr.keys, end=" -> ")
            curr = curr.next
        print("None")


if __name__ == "__main__":
    start_time = time.perf_counter()

    bpt = BPlusTree()
    data = [10, 20, 5, 6, 12, 30, 7, 17]

    print("Inserting keys into B+ Tree:", data)
    for x in data:
        bpt.insert(x)

    # display leaves linked list
    bpt.print_leaves()

    # search tests
    print("Search 12:", "Found!" if bpt.search(12) else "Not Found")
    print("Search 25:", "Found!" if bpt.search(25) else "Not Found")

    end_time = time.perf_counter()
    elapsed_ms = (end_time - start_time) * 1000

    print("\n--- Complexity & Execution Time ---")
    print(f"Code Execution Time: {elapsed_ms:.4f} ms")
    print("Time Complexity: O(log n) for Search and Insert")
    print("Time Complexity for Range Search: O(log n + k)  [k = number of elements]")
    print("Space Complexity: O(n)")
