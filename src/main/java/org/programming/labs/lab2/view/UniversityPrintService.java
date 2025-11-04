package org.programming.labs.lab2.view;

import org.programming.labs.lab2.model.Department;
import org.programming.labs.lab2.model.Faculty;
import org.programming.labs.lab2.model.Group;
import org.programming.labs.lab2.model.Student;
import org.programming.labs.lab2.model.University;

public class UniversityPrintService {

    public void printStructure(University university) {
        System.out.println("\n" + "=".repeat(80));
        System.out.println("УНІВЕРСИТЕТ: " + university.getName() +
                " (Ректор: " + university.getHead() + ")");
        System.out.println("=".repeat(80));

        for (Faculty faculty : university.getFaculties()) {
            System.out.println("  ФАКУЛЬТЕТ: " + faculty.getName() +
                    " (Декан: " + faculty.getHead() + ")");
            System.out.println("  " + "-".repeat(76));

            for (Department department : faculty.getDepartments()) {
                System.out.println("    КАФЕДРА: " + department.getName() +
                        " (Зав. Кафедри: " + department.getHead() + ")");
                System.out.println("    " + "-".repeat(72));

                for (Group group : department.getGroups()) {
                    System.out.println("      ГРУПА: " + group.getName() +
                            " (Куратор: " + group.getHead() + ")");

                    if (!group.getStudents().isEmpty()) {
                        System.out.println("        Студенти (" + group.getStudents().size() + "):");
                        for (Student student : group.getStudents()) {
                            System.out.println("          - " + student);
                        }
                    } else {
                        System.out.println("        (Студенти відсутні)");
                    }
                    System.out.println("      " + "~".repeat(30));
                }
            }
        }
        System.out.println("\n" + "Виведення структури завершено.");
    }
}