package org.programming.labs.lab2;

import org.programming.labs.lab2.controller.*;
import org.programming.labs.lab2.model.University;
import org.programming.labs.lab2.view.UniversityPrintService;

public class Run {

    public static void main(String[] args) {
        StudentCreator studentCreator = new StudentCreator();
        GroupCreator groupCreator = new GroupCreator(studentCreator);
        DepartmentCreator departmentCreator = new DepartmentCreator(groupCreator);
        FacultyCreator facultyCreator = new FacultyCreator(departmentCreator);
        UniversityCreator universityCreator = new UniversityCreator(facultyCreator);

        University university = universityCreator.createTypicalUniversity("Dnipro University of Technology");

        UniversityPrintService printService = new UniversityPrintService();
        printService.printStructure(university);
    }
}