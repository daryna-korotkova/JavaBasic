package org.programming.labs.Other;

import java.util.Scanner;
import java.util.Random;

public class Lab_1 {

    // Константи для меж випадкових чисел
    private static final int MIN_RANDOM = -50;
    private static final int MAX_RANDOM = 50;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Програма для роботи з матрицею цілих чисел ===");

        // Введення розмірів матриці
        int rows = getMatrixSize(scanner, "Введіть кількість рядків (1–20): ");
        int cols = getMatrixSize(scanner, "Введіть кількість стовпців (1–20): ");

        // Вибір способу створення матриці
        System.out.println("Оберіть спосіб створення матриці:");
        System.out.println("1 — Ввести вручну");
        System.out.println("2 — Згенерувати випадково");
        int choice = getChoice(scanner);

        int[][] matrix;
        if (choice == 1) {
            matrix = createMatrixManual(scanner, rows, cols);
        } else {
            matrix = createMatrixRandom(rows, cols);
        }

        // Виведення матриці
        System.out.println("\nМатриця:");
        printMatrix(matrix);

        // Пошук мінімального, максимального та обчислення середніх значень
        int min = findMin(matrix);
        int max = findMax(matrix);
        double avg = calculateArithmeticMean(matrix);
        double geoMean = calculateGeometricMean(matrix);

        // Виведення результатів
        System.out.println("\n=== Результати обчислень ===");
        System.out.println("Мінімальний елемент: " + min);
        System.out.println("Максимальний елемент: " + max);
        System.out.printf("Середнє арифметичне: %.2f\n", avg);
        System.out.printf("Середнє геометричне: %.2f\n", geoMean);

        scanner.close();
    }

    /**
     * Зчитування розміру матриці з перевіркою правильності введення.
     */
    private static int getMatrixSize(Scanner scanner, String message) {
        int size;
        do {
            System.out.print(message);
            size = scanner.nextInt();
            if (size < 1 || size > 20) {
                System.out.println("Некоректне значення! Введіть число від 1 до 20.");
            }
        } while (size < 1 || size > 20);
        return size;
    }

    /**
     * Отримання вибору користувача (1 або 2)
     */
    private static int getChoice(Scanner scanner) {
        int choice;
        do {
            System.out.print("Ваш вибір: ");
            choice = scanner.nextInt();
            if (choice != 1 && choice != 2) {
                System.out.println("Введіть 1 або 2.");
            }
        } while (choice != 1 && choice != 2);
        return choice;
    }

    /**
     * Створення матриці вручну через клавіатуру.
     */
    private static int[][] createMatrixManual(Scanner scanner, int rows, int cols) {
        int[][] matrix = new int[rows][cols];
        System.out.println("Введіть елементи матриці (" + rows + "×" + cols + "):");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print("Елемент [" + i + "][" + j + "]: ");
                matrix[i][j] = scanner.nextInt();
            }
        }
        return matrix;
    }

    /**
     * Створення матриці випадковим чином у межах від MIN_RANDOM до MAX_RANDOM.
     */
    private static int[][] createMatrixRandom(int rows, int cols) {
        int[][] matrix = new int[rows][cols];
        Random random = new Random();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = random.nextInt(MAX_RANDOM - MIN_RANDOM + 1) + MIN_RANDOM;
            }
        }
        return matrix;
    }

    /**
     * Виведення матриці у зручному форматі.
     */
    private static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int val : row) {
                System.out.printf("%6d", val);
            }
            System.out.println();
        }
    }

    /**
     * Знаходження мінімального елемента у матриці.
     */
    private static int findMin(int[][] matrix) {
        int min = matrix[0][0];
        for (int[] row : matrix) {
            for (int val : row) {
                if (val < min) {
                    min = val;
                }
            }
        }
        return min;
    }

    /**
     * Знаходження максимального елемента у матриці.
     */
    private static int findMax(int[][] matrix) {
        int max = matrix[0][0];
        for (int[] row : matrix) {
            for (int val : row) {
                if (val > max) {
                    max = val;
                }
            }
        }
        return max;
    }

    /**
     * Обчислення середнього арифметичного всіх елементів матриці.
     */
    private static double calculateArithmeticMean(int[][] matrix) {
        double sum = 0;
        int count = 0;
        for (int[] row : matrix) {
            for (int val : row) {
                sum += val;
                count++;
            }
        }
        return sum / count;
    }

    /**
     * Обчислення середнього геометричного елементів матриці.
     * Використовується тільки для додатних чисел.
     */
    private static double calculateGeometricMean(int[][] matrix) {
        double product = 1.0;
        int count = 0;

        for (int[] row : matrix) {
            for (int val : row) {
                if (val > 0) { // геометричне середнє визначене лише для додатних чисел
                    product *= val;
                    count++;
                }
            }
        }

        if (count == 0) {
            System.out.println("У матриці немає додатних елементів. Неможливо обчислити середнє геометричне.");
            return 0.0;
        }

        return Math.pow(product, 1.0 / count);
    }
}

