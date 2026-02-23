
import java.sql.*;
import java.util.*;

public class ProjectRepository {

    private Connection getConnection() throws Exception {
        return DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/promanage",
                "postgres",
                "12345"
        );
    }

    public void saveProject(Project project) throws Exception {
        Connection conn = getConnection();

        PreparedStatement ps = conn.prepareStatement(
                "INSERT INTO projects_current (title, deadline, revenue, status) VALUES (?, ?, ?, ?)"
        );

        ps.setString(1, project.getTitle());
        ps.setInt(2, project.getDeadline());
        ps.setInt(3, project.getRevenue());
        ps.setString(4, project.getStatus());

        ps.executeUpdate();
        conn.close();
    }

    public List<Project> getAllCurrentProjects() throws Exception {

        List<Project> list = new ArrayList<>();
        Connection conn = getConnection();
        Statement stmt = conn.createStatement();

        ResultSet rs = stmt.executeQuery(
                "SELECT * FROM projects_current WHERE status='PENDING'"
        );

        while (rs.next()) {
            list.add(new Project(
                    rs.getInt("id"),
                    rs.getString("title"),
                    rs.getInt("deadline"),
                    rs.getInt("revenue"),
                    rs.getString("status")
            ));
        }

        conn.close();
        return list;
    }

    public List<Integer> getHistoricalRevenues() throws Exception {

        List<Integer> revenues = new ArrayList<>();
        Connection conn = getConnection();
        Statement stmt = conn.createStatement();

        ResultSet rs = stmt.executeQuery("SELECT revenue FROM projects_history");

        while (rs.next()) {
            revenues.add(rs.getInt("revenue"));
        }

        conn.close();
        return revenues;
    }
}