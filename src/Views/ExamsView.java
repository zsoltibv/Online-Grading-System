package Views;

import Models.Groups;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import Models.Exams;

public class ExamsView {
    private JPanel groupsView;
    private JTable groupTable;
    private JTextField examNameInput;
    private JButton submitButton;
    JFrame frame;
    ArrayList<Exams> exams;
    Exams ex;

    public ExamsView(JFrame f) throws SQLException {
        frame = f;
        frame.setContentPane(groupsView);
        frame.revalidate();
        groupTable.setDefaultEditor(Object.class, null);
        ex = new Exams();
        populateTable();
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    addNewExam();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }

    private void addNewExam() throws SQLException {
        if (examNameInput.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "One of the fields is empty");
        } else {
            ex.setExamName(examNameInput.getText());
            ex.insertExam();
            populateTable();
        }
    }

    public void populateTable() throws SQLException {

        exams = ex.getAllExams();

        // Column Names
        String[] columnNames = {"Exam Name"};

        // Initializing the JTable
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        for (Exams record : exams) {
            model.addRow(new String[]{record.getExamName()});
        }

        groupTable.setModel(model);
    }
}
