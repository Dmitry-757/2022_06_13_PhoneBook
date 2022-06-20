package org.dng;

import java.util.HashMap;

/**
 1. Написать программу «Телефонная книга». иметь возможность создавать и сохранять новые контакты.
 Просматривать ранее сохраненные имена. Искать контакт по номеру телефона или по имени.
 Телефонная книга состоит из контактов, каждый из которых представляет пару имя контакта:объект контакта.
 В каждом объекте контакта существуют разные номер: домашний, рабочий и т.д.
 */

public class Main {
    //public static PhoneBook phoneBook = PhoneBook.getInstance();

    public static void main(String[] args) {
        Contact contact1 = new Contact("Pupkin");
        try {
            contact1.addNewNumber(11111, "home");
            contact1.addNewNumber(22222,"work");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        Contact contact2 = new Contact("Sidorov");
        try {
            contact2.addNewNumber(33333, "official");
            contact2.addNewNumber(44444,"mobile");
            contact2.addNewNumber(34343,"mobile");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        Contact contact3 = new Contact("Arbuzov");
        try {
            contact3.addNewNumber(55555, "official");
            contact3.addNewNumber(12345,"mobile");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println("\nlet`s print all phone book:");
        PhoneBook.printAllBook();

        System.out.println("let`s try to find owner of phone 33333 (it is Sidorov)");
        PhoneBook.printContactByPhone(33333);
        System.out.println();
        System.out.println("let`s try to find owner of phone 333332 (it is not exist)");
        PhoneBook.printContactByPhone(333332);
        System.out.println();
        System.out.println("let`s try to find contact by name `Pupkin`");
        PhoneBook.printContactByName("Pupkin");
        System.out.println();
        System.out.println("let`s try to find non-existent contact by name `Popkin`");
        PhoneBook.printContactByName("Popkin");

        System.out.println("**********************************************");
        System.out.println("lets try to delete 'Pupkin'");
        PhoneBook.deleteRecord("Pupkin");
        PhoneBook.printAllBook();


        //************************ Simple FileIO Section *********************************************
        System.out.println("************************************************");
        System.out.println("save our book to file...");
        Service.saveBook();
        System.out.println("now let`s to clear book...");
        PhoneBook.clear();
        System.out.println("\nlet`s print all phone book:");
        PhoneBook.printAllBook();

        System.out.println("************************************************");
        System.out.println("read book from file...");
        Service.readBook();
        System.out.println("\nlet`s print all phone book:");
        PhoneBook.printAllBook();
        //************************ Simple FileIO Section *********************************************
        //********************************************************************************************

        //************************ Serealising/Deserializing to/from JSON and FileIO Section *********
        System.out.println("************************************************");
        System.out.println("lets convert phoneBook to JSON");
        System.out.println(ServiceJSON.getJSON(PhoneBook.getPhoneBookMap()));

        System.out.println("************************************************");
        System.out.println("convert our book to JSON and save to file...");
        ServiceJSON.writeJSONToFile(PhoneBook.getPhoneBookMap());
        System.out.println("now let`s to clear book...");

        PhoneBook.clear();
        System.out.println("\nlet`s print all phone book:");
        PhoneBook.printAllBook();

        System.out.println("************************************************");
        System.out.println("read JSON from file and try to convert it to phoneBookMap");
        HashMap<Integer, Contact> hm = ServiceJSON.readJSONFromFile();
        if (hm != null)
            PhoneBook.setPhoneBookMap(hm);
        System.out.println("\nlet`s print all phone book:");
        PhoneBook.printAllBook();

        System.out.println("***************************************************************");
        System.out.println("convert our book to JSON and save to file by FileWriter...");
        ServiceJSON.writeJSONToFile2(PhoneBook.getPhoneBookMap());
        System.out.println("now let`s to clear book...");

        PhoneBook.clear();
        System.out.println("\nlet`s print all phone book:");
        PhoneBook.printAllBook();

        System.out.println("************************************************");
        System.out.println("read JSON from file by FileReader and try to convert it to phoneBookMap");
        HashMap<Integer, Contact> hm2 = ServiceJSON.readJSONFromFile2();
        if (hm != null)
            PhoneBook.setPhoneBookMap(hm2);
        System.out.println("\nlet`s print all phone book:");
        PhoneBook.printAllBook();


        //************************ Serealising/Deserializing to/from JSON and FileIO Section *********
        //********************************************************************************************

    }
}
