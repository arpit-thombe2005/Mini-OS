# Mini Java OS

Mini Java OS is a simple desktop-like application built with Java Swing. It simulates a basic operating system environment with a windowed interface and several built-in applications.

## Features

- **Boot Animation:** Displays a startup animation when launching or restarting.
- **File Explorer:** Opens a window simulating file browsing.
- **Text Editor:** Opens a basic text editor window.
- **Task Manager:** Shows currently running application windows.
- **Restart & Shutdown:** Simulates OS restart and shutdown with animations.

## Requirements

- **Java 21 or newer** (Make sure your `java` and `javac` commands use Java 21+)
- Works on Windows, Linux, and macOS

## How to Run

1. **Compile the source code:**

   Open a terminal in the `src` directory and run:
   ```
   javac miniOS/*.java
   ```

2. **Run the application:**

   ```
   java miniOS.MiniOS
   ```

## Project Structure

```
mini_java_os/
└── src/
    └── miniOS/
        ├── MiniOS.java
        ├── FileExplorerWindow.java
        ├── TextEditorWindow.java
        ├── TaskManagerWindow.java
        └── BootAnimation.java
```

## Notes

- All source files are in the `miniOS` package.
- If you get a Java version error, update your Java installation to version 21 or newer.

## License

This project is for educational purposes.
