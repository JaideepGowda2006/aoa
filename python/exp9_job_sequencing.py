# Experiment 9: Implementation of Job Sequencing with Deadlines using Greedy Algorithm
# Maximize profit by assigning jobs to time slots before their deadlines

import time

class Job:
    def __init__(self, job_id, deadline, profit):
        self.id = job_id
        self.deadline = deadline
        self.profit = profit

def job_sequencing(jobs):
    # Step 1: Sort jobs descending by profit
    jobs.sort(key=lambda x: x.profit, reverse=True)

    # Step 2: Find max deadline to size our time slots
    max_deadline = max(job.deadline for job in jobs)

    # time slot array (1-indexed for clarity)
    slot = [None] * (max_deadline + 1)
    total_profit = 0
    jobs_done = 0

    # Step 3: For each job, find latest free slot before its deadline
    for job in jobs:
        for t in range(min(max_deadline, job.deadline), 0, -1):
            if slot[t] is None:
                slot[t] = job.id
                total_profit += job.profit
                jobs_done += 1
                break

    # print the scheduled jobs
    scheduled = [slot[t] for t in range(1, max_deadline + 1) if slot[t] is not None]
    print("Scheduled Jobs order:", " ".join(scheduled))
    print(f"Total Jobs Done: {jobs_done}")
    print(f"Total Max Profit: {total_profit}")

if __name__ == "__main__":
    jobs = [
        Job('A', 2, 100),
        Job('B', 1, 19),
        Job('C', 2, 27),
        Job('D', 1, 25),
        Job('E', 3, 15)
    ]

    print("--- Job Sequencing with Deadlines ---")
    print("Given Jobs:")
    for j in jobs:
        print(f"Job {j.id} -> Deadline: {j.deadline}, Profit: {j.profit}")
    print()

    start_time = time.perf_counter()

    job_sequencing(jobs)

    end_time = time.perf_counter()
    elapsed_ms = (end_time - start_time) * 1000

    print("\n--- Complexity & Execution Time ---")
    print(f"Code Execution Time: {elapsed_ms:.4f} ms")
    print("Time Complexity: O(n^2)  [or O(n log n + n * d) where d is max deadline]")
    print("Space Complexity: O(d)   [to store scheduled slots]")
