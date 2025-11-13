package org.programming.labs.lab3;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.programming.labs.lab2.model.University;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class JsonManager {

    // Створюємо екземпляр Gson. setPrettyPrinting() додає форматування для читабельності
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    /**
     * Записує об'єкт University у файл у форматі JSON.
     * @param university Об'єкт університету для серіалізації.
     * @param filePath Шлях до файлу, куди буде збережено JSON.
     * @throws IOException Якщо виникає помилка при роботі з файлом.
     */
    public void writeUniversityToJsonFile(University university, String filePath) throws IOException {
        // Перетворюємо об'єкт University на рядок JSON
        String json = gson.toJson(university);

        // Використовуємо try-with-resources для автоматичного закриття FileWriter
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write(json);
            System.out.println("The University object has been successfully saved to a JSON file: " + filePath);
        }
    }

    /**
     * Зчитує об'єкт University з файлу JSON.
     * @param filePath Шлях до файлу JSON.
     * @return Відновлений об'єкт University.
     * @throws IOException Якщо виникає помилка при роботі з файлом.
     */
    public University readUniversityFromJsonFile(String filePath) throws IOException {
        // Використовуємо try-with-resources для автоматичного закриття FileReader
        try (FileReader reader = new FileReader(filePath)) {
            // Десеріалізуємо JSON рядок назад в об'єкт University
            University university = gson.fromJson(reader, University.class);
            System.out.println("The University object has been successfully read from the JSON file: " + filePath);
            return university;
        }
    }
}