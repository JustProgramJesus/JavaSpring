package com.example.contacts;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ContactService {
    private final List<Contact> contacts = new ArrayList<>();

    // Получение всех контактов
    public List<Contact> getAllContacts() {
        return contacts;
    }

    // Добавление контакта
    public void addContact(Contact contact) {
        contacts.add(contact);
    }

    // Удаление контакта по email
    public boolean removeContactByEmail(String email) {
        Optional<Contact> contactToRemove = contacts.stream()
                .filter(contact -> contact.getEmail().equals(email))
                .findFirst();
        if (contactToRemove.isPresent()) {
            contacts.remove(contactToRemove.get());
            return true;
        }
        return false;
    }

    // Сохранение контактов в файл
    public void saveContactsToFile(String filePath) {
        try (java.io.BufferedWriter writer = new java.io.BufferedWriter(new java.io.FileWriter(filePath))) {
            for (Contact contact : contacts) {
                writer.write(contact.getFullName() + ";" + contact.getPhoneNumber() + ";" + contact.getEmail());
                writer.newLine();
            }
        } catch (Exception e) {
            System.out.println("Ошибка при сохранении контактов в файл: " + e.getMessage());
        }
    }

    // Загрузка контактов из файла
    public void loadContactsFromFile(String filePath) {
        try (java.io.BufferedReader reader = new java.io.BufferedReader(new java.io.FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length == 3) {
                    addContact(new Contact(parts[0], parts[1], parts[2]));
                }
            }
        } catch (Exception e) {
            System.out.println("Ошибка при загрузке контактов из файла: " + e.getMessage());
        }
    }
}
