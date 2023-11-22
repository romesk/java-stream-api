package org.lab.models;

public class Abonement {
    private int id;
    private String name;
    private String surname;
    private String patronymic;
    private String email;

    public Abonement() { }

    public Abonement(int id, String name, String surname, String patronymic, String email) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Abonement{" + "id=" + id + ", name=" + name + ", surname=" + surname + ", patronymic=" + patronymic + ", email=" + email + '}';
    }
}
