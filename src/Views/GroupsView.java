package Views;

import Models.Groups;
import Models.Student;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

public class GroupsView {
    private JPanel groupsView;
    private JTable groupTable;
    private JTextField fullNameInput;
    private JTextField shortNameInput;
    private JButton submitButton;
    JFrame frame;
    ArrayList<String[]> groups;

    public GroupsView(JFrame f) throws SQLException {
        frame = f;
        frame.setContentPane(groupsView);
        frame.revalidate();
        populateTable();
        groupTable.setDefaultEditor(Object.class, null);
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    addNewGroup();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }

    private void addNewGroup() throws SQLException {
        Groups gr = new Groups();
        if (fullNameInput.getText().equals("") || shortNameInput.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "One of the fields is empty");
        } else {
            gr.insertGroup(fullNameInput.getText(), shortNameInput.getText());
            populateTable();
        }
    }

    public void populateTable() throws SQLException {

        Groups gr = new Groups();
        groups = gr.getAllGroups();

        // Column Names
        String[] columnNames = {"Full Name", "Short Name"};

        // Initializing the JTable
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        for (String[] record : groups) {
            model.addRow(new String[]{record[1], record[2]});
        }

        groupTable.setModel(model);
    }
}
