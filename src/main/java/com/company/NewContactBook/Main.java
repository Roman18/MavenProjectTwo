package com.company.NewContactBook;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Menu menu = new Menu(scanner);
        ContactService[] cs = {new InMemoryContactsService(), new InFileContactsService()};
        menu.addAction(new AddContactMenuAction(scanner, cs));
        menu.addAction(new RemoveContactMenuAction(scanner, cs));
        menu.addAction(new ShowAllContactsMenuAction(scanner, cs));
        menu.addAction(new SearchByNameMenuAction(scanner, cs));
        menu.addAction(new SearchByContactMenuAction(scanner, cs));
        menu.run();

    }
}
