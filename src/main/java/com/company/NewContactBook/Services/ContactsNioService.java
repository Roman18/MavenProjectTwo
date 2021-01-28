package com.company.NewContactBook.Services;

import com.company.NewContactBook.Contacts.Contact;
import com.company.NewContactBook.ProcessingFileNio.ParseString;
import com.company.NewContactBook.ProcessingFileNio.ReaderNioFile;

import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;


public class ContactsNioService implements ContactService {
    private Path path;
    private ParseString parseString;
    private ReaderNioFile readerNioFile;

    public ContactsNioService() {
        this.path = Paths.get("D:\\ContactBookProject\\src\\main\\resources\\addressesNio.txt");
        this.readerNioFile = new ReaderNioFile(this.path);
        this.parseString = new ParseString();
        if (!Files.exists(this.path)) {
            try {
                this.path = Files.createFile(this.path);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<Contact> getAll() {
        System.out.println("=====Result from NIO file=====");
        List<Contact> listOfContacts = new ArrayList<>();
        readerNioFile.readByLine(s ->
                listOfContacts.add(parseString.toContact(s))
        );
        return listOfContacts;
    }

    @Override
    public void remove(String name) {
        try {
            Path pathBuffer = Files.createFile(Paths.get("D:\\ContactBookProject\\src\\main\\resources\\addressesNioBuffer.txt"));
            readerNioFile.readByLine(s -> {
                if (!s.startsWith(name)) {
                    try {
                        Files.write(pathBuffer, s.getBytes(), StandardOpenOption.APPEND);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            Files.copy(pathBuffer, this.path, StandardCopyOption.REPLACE_EXISTING);
            Files.delete(pathBuffer);
            System.out.println("The contact has been removed from list in nio file!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void add(Contact c) {
        try {
            Files.write(this.path,
                    this.parseString.parseToString(c).getBytes(),
                    StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<Contact> searchByName(String name) {
        System.out.println("=====Result from NIO file=====");
        List<Contact> listOfContacts = new ArrayList<>();
        readerNioFile.readByLine(s -> {
            if (s.startsWith(name)) {
                listOfContacts.add(parseString.toContact(s));
            }
        });
        return listOfContacts;
    }

    @Override
    public List<Contact> searchByContact(String contact) {
        System.out.println("=====Result from NIO file=====");
        List<Contact> listOfContacts = new ArrayList<>();
        readerNioFile.readByLine(s -> {
            Contact contactParse = parseString.toContact(s);
            if (contactParse.getContact().contains(contact)) {
                listOfContacts.add(contactParse);
            }
        });
        return listOfContacts;
    }
}
