
import java.util.*;

public class WeeklyPlannerService {

    private StrategicDecisionService decisionService =
            new StrategicDecisionService();

    private GreedyScheduler scheduler =
            new GreedyScheduler();

    public List<Project> generatePlan(List<Project> currentProjects,
                                      List<Integer> history) {

        List<Project> approved = new ArrayList<>();

        for (Project p : currentProjects) {

            if (p.getDeadline() > 5) {

                boolean decision =
                        decisionService.shouldScheduleNow(
                                p.getRevenue(),
                                history);

                if (decision)
                    approved.add(p);

            } else {
                approved.add(p);
            }
        }

        return scheduler.schedule(approved);
    }
}