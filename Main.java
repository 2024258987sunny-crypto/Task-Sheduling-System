import java.util.*;

public class Main {

    private static Scanner sc = new Scanner(System.in);
    private static ProjectRepository repo = new ProjectRepository();
    private static WeeklyPlannerService planner = new WeeklyPlannerService();

    public static void main(String[] args) throws Exception {

        while (true) {
            System.out.println("\n=== ProManage Menu ===");
            System.out.println("1. Add New Project");
            System.out.println("2. View All Current Projects");
            System.out.println("3. Generate Weekly Schedule");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");

            int choice = sc.nextInt();
            sc.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    addNewProject();
                    break;
                case 2:
                    viewAllProjects();
                    break;
                case 3:
                    generateWeeklySchedule();
                    break;
                case 4:
                    System.out.println("Exiting... Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static void addNewProject() throws Exception {
        System.out.println("\n=== Add New Project ===");

        System.out.print("Title: ");
        String title = sc.nextLine();

        System.out.print("Deadline (days): ");
        int deadline = sc.nextInt();

        System.out.print("Revenue: ");
        int revenue = sc.nextInt();
        sc.nextLine();  // consume newline

        Project newProject = new Project(0, title, deadline, revenue, "PENDING");

        repo.saveProject(newProject);
        System.out.println("Project added successfully.");
    }

    private static void viewAllProjects() throws Exception {
        System.out.println("\n=== Current Projects ===");
        List<Project> projects = repo.getAllCurrentProjects();

        if (projects.isEmpty()) {
            System.out.println("No current projects found.");
            return;
        }

        System.out.printf("%-5s %-20s %-10s %-10s %-10s\n",
                "ID", "Title", "Deadline", "Revenue", "Status");
        System.out.println("--------------------------------------------------------");

        for (Project p : projects) {
            System.out.printf("%-5d %-20s %-10d %-10d %-10s\n",
                    p.getId(), p.getTitle(), p.getDeadline(), p.getRevenue(), p.getStatus());
        }
    }

    private static void generateWeeklySchedule() throws Exception {
        List<Project> current = repo.getAllCurrentProjects();
        List<Integer> history = repo.getHistoricalRevenues();

        if (current.isEmpty()) {
            System.out.println("\nNo current projects to schedule.");
            return;
        }
        if (history.isEmpty()) {
            System.out.println("\nWarning: No historical data found. Monte Carlo simulation may be inaccurate.");
        }

        List<Project> weeklyPlan = planner.generatePlan(current, history);

        System.out.println("\n=== Weekly Schedule ===");

        String[] days = {"Mon", "Tue", "Wed", "Thu", "Fri"};

        for (int i = 0; i < weeklyPlan.size(); i++) {
            if (weeklyPlan.get(i) != null)
                System.out.println(days[i] + " → " + weeklyPlan.get(i).getTitle());
            else
                System.out.println(days[i] + " → Free");
        }
    }
}