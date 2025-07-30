package Employee_Management;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class AddProfilePage extends JFrame implements ActionListener {
    JTextField t1, t2, t3, t4, t5, t6, t7, t8, t9, t10;
    JButton b1, b2;

    AddProfilePage() {
        setTitle("New Employee Details");
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);

        JLabel l1 = new JLabel("Employee ID:");
        l1.setBounds(50, 20, 100, 30);
        add(l1);
        t1 = new JTextField();
        t1.setBounds(160, 20, 150, 30);
        add(t1);

        JLabel l2 = new JLabel("Job Post:");
        l2.setBounds(50, 60, 100, 30);
        add(l2);
        t2 = new JTextField();
        t2.setBounds(160, 60, 150, 30);
        add(t2);

        JLabel l3 = new JLabel("Email:");
        l3.setBounds(50, 100, 100, 30);
        add(l3);
        t3 = new JTextField();
        t3.setBounds(160, 100, 150, 30);
        add(t3);

        JLabel l4 = new JLabel("Address:");
        l4.setBounds(50, 140, 100, 30);
        add(l4);
        t4 = new JTextField();
        t4.setBounds(160, 140, 150, 30);
        add(t4);

        JLabel l5 = new JLabel("Age:");
        l5.setBounds(50, 180, 100, 30);
        add(l5);
        t5 = new JTextField();
        t5.setBounds(160, 180, 150, 30);
        add(t5);
        JLabel l6 = new JLabel("Name:");
        l6.setBounds(50, 220, 100, 30);
        add(l6);
        t6 = new JTextField();
        t6.setBounds(160, 220, 150, 30);
        add(t6);
        JLabel l7 = new JLabel("DOB:");
        l7.setBounds(50, 260, 100, 30);
        add(l7);
        t7 = new JTextField();
        t7.setBounds(160, 260, 150, 30);
        add(t7);
        JLabel l8 = new JLabel("Phone:");
        l8.setBounds(50, 300, 100, 30);
        add(l8);
        t8 = new JTextField();
        t8.setBounds(160, 300, 150, 30);
        add(t8);
        JLabel l9 = new JLabel("Education:");
        l9.setBounds(50, 340, 100, 30);
        add(l9);
        t9 = new JTextField();
        t9.setBounds(160, 340, 150, 30);
        add(t9);
        JLabel l10 = new JLabel("Extra:");
        l10.setBounds(50, 380, 100, 30);
        add(l10);
        t10 = new JTextField();
        t10.setBounds(160, 380, 150, 30);
        add(t10);

        b1 = new JButton("Submit");
        b1.setBounds(50, 430, 120, 40);
        b1.setBackground(Color.BLACK);
        b1.setForeground(Color.WHITE);
        b1.addActionListener(this);
        add(b1);

        b2 = new JButton("Cancel");
        b2.setBounds(200, 430, 120, 40);
        b2.setBackground(Color.RED);
        b2.setForeground(Color.BLACK);
        b2.addActionListener(this);
        add(b2);
        setSize(400, 550);
        setLocation(450, 150);
        setVisible(true);
    }
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b1) {
            saveToDatabase();
            dispose();
        } else if (e.getSource() == b2) {
            dispose();
        }
    }
    private void saveToDatabase() {
        try {
            ConnectionClass obj = new ConnectionClass();
            String query = "INSERT INTO employees (emp_id, job_post, email, address, age, name, dob, phone, education, extra)"
                           + " VALUES ('" 
                           + t1.getText() + "', '" + t2.getText() + "', '" + t3.getText() + "', '" + t4.getText() + "', '" 
                           + t5.getText() + "', '" + t6.getText() + "', '" + t7.getText() + "', '" + t8.getText() + "', '" 
                           + t9.getText() + "', '" + t10.getText() + "')";
            obj.stm.executeUpdate(query);
            JOptionPane.showMessageDialog(this, "Employee Added Successfully!");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    public static void main(String[] args) {
        new AddProfilePage();
    }
}
