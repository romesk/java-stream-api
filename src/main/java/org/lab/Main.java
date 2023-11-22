package org.lab;

import org.lab.models.Abonement;
import org.lab.models.Book;
import org.lab.models.JournalRecord;
import org.lab.models.Library;
import org.lab.utils.InputReader;
import org.lab.utils.JsonReader;
import org.lab.utils.LibraryAppMenu;
import org.lab.utils.OutputFormatter;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("\uD83D\uDC4B Welcome to the Library!");

        Library library = setupLibrary();
        LibraryAppMenu menu = new LibraryAppMenu(library);

        while (true) {
            menu.showMenuSections();
            var sectionOption = InputReader.readInteger();
            if (sectionOption == 4) {
                break;
            }

            menu.processMenuSection(sectionOption);
            var option = InputReader.readInteger();

            menu.processSubMenuOption(sectionOption, option);
        }

        System.out.println("\uD83D\uDC4B Goodbye!");
        InputReader.close();
    }

    public static Library setupLibrary() {
        List<Book> books = JsonReader.readListFromJson(JsonReader.BOOKS_FILE, Book.class);
        List<Abonement> abonements = JsonReader.readListFromJson(JsonReader.ABONEMENTS_FILE, Abonement.class);
        List<JournalRecord> journalRecords = JsonReader.readListFromJson(JsonReader.JOURNAL_FILE, JournalRecord.class);

        Library library = new Library();
        library.bookManager.addBooks(books);
        library.abonementManager.addAbonements(abonements);
        library.journalManager.addJournalRecords(journalRecords);

        return library;
    }
}