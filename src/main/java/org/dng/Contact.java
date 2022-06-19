package org.dng;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;


public class Contact implements Serializable, Comparable<Contact>{
//    private final HashMap<Integer, String> contactPhoneMap = new HashMap<>();
    private HashMap<Integer, String> contactPhoneMap;
    private String name;

    public Contact(String name) {
        this.name = name;
        this.contactPhoneMap = new HashMap<>();
    }

    //need  for deserialization by objectMapper.readValue(fis, new TypeReference<HashMap<Integer, Contact>>() {});
    public Contact() {
        this.name = "name";
        this.contactPhoneMap = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonIgnore//without this annotation jackson will search property with name `PhoneNumberList`
    public List<Integer> getPhoneNumberList(){
        return contactPhoneMap.keySet().stream().toList();
    }

    public HashMap<Integer, String> getContactPhoneMap() {
        return contactPhoneMap;
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


//    public String getContactInfo(){
public String contactInfo(){
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Contact)) return false;
        Contact contact = (Contact) o;
        return name.equals(contact.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
