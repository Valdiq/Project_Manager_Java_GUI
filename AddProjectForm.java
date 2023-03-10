package Project2_GUI_v2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

public class AddProjectForm implements ActionListener {
    private String[] developers = {null, (String) Developer.developers.get(0).getId(), (String) Developer.developers.get(1).getId(), (String) Developer.developers.get(2).getId(), (String) Developer.developers.get(3).getId(), (String) Developer.developers.get(4).getId()};

    private JFrame frame = new JFrame("Create project");
    private JLabel projectTitle = new JLabel("Title");
    private JLabel projectInfo = new JLabel("Info");
    private JLabel projectDeveloper1 = new JLabel("Developer");
    private JLabel projectDeveloper2 = new JLabel("Developer");
    private JTextField projectTitleField = new JTextField();
    private JTextArea projectInfoArea = new JTextArea();
    private JComboBox<String> comboBoxDeveloper1 = new JComboBox<String>(developers);
    private JComboBox<String> comboBoxDeveloper2 = new JComboBox<String>(developers);
    private JButton projectAdd = new JButton("Add project");


    public AddProjectForm() {

        frame.add(projectTitle);
        projectTitle.setFont(new Font(null, Font.PLAIN, 16));
        frame.add(projectTitleField);
        frame.add(projectInfo);
        projectInfo.setFont(new Font(null, Font.PLAIN, 16));
        frame.add(projectInfoArea);
        frame.add(projectDeveloper1);
        projectDeveloper1.setFont(new Font(null, Font.PLAIN, 16));
        frame.add(comboBoxDeveloper1);
        frame.add(projectDeveloper2);
        projectDeveloper2.setFont(new Font(null, Font.PLAIN, 16));
        frame.add(comboBoxDeveloper2);
        frame.add(projectAdd);
        projectAdd.setSize(80, 30);
        projectAdd.addActionListener(this);


        projectAdd.setFocusable(false);
        frame.setLayout(new GridLayout(5, 2, 5, 5));
        frame.setSize(350, 300);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == projectAdd) {
            try {
                FileWriter fileWriter = new FileWriter("log.txt", true);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                bufferedWriter.write("Project form was opened\n");
                bufferedWriter.close();
            } catch (IOException v) {
                System.out.println("Error: " + v);
            }
            if (comboBoxDeveloper1.getSelectedItem() == (comboBoxDeveloper2.getSelectedItem())) {
                String message = "Choose different developers";
                JOptionPane.showMessageDialog(null, message, "Add Error", JOptionPane.PLAIN_MESSAGE);
            } else if (comboBoxDeveloper1.getSelectedItem() == null && comboBoxDeveloper2.getSelectedItem() == null) {
                String message = "Choose attlist one developer";
                JOptionPane.showMessageDialog(null, message, "Add Error", JOptionPane.PLAIN_MESSAGE);
            } else {
                Project.projects.add(new Project(projectTitleField.getText(), projectInfoArea.getText(), findDev(comboBoxDeveloper1), findDev(comboBoxDeveloper2)));
                frame.dispose();
                try {
                    FileWriter fileWriter = new FileWriter("log.txt", true);
                    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                    bufferedWriter.write("Project " + projectTitleField.getText() + " was added\n");
                    bufferedWriter.close();
                } catch (IOException v) {
                    System.out.println("Error: " + v);
                }
            }
            if (comboBoxDeveloper1.getSelectedItem() == null) {

                Project.projects.add(new Project(projectTitleField.getText(), projectInfoArea.getText(), null, findDev(comboBoxDeveloper2)));
                frame.dispose();
                try {
                    FileWriter fileWriter = new FileWriter("log.txt", true);
                    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                    bufferedWriter.write("Project " + projectTitleField.getText() + " was added\n");
                    bufferedWriter.close();
                } catch (IOException v) {
                    System.out.println("Error: " + v);
                }
            } else if (comboBoxDeveloper2.getSelectedItem() == null) {
                Project.projects.add(new Project(projectTitleField.getText(), projectInfoArea.getText(), findDev(comboBoxDeveloper1), null));
                frame.dispose();
                try {
                    FileWriter fileWriter = new FileWriter("log.txt", true);
                    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                    bufferedWriter.write("Project " + projectTitleField.getText() + " was added\n");
                    bufferedWriter.close();
                } catch (IOException v) {
                    System.out.println("Error: " + v);
                }
            }
        }
    }


    public static Developer findDev(JComboBox comboBox) {
        Developer developer = null;
        for (Developer dev : Developer.developers) {
            if (dev.getId().equals(comboBox.getSelectedItem())) {
                developer = dev;
            }
        }
        return developer;
    }

}
