package com.company.NewContactBook;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

public class ContactsNioService implements ContactService {
    private Path path;

    public ContactsNioService() {
        this.path = Paths.get("D:\\ContactBookProject\\src\\main\\resources\\addressesNio.txt");
        if(!Files.exists(this.path)){
            try {
                this.path=Files.createFile(this.path);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<Contact> getAll() {
        System.out.println("=====Result from NIO file=====");
        List<Contact> listOfContacts = new ArrayList<>();
        try (FileChannel fileChannel = (FileChannel) Files.newByteChannel(this.path)) {
            ByteBuffer byteBuffer = ByteBuffer.allocate((int) fileChannel.size());
            fileChannel.read(byteBuffer);
            byteBuffer.flip();
            String stringBuffer = "";
            for (int i = 0; i < fileChannel.size(); i++) {
                char ch = (char) byteBuffer.get();
                if (ch == ']') {
                    stringBuffer += ch;
                    String[] answer = stringBuffer.trim().split("\\[|:");
                    listOfContacts.add(new Contact(answer[0],
                            answer[2].substring(0, answer[2].length() - 1),
                            answer[1].equals("email") ? EnumContacts.email : EnumContacts.phone));
                    stringBuffer = "";
                } else {
                    stringBuffer += ch;
                }
            }
            byteBuffer.clear();

        } catch (IOException e) {
            e.printStackTrace();

        }
        return listOfContacts;
    }

    @Override
    public void remove(String name) {
        Path pathBuffer=null;
        try (FileChannel fileChannel = (FileChannel) Files.newByteChannel(this.path)) {
            pathBuffer=Files.createFile(Paths.get("D:\\ContactBookProject\\src\\main\\resources\\addressesNioBuffer.txt"));
            ByteBuffer byteBuffer = ByteBuffer.allocate((int) fileChannel.size());
            fileChannel.read(byteBuffer);
            byteBuffer.flip();
            String stringBuffer = "";
            for (int i = 0; i < fileChannel.size(); i++) {
                char ch = (char) byteBuffer.get();
                if (ch == ']') {
                    stringBuffer += ch;
                    stringBuffer=stringBuffer.trim();
                    if (!stringBuffer.startsWith(name)) {
                        stringBuffer += '\n';
                        Files.write(pathBuffer, stringBuffer.getBytes(),StandardOpenOption.APPEND);
                    }
                    stringBuffer = "";
                } else {
                    stringBuffer += ch;
                }
            }
            byteBuffer.clear();

        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            if (pathBuffer != null) {
                Files.copy(pathBuffer,this.path, StandardCopyOption.REPLACE_EXISTING);
                Files.delete(pathBuffer);
            }
            System.out.println("The contact has been removed from list in nio file!");
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void add(Contact c) {
        String result = c.toString() + "\n";
        try {
            Files.write(this.path, result.getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<Contact> searchByName(String name) {
        System.out.println("=====Result from NIO file=====");
        List<Contact> listOfContacts = new ArrayList<>();
        try (FileChannel fileChannel = (FileChannel) Files.newByteChannel(this.path)) {
            ByteBuffer byteBuffer = ByteBuffer.allocate((int) fileChannel.size());
            fileChannel.read(byteBuffer);
            byteBuffer.flip();
            String stringBuffer = "";
            for (int i = 0; i < fileChannel.size(); i++) {
                char ch = (char) byteBuffer.get();
                if (ch == ']') {
                    stringBuffer=stringBuffer.trim();
                    if (stringBuffer.startsWith(name)) {
                        String[] answer = stringBuffer.split("\\[|:");
                        listOfContacts.add(new Contact(answer[0],
                                answer[2],
                                answer[1].equals("email") ? EnumContacts.email : EnumContacts.phone));
                    }
                    stringBuffer = "";
                } else {
                    stringBuffer += ch;
                }
            }
            byteBuffer.clear();

        } catch (IOException e) {
            e.printStackTrace();

        }
        return listOfContacts;
    }

    @Override
    public List<Contact> searchByContact(String contact) {
        System.out.println("=====Result from NIO file=====");
        List<Contact> listOfContacts = new ArrayList<>();
        try (FileChannel fileChannel = (FileChannel) Files.newByteChannel(this.path)) {
            ByteBuffer byteBuffer = ByteBuffer.allocate((int) fileChannel.size());
            fileChannel.read(byteBuffer);
            byteBuffer.flip();
            String stringBuffer = "";
            for (int i = 0; i < fileChannel.size(); i++) {
                char ch = (char) byteBuffer.get();
                if (ch == ']') {
                    String[] answer = stringBuffer.trim().split("\\[|:");
                    if (answer[2].contains(contact)) {
                        listOfContacts.add(new Contact(answer[0],
                                answer[2],
                                answer[1].equals("email") ? EnumContacts.email : EnumContacts.phone));
                    }
                    stringBuffer = "";
                } else {
                    stringBuffer += ch;
                }
            }
            byteBuffer.clear();

        } catch (IOException e) {
            e.printStackTrace();

        }
        return listOfContacts;
    }
}
