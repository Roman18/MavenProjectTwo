package com.company.NewContactBook.ProcessingFileNio;

import com.company.NewContactBook.Contacts.Contact;
import com.company.NewContactBook.Contacts.EnumContacts;

public class ParseString {

    public Contact toContact(String stringContact){
        String[] answer = stringContact.trim().split("\\[|:");
        return new Contact(answer[0],
                answer[2].substring(0, answer[2].length() - 1),
                answer[1].equals("email") ? EnumContacts.email : EnumContacts.phone);
    }


    public String parseToString(Contact contact){
        return contact.toString()+"\n";

    }
}
