package com.company.NewContactBook;


import java.util.Arrays;
import java.util.Scanner;

public class Menu {
    private MenuAction[] actions;
    private Scanner sc;

    public Menu(Scanner sc) {
        this.actions = new MenuAction[0];
        this.sc = sc;
    }

    public void addAction(MenuAction ma) {
        actions = Arrays.copyOf(actions, actions.length + 1);
        actions[actions.length - 1] = ma;
    }

    public void run() {
        while (true) {
            showMenu();
            int choose = sc.nextInt();
            sc.nextLine();
            if (choose == 6) {
                break;
            }
            if (validIndex(choose)) {
                actions[choose - 1].doAction();
            } else {
                System.out.println("Invalid index...");
            }
        }
    }

    private void showMenu() {
        for (int i = 0; i < actions.length; i++) {
            System.out.println(i + 1 + " - " + actions[i].getName());
        }
        System.out.println("6 - Exit");

    }

    private boolean validIndex(int index) {
        if (index <= 0 || index > actions.length) {
            return false;
        }
        return true;
    }
}
