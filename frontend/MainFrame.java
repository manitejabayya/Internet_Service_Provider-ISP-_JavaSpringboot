import javax.swing.*; // Swing components (JFrame, JButton, etc.)
import java.awt.*; // Layouts, fonts, colors

public class MainFrame {

    public static void main(String[] args) {

        // Create main window (JFrame)
        JFrame frame = new JFrame("ISP Management System");

        // Set window size (width, height)
        frame.setSize(500, 400);

        // Close program when user clicks X
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Center window on screen
        frame.setLocationRelativeTo(null);

        // ===== TITLE LABEL =====
        // Create title text in center
        JLabel title = new JLabel("ISP Management System", SwingConstants.CENTER);

        // Set font: Arial, Bold, size 20
        title.setFont(new Font("Arial", Font.BOLD, 20));

        // Add spacing around title (top, left, bottom, right)
        title.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));

        // ===== BUTTON PANEL =====
        // Panel holds buttons in grid layout
        JPanel panel = new JPanel();

        // 4 rows, 1 column, spacing between buttons
        panel.setLayout(new GridLayout(4, 1, 10, 10));

        // Padding around panel
        panel.setBorder(BorderFactory.createEmptyBorder(20, 80, 20, 80));

        // ===== BUTTONS =====
        JButton addCustomer = new JButton("Add Customer");
        JButton viewCustomers = new JButton("View Customers");
        JButton plans = new JButton("Plans");
        JButton exit = new JButton("Exit");

        // Add buttons to panel
        panel.add(addCustomer);
        panel.add(viewCustomers);
        panel.add(plans);
        panel.add(exit);

        // ===== FRAME LAYOUT =====
        // BorderLayout divides window into regions
        frame.setLayout(new BorderLayout());

        // Title at top
        frame.add(title, BorderLayout.NORTH);

        // Buttons in center
        frame.add(panel, BorderLayout.CENTER);

        // ===== BUTTON ACTION =====

        // Add Customer button → open customer form
        addCustomer.addActionListener(e -> new CustomerForm());

        //Add view customer button -> open view
        viewCustomers.addActionListener(e -> new ViewCustomers());

        // When Exit button clicked → close program
        exit.addActionListener(e -> System.exit(0));

        // Make window visible
        frame.setVisible(true);
    }
}