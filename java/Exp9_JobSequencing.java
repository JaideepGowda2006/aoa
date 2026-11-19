// Exp 9: Job Sequencing with Deadlines using Greedy Algorithm

import java.util.Arrays;

public class Exp9_JobSequencing {

    static class Job {
        char id;
        int deadline, profit;

        Job(char id, int deadline, int profit) {
            this.id = id;
            this.deadline = deadline;
            this.profit = profit;
        }
    }

    static void jobSequencing(Job[] jobs) {
        // sort jobs by profit descending
        Arrays.sort(jobs, (a, b) -> b.profit - a.profit);

        int maxDeadline = 0;
        for (Job j : jobs) {
            if (j.deadline > maxDeadline) maxDeadline = j.deadline;
        }

        char[] slots = new char[maxDeadline + 1];
        boolean[] filled = new boolean[maxDeadline + 1];
        int totalProfit = 0;

        // find free slot from deadline down to 1
        for (Job j : jobs) {
            for (int t = Math.min(maxDeadline, j.deadline); t > 0; t--) {
                if (!filled[t]) {
                    filled[t] = true;
                    slots[t] = j.id;
                    totalProfit += j.profit;
                    break;
                }
            }
        }

        System.out.print("Scheduled Jobs: ");
        for (int t = 1; t <= maxDeadline; t++) {
            if (filled[t]) System.out.print(slots[t] + " ");
        }
        System.out.println("\nTotal Profit: " + totalProfit);
    }

    public static void main(String[] args) {
        Job[] jobs = {
            new Job('A', 2, 100),
            new Job('B', 1, 19),
            new Job('C', 2, 27),
            new Job('D', 1, 25),
            new Job('E', 3, 15)
        };

        long startTime = System.nanoTime();

        jobSequencing(jobs);

        long endTime = System.nanoTime();
        System.out.println("\nExecution Time: " + (endTime - startTime) + " ns");
        System.out.println("Time Complexity: O(n^2)");
        System.out.println("Space Complexity: O(d)");
    }
}
