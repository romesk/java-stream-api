package org.lab.services;

import java.util.List;

public class EmailService {
    private static final String LOG_FORMAT = "Sending email to %s: %s%n";

    public static void sendEmail(String recipient, String message) {
        // Simulate sending email by logging the action
        System.out.printf(LOG_FORMAT, recipient, message);
    }

    public static void doMailingList(List<String> recipients, String message) {
        for (String recipient : recipients) {
            sendEmail(recipient, message);
        }
    }
}
