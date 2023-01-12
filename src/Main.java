import Views.MenuBar;
import Views.StudentView;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import java.sql.SQLException;

public class Main
{
    private static void initUI() throws SQLException {
        JFrame f = new JFrame("Catalog Online");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.add(new MenuBar(f));
        new StudentView(f);
        f.setExtendedState(JFrame.MAXIMIZED_BOTH);
        f.setVisible(true);
    }

    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(new Runnable() //new Thread()
        {
            public void run()
            {
                try {
                    initUI();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
}
