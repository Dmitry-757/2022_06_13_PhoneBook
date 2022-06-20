package org.dng;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.util.HashMap;

public class ServiceJSON {
    private static String fileName = "json.sav";
    private static String fileName2 = "json.txt";
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

    //using fileWriter
    public static void writeJSONToFile2(Object item) {
        {
            try(BufferedWriter bw = new BufferedWriter(new FileWriter(fileName2))) {
                objectMapper.writeValue(bw, item);
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

    //using fileReader
    public static HashMap<Integer, Contact> readJSONFromFile2() {
        HashMap<Integer, Contact> bookHashMap = null;
        {
            try(BufferedReader br = new BufferedReader(new FileReader(fileName2))) {
                String s;
                if((s=br.readLine())!=null){
                    System.out.println(s);
                    bookHashMap = objectMapper.readValue(s, new TypeReference<HashMap<Integer, Contact>>() {});
                }
//                bookHashMap = objectMapper.readValue(fr, new TypeReference<HashMap<Integer, Contact>>() {});
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
