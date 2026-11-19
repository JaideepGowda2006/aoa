# Exp 9: Job Sequencing with Deadlines using Greedy Algorithm

import time

class Job:
    def __init__(self, job_id, deadline, profit):
        self.id = job_id
        self.deadline = deadline
        self.profit = profit

def job_sequencing(jobs):
    # sort jobs by profit descending
    jobs.sort(key=lambda x: x.profit, reverse=True)

    max_deadline = max(j.deadline for j in jobs)
    slots = [None] * (max_deadline + 1)
    total_profit = 0

    # find latest free slot <= deadline
    for j in jobs:
        for t in range(min(max_deadline, j.deadline), 0, -1):
            if slots[t] is None:
                slots[t] = j.id
                total_profit += j.profit
                break

    scheduled = [slots[t] for t in range(1, max_deadline + 1) if slots[t] is not None]
    print("Scheduled Jobs:", " ".join(scheduled))
    print(f"Total Profit: {total_profit}")

if __name__ == "__main__":
    jobs = [
        Job('A', 2, 100),
        Job('B', 1, 19),
        Job('C', 2, 27),
        Job('D', 1, 25),
        Job('E', 3, 15)
    ]

    start_time = time.perf_counter()

    job_sequencing(jobs)

    end_time = time.perf_counter()
    print(f"\nExecution Time: {(end_time - start_time) * 1e9:.0f} ns")
    print("Time Complexity: O(n^2)")
    print("Space Complexity: O(d)")
