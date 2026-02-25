import javax.swing.*;
import java.awt.*;

public class SignupFrame extends JFrame {

    private JTextField fullNameField;
    private JTextField usernameField;
    private JTextField emailField;
    private JPasswordField passwordField;
    private JPasswordField confirmPasswordField;

    public SignupFrame() {

        setTitle("ISP Signup");
        setSize(500, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setMinimumSize(new Dimension(400, 450));
        setLayout(new BorderLayout());

        // ===== TOP BAR =====
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

        // ===== TITLE =====
        JLabel title = new JLabel("Create ISP Account");
        title.setFont(new Font("Segoe UI", Font.BOLD, 22));
        title.setForeground(Color.WHITE);
        title.setHorizontalAlignment(SwingConstants.CENTER);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        mainPanel.add(title, gbc);
        gbc.gridwidth = 1;

        // ===== FULL NAME =====
        gbc.gridy++;
        gbc.gridx = 0;
        JLabel nameLabel = new JLabel("Full Name");
        nameLabel.setForeground(Color.WHITE);
        mainPanel.add(nameLabel, gbc);

        gbc.gridx = 1;
        fullNameField = new JTextField(15);
        styleField(fullNameField);
        mainPanel.add(fullNameField, gbc);

        // ===== USERNAME =====
        gbc.gridy++;
        gbc.gridx = 0;
        JLabel userLabel = new JLabel("Username");
        userLabel.setForeground(Color.WHITE);
        mainPanel.add(userLabel, gbc);

        gbc.gridx = 1;
        usernameField = new JTextField(15);
        styleField(usernameField);
        mainPanel.add(usernameField, gbc);

        // ===== EMAIL =====
        gbc.gridy++;
        gbc.gridx = 0;
        JLabel emailLabel = new JLabel("Email");
        emailLabel.setForeground(Color.WHITE);
        mainPanel.add(emailLabel, gbc);

        gbc.gridx = 1;
        emailField = new JTextField(15);
        styleField(emailField);
        mainPanel.add(emailField, gbc);

        // ===== PASSWORD =====
        gbc.gridy++;
        gbc.gridx = 0;
        JLabel passLabel = new JLabel("Password");
        passLabel.setForeground(Color.WHITE);
        mainPanel.add(passLabel, gbc);

        gbc.gridx = 1;
        passwordField = new JPasswordField(15);
        styleField(passwordField);
        mainPanel.add(passwordField, gbc);

        // ===== CONFIRM PASSWORD =====
        gbc.gridy++;
        gbc.gridx = 0;
        JLabel confirmLabel = new JLabel("Confirm Password");
        confirmLabel.setForeground(Color.WHITE);
        mainPanel.add(confirmLabel, gbc);

        gbc.gridx = 1;
        confirmPasswordField = new JPasswordField(15);
        styleField(confirmPasswordField);
        mainPanel.add(confirmPasswordField, gbc);

        // ===== REGISTER BUTTON =====
        gbc.gridy++;
        gbc.gridx = 0;
        gbc.gridwidth = 2;

        JButton registerBtn = new JButton("Register");
        registerBtn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        registerBtn.setBackground(new Color(34, 197, 94));
        registerBtn.setForeground(Color.WHITE);
        registerBtn.setFocusPainted(false);
        registerBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        registerBtn.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        registerBtn.addActionListener(e -> performSignup());

        mainPanel.add(registerBtn, gbc);

        // ===== BACK TO LOGIN BUTTON =====
        gbc.gridy++;
        JButton backBtn = new JButton("Back to Login");
        backBtn.setBackground(new Color(59, 130, 246));
        backBtn.setForeground(Color.WHITE);
        backBtn.setFocusPainted(false);
        backBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        backBtn.setBorder(BorderFactory.createEmptyBorder(8, 10, 8, 10));

        backBtn.addActionListener(e -> {
            dispose();
            new LoginFrame().setVisible(true);
        });

        mainPanel.add(backBtn, gbc);

        getRootPane().setDefaultButton(registerBtn);

        add(mainPanel, BorderLayout.CENTER);
    }

    private void styleField(JTextField field) {
        field.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        field.setBackground(new Color(30, 41, 59));
        field.setForeground(Color.WHITE);
        field.setCaretColor(Color.WHITE);
        field.setBorder(BorderFactory.createEmptyBorder(8, 10, 8, 10));
    }

    private void performSignup() {

        String name = fullNameField.getText().trim();
        String username = usernameField.getText().trim();
        String email = emailField.getText().trim();
        String password = new String(passwordField.getPassword()).trim();
        String confirmPassword = new String(confirmPasswordField.getPassword()).trim();

        if (name.isEmpty() || username.isEmpty() || email.isEmpty()
                || password.isEmpty() || confirmPassword.isEmpty()) {

            JOptionPane.showMessageDialog(this,
                    "All fields are required.",
                    "Signup Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!password.equals(confirmPassword)) {
            JOptionPane.showMessageDialog(this,
                    "Passwords do not match.",
                    "Signup Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        JOptionPane.showMessageDialog(this,
                "Account Created Successfully!",
                "Success",
                JOptionPane.INFORMATION_MESSAGE);

        dispose();
        new LoginFrame().setVisible(true);
    }
}