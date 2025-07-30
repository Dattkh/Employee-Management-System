package Employee_Management;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

public class GetLeaveListPage extends JFrame implements ActionListener {
    JTable table;
    JButton back;

    GetLeaveListPage() {
        setTitle("Leave List");
        setSize(800, 400);
        setLocation(300, 150);
        setLayout(new BorderLayout());

        String[] columnNames = {"Employee ID", "Name", "Leave Type", "From", "To", "Reason"};
        String[][] data = getLeaveData();

        table = new JTable(data, columnNames);
        JScrollPane sp = new JScrollPane(table);
        add(sp, BorderLayout.CENTER);

        back = new JButton("Back");
        back.setBackground(Color.RED);
        back.setForeground(Color.BLACK);
        back.addActionListener(this);
        add(back, BorderLayout.SOUTH);

        setVisible(true);
    }

    public String[][] getLeaveData() {
        String[][] data = new String[100][6]; // Max 100 rows
        try {
            ConnectionClass c = new ConnectionClass();
            String query = "SELECT * FROM leaveData";
            ResultSet rs = c.stm.executeQuery(query);

            int i = 0;
            while (rs.next()) {
                data[i][0] = rs.getString("emp_id");
                data[i][1] = rs.getString("emp_name");
                data[i][2] = rs.getString("leave_type");
                data[i][3] = rs.getString("leave_from");
                data[i][4] = rs.getString("leave_to");
                data[i][5] = rs.getString("reason");
                i++;
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error retrieving leave data.");
        }
        return data;
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == back) {
            this.setVisible(false);
        }
    }

    public static void main(String[] args) {
        new GetLeaveListPage();
    }
}
