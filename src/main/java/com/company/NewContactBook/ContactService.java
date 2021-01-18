package com.company.NewContactBook;

import java.util.List;

public interface ContactService {
    List getAll();
    void remove(String name);
    void add(Contact c);
    void searchByName(String name);
    void searchByPhone(String number);
}
