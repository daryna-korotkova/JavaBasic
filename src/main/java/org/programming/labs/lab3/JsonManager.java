package org.programming.labs.lab3;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.programming.labs.lab2.model.University;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class JsonManager {

    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public void writeUniversityToJsonFile(University university, String filePath) throws IOException {

        String json = gson.toJson(university);


        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write(json);
            System.out.println("The University object has been successfully saved to a JSON file: " + filePath);
        }
    }


    public University readUniversityFromJsonFile(String filePath) throws IOException {

        try (FileReader reader = new FileReader(filePath)) {
            University university = gson.fromJson(reader, University.class);
            System.out.println("The University object has been successfully read from the JSON file: " + filePath);
            return university;
        }
    }
}