package Employee_Management;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class ViewProfilePage extends JFrame implements ActionListener {
    JTextField empIdField;
    JLabel nameLabel, ageLabel, addressLabel, emailLabel, jobLabel, dobLabel, phoneLabel, educationLabel, extraLabel;
    JButton searchButton, closeButton;

    ViewProfilePage() {
        setTitle("Employee Detail");
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);

        JLabel enterIdLabel = new JLabel("Enter ID:");
        enterIdLabel.setBounds(30, 20, 100, 30);
        add(enterIdLabel);

        empIdField = new JTextField();
        empIdField.setBounds(100, 20, 150, 30);
        add(empIdField);

        searchButton = new JButton("Search");
        searchButton.setBounds(270, 20, 100, 30);
        searchButton.addActionListener(this);
        add(searchButton);

        nameLabel = new JLabel("Name: ");
        nameLabel.setBounds(30, 60, 300, 30);
        add(nameLabel);

        ageLabel = new JLabel("Age: ");
        ageLabel.setBounds(30, 100, 300, 30);
        add(ageLabel);

        addressLabel = new JLabel("Address: ");
        addressLabel.setBounds(30, 140, 300, 30);
        add(addressLabel);

        emailLabel = new JLabel("Email: ");
        emailLabel.setBounds(30, 180, 300, 30);
        add(emailLabel);

        jobLabel = new JLabel("Job Post: ");
        jobLabel.setBounds(30, 220, 300, 30);
        add(jobLabel);

        dobLabel = new JLabel("DOB: ");
        dobLabel.setBounds(30, 260, 300, 30);
        add(dobLabel);

        phoneLabel = new JLabel("Phone: ");
        phoneLabel.setBounds(30, 300, 300, 30);
        add(phoneLabel);

        educationLabel = new JLabel("Education: ");
        educationLabel.setBounds(30, 340, 300, 30);
        add(educationLabel);

        extraLabel = new JLabel("Extra: ");
        extraLabel.setBounds(30, 380, 300, 30);
        add(extraLabel);

        closeButton = new JButton("Close");
        closeButton.setBounds(150, 430, 100, 30);
        closeButton.addActionListener(this);
        add(closeButton);

        setSize(400, 520);
        setLocation(450, 200);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == searchButton) {
            fetchEmployeeDetails();
        }else if(e.getSource() == closeButton){
            System.exit(0);
        }
    }

    private void fetchEmployeeDetails() {
        String empId = empIdField.getText();
        if (empId.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter Employee ID!");
            return;
        }

        try {
            ConnectionClass obj = new ConnectionClass();
            String query = "SELECT * FROM employees WHERE emp_id = '" + empId + "'";
            ResultSet rs = obj.stm.executeQuery(query);

            if (rs.next()) {
                nameLabel.setText("Name: " + rs.getString("name"));
                ageLabel.setText("Age: " + rs.getInt("age"));
                addressLabel.setText("Address: " + rs.getString("address"));
                emailLabel.setText("Email: " + rs.getString("email"));
                jobLabel.setText("Job Post: " + rs.getString("job_post"));
                dobLabel.setText("DOB: " + rs.getString("dob"));
                phoneLabel.setText("Phone: " + rs.getString("phone"));
                educationLabel.setText("Education: " + rs.getString("education"));
                extraLabel.setText("Extra: " + rs.getString("extra"));
            } else {
                JOptionPane.showMessageDialog(this, "Employee ID not found!");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new ViewProfilePage();
    }
}
