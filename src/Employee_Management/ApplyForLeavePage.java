package Employee_Management;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

public class ApplyForLeavePage extends JFrame implements ActionListener {
    JFrame f;
    JLabel empId, empName, leaveType, leaveFrom, leaveTo, reason;
    JComboBox<String> leaveTypeList;
    JTextField empIdText, empNameText, leaveFromText, leaveToText, reasonText;
    JButton apply, cancel;

    ApplyForLeavePage() {
        f = new JFrame("Apply For Leave");
        f.setLayout(null);
        f.setBackground(Color.WHITE);
        f.setSize(500, 575);
        f.setLocation(400, 150);
        f.setVisible(true);

        empId = new JLabel("Employee ID");
        empId.setBounds(50, 50, 100, 30);
        f.add(empId);

        empIdText = new JTextField();
        empIdText.setBounds(150, 50, 150, 30);
        f.add(empIdText);

        empName = new JLabel("Employee Name");
        empName.setBounds(50, 100, 100, 30);
        f.add(empName);

        empNameText = new JTextField();
        empNameText.setBounds(150, 100, 150, 30);
        f.add(empNameText);

        leaveType = new JLabel("Leave Type:");
        leaveType.setBounds(50, 150, 100, 30);
        f.add(leaveType);

        String[] leaveTypes = {"Casual Leave", "Sick Leave", "Maternity Leave", "Paternity Leave", "Compensatory Leave"};
        leaveTypeList = new JComboBox<>(leaveTypes);
        leaveTypeList.setBounds(150, 150, 150, 30);
        f.add(leaveTypeList);

        leaveFrom = new JLabel("Leave From");
        leaveFrom.setBounds(50, 200, 150, 30);
        f.add(leaveFrom);

        leaveFromText = new JTextField();
        leaveFromText.setBounds(150, 200, 150, 30);
        f.add(leaveFromText);

        leaveTo = new JLabel("Leave To");
        leaveTo.setBounds(50, 250, 150, 30);
        f.add(leaveTo);

        leaveToText = new JTextField();
        leaveToText.setBounds(150, 250, 150, 30);
        f.add(leaveToText);

        reason = new JLabel("Reason");
        reason.setBounds(50, 300, 100, 30);
        f.add(reason);

        reasonText = new JTextField();
        reasonText.setBounds(150, 300, 275, 100);
        f.add(reasonText);

        apply = new JButton("Apply");
        apply.setBounds(50, 450, 100, 30);
        apply.setBackground(Color.BLACK);
        apply.setForeground(Color.WHITE);
        apply.addActionListener(this);
        f.add(apply);

        cancel = new JButton("Cancel");
        cancel.setBounds(200, 450, 100, 30);
        cancel.setBackground(Color.RED);
        cancel.setForeground(Color.BLACK);
        cancel.addActionListener(this);
        f.add(cancel);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == apply) {
            String empID = empIdText.getText();
            String empName = empNameText.getText();
            String leaveType = (String) leaveTypeList.getSelectedItem();
            String leaveFrom = leaveFromText.getText();
            String leaveTo = leaveToText.getText();
            String reason = reasonText.getText();

            try {
                ConnectionClass c = new ConnectionClass();
                String query = "INSERT INTO leaveData (emp_id, emp_name, leave_type, leave_from, leave_to, reason) VALUES (?, ?, ?, ?, ?, ?)";
                PreparedStatement stmt = c.con.prepareStatement(query);
                stmt.setString(1, empID);
                stmt.setString(2, empName);
                stmt.setString(3, leaveType);
                stmt.setString(4, leaveFrom);
                stmt.setString(5, leaveTo);
                stmt.setString(6, reason);
                stmt.executeUpdate();
                
                JOptionPane.showMessageDialog(null, "Leave Applied Successfully!");
                f.setVisible(false);
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error while applying leave.");
            }
        } else if (e.getSource() == cancel) {
            f.setVisible(false);
        }
    }

    public static void main(String args[]) {
        new ApplyForLeavePage();
    }
}
