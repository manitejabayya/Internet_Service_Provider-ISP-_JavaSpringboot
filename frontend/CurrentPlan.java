import javax.swing.*;
import java.awt.*;
import javax.swing.Timer;

public class CurrentPlan extends JFrame {

    private float opacity = 0f;

    public CurrentPlan() {

        setTitle("Current Plan");
        setSize(800, 500);
        setLocationRelativeTo(null);
        setUndecorated(true);
        setShape(new java.awt.geom.RoundRectangle2D.Double(0, 0, 800, 500, 30, 30));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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

        // ===== HEADER PANEL =====
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setOpaque(false);

        JLabel heading = new JLabel("CURRENT PLAN");
        heading.setFont(new Font("Segoe UI", Font.BOLD, 28));
        heading.setForeground(Color.WHITE);

        JButton closeBtn = createCloseButton();

        headerPanel.add(heading, BorderLayout.WEST);
        headerPanel.add(closeBtn, BorderLayout.EAST);

        mainPanel.add(headerPanel, BorderLayout.NORTH);

        // ===== PLAN DETAILS =====
        JPanel detailsPanel = new JPanel();
        detailsPanel.setLayout(new BoxLayout(detailsPanel, BoxLayout.Y_AXIS));
        detailsPanel.setOpaque(false);
        detailsPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        JLabel planName = new JLabel("Plan: Premium Internet");
        planName.setFont(new Font("Segoe UI", Font.BOLD, 18));
        planName.setForeground(Color.WHITE);

        JLabel speed = new JLabel("Speed: 100 Mbps");
        speed.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        speed.setForeground(Color.WHITE);

        JLabel data = new JLabel("Data: Unlimited");
        data.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        data.setForeground(Color.WHITE);

        JLabel price = new JLabel("Price: ₹999/month");
        price.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        price.setForeground(Color.WHITE);

        JLabel validTill = new JLabel("Valid Till: 2025-12-31");
        validTill.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        validTill.setForeground(Color.WHITE);

        detailsPanel.add(planName);
        detailsPanel.add(Box.createVerticalStrut(15));
        detailsPanel.add(speed);
        detailsPanel.add(Box.createVerticalStrut(10));
        detailsPanel.add(data);
        detailsPanel.add(Box.createVerticalStrut(10));
        detailsPanel.add(price);
        detailsPanel.add(Box.createVerticalStrut(10));
        detailsPanel.add(validTill);

        mainPanel.add(detailsPanel, BorderLayout.CENTER);

        add(mainPanel);
        setVisible(true);
    }

    // ===== Close Button =====
    private JButton createCloseButton() {
        JButton closeBtn = new JButton("✕");
        closeBtn.setFont(new Font("Arial", Font.BOLD, 16));
        closeBtn.setForeground(Color.WHITE);
        closeBtn.setBackground(new Color(239, 68, 68));
        closeBtn.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        closeBtn.setFocusPainted(false);
        closeBtn.addActionListener(e -> dispose());
        return closeBtn;
    }
}
