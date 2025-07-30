package Employee_Management;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import javax.swing.*;

public class TakeAttendancePage extends JFrame implements ActionListener {
    JTextField txtEmpId, txtInTime, txtOutTime;
    JButton btnSave;

    TakeAttendancePage() {
        setTitle("Take Attendance");
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);

        JLabel lblEmpId = new JLabel("Employee ID:");
        lblEmpId.setBounds(20, 20, 100, 30);
        add(lblEmpId);

        txtEmpId = new JTextField();
        txtEmpId.setBounds(130, 20, 150, 30);
        add(txtEmpId);

        JLabel lblInTime = new JLabel("In Time (HH:mm):");
        lblInTime.setBounds(20, 60, 120, 30);
        add(lblInTime);

        txtInTime = new JTextField();
        txtInTime.setBounds(130, 60, 150, 30);
        add(txtInTime);

        JLabel lblOutTime = new JLabel("Out Time (HH:mm):");
        lblOutTime.setBounds(20, 100, 120, 30);
        add(lblOutTime);

        txtOutTime = new JTextField();
        txtOutTime.setBounds(130, 100, 150, 30);
        add(txtOutTime);

        btnSave = new JButton("Save Attendance");
        btnSave.setBounds(20, 160, 150, 40);
        btnSave.setBackground(Color.BLACK);
        btnSave.setForeground(Color.WHITE);
        btnSave.addActionListener(this);
        add(btnSave);

        setSize(330, 270);
        setLocation(400, 200);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnSave) {
            String emp_id = txtEmpId.getText();
            String inTime = txtInTime.getText();
            String outTime = txtOutTime.getText();

            try {
                ConnectionClass obj = new ConnectionClass();
                String checkQuery = "SELECT * FROM employees WHERE emp_id='" + emp_id + "'";
                ResultSet rs = obj.stm.executeQuery(checkQuery);
                if (!rs.next()) {
                    JOptionPane.showMessageDialog(null, "Employee ID does not exist.");
                    return;
                }

                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");
                LocalTime in = LocalTime.parse(inTime, dtf);
                LocalTime out = LocalTime.parse(outTime, dtf);

                long totalMinutes = ChronoUnit.MINUTES.between(in, out);
                double totalHours = totalMinutes / 60.0;
                double salary = totalHours * 500;

                String query = "INSERT INTO attendance (emp_id, in_time, out_time, total_hours, salary, date) " +
                        "VALUES ('" + emp_id + "', '" + inTime + "', '" + outTime + "', '" +
                        totalHours + "', '" + salary + "', CURRENT_DATE)";
                obj.stm.executeUpdate(query);

                JOptionPane.showMessageDialog(null, "Attendance Saved Successfully!");

                txtEmpId.setText("");
                txtInTime.setText("");
                txtOutTime.setText("");
                
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Something went wrong.");
            }
        }
    }

    public static void main(String[] args) {
        new TakeAttendancePage();
    }
}
