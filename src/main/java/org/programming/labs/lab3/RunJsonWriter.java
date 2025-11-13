package org.programming.labs.lab3;

import org.programming.labs.lab2.controller.*;
import org.programming.labs.lab2.model.University;
import org.programming.labs.lab2.view.UniversityPrintService;

import java.io.IOException;

public class RunJsonWriter {

    private static final String ETALON_FILE_PATH = "typical_university.json";

    public static void main(String[] args) {
        StudentCreator studentCreator = new StudentCreator();
        GroupCreator groupCreator = new GroupCreator(studentCreator);
        DepartmentCreator departmentCreator = new DepartmentCreator(groupCreator);
        FacultyCreator facultyCreator = new FacultyCreator(departmentCreator);
        UniversityCreator universityCreator = new UniversityCreator(facultyCreator);

        University university = universityCreator.createTypicalUniversity("Dnipro University of Technology");

        UniversityPrintService printService = new UniversityPrintService();
        printService.printStructure(university);


        JsonManager jsonManager = new JsonManager();
        try {
            jsonManager.writeUniversityToJsonFile(university, ETALON_FILE_PATH);
        } catch (IOException e) {
            System.err.println("❌ Error writing university to JSON file: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
