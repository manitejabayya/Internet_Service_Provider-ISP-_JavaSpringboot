import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    public MainFrame() {

        setTitle("ISP Management System");
        setSize(600, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(500, 400));

        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(new Color(15, 23, 42));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 20, 15, 20);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // ===== TITLE =====
        JLabel title = new JLabel("Welcome to ISP System");
        title.setFont(new Font("Segoe UI", Font.BOLD, 26));
        title.setForeground(Color.WHITE);
        title.setHorizontalAlignment(SwingConstants.CENTER);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        mainPanel.add(title, gbc);

        gbc.gridwidth = 1;

        // ===== LOGIN BUTTON =====
        gbc.gridy++;
        JButton loginBtn = createButton("Login",
                new Color(37, 99, 235),
                new Color(59, 130, 246));

        mainPanel.add(loginBtn, gbc);

        // ===== SIGNUP BUTTON =====
        gbc.gridy++;
        JButton signupBtn = createButton("Sign Up",
                new Color(16, 185, 129),
                new Color(34, 197, 94));

        mainPanel.add(signupBtn, gbc);

        add(mainPanel);

        // ===== ACTIONS =====
        loginBtn.addActionListener(e -> new LoginFrame().setVisible(true));

        signupBtn.addActionListener(e -> new SignupFrame().setVisible(true));
    }

    private JButton createButton(String text, Color normal, Color hover) {

        JButton btn = new JButton(text);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btn.setForeground(Color.WHITE);
        btn.setBackground(normal);
        btn.setFocusPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setBorder(BorderFactory.createEmptyBorder(12, 20, 12, 20));

        btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent e) {
                btn.setBackground(hover);
            }

            public void mouseExited(java.awt.event.MouseEvent e) {
                btn.setBackground(normal);
            }
        });

        return btn;
    }

    // ENTRY POINT
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainFrame().setVisible(true));
    }
}