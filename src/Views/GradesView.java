package Views;

import Models.*;

import javax.swing.*;
import java.sql.SQLException;
import java.util.ArrayList;

public class GradesView {
    private JPanel groupsView;
    private JTable groupTable;
    private JTextField fullNameInput;
    private JButton submitButton;
    private JComboBox comboBoxStudent;
    private JComboBox comboBoxExam;

    JFrame frame;
    ArrayList<String[]> groups;

    public GradesView(JFrame f) throws SQLException {
        frame = f;
        frame.setContentPane(groupsView);
        frame.revalidate();
        //populateTable();
        //groupTable.setDefaultEditor(Object.class, null);
        fillComboBoxes();
    }

    private void fillComboBoxes() throws SQLException {
        Student gr = new Student();
        ArrayList<String[]> students = gr.getAllStudents();

        for (String[] record : students) {
            comboBoxStudent.addItem(record[1]);
        }

        Exams ex = new Exams();
        ArrayList<Exams> exams = ex.getAllExams();

        for (Exams record : exams) {
            comboBoxExam.addItem(record.getExamName());
        }
    }
}
