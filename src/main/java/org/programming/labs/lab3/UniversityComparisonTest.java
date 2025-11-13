package org.programming.labs.lab3;

import org.programming.labs.lab2.controller.*;
import org.programming.labs.lab2.model.University;

import java.io.File;
import java.io.IOException;

public class UniversityComparisonTest {

    private static final String ETALON_FILE_PATH = "typical_university.json";

    public static void main(String[] args) {

        if (!new File(ETALON_FILE_PATH).exists()) {
            System.err.println("ERROR: File not found!");
            System.out.println("Please first start the RunJsonWriter class to create " + ETALON_FILE_PATH);
            return;
        }

        StudentCreator studentCreator = new StudentCreator();
        GroupCreator groupCreator = new GroupCreator(studentCreator);
        DepartmentCreator departmentCreator = new DepartmentCreator(groupCreator);
        FacultyCreator facultyCreator = new FacultyCreator(departmentCreator);
        UniversityCreator universityCreator = new UniversityCreator(facultyCreator);

        University currentUniversity = universityCreator.createTypicalUniversity("Dnipro University of Technology");
        System.out.println("The current University object has been created according to the Creator logic.");

        JsonManager jsonManager = new JsonManager();
        University etalonUniversity = null;

        try {
            etalonUniversity = jsonManager.readUniversityFromJsonFile(ETALON_FILE_PATH);
        } catch (IOException e) {
            System.err.println("ERROR: Failed to read reference university from JSON. " + e.getMessage());
            return;
        }

        System.out.println("\n--- Comparison results ---");

        if (etalonUniversity != null && currentUniversity.equals(etalonUniversity)) {
            System.out.println("SUCCESS: The current University object is identical to the reference object (equals() returned True).");
            System.out.println("This means that the logic of creation (Creator) has not changed since the JSON file was created.");
        } else {
            System.out.println("FAIL: The current object University is not identical to the reference object (equals() returned False).");
            System.out.println("This may be due to changes in names, number of departments, heads, or other parameters.");
        }
    }
}
