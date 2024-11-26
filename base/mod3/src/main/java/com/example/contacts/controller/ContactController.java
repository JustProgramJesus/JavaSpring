package com.example.contacts.controller;

import com.example.contacts.model.Contact;
import com.example.contacts.repository.ContactRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/contacts")
public class ContactController {
    private final ContactRepository contactRepository;

    public ContactController(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    @GetMapping
    public String listContacts(Model model) {
        List<Contact> contacts = contactRepository.findAll();
        model.addAttribute("contacts", contacts); // Добавляем контакты в модель
        return "index"; // Возвращаем имя шаблона
    }

    @GetMapping("/new")
    public String newContactForm(Model model) {
        model.addAttribute("contact", new Contact());
        return "contact_form";
    }

    @PostMapping
    public String saveContact(Contact contact) {
        contactRepository.save(contact); // Метод save автоматически обработает добавление или обновление
        return "redirect:/contacts";
    }

    @GetMapping("/edit/{id}")
    public String editContactForm(@PathVariable Long id, Model model) {
        model.addAttribute("contact", contactRepository.findById(id));
        return "contact_form";
    }

    @PostMapping("/{id}")
    public String updateContact(@PathVariable Long id, Contact contact) {
        contact.setId(id);
        contactRepository.save(contact); // Метод save обработает обновление
        return "redirect:/contacts";
    }

    @PostMapping("/delete/{id}")
    public String deleteContact(@PathVariable Long id) {
        contactRepository.deleteById(id);
        return "redirect:/contacts";
    }
}

