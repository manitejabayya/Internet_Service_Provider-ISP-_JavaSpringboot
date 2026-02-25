import javax.swing.*;
import java.awt.*;

public class LoginFrame extends JFrame {

    private JTextField usernameField;
    private JPasswordField passwordField;

    public LoginFrame() {

        setTitle("ISP Login");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE); // important
        setMinimumSize(new Dimension(400, 350));
        setLayout(new BorderLayout());

        // ===== TOP BAR (close button) =====
        JPanel topBar = new JPanel(new BorderLayout());
        topBar.setBackground(new Color(15, 23, 42));

        JButton closeBtn = new JButton("✕");
        closeBtn.setForeground(Color.WHITE);
        closeBtn.setBackground(new Color(239, 68, 68));
        closeBtn.setFocusPainted(false);
        closeBtn.setBorder(BorderFactory.createEmptyBorder(5,10,5,10));
        closeBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        closeBtn.addActionListener(e -> dispose());

        topBar.add(closeBtn, BorderLayout.EAST);
        add(topBar, BorderLayout.NORTH);

        // ===== MAIN PANEL =====
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(new Color(15, 23, 42));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 15, 10, 15);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel title = new JLabel("ISP Management Login");
        title.setFont(new Font("Segoe UI", Font.BOLD, 22));
        title.setForeground(Color.WHITE);
        title.setHorizontalAlignment(SwingConstants.CENTER);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        mainPanel.add(title, gbc);
        gbc.gridwidth = 1;

        gbc.gridy++;
        gbc.gridx = 0;
        JLabel userLabel = new JLabel("Username");
        userLabel.setForeground(Color.WHITE);
        mainPanel.add(userLabel, gbc);

        gbc.gridx = 1;
        usernameField = new JTextField(15);
        styleField(usernameField);
        mainPanel.add(usernameField, gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        JLabel passLabel = new JLabel("Password");
        passLabel.setForeground(Color.WHITE);
        mainPanel.add(passLabel, gbc);

        gbc.gridx = 1;
        passwordField = new JPasswordField(15);
        styleField(passwordField);
        mainPanel.add(passwordField, gbc);

        add(mainPanel, BorderLayout.CENTER);
    }

    private void styleField(JTextField field) {
        field.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        field.setBackground(new Color(30, 41, 59));
        field.setForeground(Color.WHITE);
        field.setCaretColor(Color.WHITE);
        field.setBorder(BorderFactory.createEmptyBorder(8, 10, 8, 10));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() ->
                new LoginFrame().setVisible(true)
        );
    }
}