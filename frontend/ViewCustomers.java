import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;
import javax.swing.Timer;
import java.util.Vector;

public class ViewCustomers extends JFrame {

    private JTable table;
    private DefaultTableModel model;
    private TableRowSorter<DefaultTableModel> sorter;
    private float opacity = 0f;

    public ViewCustomers() {

        setTitle("View Customers");
        setSize(1000, 550);
        setLocationRelativeTo(null);
        setUndecorated(true); // Required for rounded corners
        setShape(new java.awt.geom.RoundRectangle2D.Double(0, 0, 1000, 550, 30, 30));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setOpacity(0f);

        // Fade-in animation
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
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // HEADER
        JLabel heading = new JLabel("Customer Management");
        heading.setFont(new Font("Segoe UI", Font.BOLD, 28));
        heading.setForeground(Color.WHITE);
        heading.setHorizontalAlignment(SwingConstants.CENTER);

        panel.add(heading, BorderLayout.NORTH);

        // TABLE
        String[] columns = {"ID", "Name", "Email", "Phone", "Plan"};
        model = new DefaultTableModel(columns, 0);

        model.addRow(new Object[]{"101", "Guru", "guru@mail.com", "9876543210", "Premium"});
        model.addRow(new Object[]{"102", "Rahul", "rahul@mail.com", "9123456780", "Basic"});
        model.addRow(new Object[]{"103", "Anjali", "anjali@mail.com", "9988776655", "Standard"});

        table = new JTable(model) {
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                Component c = super.prepareRenderer(renderer, row, column);
                if (!isRowSelected(row))
                    c.setBackground(row == getSelectedRow() ? new Color(60, 130, 255) :
                            (row % 2 == 0 ? new Color(30, 30, 50) : new Color(25, 25, 45)));
                c.setForeground(Color.WHITE);
                return c;
            }
        };

        table.setRowHeight(35);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        table.setGridColor(new Color(70, 70, 100));
        table.setSelectionBackground(new Color(0, 150, 255));

        JTableHeader header = table.getTableHeader();
        header.setBackground(new Color(0, 120, 215));
        header.setForeground(Color.WHITE);
        header.setFont(new Font("Segoe UI", Font.BOLD, 16));

        sorter = new TableRowSorter<>(model);
        table.setRowSorter(sorter);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setOpaque(false);

        panel.add(scrollPane, BorderLayout.CENTER);

        // BOTTOM PANEL
        JPanel bottomPanel = new JPanel();
        bottomPanel.setOpaque(false);

        JTextField searchField = new JTextField(20);
        searchField.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        searchField.setPreferredSize(new Dimension(200, 35));

        searchField.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) { filter(); }
            public void removeUpdate(DocumentEvent e) { filter(); }
            public void changedUpdate(DocumentEvent e) { filter(); }
            private void filter() {
                String text = searchField.getText();
                if (text.trim().length() == 0)
                    sorter.setRowFilter(null);
                else
                    sorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
            }
        });

        JButton editBtn = createButton("Edit");
        JButton deleteBtn = createButton("Delete");
        JButton closeBtn = createButton("Close");

        editBtn.addActionListener(e -> editCustomer());
        deleteBtn.addActionListener(e -> deleteCustomer());
        closeBtn.addActionListener(e -> dispose());

        bottomPanel.add(new JLabel("Search: "));
        bottomPanel.add(searchField);
        bottomPanel.add(editBtn);
        bottomPanel.add(deleteBtn);
        bottomPanel.add(closeBtn);

        panel.add(bottomPanel, BorderLayout.SOUTH);

        add(panel);
        setVisible(true);
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

    private void editCustomer() {
        int row = table.getSelectedRow();
        if (row < 0) return;

        row = table.convertRowIndexToModel(row);

        String name = (String) model.getValueAt(row, 1);
        String email = (String) model.getValueAt(row, 2);

        JTextField nameField = new JTextField(name);
        JTextField emailField = new JTextField(email);

        Object[] message = {
                "Name:", nameField,
                "Email:", emailField
        };

        int option = JOptionPane.showConfirmDialog(this, message, "Edit Customer",
                JOptionPane.OK_CANCEL_OPTION);

        if (option == JOptionPane.OK_OPTION) {
            model.setValueAt(nameField.getText(), row, 1);
            model.setValueAt(emailField.getText(), row, 2);
        }
    }

    private void deleteCustomer() {
        int row = table.getSelectedRow();
        if (row < 0) return;

        row = table.convertRowIndexToModel(row);

        int confirm = JOptionPane.showConfirmDialog(this,
                "Are you sure you want to delete this customer?",
                "Confirm Delete",
                JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            model.removeRow(row);
        }
    }

    // Gradient Background Panel
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