package miniOS;

import javax.swing.*;
import java.awt.*;

public class BootAnimation extends JWindow {
    public BootAnimation(String message) {
        JLabel label = new JLabel(message, SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 24));
        label.setForeground(Color.WHITE);
        getContentPane().setBackground(Color.BLACK);
        getContentPane().add(label, BorderLayout.CENTER);
        setSize(400, 200);
        setLocationRelativeTo(null);
    }

    public void showAnimation(int durationMillis) {
        setVisible(true);
        new Timer(150, e -> {}).start(); // dummy timer to simulate delay
        try {
            Thread.sleep(durationMillis);
        } catch (InterruptedException ignored) {}
        setVisible(false);
        dispose();
    }

    // Static helpers for clarity
    public static void showBooting() {
        new BootAnimation("Booting MiniOS...").showAnimation(2000);
    }

    public static void showRestarting() {
        new BootAnimation("Restarting MiniOS...").showAnimation(2000);
    }

    public static void showShutdown() {
        new BootAnimation("Shutting down MiniOS...").showAnimation(2000);
    }
}
