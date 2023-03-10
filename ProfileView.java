package Project2_GUI_v2;

import javax.swing.*;
import java.awt.*;

public class ProfileView {
    static String username;

    private JFrame frame = new JFrame();
    private JLabel nameLabel = new JLabel("Name: ");
    private JLabel showNameLabel = new JLabel();
    private JLabel surnameLabel = new JLabel("Surname: ");
    private JLabel showSurnameLabel = new JLabel();
    private JLabel emailLabel = new JLabel("Email: ");
    private JLabel showEmailLabel = new JLabel();
    private JPanel mainPanel = new JPanel();
    private JPanel namePanel = new JPanel();
    private JPanel surnamePanel = new JPanel();
    private JPanel emailPanel = new JPanel();


    public ProfileView(String typeOfAccount) {
        if (typeOfAccount.equals("d")) {
            for (Developer dev : Developer.developers) {
                if (dev.getId().equals(username)) {
                    showNameLabel.setText((String) dev.getName());
                    showSurnameLabel.setText((String) dev.getSurname());
                    showEmailLabel.setText((String) dev.getEmail());
                }
            }
        } else if (typeOfAccount.equals("m")) {
            for (Manager mgr : Manager.managers) {
                if (mgr.getId().equals(username)) {
                    showNameLabel.setText((String) mgr.getName());
                    showSurnameLabel.setText((String) mgr.getSurname());
                    showEmailLabel.setText((String) mgr.getEmail());
                }
            }
        }

        frame.setLayout(new BorderLayout());
        frame.setTitle("Profile Review");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(300, 200);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);


        nameLabel.setFont(new Font(null, Font.PLAIN, 16));
        showNameLabel.setFont(new Font(null, Font.PLAIN, 16));
        surnameLabel.setFont(new Font(null, Font.PLAIN, 16));
        showSurnameLabel.setFont(new Font(null, Font.PLAIN, 16));
        emailLabel.setFont(new Font(null, Font.PLAIN, 16));
        showEmailLabel.setFont(new Font(null, Font.PLAIN, 16));
        nameLabel.setForeground(new Color(0xA7A9BE));
        showNameLabel.setForeground(new Color(0xA7A9BE));
        surnameLabel.setForeground(new Color(0xA7A9BE));
        showSurnameLabel.setForeground(new Color(0xA7A9BE));
        emailLabel.setForeground(new Color(0xA7A9BE));
        showEmailLabel.setForeground(new Color(0xA7A9BE));


        namePanel.setLayout(new FlowLayout());
        surnamePanel.setLayout(new FlowLayout());
        emailPanel.setLayout(new FlowLayout());
        namePanel.setBackground(new Color(0xFF1A1926));
        surnamePanel.setBackground(new Color(0xFF1A1926));
        emailPanel.setBackground(new Color(0xFF1A1926));
        namePanel.add(nameLabel);
        namePanel.add(showNameLabel);
        surnamePanel.add(surnameLabel);
        surnamePanel.add(showSurnameLabel);
        emailPanel.add(emailLabel);
        emailPanel.add(showEmailLabel);

        mainPanel.setLayout(new GridLayout(3, 1));
        mainPanel.setBackground(new Color(0xFF1A1926));
        mainPanel.add(namePanel);
        mainPanel.add(surnamePanel);
        mainPanel.add(emailPanel);


        frame.add(mainPanel, BorderLayout.CENTER);

    }
}
