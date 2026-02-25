import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.Timer;

public class Dashboard extends JFrame {

    private float opacity = 0f;

    public Dashboard() {

        setTitle("ISP Admin Dashboard");
        setSize(1100, 650);
        setLocationRelativeTo(null);
        setUndecorated(true);
        setShape(new java.awt.geom.RoundRectangle2D.Double(0, 0, 1100, 650, 30, 30));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Only close this window
        setOpacity(0f);

        // ===== Fade In Animation =====
        Timer fadeIn = new Timer(20, e -> {
            opacity += 0.05f;
            if (opacity >= 1f) {
                opacity = 1f;
                ((Timer) e.getSource()).stop();
            }
            setOpacity(opacity);
        });
        fadeIn.start();

        GradientPanel mainPanel = new GradientPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // ===== HEADER PANEL (Title + Close Button) =====
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setOpaque(false);

        JLabel heading = new JLabel("ISP ADMIN DASHBOARD");
        heading.setFont(new Font("Segoe UI", Font.BOLD, 30));
        heading.setForeground(Color.WHITE);

        JButton closeBtn = createCloseButton();

        headerPanel.add(heading, BorderLayout.WEST);
        headerPanel.add(closeBtn, BorderLayout.EAST);

        mainPanel.add(headerPanel, BorderLayout.NORTH);

        // ===== KPI CARDS =====
        JPanel cardsPanel = new JPanel(new GridLayout(1, 4, 20, 20));
        cardsPanel.setOpaque(false);

        cardsPanel.add(createCard("Total Customers", "120"));
        cardsPanel.add(createCard("Active Plans", "95"));
        cardsPanel.add(createCard("Monthly Revenue", "₹75,000"));
        cardsPanel.add(createCard("Expiring Soon", "6"));

        mainPanel.add(cardsPanel, BorderLayout.CENTER);

        // ===== BOTTOM BUTTONS =====
        JPanel bottomPanel = new JPanel();
        bottomPanel.setOpaque(false);

        JButton addCustomer = createButton("Add Customer");
        JButton viewCustomers = createButton("View Customers");
        JButton viewPlans = createButton("Plans");

        addCustomer.addActionListener(e -> new CustomerForm());
        viewCustomers.addActionListener(e -> new ViewCustomers());
        viewPlans.addActionListener(e -> new Plans());

        bottomPanel.add(addCustomer);
        bottomPanel.add(viewCustomers);
        bottomPanel.add(viewPlans);

        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        add(mainPanel);
        setVisible(true);
    }

    // ===== Custom Close Button =====
    private JButton createCloseButton() {

        JButton close = new JButton("✕");
        close.setFocusPainted(false);
        close.setBorderPainted(false);
        close.setContentAreaFilled(false);
        close.setFont(new Font("Segoe UI", Font.BOLD, 22));
        close.setForeground(Color.WHITE);
        close.setCursor(new Cursor(Cursor.HAND_CURSOR));

        close.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                close.setForeground(new Color(239, 68, 68)); // Red on hover
            }
            public void mouseExited(MouseEvent e) {
                close.setForeground(Color.WHITE);
            }
        });

        close.addActionListener(e -> dispose()); // Close only dashboard

        return close;
    }

    // ===== Dashboard Cards =====
    private JPanel createCard(String title, String value) {

        JPanel card = new JPanel();
        card.setLayout(new BorderLayout());
        card.setBackground(new Color(40, 40, 70));
        card.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        titleLabel.setForeground(Color.WHITE);

        JLabel valueLabel = new JLabel(value);
        valueLabel.setFont(new Font("Segoe UI", Font.BOLD, 28));
        valueLabel.setForeground(new Color(0, 200, 255));

        card.add(titleLabel, BorderLayout.NORTH);
        card.add(valueLabel, BorderLayout.CENTER);

        card.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                card.setBackground(new Color(60, 90, 160));
            }
            public void mouseExited(MouseEvent e) {
                card.setBackground(new Color(40, 40, 70));
            }
        });

        return card;
    }

    // ===== Bottom Buttons =====
    private JButton createButton(String text) {

        JButton btn = new JButton(text);
        btn.setFocusPainted(false);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btn.setBackground(new Color(0, 120, 215));
        btn.setForeground(Color.WHITE);
        btn.setPreferredSize(new Dimension(150, 40));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        btn.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                btn.setBackground(new Color(0, 160, 255));
            }
            public void mouseExited(MouseEvent e) {
                btn.setBackground(new Color(0, 120, 215));
            }
        });

        return btn;
    }

    // ===== Gradient Background =====
    class GradientPanel extends JPanel {
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
            GradientPaint gp = new GradientPaint(
                    0, 0, new Color(15, 32, 39),
                    getWidth(), getHeight(), new Color(32, 58, 67));
            g2.setPaint(gp);
            g2.fillRect(0, 0, getWidth(), getHeight());
        }
    }
}