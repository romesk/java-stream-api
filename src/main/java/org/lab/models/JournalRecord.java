package org.lab.models;

import java.time.LocalDate;
import java.util.Date;

public class JournalRecord {
    private int id;
    private int bookId;
    private int abonementId;
    private Date dateOfIssue;
    private Date dateOfReturn;
    private boolean isReturned;

    public JournalRecord() {
    }
    public JournalRecord(int id, int bookId, int abonementId, Date dateOfIssue, Date dateOfReturn, boolean isReturned) {
        this.id = id;
        this.bookId = bookId;
        this.abonementId = abonementId;
        this.dateOfIssue = dateOfIssue;
        this.dateOfReturn = dateOfReturn;
        this.isReturned = isReturned;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getAbonementId() {
        return abonementId;
    }

    public void setAbonementId(int abonementId) {
        this.abonementId = abonementId;
    }

    public Date getDateOfIssue() {
        return dateOfIssue;
    }

    public void setDateOfIssue(Date dateOfIssue) {
        this.dateOfIssue = dateOfIssue;
    }

    public Date getDateOfReturn() {
        return dateOfReturn;
    }

    public void setDateOfReturn(Date dateOfReturn) {
        this.dateOfReturn = dateOfReturn;
    }

    public boolean isReturned() {
        return isReturned;
    }

    public void setIsReturned(boolean returned) {
        isReturned = returned;
    }

    @Override
    public String toString() {
        return "JournalRecord{" +
                "id=" + id +
                ", bookId=" + bookId +
                ", abonementId=" + abonementId +
                ", dateOfIssue=" + dateOfIssue +
                ", dateOfReturn=" + dateOfReturn +
                ", isReturned=" + isReturned +
                '}';
    }
}
