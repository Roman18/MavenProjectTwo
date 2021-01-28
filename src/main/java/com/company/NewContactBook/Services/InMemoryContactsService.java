package com.company.NewContactBook.Services;


import com.company.NewContactBook.Contacts.Contact;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class InMemoryContactsService implements ContactService {
    private List<Contact> list;

    public InMemoryContactsService() {
        this.list = new ArrayList<>();
    }


    @Override
    public List<Contact> getAll() {
        System.out.println("=====Result from memory=====");
        return list;
    }

    @Override
    public void remove(String name) {
        int oldSize = list.size();
        for (int i = 0; i < list.size(); i++) {
            if (name.equals(list.get(i).getName())) {
                list.remove(i);
                break;
            }
        }
        int newSize = list.size();
        if (oldSize - newSize != 1) {
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
    public List<Contact> searchByName(String name) {
        System.out.println("=====Result from memory=====");
        return list.stream().
        filter(s -> s.getName().startsWith(name)).
        collect(Collectors.toList());

    }

    @Override
    public List<Contact> searchByContact(String contact) {
        System.out.println("=====Result from memory=====");
        return list.stream().
                filter(s -> s.getContact().contains(contact)).
                collect(Collectors.toList());
    }


}
