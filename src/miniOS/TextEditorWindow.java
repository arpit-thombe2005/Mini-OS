package miniOS;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class TextEditorWindow extends JInternalFrame {
    private JTextArea textArea;
    private File currentFile;

    public TextEditorWindow() {
        super("Text Editor", true, true, true, true);
        setSize(600, 400);
        setLayout(new BorderLayout());

        textArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textArea);
        add(scrollPane, BorderLayout.CENTER);

        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");

        JMenuItem openItem = new JMenuItem("Open");
        openItem.addActionListener(e -> openFile());
        fileMenu.add(openItem);

        JMenuItem saveItem = new JMenuItem("Save");
        saveItem.addActionListener(e -> saveFile());
        fileMenu.add(saveItem);

        JMenuItem saveAsItem = new JMenuItem("Save As");
        saveAsItem.addActionListener(e -> saveFileAs());
        fileMenu.add(saveAsItem);

        menuBar.add(fileMenu);
        setJMenuBar(menuBar);
    }

    public class BootAnimation extends JWindow {
    public BootAnimation(String message) {
        JLabel label = new JLabel(message, SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 24));
        label.setForeground(Color.WHITE);
        getContentPane().setBackground(Color.BLACK);
        getContentPane().add(label);
        setSize(400, 200);
        setLocationRelativeTo(null);
    }

    public void showAnimation(int duration) {
        setVisible(true);
        new Timer(150, new java.awt.event.ActionListener() {
            int count = 0;
            public void actionPerformed(java.awt.event.ActionEvent e) {
                if (count++ >= duration / 150) {
                    ((Timer) e.getSource()).stop();
                    setVisible(false);
                    dispose();
                }
            }
        }).start();
    }
}

    private void openFile() {
        JFileChooser fileChooser = new JFileChooser();
        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            currentFile = fileChooser.getSelectedFile();
            try (BufferedReader reader = new BufferedReader(new FileReader(currentFile))) {
                textArea.read(reader, null);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Could not open file.");
            }
        }
    }

    private void saveFile() {
        if (currentFile == null) {
            saveFileAs();
        } else {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(currentFile))) {
                textArea.write(writer);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Could not save file.");
            }
        }
    }

    private void saveFileAs() {
        JFileChooser fileChooser = new JFileChooser();
        if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            currentFile = fileChooser.getSelectedFile();
            saveFile();
        }
    }
}
