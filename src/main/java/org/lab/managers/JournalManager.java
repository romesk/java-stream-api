package org.lab.managers;

import org.lab.models.Abonement;
import org.lab.models.JournalRecord;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class JournalManager {
    private final List<JournalRecord> journalRecords;

    public JournalManager() {
        this.journalRecords = new ArrayList<>();
    }

    public JournalManager(List<JournalRecord> journalRecords) {
        this.journalRecords = journalRecords;
    }

    public List<JournalRecord> getJournalRecords() {
        return journalRecords;
    }

    public void addJournalRecord(JournalRecord journalRecord) {
        journalRecords.add(journalRecord);
    }

    public void addJournalRecords(List<JournalRecord> journalRecords) {
        this.journalRecords.addAll(journalRecords);
    }

    public JournalRecord getJournalRecordById(int id) {
        return journalRecords.stream()
                .filter(journalRecord -> journalRecord.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public void deleteJournalRecordById(int id) {
        journalRecords.removeIf(journalRecord -> journalRecord.getId() == id);
    }

    public List<Integer> getAbonementsIdWithBooksNumberMoreEqualThan(int bookNumber) {
        return journalRecords.stream().collect(Collectors.groupingBy(JournalRecord::getAbonementId))
                .entrySet().stream()
                .filter(entry -> entry.getValue().size() >= bookNumber)
                .map(Map.Entry::getKey)
                .toList();
    }

    public List<Integer> getAbonementsWithBookNumberLessThan(int bookNumber, boolean isReturned) {
        return journalRecords.stream().filter(journalRecord -> journalRecord.isReturned() == isReturned)
                .collect(Collectors.groupingBy(JournalRecord::getAbonementId))
                .entrySet().stream()
                .filter(entry -> entry.getValue().size() < bookNumber)
                .map(entry -> entry.getValue().get(0).getAbonementId())
                .toList();
    }

    public List<Integer> getAbonementsWithBooksInUse() {
        return journalRecords.stream().filter(journalRecord -> !journalRecord.isReturned())
                .map(JournalRecord::getAbonementId)
                .toList();
    }
}
