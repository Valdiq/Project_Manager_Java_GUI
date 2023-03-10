package Project2_GUI_v2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class MgrProjectManager implements ActionListener {
    private JFrame frame = new JFrame();
    private JPanel listPanel = new JPanel();
    private JPanel projectInfoPanel = new JPanel();
    private JPanel commentPanel = new JPanel();
    private JPanel headPanel = new JPanel();
    private JButton sendCommButton = new JButton("Enter");
    private JTextArea commentArea = new JTextArea();
    private JLabel commentLabel = new JLabel("Comment");
    private JLabel projectInfoLabel = new JLabel();
    static JLabel projectNameLabel = new JLabel();
    private JLabel developersLabel = new JLabel();
    private JComboBox sortComboBox = new JComboBox();

    private JMenuBar menuBar = new JMenuBar();
    private JMenu editMenu = new JMenu("Edit");
    private JMenu profileMenu = new JMenu("Profile");
    private JMenu sortBy = new JMenu("Sort by");
    private JMenu editProject = new JMenu("Edit Project");
    private JMenuItem addProject = new JMenuItem("Add project");
    private JMenuItem addDeveloper = new JMenuItem("Add Developer");
    private JMenuItem removeDeveloper = new JMenuItem("Remove Developer");
    private JMenuItem viewProfile = new JMenuItem("View Profile");
    private JMenuItem editProfile = new JMenuItem("Edit Profile");
    private JMenuItem sortByPlanned = new JMenuItem("Planned");
    private JMenuItem sortByActive = new JMenuItem("Active");
    private JMenuItem sortByCompleted = new JMenuItem("Completed");


    Timer timer;


    public MgrProjectManager(ArrayList<Project> projects) {
        projects = Project.projects;

        frame.setLayout(new BorderLayout());
        frame.setTitle("Test");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 700);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);


        menuBar.add(editMenu);
        menuBar.add(profileMenu);
        editProject.add(addDeveloper);
        editProject.add(removeDeveloper);
        editMenu.add(sortBy);
        editMenu.add(addProject);
        editMenu.add(editProject);
        profileMenu.add(viewProfile);
        profileMenu.add(editProfile);
        sortBy.add(sortByPlanned);
        sortBy.add(sortByActive);
        sortBy.add(sortByCompleted);

        addDeveloper.addActionListener(this);
        removeDeveloper.addActionListener(this);

        sendCommButton.addActionListener(this);
        sendCommButton.setBackground(new Color(0xFF1A1926, true));
        sendCommButton.setForeground(new Color(0xA7A9BE));
        sendCommButton.setFocusable(false);
        addProject.addActionListener(this);
        viewProfile.addActionListener(this);
        editProfile.addActionListener(this);
        sortByPlanned.addActionListener(this);
        sortByActive.addActionListener(this);
        sortByCompleted.addActionListener(this);
        projectInfoLabel.setFont(new Font(null, Font.PLAIN, 20));
        projectInfoLabel.setForeground(new Color(0xA7A9BE));
        projectNameLabel.setForeground(Color.BLACK);
        projectNameLabel.setFont(new Font(null, Font.PLAIN, 24));
        developersLabel.setForeground(Color.BLACK);
        sendCommButton.setPreferredSize(new Dimension(0, 30));
        listPanel.setPreferredSize(new Dimension(150, 0));
        listPanel.setBackground(Color.red);
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

        projectNameLabel.setBounds(350, 30, 300, 100);
        headPanel.setLayout(new BorderLayout());
        headPanel.setBackground(new Color(0xFFF25F4C, true));
        headPanel.add(projectNameLabel, BorderLayout.CENTER);
        headPanel.add(developersLabel, BorderLayout.EAST);
        headPanel.setPreferredSize(new Dimension(0, 60));


        projectInfoPanel.add(commentPanel, BorderLayout.SOUTH);
        projectInfoPanel.add(headPanel, BorderLayout.NORTH);

        frame.setJMenuBar(menuBar);
        frame.add(listPanel, BorderLayout.WEST);
        frame.add(projectInfoPanel, BorderLayout.CENTER);


        //ADD ALL PROJECTS
        for (Project pr : Project.projects) {

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
                        projectNameLabel.setText(pr.getTitle());
                        projectInfoLabel.setText(pr.getInfo());
                        if (pr.getSecondDeveloper() == null) {
                            developersLabel.setText("<html>" + pr.getDeveloper().getName() + " ( " + pr.getDeveloper().getId() + " )      " + "</html>");
                        } else {
                            developersLabel.setText("<html>" + pr.getDeveloper().getName() + " ( " + pr.getDeveloper().getId() + " )      " + "<br>" +
                                    pr.getSecondDeveloper().getName() + " ( " + pr.getSecondDeveloper().getId() + " ) " + "</html>");
                        }
                    }
                }
            });
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {

        //ADD PROJECT
        if (e.getSource() == addProject) {
            userInteraction();
            SwingUtilities.invokeLater(
                    () -> {
                        AddProjectForm addProjectForm = new AddProjectForm();
                    }
            );
        }

        //SORT
        if (e.getSource() == sortByPlanned) {
            userInteraction();
            listPanel.removeAll();
            for (Project pr : Project.projects) {
                if (pr.getStatus().equals("Planned")) {
                }
            }
            try {
                FileWriter fileWriter = new FileWriter("log.txt", true);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                bufferedWriter.write("Projects were sorted by 'Planed'\n");
                bufferedWriter.close();
            } catch (IOException v) {
                System.out.println("Error: " + v);
            }
        }

        if (e.getSource() == sortByActive) {
            userInteraction();
            for (Project pr : Project.projects) {
                if (!pr.getStatus().equals("Active")) {
                }
            }
            try {
                FileWriter fileWriter = new FileWriter("log.txt", true);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                bufferedWriter.write("Projects were sorted by 'Active'\n");
                bufferedWriter.close();
            } catch (IOException v) {
                System.out.println("Error: " + v);
            }
        }

        if (e.getSource() == sortByCompleted) {
            userInteraction();
            for (Project pr : Project.projects) {
                if (!pr.getStatus().equals("Completed")) {
                }
            }
            try {
                FileWriter fileWriter = new FileWriter("log.txt", true);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                bufferedWriter.write("Projects were sorted by 'Completed'\n");
                bufferedWriter.close();
            } catch (IOException v) {
                System.out.println("Error: " + v);
            }
        }

        //ADD DEVELOPER
        if (e.getSource() == addDeveloper) {
            for (Project pr : Project.projects) {
                if (pr.getTitle().equals(projectNameLabel.getText())) {
                    if (pr.getSecondDeveloper() == null) {
                        SwingUtilities.invokeLater(
                                () -> {
                                    EditProject editProject = new EditProject("add");
                                }
                        );
                    } else {
                        String message = "This Project already have 2 developers";
                        JOptionPane.showMessageDialog(null, message, "Add Developer Error", JOptionPane.PLAIN_MESSAGE);
                    }
                }
            }

        }

        //REMOVE DEVELOPER
        if (e.getSource() == removeDeveloper) {
            for (Project pr : Project.projects) {
                if (pr.getTitle().equals(projectNameLabel.getText())) {
                    if (pr.getDeveloper() != null && pr.getSecondDeveloper() != null) {
                        SwingUtilities.invokeLater(
                                () -> {
                                    EditProject editProject = new EditProject("remove");
                                }
                        );
                    } else {
                        String message = "This Project have only 1 developer";
                        JOptionPane.showMessageDialog(null, message, "Remove Developer Error", JOptionPane.PLAIN_MESSAGE);
                    }
                }
            }
        }

        //VIEW PROFILE
        if (e.getSource() == viewProfile) {
            userInteraction();
            SwingUtilities.invokeLater(
                    () -> {
                        ProfileView profileView = new ProfileView("m");
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
                        ProfileEdit profileEdit = new ProfileEdit("m");
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
