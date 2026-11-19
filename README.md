# Analysis of Algorithms (AOA) - Lab Experiments

This repository contains clean, human-written implementations of all 11 Analysis of Algorithms (AOA) lab experiments in both **Java** and **Python**.

Each experiment includes:
- Clear, simple, student-friendly comments.
- Code execution time measurement (in milliseconds and nanoseconds).
- Explicit Time and Space Complexity printed during output.
- Simple, straightforward logic designed for viva explanations.

---

## 📂 Folder Structure

```
aoa/
├── java/
│   ├── Exp1_RecursionTree.java
│   ├── Exp2_MasterMethod.java
│   ├── Exp3_BTree.java
│   ├── Exp4_BPlusTree.java
│   ├── Exp5_BinarySearch.java
│   ├── Exp6_MergeSort.java
│   ├── Exp7_QuickSort.java
│   ├── Exp8_FractionalKnapsack.java
│   ├── Exp9_JobSequencing.java
│   ├── Exp10_Knapsack01.java
│   └── Exp11_LCS.java
├── python/
│   ├── exp1_recursion_tree.py
│   ├── exp2_master_method.py
│   ├── exp3_b_tree.py
│   ├── exp4_b_plus_tree.py
│   ├── exp5_binary_search.py
│   ├── exp6_merge_sort.py
│   ├── exp7_quick_sort.py
│   ├── exp8_fractional_knapsack.py
│   ├── exp9_job_sequencing.py
│   ├── exp10_knapsack_01.py
│   └── exp11_lcs.py
└── README.md
```

---

## 📋 List of Experiments & Complexity Summary

| Exp No. | Experiment Name | Algorithm Technique | Time Complexity | Space Complexity |
|---|---|---|---|---|
| **1** | Recurrence Relations: Recursion Tree Method | Tree Simulation / Divide & Conquer | $O(n \log n)$ | $O(\log n)$ (tree height) |
| **2** | Recurrence Relations: Master Method | Master Theorem Cases | $O(1)$ (formula check) | $O(1)$ |
| **3** | B-Tree Implementation | Balanced Multiway Search Tree | $O(\log n)$ search / insert | $O(n)$ |
| **4** | B+ Tree Implementation | Multiway Tree with Linked Leaves | $O(\log n)$ search / insert | $O(n)$ |
| **5** | Binary Search Algorithm | Divide and Conquer / Decrease & Conquer | Best: $O(1)$, Avg/Worst: $O(\log n)$ | $O(1)$ (iterative) |
| **6** | Merge Sort | Divide and Conquer | $O(n \log n)$ (all cases) | $O(n)$ (temp arrays) |
| **7** | Quick Sort | Divide and Conquer (Partitioning) | Best/Avg: $O(n \log n)$, Worst: $O(n^2)$ | $O(\log n)$ (stack depth) |
| **8** | Fractional Knapsack | Greedy Algorithm (Ratio Sorting) | $O(n \log n)$ | $O(1)$ or $O(n)$ |
| **9** | Job Sequencing with Deadlines | Greedy Algorithm | $O(n^2)$ | $O(d)$ ($d$ = max deadline) |
| **10** | 0/1 Knapsack Problem | Dynamic Programming (2D Table) | $O(n \cdot W)$ | $O(n \cdot W)$ |
| **11** | Longest Common Subsequence (LCS) | Dynamic Programming (2D Table) | $O(m \cdot n)$ | $O(m \cdot n)$ |

---

## 🚀 How to Run the Programs

### Java
Navigate to the `java` directory:
```bash
cd java
```
Compile and run any experiment:
```bash
javac Exp1_RecursionTree.java && java Exp1_RecursionTree
javac Exp2_MasterMethod.java && java Exp2_MasterMethod
javac Exp3_BTree.java && java Exp3_BTree
javac Exp4_BPlusTree.java && java Exp4_BPlusTree
javac Exp5_BinarySearch.java && java Exp5_BinarySearch
javac Exp6_MergeSort.java && java Exp6_MergeSort
javac Exp7_QuickSort.java && java Exp7_QuickSort
javac Exp8_FractionalKnapsack.java && java Exp8_FractionalKnapsack
javac Exp9_JobSequencing.java && java Exp9_JobSequencing
javac Exp10_Knapsack01.java && java Exp10_Knapsack01
javac Exp11_LCS.java && java Exp11_LCS
```

