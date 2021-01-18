package com.company.NewContactBook;



import java.util.Scanner;

public class AddContactMenuAction implements MenuAction {
    private Scanner scanner;
    private ContactService cs;

    public AddContactMenuAction(Scanner sc, ContactService cs){
        this.scanner=sc;
        this.cs=cs;
    }
    @Override
    public void doAction() {//передаём 2 аргумента для работы в памяти и работы с файлом
        while (true) {
            System.out.println("Please, enter name:");
            String name = scanner.next();
            System.out.println("Please, enter phone number:");
            String number = validNumber();
            Contact contact = new Contact(name, number);
            cs.add(contact);
            if (!closeAfter()){
                break;
            }
        }
    }
private String validNumber(){
        while (true){
            String number=scanner.next();
            if (number.matches("\\+380\\d{9}")){
              return number;
            }else {
                System.out.println("Invalid phone number\nTry again...");
            }
        }
}
    @Override
    public String getName() {// не реализовал т.к. не очень понимаю зачем метод
        return "Add contact";
    }

    @Override
    public boolean closeAfter() {//если пользователь хочет повторить операцию пусть вводит Y или y
        System.out.println("Do you want continue this operation? Y/n");
        String answer=scanner.next();
        if (answer.equals("y")||answer.equals("Y")){
            return true;
        }else{
            return false;
        }
    }
}
