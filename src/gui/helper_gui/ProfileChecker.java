package gui.helper_gui;

import javax.swing.*;
import java.awt.*;
import cinema.Cinema;
import gui.helper_gui.FrameUI;
import user.Admin;
import user.Customer;
import user.User;
import java.util.ArrayList;

public class ProfileChecker extends FrameUI {
    private User currentUser;

    public ProfileChecker(Cinema cinema, User user) {
        super(cinema);
        this.currentUser = user;
        
        // Load user data from database
        this.currentUser.loadData();
        
        profile();
        frame.setVisible(true);
    }

    private void profile() {

        panelCenter.setLayout(new GridBagLayout());

        // Display different profiles based on user role
        if (currentUser instanceof Admin) {
            showAdminProfile();
        } else if (currentUser instanceof Customer) {
            showCustomerProfile();
        }

        // Add back button to left panel
        JButton backButton = new JButton("Back");
        backButton.setBackground(new Color(0xFFFFFF));
        backButton.setForeground(new Color(0x0C0950));
        backButton.setFocusable(false);
        backButton.addActionListener(e -> {
            frame.dispose();
        });
        panelLeft.add(backButton);

        // Refresh the UI
        panelCenter.revalidate();
        panelCenter.repaint();
        panelLeft.revalidate();
        panelLeft.repaint();
    }

    private void showAdminProfile() {
        JPanel profilePanel = new JPanel();
        profilePanel.setLayout(new BoxLayout(profilePanel, BoxLayout.Y_AXIS));
        profilePanel.setPreferredSize(new Dimension(400, 400));
        profilePanel.setBackground(new Color(0xFFFFFF));

        // Admin profile header
        JLabel headerLabel = new JLabel("Admin Profile");
        headerLabel.setFont(new Font("Arial", Font.BOLD, 24));
        headerLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Admin details
        JPanel detailsPanel = new JPanel();
        detailsPanel.setLayout(new GridLayout(0, 2, 10, 10));
        detailsPanel.setBackground(new Color(0xFFFFFF));
        detailsPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        detailsPanel.add(new JLabel("Username:"));
        detailsPanel.add(new JLabel(currentUser.getUsername()));

        detailsPanel.add(new JLabel("Email:"));
        detailsPanel.add(new JLabel(currentUser.getEmail()));

        detailsPanel.add(new JLabel("Phone:"));
        detailsPanel.add(new JLabel(currentUser.getPhone()));

        // Admin specific actions
        JPanel actionsPanel = new JPanel();
        actionsPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        actionsPanel.setBackground(new Color(0xFFFFFF));

        // Add components to profile panel
        profilePanel.add(Box.createVerticalStrut(20));
        profilePanel.add(headerLabel);
        profilePanel.add(Box.createVerticalStrut(20));
        profilePanel.add(detailsPanel);

        // Add to center panel
        panelCenter.add(profilePanel);
    }

    private void showCustomerProfile() {
        Customer customer = (Customer) currentUser;
        
        JPanel profilePanel = new JPanel();
        profilePanel.setLayout(new BoxLayout(profilePanel, BoxLayout.Y_AXIS));
        profilePanel.setPreferredSize(new Dimension(400, 400));
        profilePanel.setBackground(new Color(0xFFFFFF));

        // Customer profile header
        JLabel headerLabel = new JLabel("Customer Profile");
        headerLabel.setFont(new Font("Arial", Font.BOLD, 24));
        headerLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Customer details
        JPanel detailsPanel = new JPanel();
        detailsPanel.setLayout(new GridLayout(0, 2, 10, 10));
        detailsPanel.setBackground(new Color(0xFFFFFF));
        detailsPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        detailsPanel.add(new JLabel("Username:"));
        detailsPanel.add(new JLabel(customer.getUsername()));

        detailsPanel.add(new JLabel("Email:"));
        detailsPanel.add(new JLabel(customer.getEmail()));

        detailsPanel.add(new JLabel("Phone:"));
        detailsPanel.add(new JLabel(customer.getPhone()));

        detailsPanel.add(new JLabel("Wallet Balance:"));
        detailsPanel.add(new JLabel(String.format("$%.2f", customer.getBalance())));

        detailsPanel.add(new JLabel("Membership Level:"));
        detailsPanel.add(new JLabel(customer.getMembershipLevel()));

        detailsPanel.add(new JLabel("Favorite Genre:"));
        detailsPanel.add(new JLabel(customer.getFavoriteGenre() != null ? customer.getFavoriteGenre() : "Not specified"));

        // Add components to profile panel
        profilePanel.add(Box.createVerticalStrut(20));
        profilePanel.add(headerLabel);
        profilePanel.add(Box.createVerticalStrut(20));
        profilePanel.add(detailsPanel);
        profilePanel.add(Box.createVerticalStrut(20));

        // Add to center panel
        panelCenter.add(profilePanel);
    }

    public static void main(String[] args) {
        Cinema cinema = new Cinema("Cineplex", "Phnom Penh", 5);
        
        Admin admin = new Admin(1, "admin1", "admin", "password", "1234567890", new ArrayList<>(), false);
        new ProfileChecker(cinema, admin);

        Customer customer = new Customer(2, "customer1", "customer", "customer1", "0987654321", 50.00, "Gold", "Action", false);
        new ProfileChecker(cinema, customer);
    }
}