import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import java.awt.event.*;
import java.awt.image.*;
import java.awt.*;
import java.util.*;
import java.text.DecimalFormat;

public class Gui
{
    private static final DecimalFormat secondDecimal = new DecimalFormat("0.00");

    JFrame passwordFrame;
    JPanel passwordPanel;
    JLabel passwordLabel;
    JLabel passwordStatus;
    JPasswordField passwordTextField;

    JFrame frame;
    JTable studentInfoTable;
    JScrollPane scrollPane;
    String[] columnNames = {"OSIS", "Name", "Gender", "Grade", "Average"};
    String[] sus = {"?"};
    JLabel susLabel;
    ArrayList<Integer> allOSIS = new ArrayList<Integer>();
    DefaultTableModel model = new DefaultTableModel(columnNames, 0);
    JButton addStudent;
    JButton removeStudent;
    JButton editStudent;
    JCheckBox removeSelected;
    JLabel status;
    JComboBox sortTable;
    Room selectedClassroom = null;
    JPanel staticPanel;
    ImageIcon floorImage;
    ImageIcon floorImageHover;
    ImageIcon bg;
    int selectedImage = 1;
    Insets finalInset;
    Insets finalInsetStatic;
    Dimension finalSize;
    Dimension finalSizeStatic;

    JPanel third;
    JLabel picLabel3;
    Room[][] rooms3 = new Room[5][10];
    ImageIcon thirdFloor;

    JPanel second;
    JLabel picLabel2;
    Room[][] rooms2 = new Room[5][10];
    ImageIcon secondFloor;

    JPanel first;
    JLabel picLabel1;
    Room[][] rooms1 = new Room[5][10];
    ImageIcon firstFloor;

    JPanel basement;
    JLabel picLabel0;
    Room[][] rooms0 = new Room[5][10];
    ImageIcon basementFloor;

    JPanel cards;
    public Gui() {
        passwordFrame = new JFrame("School Management");
        passwordFrame.setLayout(null);
        passwordPanel = new JPanel();
        passwordPanel.setBounds(0, 0, 500, 170);
        passwordPanel.setLayout(null);
        passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(60, 40, 200, 30);
        passwordLabel.setFont(new Font("Serif", Font.PLAIN, 30));
        passwordPanel.add(passwordLabel);
        passwordStatus = new JLabel("");
        passwordStatus.setBounds(170, 100, 200, 30);
        passwordStatus.setFont(new Font("Serif", Font.PLAIN, 20));
        passwordPanel.add(passwordStatus);
        passwordTextField = new JPasswordField();
        passwordTextField.setBounds(200, 35, 100, 30);
        passwordTextField.setFont(new Font("Serif", Font.PLAIN, 20));
        passwordTextField.setSize(200, 50);
        passwordPanel.add(passwordTextField);
        passwordFrame.add(passwordPanel);
        ArrayList d = new ArrayList();
        d.add("hi");
        passwordTextField.addActionListener(new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                if (String.valueOf(passwordTextField.getPassword()).equals("password123")) {
                    passwordFrame.dispose();
                    Border br = BorderFactory.createLineBorder(Color.BLACK);
                    cards = new JPanel(new CardLayout());
                    cards.setBounds(10, 50, 1200, 450);
                    cards.setBorder(br);
                    frame = new JFrame("School Management");
                    frame.setLayout(null);
                    staticPanel = new JPanel();
                    staticPanel.setLayout(null);
                    staticPanel.setBounds(0, 0, 1600, 900);
                    staticPanel.setBackground(new Color(242, 208, 172));
                    status = new JLabel("");
                    status.setBounds(1390, 730, 200, 50);
                    status.setFont(new Font("Serif", Font.PLAIN, 30));
                    staticPanel.add(status);
                    String[] possibleSorts = {"Name Alphabetical", "Gender", "Grade(high to low)", "Grade(low to high)", "Average(high to low)", "Average(low to high)"};
                    sortTable = new JComboBox(possibleSorts);
                    sortTable.setBounds(1430, 630, 150, 60);
                    sortTable.setFont(new Font("Serif", Font.PLAIN, 15));
                    sortTable.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            if (selectedClassroom != null) {
                                while (model.getRowCount() > 0) {
                                    model.removeRow(0);
                                }
                                ArrayList<Student> students = new ArrayList<Student>();
                                for (Student st: ((Classroom)selectedClassroom).getStudents()) {
                                    students.add(st);
                                }
                                String s = String.valueOf(sortTable.getSelectedItem());
                                if (s.equals("Name Alphabetical")) {
                                    sortNameAlphabetical(students);
                                }
                                else if (s.equals("Gender")) {
                                    sortGender(students);
                                }
                                else if (s.equals("Grade(high to low)")) {
                                    sortGradeHighToLow(students);
                                } 
                                else if (s.equals("Grade(low to high)")) {
                                    sortGradeLowToHigh(students);
                                }
                                else if (s.equals("Average(high to low)")) {
                                    sortAverageHighToLow(students);
                                }
                                else if (s.equals("Average(low to high)")) {
                                    sortAverageLowToHigh(students);
                                }
                                for (Student st: students) {
                                    model.addRow(new Object[] {
                                        String.valueOf(st.getOSIS()), st.getName(), st.getGender(), String.valueOf(st.getGrade()), String.valueOf(st.getAverage())
                                    });
                                }
                            }
                        }
                    });
                    staticPanel.add(sortTable);


