
import java.util.*;

public class MonteCarloSimulator {

    public double simulate(int projectRevenue,
                           boolean scheduleNow,
                           List<Integer> historicalRevenues) {

        Random rand = new Random();
        int simulations = 500;
        double totalProfit = 0;

        for (int i = 0; i < simulations; i++) {

            double profit = 0;

            if (scheduleNow)
                profit += projectRevenue;

            for (int j = 0; j < 5; j++) {
                int randomRevenue = historicalRevenues.get(
                        rand.nextInt(historicalRevenues.size())
                );
                profit += randomRevenue;
            }

            totalProfit += profit;
        }

        return totalProfit / simulations;
    }
}