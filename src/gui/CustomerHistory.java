package gui;

import javax.swing.JButton;
import javax.swing.JPanel;

import java.awt.Color;

public class CustomerHistory extends CustomerMovie {
    protected JPanel historyPanel;
    public CustomerHistory() {
        viewOwnHistory.setEnabled(false);
        addMovie.setEnabled(true);
        updateCenterPanel(historyPanel);
    }
    public static void main(String[] args) {
        new CustomerHistory();
    }
}
