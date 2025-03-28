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
        frame.setBounds(300, 100, 1000, 650);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(true);
        frame.setTitle(cinema.getName());
        frame.setLayout(new BorderLayout());

        panelTop = new JPanel();
        panelLeft = new JPanel();
        panelCenter = new JPanel();
        panelBottom = new JPanel();

        panelTop.setBackground(new Color(0x0C0950));
        panelTop.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 30));
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
        label.setText(cinema.getName() + " Cinema Management System");
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Arial", Font.BOLD, 30));
        label.setIconTextGap(10);
        panelTop.add(label);

        // Add panels to frame
        frame.add(panelTop, BorderLayout.NORTH);
        frame.add(panelLeft, BorderLayout.WEST);
        frame.add(panelCenter, BorderLayout.CENTER);
        frame.add(panelBottom, BorderLayout.SOUTH);
        frame.setVisible(true);

        ImageIcon icon = new ImageIcon("src/gui/image/image.png");
        frame.setIconImage(icon.getImage());
    }

    public void updateTopPanel(JPanel newTopPanel) {
        frame.remove(panelTop);
        panelTop = newTopPanel;
        frame.add(panelTop, BorderLayout.NORTH);
        frame.revalidate();
        frame.repaint();
    }

    // Method to clear and update the center panel
    protected void updateCenterPanel(JPanel newPanel) {
        panelCenter.removeAll(); // Clear all existing components
        panelCenter.setLayout(new BorderLayout()); // Ensure the layout is set
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

    // Method to clear and update the bottom panel
    protected void updateBottomPanel(JPanel newPanel) {
        panelBottom.removeAll(); // Clear all existing components
        panelBottom.setLayout(new BorderLayout()); // Ensure the layout is set
        panelBottom.add(newPanel, BorderLayout.CENTER); // Add the new panel
        panelBottom.revalidate(); // Refresh the layout
        panelBottom.repaint(); // Redraw the panel
    }

    public static void main(String[] args) {
        // new FrameUI();
    }
}
