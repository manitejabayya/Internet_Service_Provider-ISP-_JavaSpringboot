import javax.swing.*;
import java.awt.*;
import javax.swing.Timer;

public class CustomerProfile extends JFrame {

    private float opacity = 0f;

    public CustomerProfile() {

        setTitle("Customer Profile");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setUndecorated(true);
        setShape(new java.awt.geom.RoundRectangle2D.Double(0, 0, 800, 600, 30, 30));
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

        JLabel heading = new JLabel("YOUR PROFILE");
        heading.setFont(new Font("Segoe UI", Font.BOLD, 28));
        heading.setForeground(Color.WHITE);

        JButton closeBtn = createCloseButton();

        headerPanel.add(heading, BorderLayout.WEST);
        headerPanel.add(closeBtn, BorderLayout.EAST);

        mainPanel.add(headerPanel, BorderLayout.NORTH);

        // ===== PROFILE DETAILS =====
        JPanel profilePanel = new JPanel();
        profilePanel.setLayout(new BoxLayout(profilePanel, BoxLayout.Y_AXIS));
        profilePanel.setOpaque(false);
        profilePanel.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));

        JLabel name = new JLabel("Name: John Doe");
        name.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        name.setForeground(Color.WHITE);

        JLabel email = new JLabel("Email: john.doe@example.com");
        email.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        email.setForeground(Color.WHITE);

        JLabel phone = new JLabel("Phone: +91 98765 43210");
        phone.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        phone.setForeground(Color.WHITE);

        JLabel address = new JLabel("Address: 123 Main Street, City");
        address.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        address.setForeground(Color.WHITE);

        JLabel customerId = new JLabel("Customer ID: ISP-12345");
        customerId.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        customerId.setForeground(Color.WHITE);

        JLabel accountStatus = new JLabel("Account Status: Active");
        accountStatus.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        accountStatus.setForeground(new Color(34, 197, 94));

        profilePanel.add(name);
        profilePanel.add(Box.createVerticalStrut(12));
        profilePanel.add(email);
        profilePanel.add(Box.createVerticalStrut(12));
        profilePanel.add(phone);
        profilePanel.add(Box.createVerticalStrut(12));
        profilePanel.add(address);
        profilePanel.add(Box.createVerticalStrut(12));
        profilePanel.add(customerId);
        profilePanel.add(Box.createVerticalStrut(12));
        profilePanel.add(accountStatus);

        mainPanel.add(profilePanel, BorderLayout.CENTER);

        // ===== BUTTON PANEL =====
        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);

        JButton editBtn = createActionButton("Edit Profile", new Color(59, 130, 246));
        JButton changePassBtn = createActionButton("Change Password", new Color(168, 85, 247));

        editBtn.addActionListener(e -> JOptionPane.showMessageDialog(this, "Edit profile feature coming soon..."));
        changePassBtn.addActionListener(e -> JOptionPane.showMessageDialog(this, "Change password feature coming soon..."));

        buttonPanel.add(editBtn);
        buttonPanel.add(Box.createHorizontalStrut(10));
        buttonPanel.add(changePassBtn);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);
        setVisible(true);
    }

    // ===== Action Button =====
    private JButton createActionButton(String text, Color bgColor) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        button.setForeground(Color.WHITE);
        button.setBackground(bgColor);
        button.setBorder(BorderFactory.createEmptyBorder(8, 20, 8, 20));
        button.setFocusPainted(false);
        return button;
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
