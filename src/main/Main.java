package main;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class Main{
    public static void main(String[] args){
        JFrame frame = new JFrame();
        frame.setSize(1000,1000);
        JPanel panel = new JPanel();
        frame.add(panel);
        frame.setLayout(null);
        frame.setVisible(true);
    }
}