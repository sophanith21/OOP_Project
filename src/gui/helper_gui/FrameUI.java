package gui.helper_gui;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import cinema.Cinema;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

public class FrameUI {
    protected JFrame frame;
    protected JPanel panelTop;
    protected JPanel panelLeft;
    protected JPanel panelCenter;
    protected JPanel panelRight;
    protected JPanel panelBottom;
    protected JButton next;
    protected JLabel label;
    public FrameUI(Cinema cinema) {
        frame = new JFrame();
        frame.setBounds(300, 100, 700, 650);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setTitle(cinema.name);
        frame.setLayout(new BorderLayout());
        
        panelTop = new JPanel();
        panelLeft = new JPanel();
        panelCenter = new JPanel();
        panelBottom = new JPanel();

        panelTop.setBackground(new Color(0x0C0950));
        panelTop.setLayout(new FlowLayout(FlowLayout.CENTER,0,30));
        panelLeft.setBackground(new Color(0x261FB3));
        panelCenter.setBackground(new Color(0xFFFFFF));
        panelBottom.setBackground(new Color(0x0C0950));

        panelTop.setPreferredSize(new Dimension(100, 100));
        panelLeft.setPreferredSize(new Dimension(100, 100));
        panelCenter.setPreferredSize(new Dimension(100, 100));
        panelBottom.setPreferredSize(new Dimension(100, 100));

        panelCenter.setLayout(null);

        // Top panel
        label = new JLabel();
        label.setText(cinema.name);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Arial", Font.BOLD, 30));
        label.setIconTextGap(10);
        label.setBounds(frame.getWidth()/2-(label.getText().length()/2),panelTop.getHeight()/2,100,50);
        panelTop.add(label);

        // Add panels to frame
        frame.add(panelTop,BorderLayout.NORTH);
        frame.add(panelLeft,BorderLayout.WEST);
        frame.add(panelCenter,BorderLayout.CENTER);
        frame.add(panelBottom,BorderLayout.SOUTH);
        frame.setVisible(true);

        ImageIcon icon = new ImageIcon("src/gui/image/image.png");
        frame.setIconImage(icon.getImage());
    }
    // Method to clear and update the center panel
    protected void updateCenterPanel(JPanel newPanel) {
        panelCenter.removeAll(); // Clear all existing components
        panelCenter.add(newPanel, BorderLayout.CENTER); // Add the new panel
        panelCenter.revalidate(); // Refresh the layout
        panelCenter.repaint(); // Redraw the panel
    }
    // Method to clear and update the Left panel
    protected void updateLeftPanel(JPanel newPanel) {
        panelLeft.removeAll(); // Clear all existing components
        panelLeft.add(newPanel, BorderLayout.WEST); // Add the new panel
        panelLeft.revalidate(); // Refresh the layout
        panelLeft.repaint(); // Redraw the panel
    }
    
    public static void main(String[] args) {
        //new FrameUI();
    }
}
