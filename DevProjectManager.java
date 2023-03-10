package Project2_GUI_v2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class DevProjectManager implements ActionListener {
    static String username;
    private JFrame frame = new JFrame();
    private JPanel listPanel = new JPanel();
    private JPanel projectInfoPanel = new JPanel();
    private JPanel commentPanel = new JPanel();
    private JPanel headPanel = new JPanel();
    private JButton sendCommButton = new JButton("Enter");
    private JTextArea commentArea = new JTextArea();
    private JLabel commentLabel = new JLabel("Comment");
    private JLabel projectInfoLabel = new JLabel();
    private JLabel projectNameLabel = new JLabel();
    private JLabel developersLabel = new JLabel();
    private JComboBox sortComboBox = new JComboBox();
    private JMenuBar menuBar = new JMenuBar();
    private JMenu editMenu = new JMenu("Edit");
    private JMenu profileMenu = new JMenu("Profile");
    private JMenuItem viewProfile = new JMenuItem("View Profile");
    private JMenuItem editProfile = new JMenuItem("Edit Profile");
    private JScrollPane scrollPane = new JScrollPane(commentArea);

    Timer timer;

    public DevProjectManager(ArrayList<Project> projects) {
        projects = Project.projects;

        frame.setLayout(new BorderLayout());
        frame.setTitle("Test");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 700);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        viewProfile.addActionListener(this);
        editProfile.addActionListener(this);

        menuBar.add(profileMenu);
        profileMenu.add(viewProfile);
        profileMenu.add(editProfile);

        sendCommButton.setBackground(new Color(0xFF1A1926, true));
        sendCommButton.setForeground(new Color(0xA7A9BE));
        sendCommButton.addActionListener(this);
        sendCommButton.setFocusable(false);

        projectInfoLabel.setFont(new Font(null, Font.PLAIN, 20));
        projectInfoLabel.setForeground(new Color(0xA7A9BE));
        projectNameLabel.setForeground(Color.BLACK);
        projectNameLabel.setFont(new Font(null, Font.PLAIN, 24));
        developersLabel.setForeground(Color.BLACK);
        developersLabel.setFont(new Font(null, Font.PLAIN, 13));
        sendCommButton.setPreferredSize(new Dimension(0, 30));
        listPanel.setPreferredSize(new Dimension(150, 0));
        projectInfoPanel.setBackground(new Color(0xFF1A1926, true));
        listPanel.setLayout(new FlowLayout());
        projectInfoPanel.setLayout(new BorderLayout());
        listPanel.setBackground(new Color(0xFF0F0E17, true));
        projectInfoPanel.add(projectInfoLabel);
        commentPanel.setBackground(new Color(0xE45858));
        commentPanel.setLayout(new BorderLayout());
        commentPanel.add(sendCommButton, BorderLayout.SOUTH);
        commentPanel.add(commentLabel, BorderLayout.NORTH);
        commentPanel.add(commentArea, BorderLayout.CENTER);
        commentLabel.setPreferredSize(new Dimension(0, 70));
        commentPanel.setPreferredSize(new Dimension(0, 150));
        headPanel.setLayout(new BorderLayout());
        headPanel.setBackground(new Color(0xFFF25F4C, true));
        headPanel.add(projectNameLabel, BorderLayout.CENTER);
        headPanel.add(developersLabel, BorderLayout.EAST);
        headPanel.setPreferredSize(new Dimension(0, 60));

        projectInfoPanel.add(commentPanel, BorderLayout.SOUTH);
        projectInfoPanel.add(headPanel, BorderLayout.NORTH);

        frame.getContentPane().add(scrollPane);
        frame.setJMenuBar(menuBar);
        frame.add(listPanel, BorderLayout.WEST);
        frame.add(projectInfoPanel, BorderLayout.CENTER);

        //ADD PROJECTS FOR EACH DEVELOPER
        for (Project pr : Project.projects) {

            if (pr.getSecondDeveloper() != null) {

                if (pr.getDeveloper().getId().equals(username) || pr.getSecondDeveloper().getId().equals(username)) {

                    JButton prButton = new JButton(pr.getTitle());
                    prButton.setForeground(new Color(0xFFFFFE));
                    prButton.setBackground(new Color(0xFF8906));
                    prButton.setFocusable(false);
                    listPanel.add(prButton);
                    prButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {

                            //OPEN THE PROJECT
                            if (e.getSource() == prButton) {
                                userInteraction();
                                try {
                                    FileWriter fileWriter = new FileWriter("log.txt", true);
                                    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                                    bufferedWriter.write("Project " + pr.getTitle() + " was opened\n");
                                    bufferedWriter.close();
                                } catch (IOException v) {
                                    System.out.println("Error: " + v);
                                }
                                JPanel startEndPanel = new JPanel();
                                JButton startProject = new JButton("Start");
                                JButton endProject = new JButton("End");
                                headPanel.add(startEndPanel, BorderLayout.WEST);
                                startEndPanel.add(startProject);
                                startEndPanel.add(endProject);
                                startEndPanel.setBackground(new Color(0xFFF25F4C, true));
                                startProject.setBackground(new Color(0xFF1A1926));
                                startProject.setForeground(new Color(0xA7A9BE));
                                endProject.setBackground(new Color(0xFF1A1926));
                                endProject.setForeground(new Color(0xA7A9BE));
                                startProject.setFocusable(false);
                                endProject.setFocusable(false);
                                projectNameLabel.setText(pr.getTitle());
                                projectInfoLabel.setText(pr.getInfo());
                                if (pr.getSecondDeveloper() == null) {

                                    developersLabel.setText("<html>" + pr.getDeveloper().getName() + " ( " + pr.getDeveloper().getId() + " )      " + "</html>");

                                } else {
                                    developersLabel.setText("<html>" + pr.getDeveloper().getName() + " ( " + pr.getDeveloper().getId() + " )      " + "<br>" +
                                            pr.getSecondDeveloper().getName() + " ( " + pr.getSecondDeveloper().getId() + " ) " + "</html>");
                                }
                                startProject.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent v) {
                                        if (v.getSource() == startProject) {
                                            userInteraction();

                                            if (pr.getStatus().equals("Planned")) {

                                                try {
                                                    FileWriter fileWriter = new FileWriter("log.txt", true);
                                                    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                                                    bufferedWriter.write("Project " + pr.getTitle() + " was started\n");
                                                    bufferedWriter.close();
                                                } catch (IOException t) {
                                                    System.out.println("Error: " + t);
                                                }
                                                pr.setStatus("Active");
                                                String message = "Project " + pr.getTitle() + " is started!";
                                                JOptionPane.showMessageDialog(null, message, "Start", JOptionPane.PLAIN_MESSAGE);

                                            } else {

                                                try {
                                                    FileWriter fileWriter = new FileWriter("log.txt", true);
                                                    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                                                    bufferedWriter.write("Project " + pr.getTitle() + " start Error\n");
                                                    bufferedWriter.close();
                                                } catch (IOException t) {
                                                    System.out.println("Error: " + t);
                                                }
                                                String error = "Project " + pr.getTitle() + " is already active or complened!";
                                                JOptionPane.showMessageDialog(null, error, "Error", JOptionPane.PLAIN_MESSAGE);
                                            }
                                        }
                                    }
                                });
                                endProject.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent u) {

                                        if (u.getSource() == endProject) {
                                            userInteraction();

                                            if (pr.getStatus().equals("Active")) {
                                                try {
                                                    FileWriter fileWriter = new FileWriter("log.txt", true);
                                                    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                                                    bufferedWriter.write("Project " + pr.getTitle() + " was finished\n");
                                                    bufferedWriter.close();
                                                } catch (IOException v) {
                                                    System.out.println("Error: " + v);
                                                }
                                                pr.setStatus("Completed");
                                                String message = "Project " + pr.getTitle() + " is completed!";
                                                JOptionPane.showMessageDialog(null, message, "Complete", JOptionPane.PLAIN_MESSAGE);

                                            } else {
                                                try {
                                                    FileWriter fileWriter = new FileWriter("log.txt", true);
                                                    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                                                    bufferedWriter.write("Project " + pr.getTitle() + " finish Error\n");
                                                    bufferedWriter.close();
                                                } catch (IOException v) {
                                                    System.out.println("Error: " + v);
                                                }
                                                String error = "Project " + pr.getTitle() + " is not active or already completed!";
                                                JOptionPane.showMessageDialog(null, error, "Error", JOptionPane.PLAIN_MESSAGE);

                                            }
                                        }
                                    }
                                });
                            }
                        }
                    });
                }

            } else {

                if (pr.getDeveloper().getId().equals(username)) {
                    JButton prButton = new JButton(pr.getTitle());
                    prButton.setForeground(new Color(0xFFFFFE));
                    prButton.setBackground(new Color(0xFF8906));
                    prButton.setFocusable(false);
                    listPanel.add(prButton);
                    prButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            //OPEN THE PROJECT
                            if (e.getSource() == prButton) {
                                userInteraction();

                                try {
                                    FileWriter fileWriter = new FileWriter("log.txt", true);
                                    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                                    bufferedWriter.write("Project " + pr.getTitle() + " was opened\n");
                                    bufferedWriter.close();
                                } catch (IOException v) {
                                    System.out.println("Error: " + v);
                                }
                                JPanel startEndPanel = new JPanel();
                                JButton startProject = new JButton("Start");
                                JButton endProject = new JButton("End");
                                headPanel.add(startEndPanel, BorderLayout.WEST);
                                startEndPanel.add(startProject);
                                startEndPanel.add(endProject);
                                startEndPanel.setBackground(new Color(0xFFF25F4C, true));
                                startProject.setBackground(new Color(0xFF1A1926));
                                startProject.setForeground(new Color(0xA7A9BE));
                                endProject.setBackground(new Color(0xFF1A1926));
                                endProject.setForeground(new Color(0xA7A9BE));
                                startProject.setFocusable(false);
                                endProject.setFocusable(false);
                                projectNameLabel.setText(pr.getTitle());
                                projectInfoLabel.setText(pr.getInfo());
                                if (pr.getSecondDeveloper() == null) {

                                    developersLabel.setText("<html>" + pr.getDeveloper().getName() + " ( " + pr.getDeveloper().getId() + " )      " + "</html>");

                                } else {
                                    developersLabel.setText("<html>" + pr.getDeveloper().getName() + " ( " + pr.getDeveloper().getId() + " )      " + "<br>" +
                                            pr.getSecondDeveloper().getName() + " ( " + pr.getSecondDeveloper().getId() + " ) " + "</html>");
                                }
                                startProject.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent v) {
                                        if (v.getSource() == startProject) {
                                            userInteraction();

                                            if (pr.getStatus().equals("Planned")) {
                                                try {
                                                    FileWriter fileWriter = new FileWriter("log.txt", true);
                                                    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                                                    bufferedWriter.write("Project " + pr.getTitle() + " was started\n");
                                                    bufferedWriter.close();
                                                } catch (IOException t) {
                                                    System.out.println("Error: " + t);
                                                }
                                                pr.setStatus("Active");
                                                String message = "Project " + pr.getTitle() + " is started!";
                                                JOptionPane.showMessageDialog(null, message, "Start", JOptionPane.PLAIN_MESSAGE);

                                            } else {
                                                try {
                                                    FileWriter fileWriter = new FileWriter("log.txt", true);
                                                    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                                                    bufferedWriter.write("Project " + pr.getTitle() + " start Error\n");
                                                    bufferedWriter.close();
                                                } catch (IOException t) {
                                                    System.out.println("Error: " + t);
                                                }
                                                String error = "Project " + pr.getTitle() + " is already active or complened!";
                                                JOptionPane.showMessageDialog(null, error, "Error", JOptionPane.PLAIN_MESSAGE);
                                            }
                                        }
                                    }
                                });
                                endProject.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent u) {
                                        if (u.getSource() == endProject) {
                                            userInteraction();

                                            if (pr.getStatus().equals("Active")) {
                                                try {
                                                    FileWriter fileWriter = new FileWriter("log.txt", true);
                                                    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                                                    bufferedWriter.write("Project " + pr.getTitle() + " was finished\n");
                                                    bufferedWriter.close();
                                                } catch (IOException v) {
                                                    System.out.println("Error: " + v);
                                                }
                                                pr.setStatus("Completed");
                                                String message = "Project " + pr.getTitle() + " is completed!";
                                                JOptionPane.showMessageDialog(null, message, "Complete", JOptionPane.PLAIN_MESSAGE);

                                            } else {
                                                try {
                                                    FileWriter fileWriter = new FileWriter("log.txt", true);
                                                    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                                                    bufferedWriter.write("Project " + pr.getTitle() + " finish Error\n");
                                                    bufferedWriter.close();
                                                } catch (IOException v) {
                                                    System.out.println("Error: " + v);
                                                }
                                                String error = "Project " + pr.getTitle() + " is not active or already completed!";
                                                JOptionPane.showMessageDialog(null, error, "Error", JOptionPane.PLAIN_MESSAGE);

                                            }
                                        }
                                    }
                                });
                            }
                        }
                    });

                }
            }
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        //COMMENT (NOT WORKING):(
        if (e.getSource() == sendCommButton) {
            userInteraction();
            try {
                FileWriter fileWriter = new FileWriter("log.txt", true);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                bufferedWriter.write("Comment added\n");
                bufferedWriter.close();
            } catch (IOException t) {
                System.out.println("Error: " + t);
            }
            commentLabel.setText("<html>" + "<br>" + commentArea.getText() + "</html>");
        }

        //VIEW PROFILE
        if (e.getSource() == viewProfile) {
            userInteraction();
            SwingUtilities.invokeLater(
                    () -> {
                        ProfileView profileView = new ProfileView("d");
                    }
            );
            try {
                FileWriter fileWriter = new FileWriter("log.txt", true);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                bufferedWriter.write("Profile review was opened\n");
                bufferedWriter.close();
            } catch (IOException v) {
                System.out.println("Error: " + v);
            }
        }

        //EDIT PROFILE
        if (e.getSource() == editProfile) {
            userInteraction();
            SwingUtilities.invokeLater(
                    () -> {
                        ProfileEdit profileEdit = new ProfileEdit("d");
                    }
            );
            try {
                FileWriter fileWriter = new FileWriter("log.txt", true);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                bufferedWriter.write("Profile editor was opened\n");
                bufferedWriter.close();
            } catch (IOException v) {
                System.out.println("Error: " + v);
            }
        }
    }


    //LOGOUT SYSTEM
    public void logout() {
        frame.dispose();
        LoginPage loginPage = new LoginPage(DeveloperDataBase.developerLoginData, ManagerDataBase.managerLoginData);
        stopTimer();
        try {
            File file = new File("log.txt");
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fileWriter = new FileWriter("log.txt", true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write("User was logging out\n");
            bufferedWriter.close();
        } catch (IOException e) {
            System.out.println("Error: " + e);
        }
    }

    public void session() {
        stopTimer();
        timer = new javax.swing.Timer(60000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                logout();
            }
        });
        timer.start();
    }

    public void stopTimer() {
        if (timer != null) {
            timer.stop();
        }
    }

    public void userInteraction() {
        session();
    }

}

