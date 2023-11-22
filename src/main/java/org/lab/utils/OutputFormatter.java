package org.lab.utils;

import java.lang.reflect.Field;
import java.util.List;

public class OutputFormatter {

    public static void outputKeyValue(String key, Object value) {
        System.out.println(key + ": " + value);
    }

    public static void outputList(List<?> list) {
        for (Object item : list) {
            System.out.println(" - " + item);
        }
    }

    private static String padRight(String s, int length) {
        return String.format("%-" + length + "s", s);
    }

    private static String getFieldStringValue(Field field, Object rowData) {
        try {
            field.setAccessible(true);
            Object value = field.get(rowData);
            return (value != null) ? String.valueOf(value) : "null";
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return "";
        }
    }

    private static int fieldListIndex(Field field, Field[] fields) {
        for (int i = 0; i < fields.length; i++) {
            if (fields[i].equals(field)) {
                return i;
            }
        }
        return -1;
    }

    private static int[] calculateMaxFieldLengths(Field[] fields, List<?> list) {
        int[] maxLengths = new int[fields.length];

        for (Field field : fields) {
            int maxLength = field.getName().length(); // Start with field name length
            for (Object item : list) {
                String value = getFieldStringValue(field, item);
                maxLength = Math.max(maxLength, value.length());
            }
            maxLengths[fieldListIndex(field, fields)] = maxLength;
        }

        return maxLengths;
    }

    private static void printTableLine(int[] maxLengths) {
        for (int maxLength : maxLengths) {
            System.out.print("+" + "-".repeat(maxLength + 2)); // Adjust the padding as needed
        }
        System.out.println("+");
    }

    private static void printTableRow(Field[] fields, Object rowData, int[] maxLengths, boolean isHeader) {
        if (isHeader) {
            for (int i = 0; i < fields.length; i++) {
                System.out.print("| " + padRight(fields[i].getName(), maxLengths[i] + 1) );
            }
            System.out.println("|");
            return;
        }
        for (int i = 0; i < fields.length; i++) {
            System.out.print("| " + padRight(getFieldStringValue(fields[i], rowData), maxLengths[i] + 1));
        }
        System.out.println("|");
    }

    public static void outputAsTable(List<?> list) {
        if (list == null || list.isEmpty()) {
            System.out.println("The list is empty.");
            return;
        }

        // Assume that all items in the list have the same structure (fields)
        Class<?> clazz = list.get(0).getClass();

        // Get all fields of the class
        Field[] fields = clazz.getDeclaredFields();

        // Calculate the maximum length of each field
        int[] maxLengths = calculateMaxFieldLengths(fields, list);

        // Print table header with lines
        printTableLine(maxLengths);
        printTableRow(fields, fields, maxLengths, true);
        printTableLine(maxLengths);

        // Print table rows
        for (Object item : list) {
            printTableRow(fields, item, maxLengths, false);
        }

        // Print bottom line
        printTableLine(maxLengths);
    }

}
