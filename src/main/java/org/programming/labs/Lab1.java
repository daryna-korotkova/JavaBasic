package org.programming.labs;

import java.util.Scanner;
import java.util.Random;

public class Lab1 {

    // Константи для меж випадкових чисел
    private static final int MIN_RANDOM = -50;  // мінімальне випадкове число
    private static final int MAX_RANDOM = 50;   // максимальне випадкове число

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Програма для роботи з матрицею цілих чисел ===");

        // Введення кількості рядків і стовпців з перевіркою
        int rows = getMatrixSize(scanner, "Введіть кількість рядків (1–20): ");
        int cols = getMatrixSize(scanner, "Введіть кількість стовпців (1–20): ");

        // Вибір способу створення матриці (1 або 2)
        System.out.println("Оберіть спосіб створення матриці:");
        System.out.println("1 — Ввести вручну");
        System.out.println("2 — Згенерувати випадково");
        int choice = getChoice(scanner);

        int[][] matrix;

        // Якщо вибрано "вручну" — користувач вводить усі елементи самостійно
        if (choice == 1) {
            matrix = createMatrixManual(scanner, rows, cols);
        } else {
            // Інакше матриця створюється автоматично випадковими числами
            matrix = createMatrixRandom(rows, cols);
        }

        // Виведення створеної матриці
        System.out.println("\nМатриця:");
        printMatrix(matrix);

        // Знаходження мінімального, максимального та обчислення середніх значень
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
     * Зчитування розміру матриці з перевіркою на правильне введення (ціле число 1–20)
     */
    private static int getMatrixSize(Scanner scanner, String message) {
        int size = 0;
        boolean validInput = false;

        // Повторюємо, поки не отримаємо коректне число
        while (!validInput) {
            System.out.print(message);

            // Перевірка: чи є наступне введення цілим числом
            if (scanner.hasNextInt()) {
                size = scanner.nextInt();
                if (size >= 1 && size <= 20) {
                    validInput = true;
                } else {
                    System.out.println("Помилка: введіть ціле число від 1 до 20.");
                }
            } else {
                // Якщо користувач ввів не число — виводимо помилку
                System.out.println("Помилка: потрібно ввести ціле число від 1 до 20.");
                scanner.next(); // очищаємо неправильне введення
            }
        }
        return size;
    }

    /**
     * Отримання вибору користувача (1 або 2) з перевіркою на правильність введення
     */
    private static int getChoice(Scanner scanner) {
        int choice = 0;
        boolean validInput = false;

        while (!validInput) {
            System.out.print("Ваш вибір: ");

            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                if (choice == 1 || choice == 2) {
                    validInput = true;
                } else {
                    System.out.println("Помилка: введіть 1 або 2.");
                }
            } else {
                System.out.println("Помилка: потрібно ввести число 1 або 2.");
                scanner.next(); // очищення буфера
            }
        }
        return choice;
    }

    /**
     * Створення матриці вручну через клавіатуру з перевіркою, щоб вводились тільки цілі числа.
     */
    private static int[][] createMatrixManual(Scanner scanner, int rows, int cols) {
        int[][] matrix = new int[rows][cols];
        System.out.println("Введіть елементи матриці (" + rows + "×" + cols + "):");

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                boolean valid = false;
                while (!valid) {
                    System.out.print("Елемент [" + (i + 1) + "][" + (j + 1) + "]: ");

                    // Перевірка: чи введено саме ціле число
                    if (scanner.hasNextInt()) {
                        matrix[i][j] = scanner.nextInt();
                        valid = true;
                    } else {
                        System.out.println("Помилка: потрібно ввести ціле число!");
                        scanner.next(); // очищення неправильного введення
                    }
                }
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

        // Заповнюємо матрицю випадковими числами
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = random.nextInt(MAX_RANDOM - MIN_RANDOM + 1) + MIN_RANDOM;
            }
        }

        return matrix;
    }

    /**
     * Виведення матриці у зручному табличному форматі.
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

        // Підрахунок суми всіх елементів і їх кількості
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

        // Множимо лише додатні числа
        for (int[] row : matrix) {
            for (int val : row) {
                if (val > 0) {
                    product *= val;
                    count++;
                }
            }
        }

        // Якщо немає додатних елементів — повідомляємо користувача
        if (count == 0) {
            System.out.println("У матриці немає додатних елементів. Неможливо обчислити середнє геометричне.");
            return 0.0;
        }

        return Math.pow(product, 1.0 / count);
    }
}

