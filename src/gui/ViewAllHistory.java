package gui;

public class ViewAllHistory extends CustomerHistory {
    public ViewAllHistory() {
        viewOwnHistory.setEnabled(false);
    }
    public static void main(String[] args) {
        new ViewAllHistory();
    }

}
