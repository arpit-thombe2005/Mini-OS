package miniOS;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

public class MiniOS {
    private static final List<JInternalFrame> runningApps = new ArrayList<>();
    private static JDesktopPane desktop;

    public static void main(String[] args) {
        showBootAnimation(); // Boot animation before GUI loads
        SwingUtilities.invokeLater(MiniOS::createAndShowGUI);
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Mini OS");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 700);
        frame.setLayout(new BorderLayout());

        desktop = new JDesktopPane();
        desktop.setBackground(new Color(30, 30, 30));
        frame.add(desktop, BorderLayout.CENTER);

        JLabel welcomeLabel = new JLabel("Welcome to Mini OS!", SwingConstants.CENTER);
        welcomeLabel.setForeground(Color.WHITE);
        welcomeLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
        welcomeLabel.setBounds(300, 50, 400, 30);
        desktop.add(welcomeLabel);

        JMenuBar menuBar = new JMenuBar();
        JMenu appMenu = new JMenu("Applications");

        // Removed Terminal menu item

        JMenuItem fileExplorer = new JMenuItem(new AbstractAction("File Explorer") {
            @Override
            public void actionPerformed(ActionEvent e) {
                JInternalFrame explorerFrame = new FileExplorerWindow();
                desktop.add(explorerFrame);
                runningApps.add(explorerFrame);
                explorerFrame.setVisible(true);
            }
        });

        JMenuItem textEditor = new JMenuItem(new AbstractAction("Text Editor") {
            @Override
            public void actionPerformed(ActionEvent e) {
                JInternalFrame editorFrame = new TextEditorWindow();
                desktop.add(editorFrame);
                runningApps.add(editorFrame);
                editorFrame.setVisible(true);
            }
        });

        JMenuItem taskManager = new JMenuItem(new AbstractAction("Task Manager") {
            @Override
            public void actionPerformed(ActionEvent e) {
                JInternalFrame taskFrame = new TaskManagerWindow(runningApps);
                desktop.add(taskFrame);
                taskFrame.setVisible(true);
            }
        });

        JMenuItem restart = new JMenuItem(new AbstractAction("Restart") {
            @Override
            public void actionPerformed(ActionEvent e) {
                showAnimation("Restarting MiniOS...");
                frame.dispose();
                main(null); // Relaunch GUI
            }
        });

        JMenuItem shutdown = new JMenuItem(new AbstractAction("Shutdown") {
            @Override
            public void actionPerformed(ActionEvent e) {
                showAnimation("Shutting down MiniOS...");
                System.exit(0);
            }
        });

        // Add remaining menu items
        appMenu.add(fileExplorer);
        appMenu.add(textEditor);
        appMenu.add(taskManager);
        appMenu.addSeparator();
        appMenu.add(restart);
        appMenu.add(shutdown);
        menuBar.add(appMenu);
        frame.setJMenuBar(menuBar);

        frame.setVisible(true);
    }

    // Boot animation
    private static void showBootAnimation() {
        BootAnimation boot = new BootAnimation("Booting MiniOS...");
        boot.showAnimation(2000);
    }

    // Restart or Shutdown animation
    private static void showAnimation(String message) {
        BootAnimation anim = new BootAnimation(message);
        anim.showAnimation(2000);
    }
}
