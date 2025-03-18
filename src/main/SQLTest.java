package src.main;


import java.util.ArrayList;

import src.user.Admin;

public class SQLTest {
    public static void main(String[] args) {
        Admin admin = new Admin(1, "admin", null, "123", null, new ArrayList<>(), true);
        admin.loadData();
        System.out.println(admin);
    }
}
