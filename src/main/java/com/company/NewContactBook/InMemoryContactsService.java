package com.company.NewContactBook;


import java.util.ArrayList;
import java.util.List;

public class InMemoryContactsService implements ContactService {
    private List<Contact> list;

    public InMemoryContactsService() {
        this.list = new ArrayList<>();
    }


    @Override
    public List getAll() {
        return list;
    }

    @Override
    public void remove(String name) {
        int oldSize = list.size();
        for (int i = 0; i <list.size() ; i++) {
            if (name.equals(list.get(i).getName())){
                list.remove(i);
                break;
            }
        }
        int newSize = list.size();
        if (oldSize-newSize != 1) {
            System.out.println("Name is not exist in memory");
        } else {
            System.out.println("The contact has been removed from list in memory!");
        }
    }

    @Override
    public void add(Contact c) {
        list.add(c);
    }

    @Override
    public void searchByName(String name) {
        list.stream().
                filter(s->s.getName().startsWith(name)).
                forEach(System.out::println);

    }

    @Override
    public void searchByPhone(String phone) {
        list.stream().
                filter(s->s.getPhone().contains(phone)).
                forEach(System.out::println);
    }

}
