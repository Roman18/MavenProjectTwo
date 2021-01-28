package com.company.NewContactBook.Menu.Actions;


import com.company.NewContactBook.Menu.Actions.MenuAction;
import com.company.NewContactBook.Services.ContactService;

import java.util.Scanner;

public class RemoveContactMenuAction implements MenuAction {

    private Scanner scanner;
    private ContactService[] cs;

    public RemoveContactMenuAction(Scanner sc, ContactService[] cs) {
        this.scanner = sc;
        this.cs = cs;

    }

    @Override
    public void doAction() {
        while (true) {
            System.out.println("Please, enter name:");
            String name = scanner.next();
            for (int i = 0; i < cs.length; i++) {
                cs[i].remove(name);
            }
            if (!closeAfter()) {
                break;
            }
        }
    }

    @Override
    public String getName() {
        return "Remove contact";
    }

    @Override
    public boolean closeAfter() {
        System.out.println("Do you want continue this operation? Y/n");
        String answer = scanner.next();
        if (answer.equals("y") || answer.equals("Y")) {
            return true;
        } else {
            return false;
        }
    }
}
