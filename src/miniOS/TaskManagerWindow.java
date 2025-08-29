package miniOS;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class TaskManagerWindow extends JInternalFrame {
    private DefaultListModel<String> listModel;
    private List<JInternalFrame> appFrames;

    public TaskManagerWindow(List<JInternalFrame> runningApps) {
        super("Task Manager", true, true, true, true);
        setSize(400, 300);
        setLayout(new BorderLayout());

        appFrames = runningApps;
        listModel = new DefaultListModel<>();
        JList<String> appList = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(appList);

        JButton killButton = new JButton("Close Selected");
        killButton.addActionListener((ActionEvent e) -> {
            int index = appList.getSelectedIndex();
            if (index != -1) {
                JInternalFrame frame = appFrames.get(index);
                frame.dispose();
                appFrames.remove(index);
                listModel.remove(index);
            }
        });

        add(scrollPane, BorderLayout.CENTER);
        add(killButton, BorderLayout.SOUTH);
        refreshList();
    }

    private void refreshList() {
        listModel.clear();
        for (JInternalFrame frame : appFrames) {
            listModel.addElement(frame.getTitle());
        }
    }
}
