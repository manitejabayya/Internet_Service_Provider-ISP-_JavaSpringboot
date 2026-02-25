import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.Timer;

public class CustomerForm extends JFrame {

    private float opacity = 0f;

    public CustomerForm() {

        setTitle("Add Customer");
        setSize(600, 500);
        setLocationRelativeTo(null);
        setUndecorated(true);
        setShape(new java.awt.geom.RoundRectangle2D.Double(0, 0, 600, 500, 30, 30));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setOpacity(0f);

        // Fade-in
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
        panel.setBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40));

        JLabel heading = new JLabel("Add New Customer");
        heading.setFont(new Font("Segoe UI", Font.BOLD, 26));
        heading.setForeground(Color.WHITE);
        heading.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(heading, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridLayout(5, 2, 15, 15));
        formPanel.setOpaque(false);

        JTextField name = createField();
        JTextField email = createField();
        JTextField phone = createField();
        JComboBox<String> plan = new JComboBox<>(new String[]{"Basic", "Standard", "Premium"});
        styleCombo(plan);

        formPanel.add(createLabel("Name"));
        formPanel.add(name);
        formPanel.add(createLabel("Email"));
        formPanel.add(email);
        formPanel.add(createLabel("Phone"));
        formPanel.add(phone);
        formPanel.add(createLabel("Plan"));
        formPanel.add(plan);

        panel.add(formPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);

        JButton save = createButton("Save");
        JButton close = createButton("Close");

        save.addActionListener(e ->
                JOptionPane.showMessageDialog(this, "Customer Added Successfully!"));

        close.addActionListener(e -> dispose());

        buttonPanel.add(save);
        buttonPanel.add(close);

        panel.add(buttonPanel, BorderLayout.SOUTH);

        add(panel);
        setVisible(true);
    }

    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        return label;
    }

    private JTextField createField() {
        JTextField field = new JTextField();
        field.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        field.setPreferredSize(new Dimension(200, 35));
        return field;
    }

    private void styleCombo(JComboBox<String> combo) {
        combo.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        combo.setBackground(Color.WHITE);
    }

    private JButton createButton(String text) {
        JButton btn = new JButton(text);
        btn.setFocusPainted(false);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btn.setBackground(new Color(0, 120, 215));
        btn.setForeground(Color.WHITE);
        btn.setPreferredSize(new Dimension(100, 35));
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