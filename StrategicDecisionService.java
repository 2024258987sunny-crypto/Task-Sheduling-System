
import java.util.*;

public class StrategicDecisionService {

    private MonteCarloSimulator simulator = new MonteCarloSimulator();

    public boolean shouldScheduleNow(int revenue,
                                     List<Integer> history) {

        double scheduleNow =
                simulator.simulate(revenue, true, history);

        double postpone =
                simulator.simulate(revenue, false, history);

        return scheduleNow >= postpone;
    }
}