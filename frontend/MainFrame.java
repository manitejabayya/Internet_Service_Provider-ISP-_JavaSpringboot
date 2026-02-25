import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainFrame {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            JFrame frame = new JFrame("ISP Management System");
            frame.setSize(750, 550);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLocationRelativeTo(null);
            frame.setMinimumSize(new Dimension(600, 500));

            // ===== Gradient Background Panel =====
            JPanel mainPanel = new JPanel(new BorderLayout()) {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    Graphics2D g2 = (Graphics2D) g;

                    GradientPaint gp = new GradientPaint(
                            0, 0, new Color(15, 23, 42),
                            0, getHeight(), new Color(30, 41, 59)
                    );
                    g2.setPaint(gp);
                    g2.fillRect(0, 0, getWidth(), getHeight());
                }
            };

            // ===== TITLE =====
            JLabel title = new JLabel("ISP Management System", SwingConstants.CENTER);
            title.setFont(new Font("Segoe UI", Font.BOLD, 32));
            title.setForeground(Color.WHITE);
            title.setBorder(BorderFactory.createEmptyBorder(40, 10, 30, 10));

            // ===== BUTTON PANEL =====
            JPanel buttonPanel = new JPanel(new GridLayout(5, 1, 25, 25));
            buttonPanel.setBorder(BorderFactory.createEmptyBorder(40, 170, 40, 170));
            buttonPanel.setOpaque(false);

            // ===== BUTTONS =====
            JButton dashboard = createStyledButton(
                    "Dashboard",
                    new Color(14, 165, 233),
                    new Color(56, 189, 248));

            JButton addCustomer = createStyledButton(
                    "Add Customer",
                    new Color(37, 99, 235),
                    new Color(59, 130, 246));

            JButton viewCustomers = createStyledButton(
                    "View Customers",
                    new Color(16, 185, 129),
                    new Color(34, 197, 94));

            JButton plans = createStyledButton(
                    "Plans",
                    new Color(168, 85, 247),
                    new Color(139, 92, 246));

            JButton exit = createStyledButton(
                    "Exit",
                    new Color(239, 68, 68),
                    new Color(220, 38, 38));

            // Add buttons to panel
            buttonPanel.add(dashboard);
            buttonPanel.add(addCustomer);
            buttonPanel.add(viewCustomers);
            buttonPanel.add(plans);
            buttonPanel.add(exit);

            mainPanel.add(title, BorderLayout.NORTH);
            mainPanel.add(buttonPanel, BorderLayout.CENTER);

            frame.add(mainPanel);

            // ===== OPEN WINDOWS =====
            dashboard.addActionListener(e -> new Dashboard());
            addCustomer.addActionListener(e -> new CustomerForm());
            viewCustomers.addActionListener(e -> new ViewCustomers());
            plans.addActionListener(e -> new Plans());
            exit.addActionListener(e -> System.exit(0));

            frame.setVisible(true);
        });
    }

    // ===== Styled Rounded Button =====
    private static JButton createStyledButton(String text, Color normal, Color hover) {

        JButton button = new JButton(text) {

            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON);

                g2.setColor(getBackground());
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 35, 35);

                super.paintComponent(g);
                g2.dispose();
            }

            @Override
            protected void paintBorder(Graphics g) {}
        };

        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setFont(new Font("Segoe UI", Font.BOLD, 18));
        button.setForeground(Color.WHITE);
        button.setBackground(normal);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Smooth Hover Effect
        button.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                button.setBackground(hover);
                button.setPreferredSize(new Dimension(
                        button.getWidth() + 5,
                        button.getHeight() + 5
                ));
                button.revalidate();
            }

            public void mouseExited(MouseEvent e) {
                button.setBackground(normal);
                button.setPreferredSize(null);
                button.revalidate();
            }
        });

        return button;
    }
}