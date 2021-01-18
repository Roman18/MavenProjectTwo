package com.company.NewContactBook;

import java.util.Scanner;

public class SearchByPhoneMenuAction implements MenuAction {
    private Scanner scanner;
    private ContactService cs;
    public SearchByPhoneMenuAction(Scanner sc, ContactService cs) {
        this.scanner = sc;
        this.cs=cs;

    }
    @Override
    public void doAction() {
        while (true) {
            System.out.println("Please, enter part of number");
            String name=validNumber();
            cs.searchByPhone(name);
            if (!closeAfter()) {
                break;
            }
        }
    }

    private String validNumber(){
        while (true){
            String number=scanner.nextLine();
            if (number.matches("(\\+380)?\\d{0,9}")&&!number.equals(" ")){
                return number;
            }else {
                System.out.println("Invalid phone number\nTry again...");
            }
        }
    }
    @Override
    public String getName() {
        return "Search by phone number";
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
