package miniOS;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class FileExplorerWindow extends JInternalFrame {
    private File currentDirectory;
    private DefaultListModel<File> listModel;
    private JList<File> fileList;

    public FileExplorerWindow() {
        super("File Explorer", true, true, true, true);
        setSize(600, 400);
        setLayout(new BorderLayout());

        currentDirectory = new File(System.getProperty("user.home"));
        listModel = new DefaultListModel<>();
        fileList = new JList<>(listModel);
        fileList.setCellRenderer((list, value, index, isSelected, cellHasFocus) -> new JLabel(value.getName()));

        refreshFileList();

        fileList.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    File selected = fileList.getSelectedValue();
                    if (selected.isDirectory()) {
                        currentDirectory = selected;
                        refreshFileList();
                    }
                }
                if (SwingUtilities.isRightMouseButton(e)) {
                    int index = fileList.locationToIndex(e.getPoint());
                    fileList.setSelectedIndex(index);
                    showContextMenu(e);
                }
            }
        });

        add(new JScrollPane(fileList), BorderLayout.CENTER);
    }

    private void refreshFileList() {
        listModel.clear();
        File[] files = currentDirectory.listFiles();
        if (files != null) {
            for (File file : files) {
                listModel.addElement(file);
            }
        }
    }

    private void showContextMenu(MouseEvent e) {
        JPopupMenu menu = new JPopupMenu();

        JMenuItem createItem = new JMenuItem("Create File");
        createItem.addActionListener(ae -> {
            String name = JOptionPane.showInputDialog(this, "Enter file name:");
            if (name != null) {
                try {
                    new File(currentDirectory, name).createNewFile();
                    refreshFileList();
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(this, "Error creating file.");
                }
            }
        });

        JMenuItem deleteItem = new JMenuItem("Delete");
        deleteItem.addActionListener(ae -> {
            File selected = fileList.getSelectedValue();
            if (selected != null && selected.exists()) {
                selected.delete();
                refreshFileList();
            }
        });

        JMenuItem renameItem = new JMenuItem("Rename");
        renameItem.addActionListener(ae -> {
            File selected = fileList.getSelectedValue();
            if (selected != null) {
                String newName = JOptionPane.showInputDialog(this, "Enter new name:");
                if (newName != null) {
                    File renamed = new File(currentDirectory, newName);
                    try {
                        Files.move(selected.toPath(), renamed.toPath(), StandardCopyOption.REPLACE_EXISTING);
                        refreshFileList();
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(this, "Rename failed.");
                    }
                }
            }
        });

        menu.add(createItem);
        menu.add(renameItem);
        menu.add(deleteItem);
        menu.show(fileList, e.getX(), e.getY());
    }
}
