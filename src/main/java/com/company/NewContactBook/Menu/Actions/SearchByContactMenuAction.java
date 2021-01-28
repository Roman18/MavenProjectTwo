package com.company.NewContactBook.Menu.Actions;

import com.company.NewContactBook.Menu.Actions.MenuAction;
import com.company.NewContactBook.Services.ContactService;

import java.util.Scanner;

public class SearchByContactMenuAction implements MenuAction {
    private Scanner scanner;
    private ContactService[]cs;
    public SearchByContactMenuAction(Scanner sc, ContactService[] cs) {
        this.scanner = sc;
        this.cs=cs;

    }
    @Override
    public void doAction() {
        while (true) {
            System.out.println("Please, enter part of contact");
            String contact=validNumber();
            for (int i = 0; i <cs.length ; i++) {
                System.out.println(cs[i].searchByContact(contact));
            }
            if (!closeAfter()) {
                break;
            }
        }
    }

    private String validNumber(){
        while (true){
            String contact=scanner.nextLine();
            if (!contact.equals(" ")){
                return contact;
            }else {
                System.out.println("Invalid contact\nTry again...");
            }
        }
    }
    @Override
    public String getName() {
        return "Search by contact";
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
