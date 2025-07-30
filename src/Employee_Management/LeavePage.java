package Employee_Management;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class LeavePage extends JFrame implements ActionListener {
    JButton b1, b2;
    LeavePage(){
        setTitle("Leave Options");
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);
        setSize(450, 200);
        setLocation(400, 250);
        setVisible(true);
        
        b1 = new JButton("Apply For Leave");
        b1.setBounds(50, 50, 150, 40);
        b1.setBackground(Color.BLACK);
        b1.setForeground(Color.WHITE);
        b1.addActionListener(this);
        add(b1);

        b2 = new JButton("Get Leave List");
        b2.setBounds(250, 50, 150, 40);
        b2.setBackground(Color.BLACK);;
        b2.setForeground(Color.WHITE);
        b2.addActionListener(this);
        add(b2);
    }
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == b1){
            new ApplyForLeavePage();
        }else if(e.getSource() == b2){
            new GetLeaveListPage();
        }
    }
    
    public static void main(String args[]) {
        new LeavePage();
    }
}
