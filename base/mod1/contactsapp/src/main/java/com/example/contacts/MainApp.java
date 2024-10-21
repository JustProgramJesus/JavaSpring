package com.example.contacts;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Scanner;

public class MainApp {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        ContactService contactService = context.getBean(ContactService.class);

        String initFilePath = "src/main/resources/contacts.txt";
        contactService.loadContactsFromFile(initFilePath);
        System.out.println("Контакты успешно загружены из файла: contacts.txt");

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Введите команду: list, add, remove, save, exit");
            String command = scanner.nextLine();

            switch (command) {
                case "list":
                    // Проверка наличия контактов и вывод сообщения, если их нет
                    if (contactService.getAllContacts().isEmpty()) {
                        System.out.println("Тут пока что пусто, добавьте кто-то");
                    } else {
                        contactService.getAllContacts().forEach(contact ->
                                System.out.println(contact.getFullName() + " | " + contact.getPhoneNumber() + " | " + contact.getEmail()));
                    }
                    break;
                case "add":
                    // Добавление нового контакта
                    System.out.println("Введите данные в формате: Ф. И. О.;номер телефона;email");
                    String input = scanner.nextLine();
                    String[] parts = input.split(";");
                    if (parts.length == 3) {
                        contactService.addContact(new Contact(parts[0], parts[1], parts[2]));
                        System.out.println("Контакт добавлен");
                    } else {
                        System.out.println("Некорректный ввод. Ожидаемый формат: Ф. И. О.;номер телефона;email");
                    }
                    break;
                case "remove":
                    // Удаление контакта по email
                    System.out.println("Введите email контакта для удаления:");
                    String email = scanner.nextLine();
                    if (contactService.removeContactByEmail(email)) {
                        System.out.println("Контакт удален");
                    } else {
                        System.out.println("Контакт с таким email не найден");
                    }
                    break;
                case "save":
                    // Сохранение контактов в файл
                    contactService.saveContactsToFile("src/main/resources/contacts.txt");
                    System.out.println("Контакты сохранены");
                    break;
                case "exit":
                    // Завершение программы
                    System.out.println("Выход");
                    return;
                default:
                    System.out.println("Неизвестная команда");
            }
        }
    }
}
