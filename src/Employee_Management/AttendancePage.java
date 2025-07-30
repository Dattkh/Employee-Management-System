package Employee_Management;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class AttendancePage extends JFrame implements ActionListener {
    JButton b1, b2;
    AttendancePage() {
        setTitle("Attendance Options");
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);

        b1 = new JButton("Take Attendance");
        b1.setBounds(50, 50, 150, 40);
        b1.setBackground(Color.BLACK);
        b1.setForeground(Color.WHITE);
        b1.addActionListener(this);
        add(b1);

        b2 = new JButton("Get Attendance List");
        b2.setBounds(250, 50, 150, 40);
        b2.setBackground(Color.BLACK);
        b2.setForeground(Color.WHITE);
        b2.addActionListener(this);
        add(b2);

        setSize(450, 200);
        setLocation(400, 250);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b1) {
            new TakeAttendancePage();
        } else if (e.getSource() == b2) {
            new GetAttendancePage();
        }
    }

    public static void main(String args[]) {
        new AttendancePage();
    }
}
