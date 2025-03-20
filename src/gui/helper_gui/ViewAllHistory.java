package gui.helper_gui;

import javax.swing.JPanel;

public class ViewAllHistory extends AdminMovie {
    JPanel allHistory;
    public ViewAllHistory() {
        viewAllHistory.setEnabled(false);
        crudMovie.setEnabled(true);
        crudMovie.addActionListener(e -> {
            frame.dispose();
            new AdminMovie();
        });


        updateCenterPanel(allHistory);
    }
    public static void main(String[] args) {
        new ViewAllHistory();
    }

}
