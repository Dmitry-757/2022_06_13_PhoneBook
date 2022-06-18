package org.dng;
import java.io.*;
import java.util.HashMap;

public class Service {
    private static String fileName = "phBook.sav";
    public static void saveBook(){
        try(ObjectOutputStream ous = new ObjectOutputStream(new FileOutputStream(fileName))){
            ous.writeObject(PhoneBook.getPhoneBookMap());
        } catch (FileNotFoundException e) {
            System.out.println("FileNotFoundException "+e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("IOException "+e.getMessage());
            e.printStackTrace();
        }
    }

    public static void readBook(){
        try(ObjectInputStream ios = new ObjectInputStream(new FileInputStream(fileName))){
            HashMap<Integer, Contact> phoneBookMap = (HashMap<Integer, Contact>) ios.readObject();
            PhoneBook.setPhoneBookMap(phoneBookMap);

        } catch (FileNotFoundException e) {
            System.out.println("FileNotFoundException "+e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("IOException "+e.getMessage());
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


}
