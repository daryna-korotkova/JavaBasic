
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.programming.labs.lab2.controller.*;
import org.programming.labs.lab2.model.University;
import org.programming.labs.lab3.JsonManager;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class UniversityTest {

    private static final String ETALON_FILE_PATH = "typical_university.json";


    private static University currentUniversity;
    private static University etalonUniversity;
    private static JsonManager jsonManager;
    private static File etalonFile;


    @BeforeAll
    static void setUpAll() {
        etalonFile = new File(ETALON_FILE_PATH);
        jsonManager = new JsonManager();

        if (!etalonFile.exists()) {
            fail("ERROR: Reference file not found! Please run RunJsonWriter first to create " + ETALON_FILE_PATH);
            return;
        }

        StudentCreator studentCreator = new StudentCreator();
        GroupCreator groupCreator = new GroupCreator(studentCreator);
        DepartmentCreator departmentCreator = new DepartmentCreator(groupCreator);
        FacultyCreator facultyCreator = new FacultyCreator(departmentCreator);
        UniversityCreator universityCreator = new UniversityCreator(facultyCreator);

        currentUniversity = universityCreator.createTypicalUniversity("Dnipro University of Technology");

        try {
            etalonUniversity = jsonManager.readUniversityFromJsonFile(ETALON_FILE_PATH);
        } catch (IOException e) {
            fail("ERROR: Failed to read reference university from JSON: " + e.getMessage());
        }
    }

    @Test
    void etalonUniversityShouldNotBeNull() {
        assertNotNull(etalonUniversity, "The reference object should not be null after reading.");
    }

    @Test
    void createdUniversityShouldBeEqualToEtalon() {
        assertNotNull(currentUniversity, "The current University object should not be null.");
        assertNotNull(etalonUniversity, "The reference University object should not be null.");

        assertEquals(etalonUniversity, currentUniversity,
                "The University object created by the Creators must be identical to the reference object from JSON. " +
                        "If this test failed, the object creation logic may have changed.");
    }

    @Test
    void universityNamesShouldBeEqual() {
        assertNotNull(currentUniversity, "The current University object should not be null.");
        assertNotNull(etalonUniversity, "The reference University object should not be null.");
        assertEquals(etalonUniversity.getName(), currentUniversity.getName(),
                "The university names must match.");
    }
}
