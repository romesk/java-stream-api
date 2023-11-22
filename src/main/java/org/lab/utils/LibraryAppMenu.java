package org.lab.utils;

import org.lab.models.Library;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;

public class LibraryAppMenu {

    private Library library;

    public LibraryAppMenu(Library library) {
        this.library = library;
    }

    public void showMenuSections() {
        System.out.println("\n\n========================================");
        System.out.println("Choose a section:");
        System.out.println("\uD83D\uDD18 [1] Books              \t\t \uD83D\uDD18 [2] Abonements");
        System.out.println("\uD83D\uDD18 [3] Library Manager    \t\t \uD83D\uDD18 [4] Exit");
    }

    public void showBookMenu() {
        System.out.println("\nChoose an option:");
        System.out.println("\uD83D\uDD18 [1] Show all books");
        System.out.println("\uD83D\uDD18 [2] Show all books sorted by year");
    }

    public void showAbonementMenu() {
        System.out.println("\nChoose an option:");
        System.out.println("\uD83D\uDD18 [1] Show all abonements");
    }

    public void showJournalMenu() {
        System.out.println("\nChoose an option:");
        System.out.println("\uD83D\uDD18 [1] Show journal records");
        System.out.println("\uD83D\uDD18 [2] Show addresses with more than 2 books");
        System.out.println("\uD83D\uDD18 [3] Show user number with books where author is...");
        System.out.println("\uD83D\uDD18 [4] Show user with the biggest number of books");
        System.out.println("\uD83D\uDD18 [5] Perform mailing list");
        System.out.println("\uD83D\uDD18 [6] Get expired journal records");
    }

    public void processSubMenuOption(int sectionOption, int option) {
        switch (sectionOption) {
            case 1:
                processBooksOption(option);
                break;
            case 2:
                processAbonementsOption(option);
                break;
            case 3:
                processJournalOption(option);
                break;
            default:
                System.out.println("Invalid option");
                break;
        }
    }

    public void processBooksOption(int option) {
        switch (option) {
            case 1:
                System.out.println("Showing all books:");
                OutputFormatter.outputAsTable(library.bookManager.getBooks());
                break;
            case 2:
                System.out.println("Showing all books sorted by year:");
                OutputFormatter.outputAsTable(library.bookManager.getBooksSortedByYear());
                break;
            default:
                System.out.println("Invalid option");
                break;
        }
    }

    public void processJournalOption(int option) {
        switch (option) {
            case 1:
                System.out.println("Showing all journal records:");
                OutputFormatter.outputAsTable(library.journalManager.getJournalRecords());
                break;
            case 2:
                System.out.println("Showing addresses with more than 2 books:");
                OutputFormatter.outputList(library.getAddressesWithMoreThanTwoBooks());
                break;
            case 3:
                System.out.println("Enter author name:");
                var author = InputReader.readString();
                System.out.print("Showing user number with books by author " + author + ": ");
                System.out.println(library.getUsersWithBooksByAuthor(author).size());
                break;
            case 4:
                System.out.println("Showing user with the biggest number of books:");
                OutputFormatter.outputList(library.getAbonementWithBiggestNumberOfBooks());
                break;
            case 5:
                System.out.println("List of addresses with less than two books:");
                var fewBooksAddresses = library.getAbonementsAdressesWithLessThanBooks(2);
                OutputFormatter.outputList(fewBooksAddresses);
                System.out.println("Other addresses:");
                var otherAddresses = library.getAbonementAdressesWithBooksInUse(fewBooksAddresses);
                OutputFormatter.outputList(otherAddresses);

                library.performMailingCompany("New Books Appeared!", fewBooksAddresses,
                        "Hello! New books appeared in our library! Come and take them!");
                library.performMailingCompany("Don't forget to bring it back!", otherAddresses,
                        "Hello! Don't forget to bring back books you took from our library!");
                break;
            case 6:
                var expired = library.getExpiredJournalRecords();
                for (var expiredRecord : expired) {
                    LocalDate localAnotherDate = expiredRecord.getDateOfReturn().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                    var days = ChronoUnit.DAYS.between(localAnotherDate, java.time.LocalDate.now());
                    OutputFormatter.outputKeyValue(days + " days", expiredRecord.toString());
                }
                break;
            default:
                System.out.println("Invalid option");
                break;
        }
    }

    public void processAbonementsOption(int option) {
        switch (option) {
            case 1:
                System.out.println("Showing all abonements:");
                OutputFormatter.outputAsTable(library.abonementManager.getAbonements());
                break;
            default:
                System.out.println("Invalid option");
                break;
        }
    }

    public void processMenuSection(int option) {
        switch (option) {
            case 1:
                showBookMenu();
                break;
            case 2:
                showAbonementMenu();
                break;
            case 3:
                showJournalMenu();
                break;
            case 4:
                System.out.println("Goodbye!");
                InputReader.close();
                System.exit(0);
                break;
            default:
                System.out.println("Invalid option");
                break;
        }
    }


}
