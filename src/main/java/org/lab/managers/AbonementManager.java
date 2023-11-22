package org.lab.managers;

import org.lab.models.Abonement;

import java.util.ArrayList;
import java.util.List;

public class AbonementManager {

    private final List<Abonement> abonements;

    public AbonementManager() {
        abonements = new ArrayList<>();
    }

    public AbonementManager(List<Abonement> abonements) {
        this.abonements = abonements;
    }

    public List<Abonement> getAbonements() {
        return abonements;
    }

    public void addAbonement(Abonement abonement) {
        this.abonements.add(abonement);
    }

    public void addAbonements(List<Abonement> abonements) {
        this.abonements.addAll(abonements);
    }

    public Abonement getAbonementById(int id) {
        return abonements.stream()
                .filter(abonement -> abonement.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public void deleteAbonementById(int id) {
        abonements.removeIf(abonement -> abonement.getId() == id);
    }

    public void updateAbonement(Abonement abonement) {
        Abonement abonementToUpdate = getAbonementById(abonement.getId());
        abonementToUpdate.setName(abonement.getName());
        abonementToUpdate.setSurname(abonement.getSurname());
        abonementToUpdate.setPatronymic(abonement.getPatronymic());
        abonementToUpdate.setEmail(abonement.getEmail());
    }

    public List<String> getAbonementsEmailsThatNotInList(List<String> emails) {
        return abonements.stream()
                .map(Abonement::getEmail)
                .filter(email -> !emails.contains(email))
                .toList();
    }

}
