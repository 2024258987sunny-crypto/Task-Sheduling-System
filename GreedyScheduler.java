
import java.util.*;

public class GreedyScheduler {

    public List<Project> schedule(List<Project> projects) {

        projects.sort((a, b) -> b.getRevenue() - a.getRevenue());

        Project[] week = new Project[5];

        for (Project p : projects) {

            int lastDay = Math.min(p.getDeadline(), 5) - 1;

            for (int d = lastDay; d >= 0; d--) {
                if (week[d] == null) {
                    week[d] = p;
                    break;
                }
            }
        }

        return Arrays.asList(week);
    }
}