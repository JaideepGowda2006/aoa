// Experiment 9: Implementation of Job Sequencing with Deadlines using Greedy Algorithm
// Goal: Schedule jobs within their deadlines to maximize total profit

import java.util.Arrays;
import java.util.Comparator;

public class Exp9_JobSequencing {

    // Job class
    static class Job {
        char id;
        int deadline;
        int profit;

        public Job(char id, int deadline, int profit) {
            this.id = id;
            this.deadline = deadline;
            this.profit = profit;
        }
    }

    public static void printJobSequence(Job[] jobs) {
        // Step 1: Sort all jobs in decreasing order of profit
        Arrays.sort(jobs, new Comparator<Job>() {
            @Override
            public int compare(Job a, Job b) {
                return b.profit - a.profit;
            }
        });

        // Step 2: Find maximum deadline among all jobs
        int maxDeadline = 0;
        for (Job job : jobs) {
            if (job.deadline > maxDeadline) {
                maxDeadline = job.deadline;
            }
        }

        // result array to keep track of booked slots
        char[] slot = new char[maxDeadline + 1];
        boolean[] filled = new boolean[maxDeadline + 1];

        int totalProfit = 0;
        int jobCount = 0;

        // Step 3: Iterate through sorted jobs and assign latest available slot
        for (Job job : jobs) {
            // search for a free slot from min(maxDeadline, job.deadline) down to 1
            for (int j = Math.min(maxDeadline, job.deadline); j > 0; j--) {
                if (!filled[j]) {
                    filled[j] = true;
                    slot[j] = job.id;
                    totalProfit += job.profit;
                    jobCount++;
                    break;
                }
            }
        }

        // print the scheduled jobs
        System.out.print("Scheduled Jobs order: ");
        for (int i = 1; i <= maxDeadline; i++) {
            if (filled[i]) {
                System.out.print(slot[i] + " ");
            }
        }
        System.out.println("\nTotal Jobs Done: " + jobCount);
        System.out.println("Total Max Profit: " + totalProfit);
    }

    public static void main(String[] args) {
        Job[] jobs = {
            new Job('A', 2, 100),
            new Job('B', 1, 19),
            new Job('C', 2, 27),
            new Job('D', 1, 25),
            new Job('E', 3, 15)
        };

        System.out.println("--- Job Sequencing with Deadlines ---");
        System.out.println("Given Jobs:");
        for (Job j : jobs) {
            System.out.println("Job " + j.id + " -> Deadline: " + j.deadline + ", Profit: " + j.profit);
        }
        System.out.println();

        long startTime = System.nanoTime();

        printJobSequence(jobs);

        long endTime = System.nanoTime();
        long timeTaken = endTime - startTime;

        System.out.println("\n--- Complexity & Execution Time ---");
        System.out.println("Execution Time: " + timeTaken + " ns (" + (timeTaken / 1_000_000.0) + " ms)");
        System.out.println("Time Complexity: O(n^2)  [or O(n log n + n * d) where d is max deadline]");
        System.out.println("Space Complexity: O(d)   [to store time slots]");
    }
}
