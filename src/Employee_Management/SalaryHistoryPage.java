package Employee_Management;

import java.awt.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class SalaryHistoryPage extends JFrame {
    JTable table;
    DefaultTableModel model;

    SalaryHistoryPage() {
        setTitle("Salary Payment History");
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);

        String[] columns = {"Payment ID", "Employee ID", "Amount", "Payment Date"};
        model = new DefaultTableModel(columns, 0);
        table = new JTable(model);

        JScrollPane sp = new JScrollPane(table);
        sp.setBounds(20, 20, 850, 500);
        add(sp);

        loadSalaryHistory();

        setSize(900, 600);
        setLocation(300, 100);
        setVisible(true);
    }

    private void loadSalaryHistory() {
        try {
            ConnectionClass obj = new ConnectionClass();
            String query = "SELECT * FROM salary_payments";
            ResultSet rs = obj.stm.executeQuery(query);

            model.setRowCount(0); 

            while (rs.next()) {
                int paymentId = rs.getInt("payment_id");
                String empId = rs.getString("emp_id");
                double amount = rs.getDouble("amount");
                Date paymentDate = rs.getDate("payment_date");

                model.addRow(new Object[]{
                    paymentId,
                    empId,
                    "₹" + amount,
                    paymentDate
                });
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error loading salary history");
        }
    }

    public static void main(String[] args) {
        new SalaryHistoryPage();
    }
}