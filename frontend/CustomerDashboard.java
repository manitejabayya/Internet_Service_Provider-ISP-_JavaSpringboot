import javax.swing.*;
import java.awt.*;
import javax.swing.Timer;

public class CustomerDashboard extends JFrame {

    private float opacity = 0f;

    public CustomerDashboard() {

        setTitle("Customer Dashboard");
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

        JLabel heading = new JLabel("YOUR DASHBOARD");
        heading.setFont(new Font("Segoe UI", Font.BOLD, 28));
        heading.setForeground(Color.WHITE);

        JButton closeBtn = createCloseButton();

        headerPanel.add(heading, BorderLayout.WEST);
        headerPanel.add(closeBtn, BorderLayout.EAST);

        mainPanel.add(headerPanel, BorderLayout.NORTH);

        // ===== INFO CARDS =====
        JPanel cardsPanel = new JPanel(new GridLayout(2, 2, 20, 20));
        cardsPanel.setOpaque(false);

        cardsPanel.add(createCard("Current Plan", "Premium"));
        cardsPanel.add(createCard("Data Used", "45 GB / 100 GB"));
        cardsPanel.add(createCard("Connection Speed", "100 Mbps"));
        cardsPanel.add(createCard("Bill Amount", "₹999"));

        mainPanel.add(cardsPanel, BorderLayout.CENTER);

        add(mainPanel);
        setVisible(true);
    }

    // ===== Create Card =====
    private JPanel createCard(String label, String value) {
        JPanel card = new JPanel(new GridLayout(2, 1));
        card.setBackground(new Color(59, 130, 246, 180));
        card.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        card.setOpaque(true);

        JLabel labelText = new JLabel(label);
        labelText.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        labelText.setForeground(Color.WHITE);

        JLabel valueText = new JLabel(value);
        valueText.setFont(new Font("Segoe UI", Font.BOLD, 20));
        valueText.setForeground(Color.WHITE);

        card.add(labelText);
        card.add(valueText);

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
