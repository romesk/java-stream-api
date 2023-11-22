package org.lab.models;

import org.lab.managers.AbonementManager;
import org.lab.managers.BookManager;
import org.lab.managers.JournalManager;
import org.lab.services.EmailService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Library {

    public BookManager bookManager = new BookManager();
    public AbonementManager abonementManager = new AbonementManager();
    public JournalManager journalManager = new JournalManager();

    public Library() { }

    public List<String> getAddressesWithMoreThanTwoBooks() {
        var abonements = journalManager.getAbonementsIdWithBooksNumberMoreEqualThan(2);
        return abonementManager.getAbonements().stream()
                .filter(abonement -> abonements.contains(abonement.getId()))
                .map(Abonement::getEmail)
                .toList();
    }

    public List<Integer> getUsersWithBooksByAuthor(String author) {
        var books = bookManager.getBooksByAuthor(author);
        var booksIds = books.stream().map(Book::getId).toList();

        var journalRecordsWithAuthor = journalManager.getJournalRecords().stream()
                .filter(journalRecord -> booksIds.contains(journalRecord.getBookId()))
                .toList();

        // get only unique abonements
        return journalRecordsWithAuthor.stream()
                .collect(Collectors.groupingBy(JournalRecord::getAbonementId))
                .keySet().stream()
                .toList();
    }

    public List<Abonement> getAbonementWithBiggestNumberOfBooks() {
        var maxNumberOfBooks = journalManager.getJournalRecords().stream()
                .collect(Collectors.groupingBy(JournalRecord::getAbonementId))
                .entrySet().stream()
                .map(entry -> Map.entry(entry.getKey(), entry.getValue().size()))
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getValue).orElse(0);

        return journalManager.getJournalRecords().stream()
                .collect(Collectors.groupingBy(JournalRecord::getAbonementId))
                .entrySet().stream()
                .filter(entry -> entry.getValue().size() == maxNumberOfBooks)
                .map(entry -> abonementManager.getAbonementById(entry.getKey())).toList();
    }

    public List<String> getAbonementsAdressesWithLessThanBooks (int bookNumber) {
        var abonementsWithLessThanTwoBooks = journalManager.getAbonementsWithBookNumberLessThan(bookNumber, false);
        System.out.println(abonementsWithLessThanTwoBooks);
        return abonementsWithLessThanTwoBooks.stream()
                .map(abonementManager::getAbonementById)
                .map(Abonement::getEmail)
                .toList();
    }

    public List<String> getAbonementAdressesWithBooksInUse (List<String> addressesToExclude) {
        var abonementsWithBooksInUse = journalManager.getAbonementsWithBooksInUse();

        return abonementsWithBooksInUse.stream()
                .map(abonementManager::getAbonementById)
                .map(Abonement::getEmail)
                .filter(email -> !addressesToExclude.contains(email)).distinct()
                .toList();
    }

    public void performMailingCompany(String companyName, List<String> recepients, String message) {
        System.out.println("===================================");
        System.out.println("Start mailing company " + companyName + "...");
        System.out.println("Sending message to " + recepients.size() + " recepients...");
        System.out.println("Message: " + message);

        EmailService.doMailingList(recepients, message);

        System.out.println("Done!");
        System.out.println("===================================");
    }

    public List<JournalRecord> getExpiredJournalRecords() {
        return journalManager.getJournalRecords().stream()
                .filter(journalRecord -> journalRecord.getDateOfReturn().before(new Date()) && !journalRecord.isReturned())
                .toList();
    }
}
