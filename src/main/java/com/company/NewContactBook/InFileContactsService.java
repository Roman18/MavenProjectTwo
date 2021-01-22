package com.company.NewContactBook;


import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InFileContactsService implements ContactService {

    private File file;
    private Scanner scanner;

    public InFileContactsService() {
        this.file = new File("D:\\ContactBookProject\\src\\main\\resources\\addresses.txt");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public List getAll() {
        System.out.println("=====Result from file=====");
        List<String> list = new ArrayList<>();
        try (FileInputStream fis = new FileInputStream(file)) {
            BufferedInputStream bis = new BufferedInputStream(fis);
            scanner = new Scanner(bis);
            while (scanner.hasNextLine()) {
                list.add(scanner.nextLine());
            }
        } catch (IOException e) {
            System.out.println("Problem with file");
        }
        return list;
    }

    @Override
    public void remove(String name) {
        PrintWriter pw = null;
        File newFile = new File("D:\\ContactBookProject\\src\\main\\resources\\addressesReplace.txt");
        BufferedInputStream bis = null;
        try (FileInputStream fis = new FileInputStream(file)) {
            bis = new BufferedInputStream(fis);
            pw = new PrintWriter(new FileOutputStream(newFile));
            scanner = new Scanner(bis);
            while (scanner.hasNextLine()) {
                String result = scanner.nextLine();
                if (!result.startsWith(name)) {
                    pw.write(result + "\n");
                }
            }

        } catch (IOException e) {
            System.out.println("Problem with file");
        } finally {
            if (pw != null) {
                pw.close();
            }
        }
        try (FileInputStream fis = new FileInputStream(newFile)) {
            bis = new BufferedInputStream(fis);
            pw = new PrintWriter(new FileOutputStream(this.file));
            scanner = new Scanner(bis);
            while (scanner.hasNextLine()) {
                pw.write(scanner.nextLine() + "\n");
            }
            System.out.println("The contact has been removed from list in file!");
        } catch (IOException e) {
            System.out.println("Problem with file");
        } finally {
            if (pw != null) {
                pw.close();
            }
            newFile.delete();
        }


    }

    @Override
    public void add(Contact c) {
        try (PrintWriter pw = new PrintWriter(new FileOutputStream(file, true))) {
            pw.write(c.toString() + "\n");

            pw.flush();
        } catch (IOException e) {
            System.out.println("Problem with file");
        }
    }

    @Override
    public void searchByName(String name) {
        System.out.println("=====Result from file=====");
        String result;
        try (FileInputStream fis = new FileInputStream(file)) {
            BufferedInputStream bis = new BufferedInputStream(fis);
            scanner = new Scanner(bis);
            while (scanner.hasNextLine()) {
                result = scanner.nextLine();
                if (result.startsWith(name)) {
                    System.out.println(result);
                }
            }
        } catch (IOException e) {
            System.out.println("Problem with file");
        }
    }

    @Override
    public void searchByContact(String contact) {
        System.out.println("=====Result from file=====");
        String result;
        try (FileInputStream fis = new FileInputStream(file)) {
            BufferedInputStream bis = new BufferedInputStream(fis);
            scanner = new Scanner(bis);
            while (scanner.hasNextLine()) {
                result = scanner.nextLine();
                String checked = processing(result);
                if (checked.contains(contact)) {
                    System.out.println(result);
                }
            }
        } catch (IOException e) {
            System.out.println("Problem with file");
        }
    }

    private String processing(String word) {
        return word.
                substring(word.lastIndexOf(":") + 1, word.length() - 1);
    }
}
