import javax.swing.*;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;

public class CustomerFrame extends JFrame {

    private float imageAlpha = 0f;
    private float imageRotation = 0f;

    public CustomerFrame() {

        setTitle("ISP Customer Dashboard");
        setSize(750, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setMinimumSize(new Dimension(600, 500));

        // ===== Background Panel with Animation =====
        JPanel mainPanel = new JPanel(new BorderLayout()) {

            private BufferedImage ispImage;

            {
                try {
                    ispImage = ImageIO.read(new File("assests/isp.png"));
                } catch (Exception e) {
                    System.err.println("Image not found: " + e.getMessage());
                }
            }

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;

                if (ispImage != null) {
                    double scaleX = (double) getWidth() / ispImage.getWidth();
                    double scaleY = (double) getHeight() / ispImage.getHeight();
                    double scale = Math.max(scaleX, scaleY);

                    AffineTransform at = AffineTransform.getTranslateInstance(
                            getWidth() / 2.0,
                            getHeight() / 2.0
                    );

                    at.rotate(Math.toRadians(imageRotation * 0.3));
                    at.scale(scale, scale);
                    at.translate(-ispImage.getWidth() / 2.0,
                            -ispImage.getHeight() / 2.0);

                    Graphics2D gAlpha = (Graphics2D) g2.create();
                    gAlpha.setComposite(AlphaComposite.getInstance(
                            AlphaComposite.SRC_OVER, imageAlpha));
                    gAlpha.drawImage(ispImage, at, null);
                    gAlpha.dispose();
                }

                g2.setColor(new Color(0, 0, 0,
                        (int) (100 + imageAlpha * 80)));
                g2.fillRect(0, 0, getWidth(), getHeight());
            }
        };

        // ===== TITLE =====
        JLabel title = new JLabel("Welcome to ISP Customer Panel",
                SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 24));
        title.setForeground(Color.WHITE);
        title.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));

        // ===== BUTTON PANEL =====
        JPanel buttonPanel = new JPanel(new GridLayout(5, 1, 15, 15));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(30, 170, 30, 170));
        buttonPanel.setOpaque(false);

        JButton dashboard = createStyledButton(
                "Dashboard",
                new Color(14, 165, 233),
                new Color(56, 189, 248));

        JButton currentPlan = createStyledButton(
                "Current Plan",
                new Color(16, 185, 129),
                new Color(34, 197, 94));

        JButton plans = createStyledButton(
                "Plans",
                new Color(168, 85, 247),
                new Color(139, 92, 246));

        JButton profile = createStyledButton(
                "Profile",
                new Color(59, 130, 246),
                new Color(37, 99, 235));

        JButton exit = createStyledButton(
                "Exit",
                new Color(239, 68, 68),
                new Color(220, 38, 38));

        buttonPanel.add(dashboard);
        buttonPanel.add(currentPlan);
        buttonPanel.add(plans);
        buttonPanel.add(profile);
        buttonPanel.add(exit);

        mainPanel.add(title, BorderLayout.NORTH);
        mainPanel.add(buttonPanel, BorderLayout.CENTER);

        add(mainPanel);

        // ===== Animation Timer =====
        Timer animationTimer = new Timer(16, e -> {
            if (imageAlpha < 1.0f)
                imageAlpha = Math.min(imageAlpha + 0.02f, 1.0f);

            imageRotation = (imageRotation + 0.5f) % 360;
            mainPanel.repaint();
        });
        animationTimer.start();

        // ===== Navigation (Create These Classes Later) =====
        dashboard.addActionListener(e -> new CustomerDashboard());
        currentPlan.addActionListener(e -> new CurrentPlan());
        plans.addActionListener(e -> new CustomerPlans());
        profile.addActionListener(e -> new CustomerProfile());
        exit.addActionListener(e -> System.exit(0));
    }

    // ===== Stylish Button =====
    private static JButton createStyledButton(String text,
                                              Color normal,
                                              Color hover) {

        JButton button = new JButton(text) {

            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON);

                g2.setColor(getBackground());
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 35, 35);
                super.paintComponent(g);
                g2.dispose();
            }

            protected void paintBorder(Graphics g) {}
        };

        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setFont(new Font("Segoe UI", Font.BOLD, 18));
        button.setForeground(Color.WHITE);
        button.setBackground(normal);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        button.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                button.setBackground(hover);
            }

            public void mouseExited(MouseEvent e) {
                button.setBackground(normal);
            }
        });

        return button;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() ->
                new CustomerFrame().setVisible(true));
    }
}