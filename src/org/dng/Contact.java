package org.dng;

import java.util.HashMap;
import java.util.Map;


public class Contact implements Comparable<Contact>{
    private HashMap<Integer, String> contactPhoneMap = new HashMap<>();
    private String name;

    public Contact(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void addNewNumber(int phoneNumber, String note) throws Exception {

        if (PhoneBook.isNumberPresent(phoneNumber)){
            throw new Exception("This number is already present in phone book!");
        }

        if (contactPhoneMap.containsKey(phoneNumber)){
            throw new Exception("This contact is already present in this contact!");
        }
        contactPhoneMap.put(phoneNumber, note);
        PhoneBook.addNewRecord(phoneNumber, this);
    }

    @NonCLI
    public String getContactInfo(){
        String res = toString();

        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Integer, String> phone:contactPhoneMap.entrySet()){
            sb.append("\n phone:").append(phone.getKey()).append(" note:").append(phone.getValue());
        }
        if (!sb.isEmpty()){
            sb.append("\n}");
            res+=sb.toString();
        }

        return res;
    }

    @Override
    public String toString() {
        return "\nContact: {" +
                "name = " + name ;
    }

    @Override
    public int compareTo(Contact o) {
        return this.getName().compareTo(o.getName());
    }
}
