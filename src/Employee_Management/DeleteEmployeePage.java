package Employee_Management;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

public class DeleteEmployeePage extends JFrame implements ActionListener {
    JTextField txtEmpId;
    JButton btnSearch, btnDelete;
    JLabel lblName, lblEmail, lblPhone, lblJobPost;
    String currentEmpId = "";

    DeleteEmployeePage() {
        setTitle("Delete Employee");
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
        detailsPanel.setBounds(20, 70, 400, 150);
        detailsPanel.setLayout(new GridLayout(4, 2));
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

        detailsPanel.add(new JLabel("Job Post:"));
        lblJobPost = new JLabel("");
        detailsPanel.add(lblJobPost);

        add(detailsPanel);

        btnDelete = new JButton("DELETE EMPLOYEE");
        btnDelete.setBounds(150, 240, 200, 40);
        btnDelete.setBackground(Color.RED);
        btnDelete.setForeground(Color.WHITE);
        btnDelete.addActionListener(this);
        btnDelete.setEnabled(false); 
        add(btnDelete);

        setSize(500, 350);
        setLocation(400, 200);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnSearch) {
            searchEmployee();
        } else if (e.getSource() == btnDelete) {
            deleteEmployee();
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
            String query = "SELECT name, email, phone, job_post FROM employees WHERE emp_id='" + currentEmpId + "'";
            ResultSet rs = obj.stm.executeQuery(query);

            if (rs.next()) {
                lblName.setText(rs.getString("name"));
                lblEmail.setText(rs.getString("email"));
                lblPhone.setText(rs.getString("phone"));
                lblJobPost.setText(rs.getString("job_post"));
                btnDelete.setEnabled(true);
            } else {
                JOptionPane.showMessageDialog(null, "Employee not found");
                clearEmployeeDetails();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error searching employee");
            clearEmployeeDetails();
        }
    }

    private void clearEmployeeDetails() {
        lblName.setText("");
        lblEmail.setText("");
        lblPhone.setText("");
        lblJobPost.setText("");
        btnDelete.setEnabled(false);
        currentEmpId = "";
    }

    private void deleteEmployee() {
        if (currentEmpId.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please search for an employee first");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(
            this, 
            "Are you sure you want to delete this employee and all their records?\nThis action cannot be undone!", 
            "Confirm Deletion", 
            JOptionPane.YES_NO_OPTION
        );

        if (confirm != JOptionPane.YES_OPTION) {
            return;
        }

        try {
            ConnectionClass obj = new ConnectionClass();
            
            obj.stm.execute("SET FOREIGN_KEY_CHECKS=0");
            
            String[] tables = {"salary_payments", "leavedata", "attendance", "employees"};
            boolean success = true;
            
            for (String table : tables) {
                String query = "DELETE FROM " + table + " WHERE emp_id='" + currentEmpId + "'";
                if (table.equals("leavedata")) {
                    query = "DELETE FROM " + table + " WHERE emp_id=" + currentEmpId;
                }
                
                try {
                    obj.stm.executeUpdate(query);
                } catch (SQLException ex) {
                    System.out.println("Error deleting from " + table + ": " + ex.getMessage());
                }
            }
            
            obj.stm.execute("SET FOREIGN_KEY_CHECKS=1");
            
            JOptionPane.showMessageDialog(null, "Employee and all related records deleted successfully!");
            clearEmployeeDetails();
            txtEmpId.setText("");

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error deleting employee");
        }
    }

    public static void main(String[] args) {
        new DeleteEmployeePage();
    }
}