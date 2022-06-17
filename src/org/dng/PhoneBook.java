package org.dng;


import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.List;

@CodeVersion(version="0.1",description="phone book realization",lastModification="2022.06.06")
public class PhoneBook {
    private static HashMap<Integer, Contact> phoneBookMap = new HashMap<>();
    //private static HashMap<String, Contact> contactBookMap = new HashMap<>();

    public static HashMap<Integer, Contact> getPhoneBookMap() {
        return phoneBookMap;
    }

    public static void setPhoneBookMap(HashMap<Integer, Contact> phoneBookMap) {
        PhoneBook.phoneBookMap = phoneBookMap;
    }

    public static void clear() {
        phoneBookMap.clear();
    }

    public static boolean isNumberPresent(int phoneNumber){
        return phoneBookMap.containsKey(phoneNumber);
    }

    public static void addNewRecord(int phoneNumber, @NotNull Contact contact) throws Exception {
        if (phoneBookMap.containsKey(phoneNumber)){
            throw new Exception("This number is already present!");
        }
        phoneBookMap.put(phoneNumber, contact);
        //contactBookMap.put(contact.getName(), phoneNumber);
    }


    public static Contact getContactByPhoneNumber(int phoneNumber) throws Exception {
        if (!phoneBookMap.containsKey(phoneNumber))
            throw new Exception("number "+phoneNumber+" is not found!");
        return phoneBookMap.get(phoneNumber);
    }

    public static List<Contact> getContactByName(@NotNull String name) throws Exception {
        return phoneBookMap.entrySet().stream()
                .filter(es -> name.equals(es.getValue().getName()))
                .map(v -> v.getValue())
                .toList();
//                .collect(Collectors.toMap(Map.Entry::getKey,Map.Entry::getValue));

//        if (!contactBookMap.containsKey(name))
//            throw new Exception("name "+name+" is not found!");
//        int phoneNumber = contactBookMap.get(name);
//
//        return getContactByPhoneNumber(phoneNumber);
    }

    public static void printContactByPhone( int phoneNumber){
        Contact someBody = null;
        try {
            someBody = PhoneBook.getContactByPhoneNumber(phoneNumber);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        if (someBody!=null)
            System.out.println("the phone number "+phoneNumber+" belongs to "+someBody.getName());
    }

    public static void printContactByName(String name){

        List<Contact> someBody = null;
        try {
            someBody = getContactByName(name);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        if (someBody.size()!=0){
            someBody.forEach(v -> System.out.println(v.getContactInfo()));
            //System.out.println(someBody.getContactInfo());
        }
        else
            System.out.println("Contact with name `"+name+"` was not find");
    }

    public static void printAllBook(){
        getPhoneBookMap()
                .values()
                .stream()
                .distinct()
                .toList()
                .stream().sorted()
                .forEach(v -> System.out.println(v.getContactInfo()))
        ;

    }

}
