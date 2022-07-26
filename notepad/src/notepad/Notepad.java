/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package notepad;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import javax.swing.filechooser.*;

/**
 *
 * @author admin
 */
public class Notepad extends JFrame implements ActionListener {

    JTextArea area;
    JScrollPane pane;
    String text;

    Notepad() {
        setSize(1480, 800);

        setLayout(new BorderLayout());

        JMenuBar menubar = new JMenuBar();

        JMenu file = new JMenu("File");

        JMenuItem newdoc = new JMenuItem("New");
        newdoc.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
        newdoc.addActionListener(this);

        JMenuItem open = new JMenuItem("Open");
        open.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
        open.addActionListener(this);

        JMenuItem save = new JMenuItem("Save");
        save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
        save.addActionListener(this);

        JMenuItem print = new JMenuItem("Print");
        print.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.CTRL_MASK));
        print.addActionListener(this);

        JMenuItem exit = new JMenuItem("Exit");
        exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0));
        exit.addActionListener(this);

        file.add(newdoc);
        file.add(open);
        file.add(save);
        file.add(print);
        file.add(exit);

        JMenu edit = new JMenu("Edit");

        JMenuItem copy = new JMenuItem("Copy");
        copy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));
        copy.addActionListener(this);

        JMenuItem paste = new JMenuItem("Paste");
        paste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.CTRL_MASK));
        paste.addActionListener(this);

        JMenuItem cut = new JMenuItem("Cut");
        cut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));
        cut.addActionListener(this);

        JMenuItem selectall = new JMenuItem("Select All");
        selectall.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.CTRL_MASK));
        selectall.addActionListener(this);

        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        edit.add(selectall);

        JMenu help = new JMenu("Help");

        JMenuItem about = new JMenuItem("About us");
        about.addActionListener(this);

        help.add(about);

        menubar.add(file);
        menubar.add(edit);
        menubar.add(help);

        setJMenuBar(menubar);

        area = new JTextArea();
        area.setFont(new Font("SAN_SERIF", Font.PLAIN, 20));
        area.setLineWrap(true);
        area.setWrapStyleWord(true);

        pane = new JScrollPane(area);
        pane.setBorder(BorderFactory.createEmptyBorder());
        add(pane, BorderLayout.CENTER);

    }

    public void actionPerformed(ActionEvent ae) {

        if (ae.getActionCommand().equals("New")) /*code for creating new file*/ {
            area.setText("");
        } else if (ae.getActionCommand().equals("Open")) /*code for opening file*/ {
            JFileChooser chooser = new JFileChooser("C:/Users/admin/Desktop");
            chooser.setAcceptAllFileFilterUsed(false);
            FileNameExtensionFilter restrict = new FileNameExtensionFilter("Only .txt .html .css .java files supported", "txt", "html", "css", "java");
            chooser.addChoosableFileFilter(restrict);

            int result = chooser.showOpenDialog(this);
            if (result == JFileChooser.APPROVE_OPTION) {
                File file = chooser.getSelectedFile();

                try {
                    FileReader reader = new FileReader(file);
                    BufferedReader br = new BufferedReader(reader);
                    area.read(br, null);
                    br.close();
                    area.requestFocus();
                } catch (Exception e) {
                    System.out.print(e);
                }
            }
        } else if (ae.getActionCommand().equals("Save")) /*code for saving file*/ {
            JFileChooser saveas = new JFileChooser("C:/Users/admin/Desktop");
            saveas.setApproveButtonText("Save");
            int action = saveas.showOpenDialog(this);
            if (action != JFileChooser.APPROVE_OPTION) {
                return;
            }

            File filename = new File(saveas.getSelectedFile() + ".txt");
            BufferedWriter outFile = null;
            try {
                outFile = new BufferedWriter(new FileWriter(filename));
                area.write(outFile);
            } catch (Exception e) {
            }

        } else if (ae.getActionCommand().equals("Print")) /*code for printing file*/ {
            try {
                area.print();
            } catch (Exception e) {
            }
        } else if (ae.getActionCommand().equals("Exit")) {
            System.exit(0);
        } else if (ae.getActionCommand().equals("Copy")) {
            text = area.getSelectedText();
        } else if (ae.getActionCommand().equals("Paste")) {
            area.insert(text, area.getCaretPosition());
        } else if (ae.getActionCommand().equals("Cut")) {
            text = area.getSelectedText();
            area.replaceRange("", area.getSelectionStart(), area.getSelectionEnd());
        } else if (ae.getActionCommand().equals("Select All")) {
            area.selectAll();
        }else if(ae.getActionCommand().equals("About us")){
            new About().setVisible(true);
        }

    }

    public static void main(String[] args) {
        new Notepad().setVisible(true);
    }

}
