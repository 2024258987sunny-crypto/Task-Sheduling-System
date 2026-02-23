

public class Project {

    private int id;
    private String title;
    private int deadline;
    private int revenue;
    private String status;

    public Project(int id, String title, int deadline, int revenue, String status) {
        this.id = id;
        this.title = title;
        this.deadline = deadline;
        this.revenue = revenue;
        this.status = status;
    }

    public int getId() { return id; }
    public String getTitle() { return title; }
    public int getDeadline() { return deadline; }
    public int getRevenue() { return revenue; }
    public String getStatus() { return status; }
}