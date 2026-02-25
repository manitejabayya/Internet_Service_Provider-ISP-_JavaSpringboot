import javax.swing.*;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;

public class MainFrame {
    private static float imageScale = 0.3f;
    private static float imageAlpha = 0f;
    private static float imageRotation = 0f;

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            JFrame frame = new JFrame("ISP Management System");
            frame.setSize(750, 550);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLocationRelativeTo(null);
            frame.setMinimumSize(new Dimension(600, 500));

            // ===== Gradient Background Panel =====
            JPanel mainPanel = new JPanel(new BorderLayout()) {
                private BufferedImage ispImage;

                {
                    try {
                        ispImage = ImageIO.read(new File("assests/isp.png"));
                    } catch (Exception e) {
                        System.err.println("Could not load image: " + e.getMessage());
                    }
                }

                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    Graphics2D g2 = (Graphics2D) g;
                    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                    g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);

                    // Draw animated background image
                    if (ispImage != null) {
                        // Scale image to fill the panel
                        double scaleX = (double) getWidth() / ispImage.getWidth();
                        double scaleY = (double) getHeight() / ispImage.getHeight();
                        double scale = Math.max(scaleX, scaleY);

                        int scaledWidth = (int) (ispImage.getWidth() * scale);
                        int scaledHeight = (int) (ispImage.getHeight() * scale);
                        int x = (getWidth() - scaledWidth) / 2;
                        int y = (getHeight() - scaledHeight) / 2;

                        AffineTransform at = AffineTransform.getTranslateInstance(
                                getWidth() / 2.0,
                                getHeight() / 2.0
                        );
                        at.rotate(Math.toRadians(imageRotation * 0.3)); // Slower rotation
                        at.scale(scale, scale);
                        at.translate(-ispImage.getWidth() / 2.0, -ispImage.getHeight() / 2.0);

                        Composite oldComposite = g2.getComposite();
                        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, imageAlpha));
                        g2.drawImage(ispImage, at, null);
                        g2.setComposite(oldComposite);
                    }

                    // Semi-transparent dark overlay for readability
                    g2.setColor(new Color(0, 0, 0, (int) (100 + imageAlpha * 80)));
                    g2.fillRect(0, 0, getWidth(), getHeight());
                }
            };

            // ===== TITLE =====
            JLabel title = new JLabel("Welcome to ISP Management System", SwingConstants.CENTER);
            title.setFont(new Font("Segoe UI", Font.BOLD, 24));
            title.setForeground(Color.WHITE);
            title.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));

            // ===== BUTTON PANEL =====
            JPanel buttonPanel = new JPanel(new GridLayout(5, 1, 15, 15));
            buttonPanel.setBorder(BorderFactory.createEmptyBorder(30, 170, 30, 170));
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

            // ===== ANIMATION LOOP =====
            Timer animationTimer = new Timer(16, e -> {
                if (imageAlpha < 1.0f) {
                    imageAlpha = Math.min(imageAlpha + 0.02f, 1.0f);
                }
                if (imageScale < 1.0f) {
                    imageScale = Math.min(imageScale + 0.015f, 1.0f);
                }
                imageRotation = (imageRotation + 0.5f) % 360;
                mainPanel.repaint();
            });
            animationTimer.start();

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