package Employee_Management;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class SalaryPage extends JFrame implements ActionListener {
    JButton btnPaySalary, btnSalaryHistory;
    SalaryPage() {
        setTitle("Salary Options");
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);

        btnPaySalary = new JButton("Pay Salary");
        btnPaySalary.setBounds(50, 50, 150, 40);
        btnPaySalary.setBackground(Color.BLACK);
        btnPaySalary.setForeground(Color.WHITE);
        btnPaySalary.addActionListener(this);
        add(btnPaySalary);

        btnSalaryHistory = new JButton("Salary History");
        btnSalaryHistory.setBounds(250, 50, 150, 40);
        btnSalaryHistory.setBackground(Color.BLACK);
        btnSalaryHistory.setForeground(Color.WHITE);
        btnSalaryHistory.addActionListener(this);
        add(btnSalaryHistory);

        setSize(450, 200);
        setLocation(400, 250);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnPaySalary) {
            new PaySalaryPage(); 
        } else if (e.getSource() == btnSalaryHistory) {
            new SalaryHistoryPage(); 
        }
    }

    public static void main(String args[]) {
        new SalaryPage();
    }
}