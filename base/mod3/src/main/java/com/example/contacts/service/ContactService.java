package com.example.contacts.service;

import com.example.contacts.model.Contact;
import com.example.contacts.repository.ContactRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactService {

    private final ContactRepository contactRepository;

    public ContactService(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    public List<Contact> findAll() {
        return contactRepository.findAll();
    }

    public void save(Contact contact) {
        contactRepository.save(contact);
    }

    public void update(Contact contact) {
        contactRepository.update(contact);
    }

    public void deleteById(Long id) {
        contactRepository.deleteById(id);
    }
}
