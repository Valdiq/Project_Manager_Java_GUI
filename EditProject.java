package Project2_GUI_v2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class EditProject extends JFrame implements ActionListener {
    private String[] developers = findDevs("add");

    private JFrame frame = new JFrame();
    private JPanel selectDeveloperPanel = new JPanel();
    private JPanel buttonPanel = new JPanel();
    private JLabel devLabel = new JLabel();
    static JComboBox selectDeveloperBox;
    private JButton saveButton = new JButton("Save");


    String ver;

    public EditProject(String version) {
        this.ver = version;
        buttonPanel.setLayout(new BorderLayout());

        selectDeveloperPanel.add(devLabel);
        buttonPanel.add(saveButton, BorderLayout.CENTER);

        devLabel.setFont(new Font(null, Font.PLAIN, 15));

        saveButton.setFocusable(false);
        saveButton.addActionListener(this);


        if (version.equals("add")) {
            frame.setLayout(new BorderLayout());
            frame.setTitle("Add Developer to current project");
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setSize(370, 120);
            frame.setVisible(true);
            frame.setResizable(false);
            frame.setLocationRelativeTo(null);
            String[] developers = findDevs("add");
            selectDeveloperBox = new JComboBox(developers);
            selectDeveloperPanel.add(selectDeveloperBox);

            devLabel.setText("Select a Developer to add to project");
            frame.add(selectDeveloperPanel, BorderLayout.CENTER);
            frame.add(buttonPanel, BorderLayout.SOUTH);


        } else if (version.equals("remove")) {
            frame.setLayout(new BorderLayout());
            frame.setTitle("Remove Developer from current project");
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setSize(370, 120);
            frame.setVisible(true);
            frame.setResizable(false);
            frame.setLocationRelativeTo(null);
            String[] developers = findDevs("remove");
            selectDeveloperBox = new JComboBox(developers);
            selectDeveloperPanel.add(selectDeveloperBox);

            devLabel.setText("Select a Developer to remove from project");
            frame.add(selectDeveloperPanel, BorderLayout.CENTER);
            frame.add(buttonPanel, BorderLayout.SOUTH);

        }


    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == saveButton) {
            if (ver.equals("add")) {
                for (Project pr : Project.projects) {
                    if (pr.getTitle().equals(MgrProjectManager.projectNameLabel.getText())) {
                        pr.setSecondDeveloper(conectIDAndDevs());
                    }
                }
                try {
                    File file = new File("log.txt");
                    if (!file.exists()) {
                        file.createNewFile();
                    }
                    FileWriter fileWriter = new FileWriter("log.txt", true);
                    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                    bufferedWriter.write("Developer " + selectDeveloperBox.getSelectedItem() + " was added to project " + MgrProjectManager.projectNameLabel.getText() + '\n');
                    bufferedWriter.close();
                } catch (IOException v) {
                    System.out.println("Error: " + v);
                }
            } else if (ver.equals("remove")) {
                for (Project pr : Project.projects) {
                    if (pr.getTitle().equals(MgrProjectManager.projectNameLabel.getText())) {
                        if (selectDeveloperBox.getSelectedItem().equals(pr.getDeveloper().getId())) {
                            pr.setDeveloper(null);
                        } else if (selectDeveloperBox.getSelectedItem().equals(pr.getSecondDeveloper().getId())) {
                            pr.setSecondDeveloper(null);
                        }
                    }
                }
                try {
                    File file = new File("log.txt");
                    if (!file.exists()) {
                        file.createNewFile();
                    }
                    FileWriter fileWriter = new FileWriter("log.txt", true);
                    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                    bufferedWriter.write("Developer " + selectDeveloperBox.getSelectedItem() + " was removed from project " + MgrProjectManager.projectNameLabel.getText() + '\n');
                    bufferedWriter.close();
                } catch (IOException v) {
                    System.out.println("Error: " + v);
                }
            }
        }
        frame.dispose();
    }

    //CUSTOM FUNCTIONS
    static String[] findDevs(String vrs) {
        int countDev = 0;
        String[] devs = new String[Developer.developers.size()];
        if (vrs.equals("add")) {
            for (Project pr : Project.projects) {
                if (pr.getTitle().equals(MgrProjectManager.projectNameLabel.getText())) {
                    for (Developer dv : Developer.developers) {
                        if (dv != pr.getDeveloper()) {
                            countDev++;
                            if (countDev < devs.length) {
                                devs[countDev] = (String) dv.getId();
                            }
                        }
                    }
                }
            }
        } else if (vrs.equals("remove")) {
            for (Project pr : Project.projects) {
                if (pr.getTitle().equals(MgrProjectManager.projectNameLabel.getText())) {
                    devs[0] = (String) pr.getDeveloper().getId();
                    devs[1] = (String) pr.getSecondDeveloper().getId();
                }
            }
        }
        return devs;
    }

    static Developer conectIDAndDevs() {
        Developer dev = null;
        for (Developer dv : Developer.developers) {
            if (dv.getId().equals(selectDeveloperBox.getSelectedItem())) {
                dev = dv;
            }
        }
        return dev;
    }
}
