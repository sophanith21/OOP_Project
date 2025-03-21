package gui.helper_gui;

import javax.swing.JButton;
import javax.swing.JPanel;

import java.awt.Color;
import cinema.Cinema;
import user.*;


public class CustomerHistory extends CustomerMovie {
    protected JPanel historyPanel;
    public CustomerHistory(Cinema cinema, Customer customer) {
        super(cinema, customer);
        viewOwnHistory.setEnabled(false);
        addMovie.setEnabled(true);
        updateCenterPanel(historyPanel);
    }
    public static void main(String[] args) {

        //new CustomerHistory(cinema, customer);
    }
}
