import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.Timer;

public class Plans extends JFrame {

    private float opacity = 0f;

    public Plans() {

        setTitle("Internet Plans");
        setSize(700, 450);
        setLocationRelativeTo(null);
        setUndecorated(true);
        setShape(new java.awt.geom.RoundRectangle2D.Double(0, 0, 700, 450, 30, 30));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setOpacity(0f);

        Timer fadeIn = new Timer(20, e -> {
            opacity += 0.05f;
            if (opacity >= 1f) {
                opacity = 1f;
                ((Timer) e.getSource()).stop();
            }
            setOpacity(opacity);
        });
        fadeIn.start();

        GradientPanel panel = new GradientPanel();
        panel.setLayout(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        JLabel heading = new JLabel("Available Internet Plans");
        heading.setFont(new Font("Segoe UI", Font.BOLD, 26));
        heading.setForeground(Color.WHITE);
        heading.setHorizontalAlignment(SwingConstants.CENTER);

        panel.add(heading, BorderLayout.NORTH);

        JPanel plansPanel = new JPanel(new GridLayout(1, 3, 20, 20));
        plansPanel.setOpaque(false);

        plansPanel.add(createPlanCard("Basic", "50 Mbps\n₹499/month"));
        plansPanel.add(createPlanCard("Standard", "100 Mbps\n₹799/month"));
        plansPanel.add(createPlanCard("Premium", "300 Mbps\n₹1299/month"));

        panel.add(plansPanel, BorderLayout.CENTER);

        JButton close = createButton("Close");
        JPanel bottom = new JPanel();
        bottom.setOpaque(false);
        bottom.add(close);

        close.addActionListener(e -> dispose());

        panel.add(bottom, BorderLayout.SOUTH);

        add(panel);
        setVisible(true);
    }

    private JPanel createPlanCard(String title, String details) {
        JPanel card = new JPanel();
        card.setLayout(new BorderLayout());
        card.setBackground(new Color(40, 40, 70));
        card.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel name = new JLabel(title);
        name.setFont(new Font("Segoe UI", Font.BOLD, 20));
        name.setForeground(Color.WHITE);

        JTextArea info = new JTextArea(details);
        info.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        info.setForeground(Color.WHITE);
        info.setOpaque(false);
        info.setEditable(false);

        card.add(name, BorderLayout.NORTH);
        card.add(info, BorderLayout.CENTER);

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

    private JButton createButton(String text) {
        JButton btn = new JButton(text);
        btn.setFocusPainted(false);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btn.setBackground(new Color(0, 120, 215));
        btn.setForeground(Color.WHITE);
        btn.setPreferredSize(new Dimension(120, 40));
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