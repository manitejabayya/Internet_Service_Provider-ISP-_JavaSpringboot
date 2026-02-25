import javax.swing.*;
import java.awt.*;
import javax.swing.Timer;

public class CustomerPlans extends JFrame {

    private float opacity = 0f;

    public CustomerPlans() {

        setTitle("Available Plans");
        setSize(900, 600);
        setLocationRelativeTo(null);
        setUndecorated(true);
        setShape(new java.awt.geom.RoundRectangle2D.Double(0, 0, 900, 600, 30, 30));
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

        JLabel heading = new JLabel("AVAILABLE PLANS");
        heading.setFont(new Font("Segoe UI", Font.BOLD, 28));
        heading.setForeground(Color.WHITE);

        JButton closeBtn = createCloseButton();

        headerPanel.add(heading, BorderLayout.WEST);
        headerPanel.add(closeBtn, BorderLayout.EAST);

        mainPanel.add(headerPanel, BorderLayout.NORTH);

        // ===== PLANS CARDS =====
        JPanel plansPanel = new JPanel(new GridLayout(1, 3, 20, 20));
        plansPanel.setOpaque(false);

        plansPanel.add(createPlanCard("Basic", "₹499/mo", "50 Mbps", "50 GB"));
        plansPanel.add(createPlanCard("Premium", "₹999/mo", "100 Mbps", "Unlimited"));
        plansPanel.add(createPlanCard("Pro", "₹1499/mo", "200 Mbps", "Unlimited"));

        mainPanel.add(plansPanel, BorderLayout.CENTER);

        add(mainPanel);
        setVisible(true);
    }

    // ===== Create Plan Card =====
    private JPanel createPlanCard(String name, String price, String speed, String data) {
        JPanel card = new JPanel(new GridLayout(5, 1));
        card.setBackground(new Color(168, 85, 247, 180));
        card.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        card.setOpaque(true);

        JLabel planName = new JLabel(name);
        planName.setFont(new Font("Segoe UI", Font.BOLD, 18));
        planName.setForeground(Color.WHITE);
        planName.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel planPrice = new JLabel(price);
        planPrice.setFont(new Font("Segoe UI", Font.BOLD, 16));
        planPrice.setForeground(Color.WHITE);
        planPrice.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel planSpeed = new JLabel("Speed: " + speed);
        planSpeed.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        planSpeed.setForeground(Color.WHITE);
        planSpeed.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel planData = new JLabel("Data: " + data);
        planData.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        planData.setForeground(Color.WHITE);
        planData.setHorizontalAlignment(SwingConstants.CENTER);

        JButton selectBtn = new JButton("Select Plan");
        selectBtn.setForeground(Color.WHITE);
        selectBtn.setBackground(new Color(139, 92, 246));
        selectBtn.setFocusPainted(false);
        selectBtn.addActionListener(e -> JOptionPane.showMessageDialog(this, "Plan " + name + " selected!"));

        card.add(planName);
        card.add(planPrice);
        card.add(planSpeed);
        card.add(planData);
        card.add(selectBtn);

        return card;
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
