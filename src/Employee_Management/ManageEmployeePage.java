package Employee_Management;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

public class ManageEmployeePage extends JFrame implements ActionListener {
    JFrame f;
    JLabel empId, empName, empAge, empAddress, empEmail, empJob, empDob, empPhone, empEducation, empExtra;
    JTextField empIdText, empNameText, empAgeText, empAddressText, empEmailText, empJobText, empDobText, empPhoneText, empEducationText, empExtraText;
    JButton search, update, close;

    public ManageEmployeePage() {
        f = new JFrame("Update Employee");
        f.setLayout(null);
        f.setSize(500, 600);
        f.setLocation(400, 150);
        f.setVisible(true);

        empId = new JLabel("Employee ID:");
        empId.setBounds(50, 30, 100, 30);
        f.add(empId);

        empIdText = new JTextField();
        empIdText.setBounds(150, 30, 150, 30);
        f.add(empIdText);

        search = new JButton("Search");
        search.setBounds(320, 30, 100, 30);
        search.addActionListener(this);
        f.add(search);

        empName = new JLabel("Name:");
        empName.setBounds(50, 80, 100, 30);
        f.add(empName);

        empNameText = new JTextField();
        empNameText.setBounds(150, 80, 200, 30);
        f.add(empNameText);

        empAge = new JLabel("Age:");
        empAge.setBounds(50, 120, 100, 30);
        f.add(empAge);

        empAgeText = new JTextField();
        empAgeText.setBounds(150, 120, 200, 30);
        f.add(empAgeText);

        empAddress = new JLabel("Address:");
        empAddress.setBounds(50, 160, 100, 30);
        f.add(empAddress);

        empAddressText = new JTextField();
        empAddressText.setBounds(150, 160, 200, 30);
        f.add(empAddressText);

        empEmail = new JLabel("Email:");
        empEmail.setBounds(50, 200, 100, 30);
        f.add(empEmail);

        empEmailText = new JTextField();
        empEmailText.setBounds(150, 200, 200, 30);
        f.add(empEmailText);

        empJob = new JLabel("Job Post:");
        empJob.setBounds(50, 240, 100, 30);
        f.add(empJob);

        empJobText = new JTextField();
        empJobText.setBounds(150, 240, 200, 30);
        f.add(empJobText);

        empDob = new JLabel("DOB:");
        empDob.setBounds(50, 280, 100, 30);
        f.add(empDob);

        empDobText = new JTextField();
        empDobText.setBounds(150, 280, 200, 30);
        f.add(empDobText);

        empPhone = new JLabel("Phone:");
        empPhone.setBounds(50, 320, 100, 30);
        f.add(empPhone);

        empPhoneText = new JTextField();
        empPhoneText.setBounds(150, 320, 200, 30);
        f.add(empPhoneText);

        empEducation = new JLabel("Education:");
        empEducation.setBounds(50, 360, 100, 30);
        f.add(empEducation);

        empEducationText = new JTextField();
        empEducationText.setBounds(150, 360, 200, 30);
        f.add(empEducationText);

        empExtra = new JLabel("Extra:");
        empExtra.setBounds(50, 400, 100, 30);
        f.add(empExtra);

        empExtraText = new JTextField();
        empExtraText.setBounds(150, 400, 200, 30);
        f.add(empExtraText);

        update = new JButton("Update");
        update.setBounds(50, 480, 100, 30);
        update.setBackground(Color.BLACK);
        update.setForeground(Color.WHITE);
        update.addActionListener(this);
        f.add(update);

        close = new JButton("Close");
        close.setBounds(200, 480, 100, 30);
        close.setBackground(Color.RED);
        close.setForeground(Color.BLACK);
        close.addActionListener(this);
        f.add(close);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == search) {
            searchEmployee();
        } else if (e.getSource() == update) {
            updateEmployee();
        } else if (e.getSource() == close) {
            f.setVisible(false);
        }
    }

    private void searchEmployee() {
        String empId = empIdText.getText().trim();
        if (empId.isEmpty()) {
            JOptionPane.showMessageDialog(f, "Please enter an Employee ID.");
            return;
        }

        try {
            ConnectionClass obj = new ConnectionClass();
            String query = "SELECT * FROM employees WHERE emp_id = ?";
            PreparedStatement pst = obj.con.prepareStatement(query);
            pst.setString(1, empId);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                empNameText.setText(rs.getString("name"));
                empAgeText.setText(String.valueOf(rs.getInt("age")));
                empAddressText.setText(rs.getString("address"));
                empEmailText.setText(rs.getString("email"));
                empJobText.setText(rs.getString("job_post"));
                empDobText.setText(rs.getString("dob"));
                empPhoneText.setText(rs.getString("phone"));
                empEducationText.setText(rs.getString("education"));
                empExtraText.setText(rs.getString("extra"));
            } else {
                JOptionPane.showMessageDialog(f, "Employee Not Found!");
            }

            rs.close();
            pst.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(f, "Database Error: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    private void updateEmployee() {
        String empId = empIdText.getText().trim();
        if (empId.isEmpty()) {
            JOptionPane.showMessageDialog(f, "Please enter Employee ID.");
            return;
        }

        try {
            ConnectionClass obj = new ConnectionClass();
            String query = "UPDATE employees SET name=?, age=?, address=?, email=?, job_post=?, dob=?, phone=?, education=?, extra=? WHERE emp_id=?";
            PreparedStatement pst = obj.con.prepareStatement(query);

            pst.setString(1, empNameText.getText().trim());
            pst.setInt(2, Integer.parseInt(empAgeText.getText().trim()));
            pst.setString(3, empAddressText.getText().trim());
            pst.setString(4, empEmailText.getText().trim());
            pst.setString(5, empJobText.getText().trim());
            pst.setString(6, empDobText.getText().trim());
            pst.setString(7, empPhoneText.getText().trim());
            pst.setString(8, empEducationText.getText().trim());
            pst.setString(9, empExtraText.getText().trim());
            pst.setString(10, empId);

            int updated = pst.executeUpdate();
            if (updated > 0) {
                JOptionPane.showMessageDialog(f, "Employee details updated successfully.");
            } else {
                JOptionPane.showMessageDialog(f, "Update failed.");
            }

            pst.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(f, "Error updating employee: " + ex.getMessage());
            ex.printStackTrace();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(f, "Invalid input.");
        }
    }

    public static void main(String args[]) {
        new ManageEmployeePage();
    }
}
