package com.example.contacts.repository;

import com.example.contacts.model.Contact;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ContactRepository {
    private final JdbcTemplate jdbcTemplate;

    public ContactRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Contact> findAll() {
        List<Contact> contacts = jdbcTemplate.query(
                "SELECT * FROM contacts",
                (rs, rowNum) -> {
                    Contact contact = new Contact();
                    contact.setId(rs.getLong("id"));
                    contact.setFirstName(rs.getString("first_name"));
                    contact.setLastName(rs.getString("last_name"));
                    contact.setEmail(rs.getString("email"));
                    contact.setPhone(rs.getString("phone"));
                    return contact;
                }
        );
        return contacts;
    }

    public void save(Contact contact) {
        // Если ID присутствует, обновляем контакт, иначе создаем новый
        if (contact.getId() != null && existsById(contact.getId())) {
            update(contact);
        } else {
            jdbcTemplate.update(
                    "INSERT INTO contacts (first_name, last_name, email, phone) VALUES (?, ?, ?, ?)",
                    contact.getFirstName(), contact.getLastName(), contact.getEmail(), contact.getPhone()
            );
        }
    }

    public void update(Contact contact) {
        jdbcTemplate.update(
                "UPDATE contacts SET first_name=?, last_name=?, email=?, phone=? WHERE id=?",
                contact.getFirstName(), contact.getLastName(), contact.getEmail(), contact.getPhone(), contact.getId()
        );
    }

    public void deleteById(Long id) {
        jdbcTemplate.update("DELETE FROM contacts WHERE id=?", id);
    }

    public Contact findById(Long id) {
        return jdbcTemplate.queryForObject("SELECT * FROM contacts WHERE id=?",
                new Object[]{id}, (rs, rowNum) -> {
                    Contact contact = new Contact();
                    contact.setId(rs.getLong("id"));
                    contact.setFirstName(rs.getString("first_name"));
                    contact.setLastName(rs.getString("last_name"));
                    contact.setEmail(rs.getString("email"));
                    contact.setPhone(rs.getString("phone"));
                    return contact;
                });
    }

    public boolean existsById(Long id) {
        Integer count = jdbcTemplate.queryForObject(
                "SELECT COUNT(*) FROM contacts WHERE id=?", new Object[]{id}, Integer.class);
        return count != null && count > 0;
    }
}