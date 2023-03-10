package Project2_GUI_v2;

import javax.swing.*;
import javax.swing.event.AncestorListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.HashMap;

public class LoginPage implements ActionListener {

    private JFrame frame = new JFrame();
    private JPanel buttonPanel = new JPanel();
    private JButton loginButton = new JButton("Login");
    private JTextField usernameFrield = new JTextField();
    private JPasswordField passwordField = new JPasswordField();
    private JLabel usernameLabel = new JLabel("Username: ");
    private JLabel passwordLabel = new JLabel("Password: ");
    private JPanel radioButtonPanel = new JPanel();
    private JRadioButton developerRadioButton = new JRadioButton("Developer");
    private JRadioButton managerRadioButton = new JRadioButton("Manager");
    private HashMap<String, String> developerInfo = new HashMap<String, String>();
    private HashMap<String, String> managerInfo = new HashMap<String, String>();

    public LoginPage(HashMap<String, String> developerLoginData, HashMap<String, String> managerLoginData) {
        developerInfo = developerLoginData;
        managerInfo = managerLoginData;

        usernameLabel.setBounds(50, 50, 100, 25);
        usernameFrield.setBounds(120, 50, 150, 25);
        passwordLabel.setBounds(50, 80, 75, 25);
        passwordField.setBounds(120, 80, 150, 25);
        radioButtonPanel.setBounds(40, 115, 250, 25);
        developerRadioButton.setFocusable(false);
        managerRadioButton.setFocusable(false);
        buttonPanel.setBounds(120, 160, 80, 30);
        loginButton.setFocusable(false);
        loginButton.addActionListener(this);


        ButtonGroup group = new ButtonGroup();
        group.add(developerRadioButton);
        group.add(managerRadioButton);

        frame.add(usernameLabel);
        frame.add(usernameFrield);
        frame.add(passwordLabel);
        frame.add(passwordField);

        radioButtonPanel.add(developerRadioButton);
        radioButtonPanel.add(managerRadioButton);
        frame.add(radioButtonPanel);

        buttonPanel.add(loginButton);
        frame.add(buttonPanel, BorderLayout.SOUTH);
        frame.setLayout(new BorderLayout());
        frame.setTitle("Login");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(350, 270);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            ProfileView.username = usernameFrield.getText(); // for profile view
            ProfileEdit.username = usernameFrield.getText(); // for profile edit
            DevProjectManager.username = usernameFrield.getText(); // for Developer Pr. Manager (list of project)

            String username = usernameFrield.getText();
            String password = String.valueOf(passwordField.getPassword());
            if (developerRadioButton.isSelected()) {
                if (developerInfo.containsKey(username)) {
                    if (developerInfo.get(username).equals(password)) {
                        try {
                            FileWriter fileWriter = new FileWriter("log.txt", true);
                            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                            bufferedWriter.write("Developer was succesuful logined\n");
                            bufferedWriter.close();
                        } catch (IOException v) {
                            System.out.println("Error: " + v);
                        }
                        DevProjectManager devProjectManager = new DevProjectManager(Project.projects);
                        frame.dispose();
                        System.out.println("Success");
                    } else {
                        try {
                            FileWriter fileWriter = new FileWriter("log.txt", true);
                            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                            bufferedWriter.write("Developer loging was failed\n");
                            bufferedWriter.close();
                        } catch (IOException v) {
                            System.out.println("Error: " + v);
                        }
                        String message = "Wrong password!";
                        JOptionPane.showMessageDialog(null, message, "Error!", JOptionPane.PLAIN_MESSAGE);
                    }
                } else {
                    try {
                        FileWriter fileWriter = new FileWriter("log.txt", true);
                        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                        bufferedWriter.write("Developer loging was failed\n");
                        bufferedWriter.close();
                    } catch (IOException v) {
                        System.out.println("Error: " + v);
                    }
                    String message = "Wrong username!";
                    JOptionPane.showMessageDialog(null, message, "Error!", JOptionPane.PLAIN_MESSAGE);
                }
            }

            if (managerRadioButton.isSelected()) {
                if (managerInfo.containsKey(username)) {
                    if (managerInfo.get(username).equals(password)) {
                        try {
                            FileWriter fileWriter = new FileWriter("log.txt", true);
                            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                            bufferedWriter.write("Manager was succesuful logined\n");
                            bufferedWriter.close();
                        } catch (IOException v) {
                            System.out.println("Error: " + v);
                        }
                        MgrProjectManager mgrProjectManager = new MgrProjectManager(Project.projects);
                        frame.dispose();
                    } else {
                        try {
                            FileWriter fileWriter = new FileWriter("log.txt", true);
                            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                            bufferedWriter.write("Manager loging was failed \n");
                            bufferedWriter.close();
                        } catch (IOException v) {
                            System.out.println("Error: " + v);
                        }
                        String message = "Wrong password!";
                        JOptionPane.showMessageDialog(null, message, "Error!", JOptionPane.PLAIN_MESSAGE);
                    }
                } else {
                    try {
                        FileWriter fileWriter = new FileWriter("log.txt", true);
                        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                        bufferedWriter.write("Manager loging was failed\n");
                        bufferedWriter.close();
                    } catch (IOException v) {
                        System.out.println("Error: " + v);
                    }
                    String message = "Wrong username!";
                    JOptionPane.showMessageDialog(null, message, "Error!", JOptionPane.PLAIN_MESSAGE);
                }
            }
        }
    }
}
