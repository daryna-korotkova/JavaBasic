package org.programming.labs.Lab2;

import org.programming.labs.Lab2.controller.*;
import org.programming.labs.Lab2.model.*;

public class Run {

    public static void main(String[] args) {
        System.out.println("=== Starting University Model Creation Program (MVC) ===");

        StudentCreator studentCreator = new StudentCreator();
        GroupCreator groupCreator = new GroupCreator();
        DepartmentCreator departmentCreator = new DepartmentCreator(groupCreator);
        FacultyCreator facultyCreator = new FacultyCreator(departmentCreator);
        UniversityCreator universityCreator = new UniversityCreator(facultyCreator);

        University university = createTypicalUniversity(
                universityCreator,
                facultyCreator,
                departmentCreator,
                groupCreator,
                studentCreator
        );

        printUniversityStructure(university);
    }

    private static University createTypicalUniversity(
            UniversityCreator universityCreator,
            FacultyCreator facultyCreator,
            DepartmentCreator departmentCreator,
            GroupCreator groupCreator,
            StudentCreator studentCreator
    ) {
        Human rector = new Human("Olexandr", "Melnyk", "Ivanovich", Sex.MALE);
        Human dean1 = new Human("Olena", "Tkachenko", "Ivanivna", Sex.FEMALE);
        Human dean2 = new Human("Viktor", "Lysenko", "Oleksiyovich", Sex.MALE);
        Human headOfDep1 = new Human("Serhiy", "Koval", "Mykolayovych", Sex.MALE);
        Human headOfDep2 = new Human("Anna", "Moroz", "Petrivna", Sex.FEMALE);
        Human groupHead1 = new Human("Iryna", "Melnyk", "Andriyivna", Sex.FEMALE);
        Human groupHead2 = new Human("Taras", "Shevchenko", "Vasyliovych", Sex.MALE);

        Student s1 = studentCreator.create("Dmytro", "Hnatiuk", "Stepanovych", Sex.MALE, 1001);
        Student s2 = studentCreator.create("Mariia", "Oliynyk", "Olehivna", Sex.FEMALE, 1002);
        Student s3 = studentCreator.create("Yana", "Kovalenko", "Romanivna", Sex.FEMALE, 1003);
        Student s4 = studentCreator.create("Maksym", "Petrenko", "Serhiyovych", Sex.MALE, 2001);

        University university = universityCreator.create("Dnipro University of Technology", rector);

        Faculty fit = facultyCreator.create("Faculty of Information Technology", dean1);

        Department csDep = departmentCreator.create("Department of Computer Science", headOfDep1);

        Group groupCS1 = groupCreator.create("CS-21", groupHead1);
        groupCS1.addStudent(s1);
        groupCS1.addStudent(s2);
        csDep.addGroup(groupCS1);

        Group groupCS2 = groupCreator.create("CS-22", groupHead2);
        groupCS2.addStudent(s3);
        csDep.addGroup(groupCS2);

        fit.addDepartment(csDep);

        Department seDep = departmentCreator.create("Department of Software Engineering", headOfDep2);
        Group groupSE1 = groupCreator.create("SE-21", groupHead2);
        groupSE1.addStudent(s4);
        seDep.addGroup(groupSE1);

        fit.addDepartment(seDep);
        university.addFaculty(fit);

        // --- 5. Faculty 2: Economics ---
        Faculty econ = facultyCreator.create("Faculty of Economics", dean2);
        university.addFaculty(econ);

        System.out.println("\nUniversity model successfully created!");
        return university;
    }

    private static void printUniversityStructure(University university) {
        System.out.println("\n" + university.getDetails());
        System.out.println("-".repeat(50));

        for (Faculty faculty : university.getFaculties()) {
            System.out.println("  -> " + faculty.getDetails());

            for (Department department : faculty.getDepartments()) {
                System.out.println("    -> " + department.getDetails());

                for (Group group : department.getGroups()) {
                    System.out.println("      -> " + group.getDetails());
                    System.out.println("         Students:");
                    for (Student student : group.getStudents()) {
                        System.out.println("           - " + student);
                    }
                }
            }
        }
    }
}