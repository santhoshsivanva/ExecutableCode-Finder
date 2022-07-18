package com.company;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
public class Guitext extends JFrame implements ActionListener {
 private JMenuBar mb;
 private JMenu file;
 private JMenuItem open;
 private JTextArea textArea;
 private JScrollPane scrollableTextArea;
 private JTextArea textArea1;
 Guitext(){
     final JFrame frame = new JFrame("Executable code finder");
     // Display the window.
     frame.setSize(500, 500);
     frame.setVisible(true);
     frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     frame.getContentPane().setLayout(new FlowLayout());
     open = new JMenuItem("Open file");
     open.addActionListener(this);
     file = new JMenu("File");
     file.add(open);
     mb = new JMenuBar();
     mb.setBounds(0,0,2000,20);
     mb.add(file);
     textArea = new JTextArea(40,70);
     textArea.setBounds(0,10,50,50);
     scrollableTextArea = new JScrollPane(textArea);
     scrollableTextArea.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
     scrollableTextArea.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
     textArea.setBackground(Color.BLACK);
     frame.getContentPane().add(scrollableTextArea);
     Font f = new Font("Verdana",Font.PLAIN,13);
     textArea.setForeground(Color.WHITE);
     Font ff = new Font("Verdana",Font.BOLD,13);
     textArea.setFont(f);
     textArea.setLineWrap(true);
     textArea1 = new JTextArea(10,10);
     textArea1.setFont(ff);
     textArea1.setBounds(40,30,50,50);
     frame.getContentPane().add(textArea1);
     frame.getContentPane().add(mb);
     frame.getContentPane().add(textArea);
 }
    @Override
    public void actionPerformed(ActionEvent e) {
     if(e.getSource()==open){
         JFileChooser fc = new JFileChooser();
         int i = fc.showOpenDialog(this);
         if(i == JFileChooser.APPROVE_OPTION){
             File f = fc.getSelectedFile();
             String getpath = f.getPath();
             Main screen = new Main(getpath);
             Find fw = new Find();
             screen.appStart();
            try{
                BufferedReader br = new BufferedReader(new FileReader(fw.temp));
                String s1="",s2="";
                while((s1=br.readLine())!=null){
                    s2+=s1+"\n";
                }
                textArea.setText(s2);
                textArea1.setText(String.format("Total lines:%d\nExecutable lines:%d",fw.count,fw.sequence-1));
                br.close();
                fw.temp.delete();
                fw.count=1;
                fw.sequence=1;
            } catch (Exception exception) {
                exception.printStackTrace();
            }
         }
     }
    }
    public static void main(String[] args){
        Guitext om = new Guitext();
    }
}