### Python
Navigate to the `python` directory:
```bash
cd python
```
Run any experiment:
```bash
python exp1_recursion_tree.py
python exp2_master_method.py
python exp3_b_tree.py
python exp4_b_plus_tree.py
python exp5_binary_search.py
python exp6_merge_sort.py
python exp7_quick_sort.py
python exp8_fractional_knapsack.py
python exp9_job_sequencing.py
python exp10_knapsack_01.py
python exp11_lcs.py
```

---

## 🎯 Viva Questions & Quick Answers

### Q1: What is the Recursion Tree Method?
> **Answer**: It is a pictorial method where each node represents the cost of a single subproblem in the recursive call. We calculate the cost at each level of the tree, determine the tree height ($\log_b n$), and sum the costs across all levels to find the overall time complexity.

### Q2: What are the three cases of the Master Theorem?
For $T(n) = a T(n/b) + \Theta(n^k)$:
> 1. **Case 1**: If $\log_b a > k \implies T(n) = \Theta(n^{\log_b a})$ (Leaf level dominates).
> 2. **Case 2**: If $\log_b a = k \implies T(n) = \Theta(n^k \log n)$ (Cost evenly split across levels).
> 3. **Case 3**: If $\log_b a < k \implies T(n) = \Theta(n^k)$ (Root level dominates).

### Q3: What is the key difference between B-Tree and B+ Tree?
> **Answer**:
> - In a **B-Tree**, keys and data records are stored in both internal nodes and leaf nodes.
> - In a **B+ Tree**, data records/keys are stored **only in leaf nodes**; internal nodes store only routing/indexing keys.
> - Furthermore, in a **B+ Tree**, all leaf nodes are connected via a linked list (`next` pointer), making **range queries** significantly faster ($O(k + \log n)$).

### Q4: Why is Binary Search faster than Linear Search?
> **Answer**: Binary search repeatedly halves the search space ($O(\log n)$) on a sorted collection, whereas Linear search inspects elements one by one ($O(n)$).

### Q5: What is the worst-case scenario for Quick Sort and how can it be avoided?
> **Answer**: The worst case is $O(n^2)$, which occurs when the chosen pivot is always the smallest or largest element (e.g., when the array is already sorted or reverse-sorted and the first or last element is chosen as pivot). It can be avoided by using **Randomized Quick Sort** or the **Median-of-Three** pivot selection rule.

### Q6: Why is Merge Sort preferred over Quick Sort for linked lists?
> **Answer**: Merge Sort does not require random access (indexing); it only needs sequential traversal, which suits linked lists naturally. Moreover, merging two linked lists does not require extra $O(n)$ auxiliary array space.

### Q7: Why does the Greedy method work for Fractional Knapsack but fail for 0/1 Knapsack?
> **Answer**:
> - In **Fractional Knapsack**, items can be broken down, so picking items with the highest value-to-weight ratio guaranteed an optimal solution without leaving unused capacity gaps.
> - In **0/1 Knapsack**, items cannot be broken. A high-ratio item might leave empty capacity that could have been filled by a combination of other items with higher total value. Hence, 0/1 Knapsack requires **Dynamic Programming**.

### Q8: What is the principle behind Job Sequencing with Deadlines?
> **Answer**: It greedily selects jobs with the highest profit first and assigns them to the latest possible free time slot before their respective deadlines.

### Q9: What are the two main properties required to solve a problem with Dynamic Programming?
> **Answer**:
> 1. **Overlapping Subproblems**: The same smaller subproblems are solved repeatedly.
> 2. **Optimal Substructure**: The optimal solution to the problem contains optimal solutions to its subproblems.

### Q10: How does Longest Common Subsequence (LCS) backtrack to find the string?
> **Answer**: Starting from `dp[m][n]`:
> - If `s1[i-1] == s2[j-1]`, that character is part of the LCS; we append it and move diagonally to `dp[i-1][j-1]`.
> - If they don't match, we move to the adjacent cell with the larger value (either `dp[i-1][j]` or `dp[i][j-1]`).
> - Reversing the collected characters yields the LCS string.
