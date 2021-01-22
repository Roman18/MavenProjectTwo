package com.company.NewContactBook;


import java.util.Scanner;

public class AddContactMenuAction implements MenuAction {
    private Scanner scanner;
    private ContactService[] cs;

    public AddContactMenuAction(Scanner sc, ContactService[] cs) {
        this.scanner = sc;
        this.cs = cs;
    }

    @Override
    public void doAction() {
        while (true) {
            System.out.println("Please, enter name:");
            String name = scanner.next();
            System.out.println("Please, enter phone number or email:");
            String info = validNumber();
            Contact contact = info.matches("\\+380\\d{9}") ?
                    new Contact(name, info, EnumContacts.phone) :
                    new Contact(name, info, EnumContacts.email);
            for (int i = 0; i < cs.length; i++) {
                cs[i].add(contact);
            }
            if (!closeAfter()) {
                break;
            }
        }
    }

    private String validNumber() {
        while (true) {
            String contact = scanner.next();
            if (contact.matches("\\+380\\d{9}")) {
                return contact;
            } else if (contact.matches("([\\w]+)(@)((gmail\\.com)|(mail\\.ru)|(ukr\\.net))")) {
                return contact;
            } else {
                System.out.println("Invalid contact\nTry again...");
            }
        }
    }

    @Override
    public String getName() {
        return "Add contact";
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
