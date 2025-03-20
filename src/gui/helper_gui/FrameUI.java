package gui.helper_gui;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

public class FrameUI {
    protected JFrame frame;
    protected JPanel panelTop;
    protected JPanel panelLeft;
    protected JPanel panelCenter;
    protected JPanel panelRight;
    protected JPanel panelBottom;
    protected JButton next;
    protected JLabel label;
    public FrameUI() {
        frame = new JFrame();
        frame.setBounds(300, 100, 700, 650);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setTitle("Cineplex");
        frame.setLayout(new BorderLayout(5,5));
        
        panelTop = new JPanel();
        panelLeft = new JPanel();
        panelCenter = new JPanel();
        panelBottom = new JPanel();

        panelTop.setBackground(new Color(0xFF3D00));
        panelLeft.setBackground(new Color(0xFFD600));
        panelCenter.setBackground(new Color(0xFFF9E6));
        panelBottom.setBackground(new Color(0xFF3D00));

        panelTop.setPreferredSize(new Dimension(100, 100));
        panelLeft.setPreferredSize(new Dimension(100, 100));
        panelCenter.setPreferredSize(new Dimension(100, 100));
        panelBottom.setPreferredSize(new Dimension(100, 100));

        panelCenter.setLayout(null);

        // Top panel
        label = new JLabel();
        label.setText("Cineplex");
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Arial", Font.BOLD, 30));
        label.setIconTextGap(10);
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
        new FrameUI();
    }
}
