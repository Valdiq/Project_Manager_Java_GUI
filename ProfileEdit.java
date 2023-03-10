package Project2_GUI_v2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class ProfileEdit implements ActionListener {
    static String username;

    private JFrame frame = new JFrame();
    private JPanel namePanel = new JPanel();
    private JPanel surnamePanel = new JPanel();
    private JPanel emailPanel = new JPanel();
    private JPanel passwordPanel = new JPanel();
    private JPanel buttonPanel = new JPanel();
    private JPanel centerPanel = new JPanel();
    private JLabel nameLabel = new JLabel("Name          ");
    private JLabel surnameLabel = new JLabel("Surname     ");
    private JLabel emailLabel = new JLabel("Email          ");
    private JLabel passwordLabel = new JLabel("Password   ");
    private JTextField nameField = new JTextField(15);
    private JTextField surnameField = new JTextField(15);
    private JTextField emailField = new JTextField(15);
    private JPasswordField passwordField = new JPasswordField(15);
    private JButton saveButton = new JButton("Save");

    String accountType;

    public ProfileEdit(String typeOfAccount) {
        this.accountType = typeOfAccount;

        if (typeOfAccount.equals("d")) {
            for (Developer dev : Developer.developers) {
                if (dev.getId().equals(username)) {
                    nameField.setText((String) dev.getName());
                    surnameField.setText((String) dev.getSurname());
                    emailField.setText((String) dev.getEmail());
                }
            }
        } else if (typeOfAccount.equals("m")) {
            for (Manager mgr : Manager.managers) {
                if (mgr.getId().equals(username)) {
                    nameField.setText((String) mgr.getName());
                    surnameField.setText((String) mgr.getSurname());
                    emailField.setText((String) mgr.getEmail());
                }
            }
        }

        frame.setLayout(new BorderLayout());
        frame.setTitle("Edit Profile");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400, 250);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);

        saveButton.setFocusable(false);
        saveButton.addActionListener(this);
        nameField.setFont(new Font(null, Font.PLAIN, 14));
        surnameField.setFont(new Font(null, Font.PLAIN, 14));
        emailField.setFont(new Font(null, Font.PLAIN, 14));
        passwordField.setFont(new Font(null, Font.PLAIN, 14));

        nameLabel.setFont(new Font(null, Font.PLAIN, 16));
        surnameLabel.setFont(new Font(null, Font.PLAIN, 16));
        emailLabel.setFont(new Font(null, Font.PLAIN, 16));
        passwordLabel.setFont(new Font(null, Font.PLAIN, 16));
        nameLabel.setForeground(new Color(0xA7A9BE));
        surnameLabel.setForeground(new Color(0xA7A9BE));
        emailLabel.setForeground(new Color(0xA7A9BE));
        passwordLabel.setForeground(new Color(0xA7A9BE));
        saveButton.setForeground(Color.white);
        saveButton.setBackground(new Color(0xFF8906));

        namePanel.setBackground(new Color(0xFF1A1926));
        surnamePanel.setBackground(new Color(0xFF1A1926));
        emailPanel.setBackground(new Color(0xFF1A1926));
        passwordPanel.setBackground(new Color(0xFF1A1926));
        buttonPanel.setBackground(new Color(0xFF1A1926));

        namePanel.add(nameLabel);
        namePanel.add(nameField);

        surnamePanel.add(surnameLabel);
        surnamePanel.add(surnameField);

        emailPanel.add(emailLabel);
        emailPanel.add(emailField);

        passwordPanel.add(passwordLabel);
        passwordPanel.add(passwordField);

        buttonPanel.add(saveButton);

        centerPanel.setLayout(new FlowLayout());
        centerPanel.add(namePanel);
        centerPanel.add(surnamePanel);
        centerPanel.add(emailPanel);
        centerPanel.add(passwordPanel);
        centerPanel.setBackground(new Color(0xFF1A1926));

        frame.add(centerPanel, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == saveButton) {
            if (accountType.equals("d")) {
                for (Developer dv : Developer.developers) {
                    if (dv.getId().equals(username)) {
                        dv.setName(nameField.getText());
                        dv.setSurname(surnameField.getText());
                        dv.setEmail(emailField.getText());
                        DeveloperDataBase.developerLoginData.replace((String) dv.getId(), String.valueOf(passwordField.getPassword()));
                        String message = "Changes saved!";
                        JOptionPane.showMessageDialog(null, message, "Changes", JOptionPane.PLAIN_MESSAGE);
                        frame.dispose();
                    }
                }
            } else if (accountType.equals("m")) {
                for (Manager mgr : Manager.managers) {
                    if (mgr.getId().equals(username)) {
                        mgr.setName(nameField.getText());
                        mgr.setSurname(surnameField.getText());
                        mgr.setEmail(emailField.getText());
                        ManagerDataBase.managerLoginData.replace((String) mgr.getId(), String.valueOf(passwordField.getPassword()));
                        String message = "Changes saved!";
                        JOptionPane.showMessageDialog(null, message, "Changes", JOptionPane.PLAIN_MESSAGE);
                        frame.dispose();
                    }
                }
            }
            try {
                FileWriter fileWriter = new FileWriter("log.txt", true);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                bufferedWriter.write("Changes was successful saved\n");
                bufferedWriter.close();
            } catch (IOException v) {
                System.out.println("Error: " + v);
            }
        }

    }
}
