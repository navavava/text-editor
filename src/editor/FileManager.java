package editor;

import javax.swing.*;
import java.io.*;
import java.util.Scanner;

public class FileManager {
    public static void openFile(TextEditor textEditor, JTextArea textArea) {
        JFileChooser fileChooser = new JFileChooser();
        if (fileChooser.showOpenDialog(textEditor) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            textEditor.currentFile = file;
            try {
                Scanner input = new Scanner(new BufferedReader(new FileReader(file.getAbsolutePath())));
                while (input.hasNextLine()) {
                    String line = input.nextLine();
                    textArea.setText(line + "\n");
                }
                input.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void saveFile(TextEditor textEditor, JTextArea textArea) {
        if (textEditor.currentFile == null) {
            JFileChooser fileChooser = new JFileChooser();
            if (fileChooser.showSaveDialog(textEditor) == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                textEditor.currentFile = file;
                try {
                    BufferedWriter output = new BufferedWriter(new FileWriter(file.getAbsolutePath()));
                    output.write(textArea.getText());
                    output.close();
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            } else {
                try {
                    BufferedWriter output = new BufferedWriter(new FileWriter(textEditor.currentFile.getAbsolutePath()));
                    output.write(textArea.getText());
                    output.close();
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    public static void newFile(TextEditor textEditor, JTextArea textArea) {
        textEditor.currentFile = null;
        textArea.setText("");
    }
}

