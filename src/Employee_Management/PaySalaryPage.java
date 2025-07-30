package Employee_Management;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class PaySalaryPage extends JFrame implements ActionListener {
    JTextField txtEmpId;
    JButton btnSearch, btnPay;
    JTable table;
    DefaultTableModel model;
    JLabel lblName, lblEmail, lblPhone;
    JTextField txtAmount;
    String currentEmpId = "";

    PaySalaryPage() {
        setTitle("Pay Salary");
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);

        JLabel lblEmpId = new JLabel("Enter Employee ID:");
        lblEmpId.setBounds(20, 20, 150, 30);
        add(lblEmpId);

        txtEmpId = new JTextField();
        txtEmpId.setBounds(170, 20, 150, 30);
        add(txtEmpId);

        btnSearch = new JButton("Search");
        btnSearch.setBounds(330, 20, 100, 30);
        btnSearch.setBackground(Color.BLACK);
        btnSearch.setForeground(Color.WHITE);
        btnSearch.addActionListener(this);
        add(btnSearch);

        JPanel detailsPanel = new JPanel();
        detailsPanel.setBounds(20, 70, 400, 100);
        detailsPanel.setLayout(new GridLayout(3, 2));
        detailsPanel.setBorder(BorderFactory.createTitledBorder("Employee Details"));

        detailsPanel.add(new JLabel("Name:"));
        lblName = new JLabel("");
        detailsPanel.add(lblName);

        detailsPanel.add(new JLabel("Email:"));
        lblEmail = new JLabel("");
        detailsPanel.add(lblEmail);

        detailsPanel.add(new JLabel("Phone:"));
        lblPhone = new JLabel("");
        detailsPanel.add(lblPhone);

        add(detailsPanel);

        JPanel paymentPanel = new JPanel();
        paymentPanel.setBounds(450, 70, 200, 100);
        paymentPanel.setLayout(new GridLayout(2, 2));
        paymentPanel.setBorder(BorderFactory.createTitledBorder("Pay Salary"));

        paymentPanel.add(new JLabel("Amount:"));
        txtAmount = new JTextField();
        paymentPanel.add(txtAmount);

        btnPay = new JButton("PAY");
        btnPay.setBackground(Color.BLACK);
        btnPay.setForeground(Color.WHITE);
        btnPay.addActionListener(this);
        paymentPanel.add(new JLabel("")); // Empty cell
        paymentPanel.add(btnPay);

        add(paymentPanel);

        String[] columns = {"Date", "Total Hours", "Salary"};
        model = new DefaultTableModel(columns, 0);
        table = new JTable(model);

        JScrollPane sp = new JScrollPane(table);
        sp.setBounds(20, 190, 650, 300);
        add(sp);

        setSize(700, 550);
        setLocation(300, 100);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnSearch) {
            searchEmployee();
        } else if (e.getSource() == btnPay) {
            paySalary();
        }
    }

    private void searchEmployee() {
        currentEmpId = txtEmpId.getText();

        if (currentEmpId.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter Employee ID");
            return;
        }

        try {
            ConnectionClass obj = new ConnectionClass();
            
            String empQuery = "SELECT name, email, phone FROM employees WHERE emp_id='" + currentEmpId + "'";
            ResultSet empRs = obj.stm.executeQuery(empQuery);

            if (empRs.next()) {
                lblName.setText(empRs.getString("name"));
                lblEmail.setText(empRs.getString("email"));
                lblPhone.setText(empRs.getString("phone"));
            } else {
                JOptionPane.showMessageDialog(null, "Employee not found");
                return;
            }

            String salaryQuery = "SELECT date, total_hours, salary FROM attendance WHERE emp_id='" + currentEmpId + "'";
            ResultSet salaryRs = obj.stm.executeQuery(salaryQuery);

            model.setRowCount(0); 

            double totalSalary = 0;
            while (salaryRs.next()) {
                String date = salaryRs.getString("date");
                double hours = salaryRs.getDouble("total_hours");
                double salary = salaryRs.getDouble("salary");
                totalSalary += salary;

                model.addRow(new Object[]{date, hours, "₹" + salary});
            }
            txtAmount.setText(String.valueOf(totalSalary));
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error searching employee");
        }
    }
    private void paySalary() {
        if (currentEmpId.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please search for an employee first");
            return;
        }
        String amountStr = txtAmount.getText();
        if (amountStr.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter amount");
            return;
        }
        try {
            double amount = Double.parseDouble(amountStr);
            ConnectionClass obj = new ConnectionClass(); 
            String insertQuery = "INSERT INTO salary_payments (emp_id, amount, payment_date) VALUES ('" + 
                                currentEmpId + "', " + amount + ", CURDATE())";
            
            int rowsAffected = obj.stm.executeUpdate(insertQuery);
            
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Salary payment successful!");

            } else {
                JOptionPane.showMessageDialog(null, "Payment failed");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error processing payment");
        }
    }

    public static void main(String[] args) {
        new PaySalaryPage();
    }
}