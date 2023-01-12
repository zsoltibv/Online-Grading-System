package Views;

import Models.Grades;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.SQLException;

public class MenuBar extends JPanel {
    //Jframe
    JFrame frame;

    // menubar
    static JMenuBar mb;

    // JMenu
    static JMenu x;

    // Menu items
    static JMenuItem m1, m2, m3;

    public MenuBar(JFrame f) {
        frame = f;

        // create a menubar
        mb = new JMenuBar();

        // create a menu
        x = new JMenu("Student");

        // create menuitems
        m1 = new JMenuItem("Toti studentii");
        m2 = new JMenuItem("Grupe studenti");

        //action listeners
        m1.addActionListener(new MenuActionListener());
        m2.addActionListener(new MenuActionListener());

        // add menu items to menu
        x.add(m1);
        x.add(m2);

        // add menu to menu bar
        mb.add(x);

        // create a menu
        x = new JMenu("Examen");

        // create menuitems
        m1 = new JMenuItem("Toate examenele");
        m2 = new JMenuItem("Note examen");

        //action listeners
        m1.addActionListener(new MenuActionListener());
        m2.addActionListener(new MenuActionListener());

        // add menu items to menu
        x.add(m1);
        x.add(m2);

        // add menu to menu bar
        mb.add(x);

        // add menubar to frame
        frame.setJMenuBar(mb);
    }

    class MenuActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand() == "Toti studentii") {
                try {
                    new StudentView(frame);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            } else if (e.getActionCommand() == "Grupe studenti") {
                try {
                    new GroupsView(frame);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
            else if (e.getActionCommand() == "Toate examenele") {
                try {
                    new ExamsView(frame);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }    else if (e.getActionCommand() == "Note examen") {
                try {
                    new GradesView(frame);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
    }
}