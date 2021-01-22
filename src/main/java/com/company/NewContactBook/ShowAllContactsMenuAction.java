package com.company.NewContactBook;




import java.util.Scanner;

public class ShowAllContactsMenuAction implements MenuAction {
    private Scanner scanner;
    private ContactService[] cs;

    public ShowAllContactsMenuAction(Scanner scanner, ContactService[] cs) {
        this.scanner = scanner;
        this.cs = cs;
    }

    @Override
    public void doAction() {
        while (true) {
            for (int i = 0; i <cs.length ; i++) {
                System.out.println(cs[i].getAll());
            }
            if (!closeAfter()){
                break;
            }
        }
    }

    @Override
    public String getName() {
        return "Show contacts";
    }

    @Override
    public boolean closeAfter() {
        System.out.println("Do you want to continue this operation? Y/n");
        String answer = scanner.next();
        if (answer.equals("y") || answer.equals("Y")) {
            return true;
        } else {
            return false;
        }
    }
}
