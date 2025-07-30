package Employee_Management;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class GetAttendancePage extends JFrame implements ActionListener {

    JTextField txtEmpId;
    JButton btnGetAttendance, btnShowAll;
    JTable table;
    DefaultTableModel model;

    GetAttendancePage() {
        setTitle("Attendance List");
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);

        JLabel lblEmpId = new JLabel("Enter Employee ID:");
        lblEmpId.setBounds(20, 20, 150, 30);
        add(lblEmpId);

        txtEmpId = new JTextField();
        txtEmpId.setBounds(170, 20, 150, 30);
        add(txtEmpId);

        btnGetAttendance = new JButton("Get Attendance");
        btnGetAttendance.setBounds(20, 60, 150, 40);
        btnGetAttendance.setBackground(Color.BLACK);
        btnGetAttendance.setForeground(Color.WHITE);
        btnGetAttendance.addActionListener(this);
        add(btnGetAttendance);

        btnShowAll = new JButton("Show All");
        btnShowAll.setBounds(200, 60, 150, 40);
        btnShowAll.setBackground(Color.BLACK);
        btnShowAll.setForeground(Color.WHITE);
        btnShowAll.addActionListener(this);
        add(btnShowAll);

        String[] columns = {
            "Emp ID", "In Time", "Out Time", "Total Hours",
            "Salary", "Date"
        };
        model = new DefaultTableModel(columns, 0);
        table = new JTable(model);

        JScrollPane sp = new JScrollPane(table);
        sp.setBounds(20, 120, 850, 400);
        add(sp);

        setSize(900, 600);
        setLocation(300, 100);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnGetAttendance) {
            getAttendanceById();
        } else if (e.getSource() == btnShowAll) {
            getAllAttendance();
        }
    }

    public void getAttendanceById() {
        String emp_id = txtEmpId.getText();

        if (emp_id.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter Employee ID");
            return;
        }

        try {
            ConnectionClass obj = new ConnectionClass();
            String query = "SELECT * FROM attendance WHERE emp_id='" + emp_id + "'";
            ResultSet rs = obj.stm.executeQuery(query);

            model.setRowCount(0);

            boolean found = false;
            while (rs.next()) {
                found = true;
                displayAttendance(rs);
            }

            if (!found) {
                JOptionPane.showMessageDialog(null, "No Record Found for Employee ID: " + emp_id);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void getAllAttendance() {
        try {
            ConnectionClass obj = new ConnectionClass();
            String query = "SELECT * FROM attendance";
            ResultSet rs = obj.stm.executeQuery(query);

            model.setRowCount(0);

            while (rs.next()) {
                displayAttendance(rs);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void displayAttendance(ResultSet rs) {
        try {
            String emp_id = rs.getString("emp_id");
            String in_time = rs.getString("in_time");
            String out_time = rs.getString("out_time");
            double total_hours = rs.getDouble("total_hours");
            double salary = rs.getDouble("salary");
            Date date = rs.getDate("date");

            model.addRow(new Object[] {
                emp_id,
                in_time,
                out_time,
                total_hours,
                "₹" + salary,
                date
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new GetAttendancePage();
    }
}