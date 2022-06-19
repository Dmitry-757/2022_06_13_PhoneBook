package org.dng;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;

public class ServiceJSON {
    private static String fileName = "json.sav";
    private static ObjectMapper objectMapper = new ObjectMapper();



    public static String getJSON(Object item) {

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = null;

        {
            try {
                jsonString = objectMapper.writeValueAsString(item);
                //System.out.println(jsonString);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        return jsonString;
    }

    public static void writeJSONToFile(Object item) {

        //ObjectMapper objectMapper = new ObjectMapper();

        {
            try(FileOutputStream fos = new FileOutputStream(fileName,false)) {
                objectMapper.writeValue(fos, item);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            } catch (FileNotFoundException e) {
                System.out.println("FileNotFoundException "+e.getMessage());
                e.printStackTrace();
            } catch (IOException e) {
                System.out.println("IOException "+e.getMessage());
                e.printStackTrace();
            }
        }
    }

    public static HashMap<Integer, Contact> readJSONFromFile() {

        //ObjectMapper objectMapper = new ObjectMapper();
        HashMap<Integer, Contact> bookHashMap = null;
        {
            try(FileInputStream fis = new FileInputStream(fileName)) {
                bookHashMap = objectMapper.readValue(fis, new TypeReference<HashMap<Integer, Contact>>() {});
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            } catch (FileNotFoundException e) {
                System.out.println("FileNotFoundException "+e.getMessage());
                e.printStackTrace();
            } catch (IOException e) {
                System.out.println("IOException "+e.getMessage());
                e.printStackTrace();
            }
        }
        return bookHashMap;
    }

}
