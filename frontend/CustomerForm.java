import javax.swing.*;
import java.awt.*;

public class CustomerForm extends JFrame {

    // Input fields (we keep them global so we can read values later)
    private JTextField nameField;
    private JTextField phoneField;
    private JTextField planField;

    public CustomerForm() {

        // Window title
        setTitle("Add Customer");

        // Window size
        setSize(400, 300);

        // Center on screen
        setLocationRelativeTo(null);

        // Layout for form: rows & columns
        setLayout(new GridLayout(4, 2, 10, 10));

        // Padding inside window
        ((JComponent) getContentPane())
                .setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // ===== NAME =====
        add(new JLabel("Customer Name:"));
        nameField = new JTextField();
        add(nameField);

        // ===== PHONE =====
        add(new JLabel("Phone:"));
        phoneField = new JTextField();
        add(phoneField);

        // ===== PLAN =====
        add(new JLabel("Plan:"));
        planField = new JTextField();
        add(planField);

        // ===== SAVE BUTTON =====
        JButton saveBtn = new JButton("Save Customer");
        add(saveBtn);

        // Empty cell for layout alignment
        add(new JLabel(""));

        // Button action (temporary)
        saveBtn.addActionListener(e -> saveCustomer());

        // Show window
        setVisible(true);
    }

    // This method runs when Save button clicked
    private void saveCustomer() {

        String name = nameField.getText();
        String phone = phoneField.getText();
        String plan = planField.getText();

        // For now just show entered data
        JOptionPane.showMessageDialog(this,
                "Customer Saved:\nName: " + name +
                "\nPhone: " + phone +
                "\nPlan: " + plan);
    }
}