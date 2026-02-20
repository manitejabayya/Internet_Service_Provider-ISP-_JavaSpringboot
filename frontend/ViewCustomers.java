import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ViewCustomers extends JFrame {

    public ViewCustomers() {

        setTitle("View Customers");
        setSize(500, 300);
        setLocationRelativeTo(null);

        // Column names
        String[] columns = {"ID", "Name", "Phone", "Plan"};

        // Dummy data (temporary)
        Object[][] data = {
                {"1", "Ravi", "9876543210", "Basic"},
                {"2", "Priya", "9123456780", "Premium"},
                {"3", "Amit", "9988776655", "Standard"}
        };

        // Table model
        DefaultTableModel model =
                new DefaultTableModel(data, columns);

        // JTable
        JTable table = new JTable(model);

        // Scroll pane for table
        JScrollPane scrollPane = new JScrollPane(table);

        add(scrollPane, BorderLayout.CENTER);

        setVisible(true);
    }
}