                    addStudent = new JButton("Add Students");
                    addStudent.setBounds(210, 700, 350, 100);
                    addStudent.setFont(new Font("Serif", Font.PLAIN, 35));
                    addStudent.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            if (selectedClassroom == null) {
                                status.setText("Select a class");
                            }
                            else {
                                status.setText("");
                                JFrame addStudents = new JFrame();
                                addStudents.setLayout(null);
                                JPanel addPanel = new JPanel();
                                addPanel.setLayout(null);
                                addPanel.setBounds(0, 0, 600, 350);
                                addStudents.add(addPanel);
                                JLabel nameLabel = new JLabel("Name:");
                                nameLabel.setBounds(65, 43, 150, 30);
                                nameLabel.setFont(new Font("Serif", Font.PLAIN, 25));
                                addPanel.add(nameLabel);
                                JTextField nameText = new JTextField();
                                nameText.setBounds(140, 40, 150, 40);
                                nameText.setFont(new Font("Serif", Font.PLAIN, 25));
                                addPanel.add(nameText);
                                JLabel genderLabel = new JLabel("Gender:");
                                genderLabel.setBounds(310, 43, 150, 30);
                                genderLabel.setFont(new Font("Serif", Font.PLAIN, 25));
                                addPanel.add(genderLabel);
                                String[] genders = {"Male", "Female", "Other"};
                                JComboBox genderSelect = new JComboBox(genders);
                                genderSelect.setBounds(400, 40, 130, 40);
                                genderSelect.setFont(new Font("Serif", Font.PLAIN, 20));
                                addPanel.add(genderSelect);
                                JLabel gradeLabel = new JLabel("Grade Level:");
                                gradeLabel.setBounds(10, 143, 150, 30);
                                gradeLabel.setFont(new Font("Serif", Font.PLAIN, 25));
                                addPanel.add(gradeLabel);
                                String[] grades = {"9", "10", "11", "12"};
                                JComboBox gradeSelect = new JComboBox(grades);
                                gradeSelect.setBounds(150, 140, 130, 40);
                                gradeSelect.setFont(new Font("Serif", Font.PLAIN, 20));
                                addPanel.add(gradeSelect);
                                JLabel averageLabel = new JLabel("Average:");
                                averageLabel.setBounds(310, 143, 150, 30);
                                averageLabel.setFont(new Font("Serif", Font.PLAIN, 25));
                                addPanel.add(averageLabel);
                                JTextField averageText = new JTextField();
                                averageText.setBounds(400, 140, 150, 40);
                                averageText.setFont(new Font("Serif", Font.PLAIN, 25));
                                addPanel.add(averageText);
                                JLabel check = new JLabel("");
                                check.setBounds(220, 200, 200, 30);
                                check.setFont(new Font("Serif", Font.PLAIN, 20));
                                JButton add = new JButton("Add");
                                add.setBounds(220, 250, 150, 30);
                                add.setFont(new Font("Serif", Font.PLAIN, 20));
                                add.addActionListener(new ActionListener() {
                                    public void actionPerformed(ActionEvent e) {
                                        try {
                                            try {
                                                double ave = Double.parseDouble(averageText.getText());
                                                if (!(nameText.getText().isEmpty())) {
                                                    double actualAve = Double.valueOf(secondDecimal.format(ave));
                                                    String l = String.valueOf(gradeSelect.getSelectedItem());
                                                    Student m = new Student(((int) (Math.random()*900000000)) + 100000000, nameText.getText(), String.valueOf(genderSelect.getSelectedItem()), Integer.parseInt(l), actualAve);
                                                    ((Classroom)selectedClassroom).addStudent(m);
                                                    while (model.getRowCount() > 0) {
                                                        model.removeRow(0);
                                                    }
                                                    ArrayList<Student> students = new ArrayList<Student>();
                                                    for (Student st: ((Classroom)selectedClassroom).getStudents()) {
                                                        students.add(st);
                                                    }
                                                    String s = String.valueOf(sortTable.getSelectedItem());
                                                    if (s.equals("Name Alphabetical")) {
                                                        sortNameAlphabetical(students);
                                                    }
                                                    else if (s.equals("Gender")) {
                                                        sortGender(students);
                                                    }
                                                    else if (s.equals("Grade(high to low)")) {
                                                        sortGradeHighToLow(students);
                                                    } 
                                                    else if (s.equals("Grade(low to high")) {
                                                        sortGradeLowToHigh(students);
                                                    }
                                                    else if (s.equals("Average(high to low)")) {
                                                        sortAverageHighToLow(students);
                                                    }
                                                    else if (s.equals("Average(low to high)")) {
                                                        sortAverageLowToHigh(students);
                                                    }
                                                    for (Student st: students) {
                                                        model.addRow(new Object[] {
                                                            String.valueOf(st.getOSIS()), st.getName(), st.getGender(), String.valueOf(st.getGrade()), String.valueOf(st.getAverage())
                                                        });
                                                    }
                                                }
                                                else {
                                                    check.setText("Invalid information");
                                                }
                                                addStudents.dispose();
                                            } catch(IllegalArgumentException s) {
                                                check.setText("Invalid information");
                                            }

                                        } catch(NumberFormatException a) {
                                            check.setText("Invalid information");
                                        }
                                    }
                                });
                                addPanel.add(add);
                                addPanel.add(check);

                                addStudents.setPreferredSize(new Dimension(600, 350));
                                addStudents.pack();
                                addStudents.setLocationRelativeTo(null);
                                addStudents.setVisible(true);
                            }
                        }
                    });
                    staticPanel.add(addStudent);
                    removeSelected = new JCheckBox("Select Remove");
                    removeSelected.setBounds(1200, 800, 200, 40);
                    removeSelected.setFont(new Font("Serif", Font.PLAIN, 20));
                    removeSelected.setOpaque(false);
                    staticPanel.add(removeSelected);
                    removeStudent = new JButton("Remove Student");
                    removeStudent.setBounds(990, 700, 350, 100);
                    removeStudent.setFont(new Font("Serif", Font.PLAIN, 35));
                    removeStudent.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            if (selectedClassroom == null) {
                                status.setText("Select a class");
                            }
                            else {
                                status.setText("");
                                if (removeSelected.isSelected()) {
                                    int[] selected = studentInfoTable.getSelectedRows();
                                    if (selected.length > 0) {
                                        JFrame confirmFrame = new JFrame();
                                        confirmFrame.setLayout(null);
                                        JPanel confirmPanel = new JPanel();
                                        confirmPanel.setLayout(null);
                                        confirmPanel.setBounds(0, 0, 600, 200);
                                        JLabel confirmLabel = new JLabel("Are you sure you want to delete these students?");
                                        confirmLabel.setFont(new Font("Serif", Font.PLAIN, 25));
                                        confirmLabel.setBounds(50, 20, 550, 30);
                                        confirmPanel.add(confirmLabel);
                                        JButton yesButton = new JButton("Yes");
                                        yesButton.setBounds(180, 100, 100, 30);
                                        yesButton.setFont(new Font("Serif", Font.PLAIN, 20));
                                        yesButton.addActionListener(new ActionListener() {
                                            public void actionPerformed(ActionEvent e) {
                                                ArrayList<Integer> selectedOSIS = new ArrayList<Integer>();
                                                for (int i: selected) {
                                                    selectedOSIS.add(Integer.parseInt(studentInfoTable.getValueAt(i, 0).toString()));
                                                }
                                                for (int osis: selectedOSIS) {
                                                    checkRoomsOSIS(rooms3, osis);
                                                    checkRoomsOSIS(rooms2, osis);
                                                    checkRoomsOSIS(rooms1, osis);
                                                    checkRoomsOSIS(rooms0, osis);
                                                }
                                                while (model.getRowCount() > 0) {
                                                    model.removeRow(0);
                                                }
                                                ArrayList<Student> students = new ArrayList<Student>();
                                                for (Student st: ((Classroom)selectedClassroom).getStudents()) {
                                                    students.add(st);
                                                }
                                                String s = String.valueOf(sortTable.getSelectedItem());
                                                if (s.equals("Name Alphabetical")) {
                                                    sortNameAlphabetical(students);
                                                }
                                                else if (s.equals("Gender")) {
                                                    sortGender(students);
                                                }
                                                else if (s.equals("Grade(high to low)")) {
                                                    sortGradeHighToLow(students);
                                                } 
                                                else if (s.equals("Grade(low to high)")) {
                                                    sortGradeLowToHigh(students);
                                                }
                                                else if (s.equals("Average(high to low)")) {
                                                    sortAverageHighToLow(students);
                                                }
                                                else if (s.equals("Average(low to high)")) {
                                                    sortAverageLowToHigh(students);
                                                }
                                                for (Student st: students) {
                                                    model.addRow(new Object[] {
                                                        String.valueOf(st.getOSIS()), st.getName(), st.getGender(), String.valueOf(st.getGrade()), String.valueOf(st.getAverage())
                                                    });
                                                }
                                                confirmFrame.dispose();
                                            }
                                        });
                                        JButton noButton = new JButton("No");
                                        noButton.setBounds(320, 100, 100, 30);
                                        noButton.setFont(new Font("Serif", Font.PLAIN, 20));
                                        noButton.addActionListener(new ActionListener() {
                                            public void actionPerformed(ActionEvent a) {
                                                confirmFrame.dispose();
                                            }
                                        });
                                        
                                        confirmPanel.add(yesButton);
                                        confirmPanel.add(noButton);
                                        confirmFrame.add(confirmPanel);
                                        confirmFrame.setPreferredSize(new Dimension(600, 200));
                                        confirmFrame.pack();
                                        confirmFrame.setLocationRelativeTo(null);
                                        confirmFrame.setVisible(true);
                                    }
                                }
                                else {
                                    JFrame removeStudents = new JFrame();
                                    removeStudents.setLayout(null);
                                    JPanel removePanel = new JPanel();
                                    removePanel.setLayout(null);
                                    removePanel.setBounds(0, 0, 380, 240);
                                    removeStudents.add(removePanel);
                                    JLabel osisLabel = new JLabel("OSIS");
                                    osisLabel.setBounds(150, 10, 100, 30);
                                    osisLabel.setFont(new Font("Serif", Font.PLAIN, 20));
                                    removePanel.add(osisLabel);
                                    JTextField osisText = new JTextField();
                                    osisText.setBounds(105, 50, 150, 40);
                                    osisText.setFont(new Font("Serif", Font.PLAIN, 20));
                                    removePanel.add(osisText);
                                    JLabel check = new JLabel("");
                                    check.setBounds(140, 100, 100, 30);
                                    check.setFont(new Font("Serif", Font.PLAIN, 15));
                                    JButton remove = new JButton("Remove");
                                    remove.setBounds(120, 135, 120, 30);
                                    remove.setFont(new Font("Serif", Font.PLAIN, 20));
                                    remove.addActionListener(new ActionListener() {
                                    public void actionPerformed(ActionEvent e) {
                                        try {
                                            int o = Integer.valueOf(osisText.getText());
                                            if (o > 100000000 && o < 999999999) {
                                                checkRoomsOSIS(rooms3, o);
                                                checkRoomsOSIS(rooms2, o);
                                                checkRoomsOSIS(rooms1, o);
                                                checkRoomsOSIS(rooms0, o);
                                                while (model.getRowCount() > 0) {
                                                    model.removeRow(0);
                                                }
                                                ArrayList<Student> students = new ArrayList<Student>();
                                                for (Student st: ((Classroom)selectedClassroom).getStudents()) {
                                                    students.add(st);
                                                }
                                                String s = String.valueOf(sortTable.getSelectedItem());
                                                if (s.equals("Name Alphabetical")) {
                                                    sortNameAlphabetical(students);
                                                }
                                                else if (s.equals("Gender")) {
                                                    sortGender(students);
                                                }
                                                else if (s.equals("Grade(high to low)")) {
                                                    sortGradeHighToLow(students);
                                                } 
                                                else if (s.equals("Grade(low to high)")) {
                                                    sortGradeLowToHigh(students);
                                                }
                                                else if (s.equals("Average(high to low)")) {
                                                    sortAverageHighToLow(students);
                                                }
                                                else if (s.equals("Average(low to high)")) {
                                                    sortAverageLowToHigh(students);
                                                }
                                                for (Student st: students) {
                                                    model.addRow(new Object[] {
                                                        String.valueOf(st.getOSIS()), st.getName(), st.getGender(), String.valueOf(st.getGrade()), String.valueOf(st.getAverage())
                                                    });
                                                }
                                                removeStudents.dispose();
                                            }
                                            else {
                                                check.setText("Input 9 Digits");
                                            }
                                        } catch (NumberFormatException s) {
                                            check.setText("Invalid input");
                                        }
                                    }
                                });
                                removePanel.add(remove);
                                removePanel.add(check);

                                removeStudents.setPreferredSize(new Dimension(380, 240));
                                removeStudents.pack();
                                removeStudents.setLocationRelativeTo(null);
                                removeStudents.setVisible(true);
                                }
                            }
                        }
                    });
                    staticPanel.add(removeStudent);

                    editStudent = new JButton("Edit Student");
                    editStudent.setBounds(600, 700, 350, 100);
                    editStudent.setFont(new Font("Serif", Font.PLAIN, 35));
                    editStudent.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            if (selectedClassroom == null) {
                                status.setText("Select a class");
                            }
                            else {
                                status.setText("");
                                int[] selected = studentInfoTable.getSelectedRows();
                                if (selected.length > 1 || selected.length < 1) {
                                    status.setText("Select 1 row");
                                }
                                else {
                                    JFrame editFrame = new JFrame();
                                    editFrame.setLayout(null);
                                    JPanel editPanel = new JPanel();
                                    editPanel.setLayout(null);
                                    editPanel.setBounds(0, 0, 600, 450);
                                    editFrame.add(editPanel);
                                    JLabel osisLabel = new JLabel("OSIS:");
                                    osisLabel.setBounds(145, 63, 150, 30);
                                    osisLabel.setFont(new Font("Serif", Font.PLAIN, 25));
                                    editPanel.add(osisLabel);
                                    JTextField osisText = new JTextField();
                                    osisText.setBounds(220, 60, 150, 40);
                                    osisText.setFont(new Font("Serif", Font.PLAIN, 25));
                                    editPanel.add(osisText);
                                    JLabel nameLabel = new JLabel("Name:");
                                    nameLabel.setBounds(65, 143, 150, 30);
                                    nameLabel.setFont(new Font("Serif", Font.PLAIN, 25));
                                    editPanel.add(nameLabel);
                                    JTextField nameText = new JTextField();
                                    nameText.setBounds(140, 140, 150, 40);
                                    nameText.setFont(new Font("Serif", Font.PLAIN, 25));
                                    editPanel.add(nameText);
                                    JLabel genderLabel = new JLabel("Gender:");
                                    genderLabel.setBounds(310, 143, 150, 30);
                                    genderLabel.setFont(new Font("Serif", Font.PLAIN, 25));
                                    editPanel.add(genderLabel);
                                    String[] genders = {"Male", "Female", "Other"};
                                    JComboBox genderSelect = new JComboBox(genders);
                                    genderSelect.setBounds(400, 140, 130, 40);
                                    genderSelect.setFont(new Font("Serif", Font.PLAIN, 20));
                                    editPanel.add(genderSelect);
                                    JLabel gradeLabel = new JLabel("Grade Level:");
                                    gradeLabel.setBounds(10, 243, 150, 30);
                                    gradeLabel.setFont(new Font("Serif", Font.PLAIN, 25));
                                    editPanel.add(gradeLabel);
                                    String[] grades = {"9", "10", "11", "12"};
                                    JComboBox gradeSelect = new JComboBox(grades);
                                    gradeSelect.setBounds(150, 240, 130, 40);
                                    gradeSelect.setFont(new Font("Serif", Font.PLAIN, 20));
                                    editPanel.add(gradeSelect);
                                    JLabel averageLabel = new JLabel("Average:");
                                    averageLabel.setBounds(310, 243, 150, 30);
                                    averageLabel.setFont(new Font("Serif", Font.PLAIN, 25));
                                    editPanel.add(averageLabel);
                                    JTextField averageText = new JTextField();
                                    averageText.setBounds(400, 240, 150, 40);
                                    averageText.setFont(new Font("Serif", Font.PLAIN, 25));
                                    editPanel.add(averageText);
                                    JLabel check = new JLabel("");
                                    check.setBounds(220, 300, 200, 30);
                                    check.setFont(new Font("Serif", Font.PLAIN, 20));
                                    JButton edit = new JButton("Edit");
                                    edit.setBounds(220, 350, 150, 30);
                                    edit.setFont(new Font("Serif", Font.PLAIN, 20));
                                    edit.addActionListener(new ActionListener() {
                                        public void actionPerformed(ActionEvent e) {
                                            try {
                                                try {
                                                    int selected = studentInfoTable.getSelectedRow();
                                                    String name = studentInfoTable.getModel().getValueAt(selected, 1).toString();
                                                    double ave = Double.parseDouble(studentInfoTable.getModel().getValueAt(selected, 4).toString());
                                                    int os = Integer.parseInt(studentInfoTable.getModel().getValueAt(selected, 0).toString());
                                                    if (!(averageText.getText().isEmpty())) {
                                                        ave = Double.parseDouble(averageText.getText());
                                                    }
                                                    if (!(osisText.getText().isEmpty())) {
                                                        os = Integer.parseInt(osisText.getText());
                                                    }
                                                    if (!(nameText.getText().isEmpty())) {
                                                        name = String.valueOf(nameText.getText());
                                                    }
                                                    if (os > 100000000 && os < 999999999) {
                                                        double actualAve = Double.valueOf(secondDecimal.format(ave));
                                                    String l = String.valueOf(gradeSelect.getSelectedItem());
                                                    Student m = new Student(os, name, String.valueOf(genderSelect.getSelectedItem()), Integer.parseInt(l), actualAve);
                                                    int origOSIS = Integer.parseInt(studentInfoTable.getModel().getValueAt(selected, 0).toString());
                                                    checkRoomsOSIS(rooms3, origOSIS);
                                                    checkRoomsOSIS(rooms2, origOSIS);
                                                    checkRoomsOSIS(rooms1, origOSIS);
                                                    checkRoomsOSIS(rooms0, origOSIS);
                                                    ((Classroom)selectedClassroom).addStudent(m);
                                                    while (model.getRowCount() > 0) {
                                                        model.removeRow(0);
                                                    }
                                                    ArrayList<Student> students = new ArrayList<Student>();
                                                    for (Student st: ((Classroom)selectedClassroom).getStudents()) {
                                                        students.add(st);
                                                    }
                                                    String s = String.valueOf(sortTable.getSelectedItem());
                                                    if (s.equals("Name Alphabetical")) {
                                                        sortNameAlphabetical(students);
                                                    }
                                                    else if (s.equals("Gender")) {
                                                        sortGender(students);
                                                    }
                                                    else if (s.equals("Grade(high to low)")) {
                                                        sortGradeHighToLow(students);
                                                    } 
                                                    else if (s.equals("Grade(low to high")) {
                                                        sortGradeLowToHigh(students);
                                                    }
                                                    else if (s.equals("Average(high to low)")) {
                                                        sortAverageHighToLow(students);
                                                    }
                                                    else if (s.equals("Average(low to high)")) {
                                                        sortAverageLowToHigh(students);
                                                    }
                                                    for (Student st: students) {
                                                        model.addRow(new Object[] {
                                                            String.valueOf(st.getOSIS()), st.getName(), st.getGender(), String.valueOf(st.getGrade()), String.valueOf(st.getAverage())
                                                        });
                                                    }
                                                    editFrame.dispose();
                                                    }
                                                    else {
                                                        check.setText("Input 9 digits");
                                                    }
                                        
                                                } catch(IllegalArgumentException s) {
                                                    check.setText("Invalid information");
                                                }
    
                                            } catch(NumberFormatException a) {
                                                check.setText("Invalid information");
                                            }
                                        }
                                    });

                                    editPanel.add(edit);
                                    editPanel.add(check);
                                    editFrame.setPreferredSize(new Dimension(600, 450));
                                    editFrame.pack();
                                    editFrame.setLocationRelativeTo(null);
                                    editFrame.setVisible(true);
                                }
                            }
                        }
                    });
                    staticPanel.add(editStudent);

                    studentInfoTable = new JTable(model);
                    studentInfoTable.getColumnModel().getColumn(0).setPreferredWidth(110);
                    studentInfoTable.getColumnModel().getColumn(1).setPreferredWidth(180);
                    studentInfoTable.getColumnModel().getColumn(2).setPreferredWidth(75);
                    studentInfoTable.getColumnModel().getColumn(3).setPreferredWidth(60);
                    studentInfoTable.getColumnModel().getColumn(4).setPreferredWidth(70);
                    TableColumn column = studentInfoTable.getColumnModel().getColumn(4);
                    column.setCellRenderer((new averageRenderer()));
                    studentInfoTable.setPreferredScrollableViewportSize(new Dimension(200, 100));
                    studentInfoTable.setFillsViewportHeight(true);
                    scrollPane = new JScrollPane(studentInfoTable);
                    scrollPane.setBounds(1222, 20, 360, 600);
                    staticPanel.add(scrollPane);

                    ImageIcon sus = new ImageIcon(getClass().getResource("/Images/sus.png"));
                    Image susImage = sus.getImage();
                    susImage = susImage.getScaledInstance(350, 500, Image.SCALE_DEFAULT);
                    sus = new ImageIcon(susImage);
                    susLabel = new JLabel(sus);
                    susLabel.setBounds(1230, 20, 350, 500);
                    staticPanel.add(susLabel);

                    third = new JPanel();
                    third.setLayout(null);
                    third.setBounds(0, 0, 1200, 450);
                    thirdFloor = new ImageIcon(getClass().getResource("/Images/third.jpg"));
                    Image imageDiagram3 = thirdFloor.getImage();
                    imageDiagram3 = imageDiagram3.getScaledInstance(1198, 448, Image.SCALE_DEFAULT);
                    thirdFloor = new ImageIcon(imageDiagram3);
                    JLabel picLabelDiagram3 = new JLabel(thirdFloor);
                    Insets insets = cards.getInsets();
                    finalInset = insets;
                    Dimension size = picLabelDiagram3.getPreferredSize();
                    finalSize = size;
                    setRooms(rooms3);
                    displayRooms(rooms3, third);
                    floorImage = new ImageIcon(getClass().getResource("/Images/floorImage.jpg"));
                    Image image = floorImage.getImage();
                    image = imageTransparency(image, 127);
                    image = image.getScaledInstance(100, 100, Image.SCALE_DEFAULT);
                    floorImage = new ImageIcon(image);
                    floorImageHover = new ImageIcon(getClass().getResource("/Images/floorImageHover.jpg"));
                    Image image2 = floorImageHover.getImage();
                    image2 = imageTransparency(image2, 127);
                    image2 = image2.getScaledInstance(100, 100, Image.SCALE_DEFAULT);
                    floorImageHover = new ImageIcon(image2);
                    Insets insetsStatic = staticPanel.getInsets();
                    finalInsetStatic = insetsStatic;
                    picLabelDiagram3.setBounds(0+insets.left, 0+insets.top, size.width, size.height);
                    third.add(picLabelDiagram3);
                    picLabel3 = new JLabel(floorImage);
                    Dimension sizeStatic = picLabel3.getPreferredSize();
                    finalSizeStatic = sizeStatic;
                    picLabel3.setBounds(50+insetsStatic.left, 540+insetsStatic.top, sizeStatic.width, sizeStatic.height);
                    staticPanel.add(picLabel3);
                    picLabel3.addMouseListener(new MouseAdapter() {
                        public void mouseClicked(MouseEvent e) {
                            System.out.println("Click 3");
                            picLabel3.setIcon(floorImageHover);
                            if (selectedImage != 3) {
                                changePreviousFloorSelect();
                            }
                            selectedImage = 3;
                            CardLayout cl = (CardLayout) cards.getLayout();
                            cl.show(cards, "Floor 3");
                        }
                        public void mouseEntered(MouseEvent e) {
                            System.out.println("Hover 3");
                            floorImageHover = new ImageIcon(getClass().getResource("/Images/floorImageHover.jpg"));
                            Image image = floorImageHover.getImage();
                            image = imageTransparency(image, 127);
                            image = image.getScaledInstance(100, 100, Image.SCALE_DEFAULT);
                            floorImageHover = new ImageIcon(image);
                            picLabel3.setIcon(floorImageHover);
                        }
                        public void mouseExited(MouseEvent e) {
                            System.out.println("Leave 3");
                            if (selectedImage != 3) {
                                picLabel3.setIcon(floorImage);
                            }
                        }
                    });

                    second = new JPanel();
                    second.setLayout(null);
                    second.setBounds(0, 0, 1200, 450);
                    secondFloor = new ImageIcon(getClass().getResource("/Images/second.jpg"));
                    Image imageDiagram2 = secondFloor.getImage();
                    imageDiagram2 = imageDiagram2.getScaledInstance(1198, 448, Image.SCALE_DEFAULT);
                    secondFloor = new ImageIcon(imageDiagram2);
                    setRooms(rooms2);
                    displayRoomsLibrary(rooms2, second);
                    JLabel picLabelDiagram2 = new JLabel(secondFloor);
                    picLabelDiagram2.setBounds(0+insets.left, 0+insets.top, size.width, size.height);
                    second.add(picLabelDiagram2);
                    picLabel2 = new JLabel(floorImage);
                    picLabel2.setBounds(50+insetsStatic.left, 590+insetsStatic.top, sizeStatic.width, sizeStatic.height);
                    staticPanel.add(picLabel2);
                    picLabel2.addMouseListener(new MouseAdapter() {
                        public void mouseClicked(MouseEvent e) {
                            System.out.println("Click 2");
                            picLabel2.setIcon(floorImageHover);
                            if (selectedImage != 2) {
                                changePreviousFloorSelect();
                            }
                            selectedImage = 2;
                            CardLayout cl = (CardLayout) cards.getLayout();
                            cl.show(cards, "Floor 2");
                        }
                        public void mouseEntered(MouseEvent e) {
                            System.out.println("Hover 2");
                            picLabel2.setIcon(floorImageHover);
                        }
                        public void mouseExited(MouseEvent e) {
                            System.out.println("Leave 2");
                            if (selectedImage != 2) {
                                picLabel2.setIcon(floorImage);
                            }
                        }
                    });


                    first = new JPanel();
                    first.setLayout(null);
                    first.setBounds(0, 0, 1200, 450);
                    firstFloor = new ImageIcon(getClass().getResource("/Images/first.jpg"));
                    Image imageDiagram1 = firstFloor.getImage();
                    imageDiagram1 = imageDiagram1.getScaledInstance(1198, 448, Image.SCALE_DEFAULT);
                    firstFloor = new ImageIcon(imageDiagram1);
                    setRooms(rooms1);
                    displayRooms(rooms1, first);
                    JLabel picLabelDiagram1 = new JLabel(firstFloor);
                    picLabelDiagram1.setBounds(insets.left, insets.top, size.width, size.height);
                    first.add(picLabelDiagram1);
                    picLabel1 = new JLabel(floorImage);
                    picLabel1.setBounds(50+insetsStatic.left, 640+insetsStatic.top, sizeStatic.width, sizeStatic.height);
                    staticPanel.add(picLabel1);
                    picLabel1.addMouseListener(new MouseAdapter() {
                        public void mouseClicked(MouseEvent e) {
                            System.out.println("Click 1");
                            picLabel1.setIcon(floorImageHover);
                            if (selectedImage != 1) {
                                changePreviousFloorSelect();
                            }
                            selectedImage = 1;
                            CardLayout cl = (CardLayout) cards.getLayout();
                            cl.show(cards, "Floor 1");
                        }
                        public void mouseEntered(MouseEvent e) {
                            System.out.println("Hover 1");
                            picLabel1.setIcon(floorImageHover);
                        }
                        public void mouseExited(MouseEvent e) {
                            System.out.println("Leave 1");
                            if (selectedImage != 1) {
                                picLabel1.setIcon(floorImage);
                            }
                        }
                    });

                    basement = new JPanel();
                    basement.setLayout(null);
                    basement.setBounds(0, 0, 1200, 450);
                    basementFloor = new ImageIcon(getClass().getResource("/Images/basement.jpg"));
                    Image imageDiagram0 = basementFloor.getImage();
                    imageDiagram0 = imageDiagram0.getScaledInstance(1198, 448, Image.SCALE_DEFAULT);
                    basementFloor = new ImageIcon(imageDiagram0);
                    setRooms(rooms0);
                    displayRoomsBasement(rooms0, basement);
                    JLabel picLabelDiagram0 = new JLabel(basementFloor);
                    picLabelDiagram0.setBounds(insets.left, insets.top, size.width, size.height);
                    basement.add(picLabelDiagram0);
                    picLabel0 = new JLabel(floorImage);
                    picLabel0.setBounds(50+insetsStatic.left, 690+insetsStatic.top, sizeStatic.width, sizeStatic.height);
                    staticPanel.add(picLabel0);
                    picLabel0.addMouseListener(new MouseAdapter() {
                        public void mouseClicked(MouseEvent e) {
                            System.out.println("Click 0");
                            picLabel0.setIcon(floorImageHover);
                            if (selectedImage != 0) {
                                changePreviousFloorSelect();
                            }
                            selectedImage = 0;
                            CardLayout cl = (CardLayout) cards.getLayout();
                            cl.show(cards, "Floor 0");

                        }
                        public void mouseEntered(MouseEvent e) {
                            System.out.println("Hover 0");
                            picLabel0.setIcon(floorImageHover);
                        }
                        public void mouseExited(MouseEvent e) {
                            System.out.println("Leave 0");
                            if (selectedImage != 0) {
                                picLabel0.setIcon(floorImage);
                            }
                        }
                    });

                    cards.add(first, "Floor 1");
                    cards.add(second, "Floor 2");
                    cards.add(third, "Floor 3");
                    cards.add(basement, "Floor 0");
                    frame.add(cards);
                    frame.add(staticPanel);
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.setPreferredSize(new Dimension(1600, 900));
                    frame.pack();
                    frame.setLocationRelativeTo(null);
                    frame.setVisible(true);
                }
                else {
                    passwordStatus.setText("Invalid Password");
                }
            }
        });
        passwordFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        passwordFrame.setPreferredSize(new Dimension(500, 170));
        passwordFrame.pack();
        passwordFrame.setLocationRelativeTo(null);
        passwordFrame.setVisible(true);
    }

    public static Image imageTransparency(Image img, int trans)
    {
        if (img instanceof BufferedImage)
        {
            return (BufferedImage) img;
        }

        // Create a buffered image with transparency
        BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

        // Draw the image on to the buffered image
        Graphics2D bGr = bimage.createGraphics();
        bGr.drawImage(img, 0, 0, null);
        bGr.dispose();

        int transparency = trans; //0-255, 0 is invisible, 255 is opaque
        int colorMask = 0x00FFFFFF; //AARRGGBB
        int alphaShift = 24;
        for(int y = 0; y < bimage.getHeight(); y++) {
            for(int x = 0; x < bimage.getWidth(); x++) {
                bimage.setRGB(x, y, (bimage.getRGB(x, y) & colorMask) | (transparency << alphaShift));
            }
        }
        img = makeRoundedCorner(bimage, 50);
        return img;
    }
    public static BufferedImage makeRoundedCorner(BufferedImage image, int cornerRadius) {
        int w = image.getWidth();
        int h = image.getHeight();
        BufferedImage output = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
    
        Graphics2D g2 = output.createGraphics();
        
        g2.setComposite(AlphaComposite.Src);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(Color.WHITE);
        g2.fill(new java.awt.geom.RoundRectangle2D.Float(0, 0, w, h, cornerRadius, cornerRadius));
        g2.setComposite(AlphaComposite.SrcAtop);
        g2.drawImage(image, 0, 0, null);
        
        g2.dispose();
        
        return output;
    }
    public void changePreviousFloorSelect() {
        if (selectedImage == 3) {
            picLabel3.setIcon(floorImage);
        }
        if (selectedImage == 2) {
            picLabel2.setIcon(floorImage);
        }
        if (selectedImage == 1) {
            picLabel1.setIcon(floorImage);
        }
        if (selectedImage == 0) {
            picLabel0.setIcon(floorImage);
        }
    }
    public ArrayList<Student> addRandomStudents(ArrayList<Student> students) {
        String[][] maleFirstNames = {{"Liam", "Noah", "Oliver", "Elijah", "William", "James", "Benjamin", "Lucas", "Henry", "Alexander", "Mason", "Michael", "Ethan", "Daniel", "Jacob", "Logan",  "Levi", "Sebastian", "Mateo", "Jack", "Owen", "Theodore", "Aiden", "Samuel", "Joseph", "John", "David", "Wyatt", "Matthew", "Luke", "Asher",  "Carter", "Julian", "Grayson", "Leo", "Jayden", "Gabriel", "Isaac", "Lincoln",
            "Anthony", "Hudson", "Dylan", "Ezra", "Thomas", "Charles", "Christopher", "Jaxon", "Maverick", "Josiah", "Isaiah", "Andrew", "Elias", "Joshua", "Caleb", "Ryan", "Adrian", "Miles", "Eli", "Nolan", "Christian", "Aaron", "Cameron", "Ezekiel", "Colton", "Luca", "Landon", "Hunter", "Jonathan", "Santiago", "Axel", "Easton", "Cooper", "Jeremiah", "Angel", "Roman", "Connor", "Jameson", "Robert", "Greyson",
            "Jordan", "Ian", "Carson", "Jaxson", "Leonardo", "Nicholas", "Dominic", "Austin", "Everett", "Brooks", "Xavier", "Kai", "José", "Parker", "Adam", "Jace", "Wesley", "Kayden", "Kyree", "Edison", "Nikolas", "Samson"}};
        String[][] femaleFirstNames = {{"Oliva", "Emma", "Ava", "Charlotte", "Sophia", "Amelia", "Isabella", "Mia", "Evelyn", "Harper", "Camila", "Gianna", "Abigail", "Luna", "Ella", "Elizabeth", "Emily", "Avery", "Mila", "Scarlett", "Eleanor", "Madison", "Layla", "Penelope", "Aria", "Chloe", "Grace", "Ellie", "Nora", "Hazel", "Zoey", "Riley", "Victoria", "Lily", "Aurora", "Violet", "Nova", "Hannah", "Emilia", 
            "Zoe", "Stella", "Everly", "Isla", "Leah", "Lillian", "Addison", "Willow", "Lucky", "Paisley", "Natalie", "Naomi", "Eliana", "Brooklyn", "Elena", "Aubrey", "Claire", "Ivy", "Kinsley", "Audrey", "Maya", "Genesis", "Skylar", "Bella", "Aaliyah", "Madelyn", "Savannah", "Anna", "Delilah", "Serenity", "Caroline", "Kennedy", "Valentina", "Ruby", "Sophie", "Alice", "Gabriella", "Sadie", "Ariana", "Allison",
            "Hailey", "Autumn", "Nevaeh", "Natalia", "Quinn", "Josephine", "Sarah", "Cora", "Emery", "Samantha", "Piper", "Leilani", "Eva", "Everleigh", "Madeline", "Lydia", "Jade", "Peyton", "Brielle", "Adeline", "Vivian", "Marie"}};
        String[][] lastNames = {{"Anderson", "Ashwoon", "Aikin", "Bateman", "Bongard", "Bowers", "Boyd", "Cannon", "Cast", "Deitz", "Dewalt", "Ebner", "Frick", "Hancock", "Haworth", "Hesch", "Hoffman", "Kassing", "Knutson", "Lawless", "Lawicki", "Mccord", "McCormack", "Miller", "Myers", "Nugent", "Ortiz", "Orwig", "Ory", "Paiser", "Pak", "Pettigrew", "Quinn", "Quizoz", "Ramachandran", "Resnick", "Sagar",
            "Schickowski", "Schiebel", "Sellon", "Severson", "Shaffer", "Solberg", "Soloman", "Sonderling", "Soukup", "Soulis", "Stahl", "Sweeney", "Tandy", "Trebil", "Trusela", "Trussel", "Turco", "Uddin", "Uflan", "Ulrich", "Upson", "Vader", "Vail", "Valente", "Van Zandt", "Vanderpoel", "Ventotla", "Vogal", "Wagle", "Wagner", "Wakefield", "Weinstein", "Weiss", "Woo", "Yang", "Yates", "Yocum", "Zeaser", "Zeller",
            "Ziegler", "Bauer", "Baxster", "Casal", "Cataldi", "Caswell", "Celedon", "Chambers", "Chapman", "Christensen", "Darnell", "Davidson", "Davis", "DeLorenzo", "Dinkins", "Doran", "Dugelman", "Dugan", "Duffman", "Eastman", "Ferro", "Ferry", "Fletcher", "Fietzer", "Hylan", "Hydinger", "Illingsworth", "Ingram", "Irwin", "Jagtap", "Jenson", "Johnson", "Johnsen", "Jones", "Jurgenson", "Kalleg", "Kaskel", "Keller",
            "Leisinger", "LePage", "Lewis", "Linde", "Lulloff", "Maki", "Martin", "McGinnis", "Mills", "Moody", "Moore", "Napier", "Nelson", "Norquist", "Nuttle", "Olson", "Ostrander", "Reamer", "Reardon", "Reyes", "Rice", "Ripka", "Roberts", "Rogers", "Root", "Sandstrom", "Sawyer", "Schlicht", "Schmitt", "Schwager", "Schutz", "Schuster", "Tapia", "Thompson", "Tiernan", "Tisler"}};
        int grade = ((int) (Math.random()*(13 - 9)) + 9);
        for (int i = 0; i < 30; i++) {
            double average;
            int OSIS = ((int) (Math.random()*900000000)) + 100000000;
            int a = ((int) (Math.random()*100)) + 1;
            if (a <= 70) {
                average = Double.valueOf(secondDecimal.format(Math.random()*21 + 80));
            }
            else if (a <= 90) {
                average = Double.valueOf(secondDecimal.format(Math.random()*31 + 50));
            }
            else {
                average = Double.valueOf(secondDecimal.format(Math.random()*51));
            }
            int g = ((int) (Math.random()*2)) + 1;
            if (g == 1) {
                String name = maleFirstNames[0][new Random().nextInt(maleFirstNames[0].length)] + " " + lastNames[0][new Random().nextInt(lastNames[0].length)];
                students.add(new Student(OSIS, name, "Male", grade, average));
            }
            else {
                String name = femaleFirstNames[0][new Random().nextInt(femaleFirstNames[0].length)] + " " + lastNames[0][new Random().nextInt(lastNames[0].length)];
                students.add(new Student(OSIS, name, "Female", grade, average));
            }
        }
        return students;
    }
    public ArrayList<Student> addRandomStudentsLib(ArrayList<Student> students, int num) {
        String[][] maleFirstNames = {{"Liam", "Noah", "Oliver", "Elijah", "William", "James", "Benjamin", "Lucas", "Henry", "Alexander", "Mason", "Michael", "Ethan", "Daniel", "Jacob", "Logan",  "Levi", "Sebastian", "Mateo", "Jack", "Owen", "Theodore", "Aiden", "Samuel", "Joseph", "John", "David", "Wyatt", "Matthew", "Luke", "Asher",  "Carter", "Julian", "Grayson", "Leo", "Jayden", "Gabriel", "Isaac", "Lincoln",
            "Anthony", "Hudson", "Dylan", "Ezra", "Thomas", "Charles", "Christopher", "Jaxon", "Maverick", "Josiah", "Isaiah", "Andrew", "Elias", "Joshua", "Caleb", "Ryan", "Adrian", "Miles", "Eli", "Nolan", "Christian", "Aaron", "Cameron", "Ezekiel", "Colton", "Luca", "Landon", "Hunter", "Jonathan", "Santiago", "Axel", "Easton", "Cooper", "Jeremiah", "Angel", "Roman", "Connor", "Jameson", "Robert", "Greyson",
            "Jordan", "Ian", "Carson", "Jaxson", "Leonardo", "Nicholas", "Dominic", "Austin", "Everett", "Brooks", "Xavier", "Kai", "José", "Parker", "Adam", "Jace", "Wesley", "Kayden", "Kyree", "Edison", "Nikolas", "Samson"}};
        String[][] femaleFirstNames = {{"Oliva", "Emma", "Ava", "Charlotte", "Sophia", "Amelia", "Isabella", "Mia", "Evelyn", "Harper", "Camila", "Gianna", "Abigail", "Luna", "Ella", "Elizabeth", "Emily", "Avery", "Mila", "Scarlett", "Eleanor", "Madison", "Layla", "Penelope", "Aria", "Chloe", "Grace", "Ellie", "Nora", "Hazel", "Zoey", "Riley", "Victoria", "Lily", "Aurora", "Violet", "Nova", "Hannah", "Emilia", 
            "Zoe", "Stella", "Everly", "Isla", "Leah", "Lillian", "Addison", "Willow", "Lucky", "Paisley", "Natalie", "Naomi", "Eliana", "Brooklyn", "Elena", "Aubrey", "Claire", "Ivy", "Kinsley", "Audrey", "Maya", "Genesis", "Skylar", "Bella", "Aaliyah", "Madelyn", "Savannah", "Anna", "Delilah", "Serenity", "Caroline", "Kennedy", "Valentina", "Ruby", "Sophie", "Alice", "Gabriella", "Sadie", "Ariana", "Allison",
            "Hailey", "Autumn", "Nevaeh", "Natalia", "Quinn", "Josephine", "Sarah", "Cora", "Emery", "Samantha", "Piper", "Leilani", "Eva", "Everleigh", "Madeline", "Lydia", "Jade", "Peyton", "Brielle", "Adeline", "Vivian", "Marie"}};
        String[][] lastNames = {{"Anderson", "Ashwoon", "Aikin", "Bateman", "Bongard", "Bowers", "Boyd", "Cannon", "Cast", "Deitz", "Dewalt", "Ebner", "Frick", "Hancock", "Haworth", "Hesch", "Hoffman", "Kassing", "Knutson", "Lawless", "Lawicki", "Mccord", "McCormack", "Miller", "Myers", "Nugent", "Ortiz", "Orwig", "Ory", "Paiser", "Pak", "Pettigrew", "Quinn", "Quizoz", "Ramachandran", "Resnick", "Sagar",
            "Schickowski", "Schiebel", "Sellon", "Severson", "Shaffer", "Solberg", "Soloman", "Sonderling", "Soukup", "Soulis", "Stahl", "Sweeney", "Tandy", "Trebil", "Trusela", "Trussel", "Turco", "Uddin", "Uflan", "Ulrich", "Upson", "Vader", "Vail", "Valente", "Van Zandt", "Vanderpoel", "Ventotla", "Vogal", "Wagle", "Wagner", "Wakefield", "Weinstein", "Weiss", "Woo", "Yang", "Yates", "Yocum", "Zeaser", "Zeller",
            "Ziegler", "Bauer", "Baxster", "Casal", "Cataldi", "Caswell", "Celedon", "Chambers", "Chapman", "Christensen", "Darnell", "Davidson", "Davis", "DeLorenzo", "Dinkins", "Doran", "Dugelman", "Dugan", "Duffman", "Eastman", "Ferro", "Ferry", "Fletcher", "Fietzer", "Hylan", "Hydinger", "Illingsworth", "Ingram", "Irwin", "Jagtap", "Jenson", "Johnson", "Johnsen", "Jones", "Jurgenson", "Kalleg", "Kaskel", "Keller",
            "Leisinger", "LePage", "Lewis", "Linde", "Lulloff", "Maki", "Martin", "McGinnis", "Mills", "Moody", "Moore", "Napier", "Nelson", "Norquist", "Nuttle", "Olson", "Ostrander", "Reamer", "Reardon", "Reyes", "Rice", "Ripka", "Roberts", "Rogers", "Root", "Sandstrom", "Sawyer", "Schlicht", "Schmitt", "Schwager", "Schutz", "Schuster", "Tapia", "Thompson", "Tiernan", "Tisler"}};
        for (int i = 0; i < num; i++) {
            int grade = ((int) (Math.random()*(13 - 9)) + 9);
            double average;
            int OSIS = ((int) (Math.random()*900000000)) + 100000000;
            int a = ((int) (Math.random()*100)) + 1;
            if (a <= 70) {
                average = Double.valueOf(secondDecimal.format(Math.random()*21 + 80));
            }
            else if (a <= 90) {
                average = Double.valueOf(secondDecimal.format(Math.random()*31 + 50));
            }
            else {
                average = Double.valueOf(secondDecimal.format(Math.random()*51));
            }
            int g = ((int) (Math.random()*2)) + 1;
            if (g == 1) {
                String name = maleFirstNames[0][new Random().nextInt(maleFirstNames[0].length)] + " " + lastNames[0][new Random().nextInt(lastNames[0].length)];
                students.add(new Student(OSIS, name, "Male", grade, average));
            }
            else {
                String name = femaleFirstNames[0][new Random().nextInt(femaleFirstNames[0].length)] + " " + lastNames[0][new Random().nextInt(lastNames[0].length)];
                students.add(new Student(OSIS, name, "Female", grade, average));
            }
        }
        return students;
    }
    public void setRooms(Room[][] rooms) {
        rooms[0][2] = new Classroom(addRandomStudents(new ArrayList<Student>()), false);
        rooms[0][3] = new Classroom(addRandomStudents(new ArrayList<Student>()), false);
        rooms[0][6] = new Classroom(addRandomStudents(new ArrayList<Student>()), false);
        rooms[0][7] = new Classroom(addRandomStudents(new ArrayList<Student>()), false);
        rooms[1][3] = new Bathroom("Male", false);
        rooms[1][5] = new Classroom(addRandomStudents(new ArrayList<Student>()), false);
        rooms[1][6] = new Classroom(addRandomStudents(new ArrayList<Student>()), false);
        rooms[1][9] = new Classroom(addRandomStudents(new ArrayList<Student>()), false);
        rooms[2][0] = new Classroom(addRandomStudents(new ArrayList<Student>()), false);
        rooms[2][1] = new Classroom(addRandomStudents(new ArrayList<Student>()), false);
        rooms[2][3] = new Classroom(addRandomStudents(new ArrayList<Student>()), false);
        rooms[2][7] = new Classroom(addRandomStudents(new ArrayList<Student>()), false);
        rooms[3][3] = new Bathroom("Female", false);
        rooms[3][5] = new Classroom(addRandomStudents(new ArrayList<Student>()), false);
        rooms[3][6] = new Classroom(addRandomStudents(new ArrayList<Student>()), false);
        rooms[3][9] = new Classroom(addRandomStudents(new ArrayList<Student>()), false);
        rooms[4][2] = new Classroom(addRandomStudents(new ArrayList<Student>()), false);
        rooms[4][3] = new Classroom(addRandomStudents(new ArrayList<Student>()), false);
        rooms[4][6] = new Classroom(addRandomStudents(new ArrayList<Student>()), false);
        rooms[4][7] = new Classroom(addRandomStudents(new ArrayList<Student>()), false);
        rooms[0][0] = new Classroom(addRandomStudentsLib(new ArrayList<Student>(), 67), false);
        rooms[1][0] = new Classroom(addRandomStudentsLib(new ArrayList<Student>(), 21), false);
    }
    public void displayRooms(Room[][] room, JPanel scene) {
        ImageIcon imI = new ImageIcon(getClass().getResource("/Images/classroom.PNG"));
        Image im = imI.getImage();
        im = im.getScaledInstance(30, 30, Image.SCALE_DEFAULT);
        imI = new ImageIcon(im);
        ImageIcon maleBath = new ImageIcon(getClass().getResource("/Images/maleBath.png"));
        Image maleBath2 = maleBath.getImage();
        maleBath2 = maleBath2.getScaledInstance(30, 30, Image.SCALE_DEFAULT);
        maleBath = new ImageIcon(maleBath2);
        ImageIcon femaleBath = new ImageIcon(getClass().getResource("/Images/femaleBath.png"));
        Image femaleBath2 = femaleBath.getImage();
        femaleBath2 = femaleBath2.getScaledInstance(30, 30, Image.SCALE_DEFAULT);
        femaleBath = new ImageIcon(femaleBath2);
        ImageIcon gym = new ImageIcon(getClass().getResource("/Images/gym.PNG"));
        Image gym2 = gym.getImage();
        gym2 = gym2.getScaledInstance(50, 50, Image.SCALE_DEFAULT);
        gym = new ImageIcon(gym2);
        JLabel a1 = new JLabel(imI);
        a1.setBounds(250, 7, 50, 50);
        scene.add(a1);
        addMouseLis(a1, room, 0, 2);
        JLabel a2 = new JLabel(imI);
        a2.setBounds(350, 7, 50, 50);
        scene.add(a2);
        addMouseLis(a2, room, 0, 3);
        JLabel a3 = new JLabel(imI);
        a3.setBounds(700, 7, 50, 50);
        scene.add(a3);
        addMouseLis(a3, room, 0, 6);
        JLabel a4 = new JLabel(imI);
        a4.setBounds(840, 7, 50, 50);
        scene.add(a4);
        addMouseLis(a4, room, 0, 7);

        JLabel maleBathLabel = new JLabel(maleBath);
        maleBathLabel.setBounds(350, 100, 50, 50);
        scene.add(maleBathLabel);
        addMouseLisBathroomMale(maleBathLabel);
        JLabel b1 = new JLabel(imI);
        b1.setBounds(635, 100, 50, 50);
        scene.add(b1);
        addMouseLis(b1, room, 1, 5);
        JLabel b2 = new JLabel(imI);
        b2.setBounds(735, 100, 50, 50);
        scene.add(b2);
        addMouseLis(b2, room, 1, 6);
        JLabel b3 = new JLabel(gym);
        b3.setBounds(1030, 100, 80, 80);
        scene.add(b3);
        addMouseLisGym(b3, room, 1, 9);

        JLabel c1 = new JLabel(imI);
        c1.setBounds(20, 200, 50, 50);
        scene.add(c1);
        addMouseLis(c1, room, 2, 0);
        JLabel c2 = new JLabel(imI);
        c2.setBounds(150, 200, 50, 50);
        scene.add(c2);
        addMouseLis(c2, room, 2, 1);
        JLabel c3 = new JLabel(imI);
        c3.setBounds(430, 200, 50, 50);
        scene.add(c3);
        addMouseLis(c3, room, 2, 3);
        JLabel c4 = new JLabel(imI);
        c4.setBounds(850, 200, 50, 50);

        scene.add(c4);
        addMouseLis(c4, room, 2, 7);

        JLabel femaleBathLabel = new JLabel(femaleBath);
        femaleBathLabel.setBounds(350, 300, 50, 50);
        scene.add(femaleBathLabel);
        addMouseLisBathroomFemale(femaleBathLabel);
        JLabel d1 = new JLabel(imI);
        d1.setBounds(635, 300, 50, 50);
        scene.add(d1);
        addMouseLis(d1, room, 3, 5);
        JLabel d2 = new JLabel(imI);
        d2.setBounds(735, 300, 50, 50);
        scene.add(d2);
        addMouseLis(d2, room, 3, 6);
        JLabel d3 = new JLabel(imI);
        d3.setBounds(1030, 300, 50, 50);
        scene.add(d3);
        addMouseLis(d3, room, 3, 9);

        JLabel e1 = new JLabel(imI);
        e1.setBounds(200, 390, 50, 50);
        scene.add(e1);
        addMouseLis(e1, room, 4, 2);
        JLabel e2 = new JLabel(imI);
        e2.setBounds(350, 390, 50, 50);
        scene.add(e2);
        addMouseLis(e2, room, 4, 3);
        JLabel e3 = new JLabel(imI);
        e3.setBounds(680, 390, 50, 50);
        scene.add(e3);
        addMouseLis(e3, room, 4, 6);
        JLabel e4 = new JLabel(imI);
        e4.setBounds(830, 390, 50, 50);
        scene.add(e4);
        addMouseLis(e4, room, 4, 7);
    }
    public void displayRoomsLibrary(Room[][] room, JPanel scene) {
        ImageIcon imI = new ImageIcon(getClass().getResource("/Images/classroom.PNG"));
        Image im = imI.getImage();
        im = im.getScaledInstance(30, 30, Image.SCALE_DEFAULT);
        imI = new ImageIcon(im);
        ImageIcon maleBath = new ImageIcon(getClass().getResource("/Images/maleBath.png"));
        Image maleBath2 = maleBath.getImage();
        maleBath2 = maleBath2.getScaledInstance(30, 30, Image.SCALE_DEFAULT);
        maleBath = new ImageIcon(maleBath2);
        ImageIcon femaleBath = new ImageIcon(getClass().getResource("/Images/femaleBath.png"));
        Image femaleBath2 = femaleBath.getImage();
        femaleBath2 = femaleBath2.getScaledInstance(30, 30, Image.SCALE_DEFAULT);
        femaleBath = new ImageIcon(femaleBath2);
        ImageIcon library = new ImageIcon(getClass().getResource("/Images/library.PNG"));
        Image library2 = library.getImage();
        library2 = library2.getScaledInstance(30, 30, Image.SCALE_DEFAULT);
        library = new ImageIcon(library2);
        JLabel a1 = new JLabel(imI);
        a1.setBounds(250, 7, 50, 50);
        scene.add(a1);
        addMouseLis(a1, room, 0, 2);
        JLabel a2 = new JLabel(imI);
        a2.setBounds(350, 7, 50, 50);
        scene.add(a2);
        addMouseLis(a2, room, 0, 3);
        JLabel a3 = new JLabel(imI);
        a3.setBounds(700, 7, 50, 50);
        scene.add(a3);
        addMouseLis(a3, room, 0, 6);
        JLabel a4 = new JLabel(imI);
        a4.setBounds(840, 7, 50, 50);
        scene.add(a4);
        addMouseLis(a4, room, 0, 7);

        JLabel maleBathLabel = new JLabel(maleBath);
        maleBathLabel.setBounds(350, 100, 50, 50);
        scene.add(maleBathLabel);
        addMouseLisBathroomMale(maleBathLabel);
        JLabel libraryLabel1 = new JLabel(library);
        libraryLabel1.setBounds(532, 100, 50, 50);
        Room[][] libraryRoom = new Room[1][1];
        libraryRoom[0][0] = new Classroom(addRandomStudentsLib(new ArrayList<Student>(), 67), false);
        scene.add(libraryLabel1);
        addMouseLisLibrary(libraryLabel1, room, 0, 0);
        JLabel b1 = new JLabel(imI);
        b1.setBounds(635, 100, 50, 50);
        scene.add(b1);
        addMouseLis(b1, room, 1, 5);
        JLabel b2 = new JLabel(imI);
        b2.setBounds(735, 100, 50, 50);
        scene.add(b2);
        addMouseLis(b2, room, 1, 6);
        JLabel b3 = new JLabel(imI);
        b3.setBounds(1030, 100, 50, 50);
        scene.add(b3);
        addMouseLis(b3, room, 1, 9);

        JLabel c1 = new JLabel(imI);
        c1.setBounds(20, 200, 50, 50);
        scene.add(c1);
        addMouseLis(c1, room, 2, 0);
        JLabel c2 = new JLabel(imI);
        c2.setBounds(150, 200, 50, 50);
        scene.add(c2);
        addMouseLis(c2, room, 2, 1);
        JLabel c4 = new JLabel(imI);
        c4.setBounds(850, 200, 50, 50);
        scene.add(c4);
        addMouseLis(c4, room, 2, 7);

        JLabel femaleBathLabel = new JLabel(femaleBath);
        femaleBathLabel.setBounds(350, 300, 50, 50);
        scene.add(femaleBathLabel);
        addMouseLisBathroomFemale(femaleBathLabel);
        JLabel libraryLabel2 = new JLabel(library);
        libraryLabel2.setBounds(532, 300, 50, 50);
        scene.add(libraryLabel2);
        addMouseLisLibrary(libraryLabel2, room, 0, 0);
        JLabel d1 = new JLabel(imI);
        d1.setBounds(635, 300, 50, 50);
        scene.add(d1);
        addMouseLis(d1, room, 3, 5);
        JLabel d2 = new JLabel(imI);
        d2.setBounds(735, 300, 50, 50);
        scene.add(d2);
        addMouseLis(d2, room, 3, 6);
        JLabel d3 = new JLabel(imI);
        d3.setBounds(1030, 300, 50, 50);
        scene.add(d3);
        addMouseLis(d3, room, 3, 9);

        JLabel e1 = new JLabel(imI);
        e1.setBounds(200, 390, 50, 50);
        scene.add(e1);
        addMouseLis(e1, room, 4, 2);
        JLabel e2 = new JLabel(imI);
        e2.setBounds(350, 390, 50, 50);
        scene.add(e2);
        addMouseLis(e2, room, 4, 3);
        JLabel e3 = new JLabel(imI);
        e3.setBounds(680, 390, 50, 50);
        scene.add(e3);
        addMouseLis(e3, room, 4, 6);
        JLabel e4 = new JLabel(imI);
        e4.setBounds(830, 390, 50, 50);
        scene.add(e4);
        addMouseLis(e4, room, 4, 7);
    }
    public void displayRoomsBasement(Room[][] room, JPanel scene) {
        ImageIcon imI = new ImageIcon(getClass().getResource("/Images/classroom.PNG"));
        Image im = imI.getImage();
        im = im.getScaledInstance(30, 30, Image.SCALE_DEFAULT);
        imI = new ImageIcon(im);
        ImageIcon maleBath = new ImageIcon(getClass().getResource("/Images/maleBath.png"));
        Image maleBath2 = maleBath.getImage();
        maleBath2 = maleBath2.getScaledInstance(30, 30, Image.SCALE_DEFAULT);
        maleBath = new ImageIcon(maleBath2);
        ImageIcon femaleBath = new ImageIcon(getClass().getResource("/Images/femaleBath.png"));
        Image femaleBath2 = femaleBath.getImage();
        femaleBath2 = femaleBath2.getScaledInstance(30, 30, Image.SCALE_DEFAULT);
        femaleBath = new ImageIcon(femaleBath2);
        ImageIcon pool = new ImageIcon(getClass().getResource("/Images/pool.PNG"));
        Image pool2 = pool.getImage();
        pool2 = pool2.getScaledInstance(50, 50, Image.SCALE_DEFAULT);
        pool = new ImageIcon(pool2);
        JLabel a4 = new JLabel(imI);
        a4.setBounds(840, 7, 50, 50);
        scene.add(a4);
        addMouseLis(a4, room, 0, 7);

        JLabel b3 = new JLabel(pool);
        b3.setBounds(1030, 100, 80, 80);
        scene.add(b3);
        addMouseLisPool(b3, room, 1, 0);

        JLabel c1 = new JLabel(imI);
        c1.setBounds(20, 200, 50, 50);
        scene.add(c1);
        addMouseLis(c1, room, 2, 0);
        JLabel c2 = new JLabel(imI);
        c2.setBounds(150, 200, 50, 50);
        scene.add(c2);
        addMouseLis(c2, room, 2, 1);
        JLabel c4 = new JLabel(imI);
        c4.setBounds(850, 200, 50, 50);
        scene.add(c4);
        addMouseLis(c4, room, 2, 7);

        JLabel femaleBathLabel = new JLabel(femaleBath);
        femaleBathLabel.setBounds(350, 300, 50, 50);
        scene.add(femaleBathLabel);
        addMouseLisBathroomFemale(femaleBathLabel);
        JLabel d1 = new JLabel(imI);
        d1.setBounds(635, 300, 50, 50);
        scene.add(d1);
        addMouseLis(d1, room, 3, 5);
        JLabel d2 = new JLabel(imI);
        d2.setBounds(735, 300, 50, 50);
        scene.add(d2);
        addMouseLis(d2, room, 3, 6);
        JLabel d3 = new JLabel(imI);
        d3.setBounds(1030, 300, 50, 50);
        scene.add(d3);
        addMouseLis(d3, room, 3, 9);

        JLabel e1 = new JLabel(imI);
        e1.setBounds(200, 390, 50, 50);
        scene.add(e1);
        addMouseLis(e1, room, 4, 2);
        JLabel e2 = new JLabel(imI);
        e2.setBounds(350, 390, 50, 50);
        scene.add(e2);
        addMouseLis(e2, room, 4, 3);
        JLabel e3 = new JLabel(maleBath);
        e3.setBounds(680, 390, 50, 50);
        addMouseLisBathroomMale(e3);
        scene.add(e3);
        addMouseLisBathroomMale(e3);
        JLabel e4 = new JLabel(imI);
        e4.setBounds(830, 390, 50, 50);
        scene.add(e4);
        addMouseLis(e4, room, 4, 7);
    }
    public void addMouseLis(JLabel label, Room[][] room, int row, int column) {
        ImageIcon imI = new ImageIcon(getClass().getResource("/Images/classroom.PNG"));
        Image im = imI.getImage();
        im = im.getScaledInstance(30, 30, Image.SCALE_DEFAULT);
        Image im2 = im.getScaledInstance(50, 50, Image.SCALE_DEFAULT);
        final ImageIcon imHover = new ImageIcon(im2);
        final ImageIcon imExit = new ImageIcon(im);
        label.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                scrollPane.setVisible(true);
                while (model.getRowCount() > 0) {
                    model.removeRow(0);
                }
                Room c = room[row][column];
                selectedClassroom = c;
                status.setText("");
                ArrayList<Student> students = new ArrayList<Student>();
                for (Student st: ((Classroom)c).getStudents()) {
                    students.add(st);
                }
                String s = String.valueOf(sortTable.getSelectedItem());
                if (s.equals("Name Alphabetical")) {
                    sortNameAlphabetical(students);
                }
                else if (s.equals("Gender")) {
                    sortGender(students);
                }
                else if (s.equals("Grade(high to low)")) {
                    sortGradeHighToLow(students);
                } 
                else if (s.equals("Grade(low to high")) {
                    sortGradeLowToHigh(students);
                }
                else if (s.equals("Average(high to low)")) {
                    sortAverageHighToLow(students);
                }
                else if (s.equals("Average(low to high)")) {
                    sortAverageLowToHigh(students);
                }
                for (Student st: students) {
                    model.addRow(new Object[] {
                        String.valueOf(st.getOSIS()), st.getName(), st.getGender(), String.valueOf(st.getGrade()), String.valueOf(st.getAverage())
                    });
                }
            }
            public void mouseEntered(MouseEvent e) {
                label.setIcon(imHover);
            }
            public void mouseExited(MouseEvent e) {
                label.setIcon(imExit);
            }
        });
    }
    public void addMouseLisGym(JLabel label, Room[][] room, int row, int column) {
        ImageIcon imI = new ImageIcon(getClass().getResource("/Images/gym.PNG"));
        Image im = imI.getImage();
        im = im.getScaledInstance(50, 50, Image.SCALE_DEFAULT);
        Image im2 = im.getScaledInstance(80, 80, Image.SCALE_DEFAULT);
        final ImageIcon imHover = new ImageIcon(im2);
        final ImageIcon imExit = new ImageIcon(im);
        label.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                scrollPane.setVisible(true);
                while (model.getRowCount() > 0) {
                    model.removeRow(0);
                }
                Room c = room[row][column];
                selectedClassroom = c;
                status.setText("");
                ArrayList<Student> students = new ArrayList<Student>();
                for (Student st: ((Classroom)c).getStudents()) {
                    students.add(st);
                }
                String s = String.valueOf(sortTable.getSelectedItem());
                if (s.equals("Name Alphabetical")) {
                    sortNameAlphabetical(students);
                }
                else if (s.equals("Gender")) {
                    sortGender(students);
                }
                else if (s.equals("Grade(high to low)")) {
                    sortGradeHighToLow(students);
                } 
                else if (s.equals("Grade(low to high)")) {
                    sortGradeLowToHigh(students);
                }
                else if (s.equals("Average(high to low)")) {
                    sortAverageHighToLow(students);
                }
                else if (s.equals("Average(low to high)")) {
                    sortAverageLowToHigh(students);
                }
                for (Student st: students) {
                    model.addRow(new Object[] {
                        String.valueOf(st.getOSIS()), st.getName(), st.getGender(), String.valueOf(st.getGrade()), String.valueOf(st.getAverage())
                    });
                }
            }
            public void mouseEntered(MouseEvent e) {
                label.setIcon(imHover);
            }
            public void mouseExited(MouseEvent e) {
                label.setIcon(imExit);
            }
        });
    }
    public void addMouseLisBathroomMale(JLabel label) {
        ImageIcon imI = new ImageIcon(getClass().getResource("/Images/maleBath.png"));
        Image im = imI.getImage();
        im = im.getScaledInstance(30, 30, Image.SCALE_DEFAULT);
        Image im2 = im.getScaledInstance(50, 50, Image.SCALE_DEFAULT);
        final ImageIcon imHover = new ImageIcon(im2);
        final ImageIcon imExit = new ImageIcon(im);
        label.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                selectedClassroom = null;
                status.setText("");
                scrollPane.setVisible(false);
                while (model.getRowCount() > 0) {
                    model.removeRow(0);
                }
            }
            public void mouseEntered(MouseEvent e) {
                label.setIcon(imHover);
            }
            public void mouseExited(MouseEvent e) {
                label.setIcon(imExit);
            }
        });
    }
    public void addMouseLisBathroomFemale(JLabel label) {
        ImageIcon imI = new ImageIcon(getClass().getResource("/Images/femaleBath.png"));
        Image im = imI.getImage();
        im = im.getScaledInstance(30, 30, Image.SCALE_DEFAULT);
        Image im2 = im.getScaledInstance(50, 50, Image.SCALE_DEFAULT);
        final ImageIcon imHover = new ImageIcon(im2);
        final ImageIcon imExit = new ImageIcon(im);
        label.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                selectedClassroom = null;
                status.setText("");
                scrollPane.setVisible(false);
                while (model.getRowCount() > 0) {
                    model.removeRow(0);
                }
            }
            public void mouseEntered(MouseEvent e) {
                label.setIcon(imHover);
            }
            public void mouseExited(MouseEvent e) {
                label.setIcon(imExit);
            }
        });
    }
    public void addMouseLisLibrary(JLabel label, Room[][] room, int row, int column) {
        ImageIcon imI = new ImageIcon(getClass().getResource("/Images/library.PNG"));
        Image im = imI.getImage();
        im = im.getScaledInstance(30, 30, Image.SCALE_DEFAULT);
        Image im2 = im.getScaledInstance(50, 50, Image.SCALE_DEFAULT);
        final ImageIcon imHover = new ImageIcon(im2);
        final ImageIcon imExit = new ImageIcon(im);
        label.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                scrollPane.setVisible(true);
                while (model.getRowCount() > 0) {
                    model.removeRow(0);
                }
                Room c = room[row][column];
                selectedClassroom = c;
                status.setText("");
                ArrayList<Student> students = new ArrayList<Student>();
                for (Student st: ((Classroom)c).getStudents()) {
                    students.add(st);
                }
                String s = String.valueOf(sortTable.getSelectedItem());
                if (s.equals("Name Alphabetical")) {
                    sortNameAlphabetical(students);
                }
                else if (s.equals("Gender")) {
                    sortGender(students);
                }
                else if (s.equals("Grade(high to low)")) {
                    sortGradeHighToLow(students);
                } 
                else if (s.equals("Grade(low to high)")) {
                    sortGradeLowToHigh(students);
                }
                else if (s.equals("Average(high to low)")) {
                    sortAverageHighToLow(students);
                }
                else if (s.equals("Average(low to high)")) {
                    sortAverageLowToHigh(students);
                }
                for (Student st: students) {
                    model.addRow(new Object[] {
                        String.valueOf(st.getOSIS()), st.getName(), st.getGender(), String.valueOf(st.getGrade()), String.valueOf(st.getAverage())
                    });
                }
            }
            public void mouseEntered(MouseEvent e) {
                label.setIcon(imHover);
            }
            public void mouseExited(MouseEvent e) {
                label.setIcon(imExit);
            }
        });
    }
    public void addMouseLisPool(JLabel label, Room[][] room, int row, int column) {
        ImageIcon imI = new ImageIcon(getClass().getResource("/Images/pool.PNG"));
        Image im = imI.getImage();
        im = im.getScaledInstance(50, 50, Image.SCALE_DEFAULT);
        Image im2 = im.getScaledInstance(80, 80, Image.SCALE_DEFAULT);
        final ImageIcon imHover = new ImageIcon(im2);
        final ImageIcon imExit = new ImageIcon(im);
        label.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                scrollPane.setVisible(true);
                while (model.getRowCount() > 0) {
                    model.removeRow(0);
                }
                Room c = room[row][column];
                selectedClassroom = c;
                status.setText("");
                ArrayList<Student> students = new ArrayList<Student>();
                for (Student st: ((Classroom)c).getStudents()) {
                    students.add(st);
                }
                String s = String.valueOf(sortTable.getSelectedItem());
                if (s.equals("Name Alphabetical")) {
                    sortNameAlphabetical(students);
                }
                else if (s.equals("Gender")) {
                    sortGender(students);
                }
                else if (s.equals("Grade(high to low)")) {
                    sortGradeHighToLow(students);
                } 
                else if (s.equals("Grade(low to high)")) {
                    sortGradeLowToHigh(students);
                }
                else if (s.equals("Average(high to low)")) {
                    sortAverageHighToLow(students);
                }
                else if (s.equals("Average(low to high)")) {
                    sortAverageLowToHigh(students);
                }
                for (Student st: students) {
                    model.addRow(new Object[] {
                        String.valueOf(st.getOSIS()), st.getName(), st.getGender(), String.valueOf(st.getGrade()), String.valueOf(st.getAverage())
                    });
                }
            }
            public void mouseEntered(MouseEvent e) {
                label.setIcon(imHover);
            }
            public void mouseExited(MouseEvent e) {
                label.setIcon(imExit);
            }
        });
    }
    public void sortNameAlphabetical(ArrayList<Student> students) {
        for(int i = 0; i < students.size() - 1; i++)
        {
            int minIndex = i;
            for(int j = i + 1; j < students.size(); j++)
            {
                if(students.get(j).getName().compareTo(students.get(minIndex).getName()) < 0)
                {
                    minIndex = j;
                }
            }
            if (i != minIndex)
            {
                Student temp = students.get(minIndex);
                students.set(minIndex, students.get(i));
                students.set(i, temp);
            }
        }
    }
    public void sortGender(ArrayList<Student> students) {
        for(int i = 1; i < students.size(); i++)
        {
            Student temp = students.get(i);
            int insertionIndex = i;
            while(insertionIndex > 0 && temp.getGender().compareTo(students.get(insertionIndex - 1).getGender()) < 0)
            {
                students.set(insertionIndex, students.get(insertionIndex-1));
                insertionIndex--;
            }
        students.set(insertionIndex, temp);
        }
    }
    public void sortGradeHighToLow(ArrayList<Student> students) {
        for(int i = 1; i < students.size(); i++)
        {
            Student temp = students.get(i);
            int insertionIndex = i;
            while(insertionIndex > 0 && temp.getGrade() > students.get(insertionIndex - 1).getGrade())
            {
                students.set(insertionIndex, students.get(insertionIndex-1));
                insertionIndex--;
            }
        students.set(insertionIndex, temp);
        }
    }
    public void sortGradeLowToHigh(ArrayList<Student> students) {
        for(int i = 1; i < students.size(); i++)
        {
            Student temp = students.get(i);
            int insertionIndex = i;
            while(insertionIndex > 0 && temp.getGrade() < students.get(insertionIndex - 1).getGrade())
            {
                students.set(insertionIndex, students.get(insertionIndex-1));
                insertionIndex--;
            }
        students.set(insertionIndex, temp);
        }
    }
    public void sortAverageHighToLow(ArrayList<Student> students) {
        for(int i = 1; i < students.size(); i++)
        {
            Student temp = students.get(i);
            int insertionIndex = i;
            while(insertionIndex > 0 && temp.getAverage() > students.get(insertionIndex - 1).getAverage())
            {
                students.set(insertionIndex, students.get(insertionIndex-1));
                insertionIndex--;
            }
        students.set(insertionIndex, temp);
        }
    }
    public void sortAverageLowToHigh(ArrayList<Student> students) {
        for(int i = 1; i < students.size(); i++)
        {
            Student temp = students.get(i);
            int insertionIndex = i;
            while(insertionIndex > 0 && temp.getAverage() < students.get(insertionIndex - 1).getAverage())
            {
                students.set(insertionIndex, students.get(insertionIndex-1));
                insertionIndex--;
            }
        students.set(insertionIndex, temp);
        }
    }
    public void checkRoomsOSIS(Room[][] rooms, int OSIS) {
        for (int c = 0; c < rooms[0].length; c++) {
            for (int r = 0; r < rooms.length; r++) {
                if (rooms[r][c] != null && rooms[r][c].getClass() != rooms3[3][3].getClass()) {
                    Classroom classroom = ((Classroom)rooms[r][c]);
                    for (int i = 0; i < classroom.getStudents().size(); i++) {
                        if (classroom.getStudents().get(i).getOSIS() == OSIS) {
                            classroom.getStudents().remove(i);
                            i--;
                        }
                    }
                }
            }
        }
    }
    class averageRenderer extends DefaultTableCellRenderer 
    {
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) 
        {
            Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            if (Double.valueOf(String.valueOf(table.getValueAt(row, column))) < 65) {
                cell.setBackground(new Color(255, 102, 102));
            }
            else if (Double.valueOf(String.valueOf(table.getValueAt(row, column))) < 80) {
                cell.setBackground(new Color(255, 255, 0));
            }
            else if (Double.valueOf(String.valueOf(table.getValueAt(row, column))) < 90) {
                cell.setBackground(new Color(0, 255, 51));
            }
            else {
                cell.setBackground(new Color(51, 153, 255));
            }
            return cell;
        }
    }
}

