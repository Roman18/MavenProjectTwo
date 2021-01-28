package com.company.NewContactBook.Services;

import com.company.NewContactBook.Contacts.Contact;

import java.util.List;

public interface ContactService {
    List<Contact> getAll();
    void remove(String name);
    void add(Contact c);
    List<Contact> searchByName(String name);
    List<Contact> searchByContact(String contact);

}
