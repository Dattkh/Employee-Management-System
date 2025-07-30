package Employee_Management;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class HomePage extends JFrame implements ActionListener {
    JButton b1, b2, b3, b4, b5, b6, b7;

    HomePage() {
        setTitle("Employee Homepage");
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);

        b1 = new JButton("Profile");
        b1.setBounds(50, 50, 150, 40);
        b1.setBackground(Color.BLACK);
        b1.setForeground(Color.WHITE);
        b1.addActionListener(this);
        add(b1);

        b2 = new JButton("Manage");
        b2.setBounds(50, 110, 150, 40);
        b2.setBackground(Color.BLACK);
        b2.setForeground(Color.WHITE);
        b2.addActionListener(this);
        add(b2);

        b3 = new JButton("Attendance");
        b3.setBounds(50, 170, 150, 40);
        b3.setBackground(Color.BLACK);
        b3.setForeground(Color.WHITE);
        b3.addActionListener(this);
        add(b3);

        b4 = new JButton("Leave");
        b4.setBounds(250, 50, 150, 40);
        b4.setBackground(Color.BLACK);
        b4.setForeground(Color.WHITE);
        b4.addActionListener(this);
        add(b4);

        b5 = new JButton("Salary");
        b5.setBounds(250, 110, 150, 40);
        b5.setBackground(Color.BLACK);
        b5.setForeground(Color.WHITE);
        b5.addActionListener(this);
        add(b5);

        b6 = new JButton("Delete");
        b6.setBounds(250, 170, 150, 40);
        b6.setBackground(Color.BLACK);
        b6.setForeground(Color.WHITE);
        b6.addActionListener(this);
        add(b6);

        b7 = new JButton("Exit");
        b7.setBounds(150, 230, 150, 40);
        b7.setBackground(Color.RED);
        b7.setForeground(Color.BLACK);
        b7.addActionListener(this);
        add(b7);

        setSize(450, 350);
        setLocation(400, 200);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

     public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b1) {
            new ProfilePage();
        }else if (e.getSource() == b2) { 
            System.out.println("Hello");
            new ManageEmployeePage(); 
        }else if (e.getSource() == b3) { 
             System.out.println("Hello");
            new AttendancePage(); 
        }else if (e.getSource() == b4){
            new LeavePage();
        }else if (e.getSource() == b5) {
            new SalaryPage();
        }else if (e.getSource() == b6) {
            new DeleteEmployeePage();
        }
        else if (e.getSource() == b7) {
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        new HomePage();
    }
}
