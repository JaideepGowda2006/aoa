# Analysis of Algorithms (AOA) - Lab Experiments

Simple, student-friendly implementations of all 11 Analysis of Algorithms (AOA) lab experiments in **Java** and **Python**.

Each program includes:
- Short, simple comments that are easy to write in exams.
- Natural execution time measurement (`ns`).
- Direct Time and Space Complexity printouts.
- Minimal, clean logic that is easy to memorize and explain in viva.

---

## 📂 Project Structure

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

## 📋 Experiments & Complexity Table

| Exp | Name | Method | Time Complexity | Space Complexity |
|---|---|---|---|---|
| **1** | Recursion Tree Method | Tree level-by-level | $O(n \log n)$ | $O(\log n)$ |
| **2** | Master Method | Master Theorem formula | $O(1)$ | $O(1)$ |
| **3** | B-Tree | Balanced search tree | $O(\log n)$ | $O(n)$ |
| **4** | B+ Tree | Keys in leaves, linked leaves | $O(\log n)$ | $O(n)$ |
| **5** | Binary Search | Divide and Conquer | $O(\log n)$ | $O(1)$ |
| **6** | Merge Sort | Divide and Conquer | $O(n \log n)$ | $O(n)$ |
| **7** | Quick Sort | Lomuto Partitioning | $O(n \log n)$ [Worst: $O(n^2)$] | $O(\log n)$ |
| **8** | Fractional Knapsack | Greedy (by ratio) | $O(n \log n)$ | $O(1)$ |
| **9** | Job Sequencing | Greedy (by profit) | $O(n^2)$ | $O(d)$ |
| **10** | 0/1 Knapsack | Dynamic Programming | $O(n \cdot W)$ | $O(n \cdot W)$ |
| **11** | Longest Common Subsequence | Dynamic Programming | $O(m \cdot n)$ | $O(m \cdot n)$ |

---

## 🚀 How to Run

### Java
```bash
cd java
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
```bash
cd python
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

## 🎯 Viva Cheat-Sheet

1. **Recursion Tree vs Master Method**:
   - Recursion Tree calculates total cost pictorially by adding work level-by-level across tree height $\log_b n$.
   - Master Method is a direct formula for recurrences $T(n) = aT(n/b) + \Theta(n^k)$.

2. **Master Theorem 3 Cases**:
   - $\log_b a > k \implies T(n) = O(n^{\log_b a})$ (leaves dominate)
   - $\log_b a = k \implies T(n) = O(n^k \log n)$ (work split equally)
   - $\log_b a < k \implies T(n) = O(n^k)$ (root dominates)

3. **Difference between B-Tree and B+ Tree**:
   - In **B-Tree**, keys and values are stored in both internal and leaf nodes.
   - In **B+ Tree**, actual records are **only in leaves**; internal nodes only store routing keys. All leaves are linked together sequentially like a linked list, making range queries much faster.

4. **Why Binary Search needs a sorted array**:
   - At each step, binary search eliminates half of the remaining array by comparing the target with the middle element. This only works if elements are ordered.

5. **Quick Sort worst case**:
   - $O(n^2)$ when pivot is always smallest or largest (e.g., already sorted array with first or last element as pivot).

6. **Fractional vs 0/1 Knapsack**:
   - **Fractional Knapsack** allows breaking items $\implies$ solved using **Greedy** (sort by value/weight ratio).
   - **0/1 Knapsack** does not allow breaking items $\implies$ Greedy fails, must use **Dynamic Programming** ($O(n \cdot W)$).

7. **Principle of Job Sequencing with Deadlines**:
   - Greedily pick the highest-profit job first and place it in the latest available slot before its deadline.

8. **LCS Backtracking**:
   - If characters match: $1 + dp[i-1][j-1]$, add character to LCS.
   - If no match: take $\max(dp[i-1][j], dp[i][j-1])$.
