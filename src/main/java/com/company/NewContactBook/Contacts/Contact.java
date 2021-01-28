package com.company.NewContactBook.Contacts;

import com.company.NewContactBook.Contacts.EnumContacts;

public class Contact {
    private String name;
    private EnumContacts enumContacts;
    private String contact;

    public Contact(String name, String contact,EnumContacts enumContacts){
        this.enumContacts=enumContacts;
        this.name=name;
        this.contact=contact;
    }

    public String getName(){
        return this.name;
    }
    public String getContact(){
        return this.contact;
    }

    @Override
    public String toString() {
        return name+"["+enumContacts+":"+contact+"]";
    }
}
