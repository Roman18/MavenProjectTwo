package com.company.NewContactBook;

import com.company.NewContactBook.Menu.*;
import com.company.NewContactBook.Menu.Actions.*;
import com.company.NewContactBook.Services.ContactService;
import com.company.NewContactBook.Services.ContactsNioService;
import com.company.NewContactBook.Services.InFileContactsService;
import com.company.NewContactBook.Services.InMemoryContactsService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Menu menu = new Menu(scanner);
        ContactService[] cs = {new InMemoryContactsService(), new InFileContactsService(),new ContactsNioService()};
        menu.addAction(new AddContactMenuAction(scanner, cs));
        menu.addAction(new RemoveContactMenuAction(scanner, cs));
        menu.addAction(new ShowAllContactsMenuAction(scanner, cs));
        menu.addAction(new SearchByNameMenuAction(scanner, cs));
        menu.addAction(new SearchByContactMenuAction(scanner, cs));
        menu.run();

    }
}
