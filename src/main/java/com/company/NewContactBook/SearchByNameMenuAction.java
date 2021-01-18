package com.company.NewContactBook;

import java.util.Scanner;

public class SearchByNameMenuAction implements MenuAction {
    private Scanner scanner;
    private ContactService cs;

    public SearchByNameMenuAction(Scanner sc, ContactService cs) {
        this.scanner = sc;
        this.cs = cs;

    }

    @Override
    public void doAction() {
        while (true) {
            System.out.println("Please, enter name");
            String name=scanner.nextLine();
            cs.searchByName(name);
            if (!closeAfter()) {
                break;
            }
        }
    }

    @Override
    public String getName() {
        return "Search by name";
    }

    @Override
    public boolean closeAfter() {
        System.out.println("Do you want continue this operation? Y/n");
        String answer = scanner.nextLine();
        if (answer.equals("y") || answer.equals("Y")) {
            return true;
        } else {
            return false;
        }
    }
}
