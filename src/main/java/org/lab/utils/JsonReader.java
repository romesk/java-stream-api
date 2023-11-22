package org.lab.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import org.lab.models.Abonement;
import org.lab.models.Book;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsonReader {
    static final String JSON_DIR = "src/main/resources/json/";
    public static final String BOOKS_FILE = JSON_DIR + "books.json";
    public static final String ABONEMENTS_FILE = JSON_DIR + "abonements.json";
    public static final String JOURNAL_FILE = JSON_DIR + "journal.json";

    private JsonReader() {
        throw new IllegalStateException("Utility class");
    }

    public static <T> List<T> readListFromJson(String jsonFilePath, Class<T> valueType) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            TypeFactory typeFactory = objectMapper.getTypeFactory();
            JavaType listType = typeFactory.constructCollectionType(List.class, valueType);
            return objectMapper.readValue(new File(jsonFilePath), listType);
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
            return new ArrayList<>();
        }
    }

//    public static List<Book> readBooksFromJson(String jsonFilePath) {
//        try {
//            ObjectMapper objectMapper = new ObjectMapper();
//            return objectMapper.readValue(new File(jsonFilePath), new TypeReference<List<Book>>() {});
//        } catch (IOException e) {
//            System.out.println("Error: " + e.getMessage());
//            return new ArrayList<>();
//        }
//    }
//
//    public static List<Abonement> readAbonementsFromJson(String jsonFilePath) {
//        try {
//            ObjectMapper objectMapper = new ObjectMapper();
//            return objectMapper.readValue(new File(jsonFilePath), new TypeReference<List<Abonement>>() {});
//        } catch (IOException e) {
//            System.out.println("Error: " + e.getMessage());
//            return new ArrayList<>();
//        }
//    }
}
