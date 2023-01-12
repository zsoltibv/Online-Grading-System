package Views;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;

import Models.Student;
import Models.Groups;

public class StudentView extends JPanel {
    private JTable studentTable;
    static JFrame frame;
    private JPanel studentView;
    private JTextField firstNameInput;
    private JTextField cnpInput;
    private JTextField emailnput;
    private JTextField lastNameInput;
    private JButton submitButton;
    private JButton deleteButton;
    private JButton updateButton;
    private JComboBox comboBox;
    ArrayList<String[]> students;

    public StudentView(JFrame f) throws SQLException {
        frame = f;
        frame.setContentPane(studentView);
        frame.revalidate();
        populateTable();
        studentTable.setDefaultEditor(Object.class, null);
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    addNewStudent();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = studentTable.getSelectedRow();
                try {
                    removeStudent(Integer.parseInt(students.get(row)[0]));
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        studentTable.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent me) {
                if (SwingUtilities.isLeftMouseButton(me)) {
                    if (me.getClickCount() == 2) {
                        int row = studentTable.getSelectedRow();
                        try {
                            addStudentToGroup(Integer.parseInt(students.get(row)[0]));
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            }
        });

        addGroupsToComboBox();
    }

    private void addGroupsToComboBox() throws SQLException {
        Groups gr = new Groups();
        ArrayList<String[]> groups = gr.getAllGroups();

        for (String[] record : groups) {
            comboBox.addItem(record[1]);
        }
    }

    public void populateTable() throws SQLException {

        Student st = new Student();
        students = st.getAllStudents();

        // Column Names
        String[] columnNames = {"First Name", "Last Name", "Cnp", "Email", "Grupa"};

        // Initializing the JTable
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        for (String[] record : students) {
            model.addRow(new String[]{record[1], record[2], record[3], record[4], record[5]});
        }

        studentTable.setModel(model);
    }

    public void addNewStudent() throws SQLException {
        Student st = new Student();
        if (firstNameInput.getText().equals("") || lastNameInput.getText().equals("") || cnpInput.getText().equals("") || emailnput.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "One of the fields is empty");
        } else {
            st.insertStudent(firstNameInput.getText(), lastNameInput.getText(), cnpInput.getText(), emailnput.getText());
            populateTable();
        }
    }

    public void removeStudent(int id) throws SQLException {
        int n = JOptionPane.showConfirmDialog(
                frame,
                "Are you sure you want to delete student with id " + id + " ?",
                "Delete Student",
                JOptionPane.YES_NO_OPTION);

        if (n == JOptionPane.YES_OPTION) {
            Student st = new Student();
            st.deleteStudent(id);
            populateTable();
        }

        studentTable.clearSelection();
    }

    public void addStudentToGroup(int id) throws SQLException {
        String m = JOptionPane.showInputDialog("Assign student to group nr:\n");

        if (m != null) {
            Student st = new Student();
            int groupId = Integer.parseInt(m);
            st.assignGroup(id, groupId);
        }
        populateTable();
    }
}
