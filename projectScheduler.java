import java.util.*;

public class projectScheduler{

    public List<project> generateOptimalSchedule(List<project> projects) {

        int maxDays = 5;

        int n = projects.size();

        // Sort by deadline (not by revenue)
        projects.sort(Comparator.comparingInt(project::getDeadline));

        // DP table
        double[][] dp = new double[n + 1][maxDays + 1];

        // Build DP table
        for (int i = 1; i <= n; i++) {

            project current = projects.get(i - 1);

            for (int day = 0; day <= maxDays; day++) {

                // Option 1: Skip project
                dp[i][day] = dp[i - 1][day];

                // Option 2: Take project (if possible)
                if (day > 0 && day <= current.getDeadline()) {
                    dp[i][day] = Math.max(
                            dp[i][day],
                            dp[i - 1][day - 1] + current.getExpectedRevenue()
                    );
                }
            }
        }

        // Backtrack to find selected projects
        List<project> selected = new ArrayList<>();
        int day = maxDays;

        for (int i = n; i > 0 && day > 0; i--) {

            if (dp[i][day] != dp[i - 1][day]) {

                project chosen = projects.get(i - 1);
                selected.add(chosen);
                day--;
            }
        }

        Collections.reverse(selected);

        return selected;
    }
}
