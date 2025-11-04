package org.programming.labs.other;

import java.util.Scanner;
import java.util.Random;

public class Lab_1 {


    private static final int MIN_ROWS_COLS = 1;
    private static final int MAX_ROWS_COLS = 20;

    private static final int MIN_RANDOM_VALUE = -50;
    private static final int MAX_RANDOM_VALUE = 50;

    private static final Random RANDOM_GENERATOR = new Random();


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Integer Matrix Processing Program ===");

        String rowPrompt = String.format("Enter the number of rows (%d–%d): ", MIN_ROWS_COLS, MAX_ROWS_COLS);
        int rows = getMatrixDimension(scanner, rowPrompt);

        String colPrompt = String.format("Enter the number of columns (%d–%d): ", MIN_ROWS_COLS, MAX_ROWS_COLS);
        int cols = getMatrixDimension(scanner, colPrompt);

        System.out.println("Select matrix creation method:");
        System.out.println("1 — Manual Input");
        System.out.println("2 — Random Generation");
        int creationChoice = getChoice(scanner);

        int[][] matrix;

        if (creationChoice == 1) {
            matrix = createMatrixManual(scanner, rows, cols);
        } else {
            matrix = createMatrixRandom(rows, cols);
        }

        System.out.println("\nMatrix:");
        printMatrix(matrix);

        int min = findMinElement(matrix);
        int max = findMaxElement(matrix);
        double avg = calculateArithmeticMean(matrix);

        double geoMean = calculateGeometricMean(matrix);

        System.out.println("\n=== Calculation Results ===");
        System.out.println("Minimum element: " + min);
        System.out.println("Maximum element: " + max);
        System.out.printf("Arithmetic Mean: %.2f\n", avg);
        System.out.printf("Geometric Mean: %.2f\n", geoMean);

        scanner.close();
    }

    private static int getMatrixDimension(Scanner scanner, String promptMessage) {
        int size = 0;
        boolean validInput = false;
        String rangeError = String.format("Error: Enter an integer from %d to %d.", MIN_ROWS_COLS, MAX_ROWS_COLS);

        while (!validInput) {
            System.out.print(promptMessage);

            if (scanner.hasNextInt()) {
                size = scanner.nextInt();
                if (size >= MIN_ROWS_COLS && size <= MAX_ROWS_COLS) {
                    validInput = true;
                } else {
                    System.out.println(rangeError);
                }
            } else {
                System.out.println(rangeError);
                scanner.next();
            }
        }
        return size;
    }

    private static int getChoice(Scanner scanner) {
        int choice = 0;
        boolean validInput = false;

        while (!validInput) {
            System.out.print("Your choice: ");

            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                if (choice == 1 || choice == 2) {
                    validInput = true;
                } else {
                    System.out.println("Error: Enter 1 or 2.");
                }
            } else {
                System.out.println("Error: Must enter number 1 or 2.");
                scanner.next();
            }
        }
        return choice;
    }

    private static int[][] createMatrixManual(Scanner scanner, int rows, int cols) {
        int[][] matrix = new int[rows][cols];
        System.out.printf("Enter matrix elements (%d×%d):\n", rows, cols);

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                boolean valid = false;
                while (!valid) {
                    System.out.printf("Element [%d][%d]: ", i + 1, j + 1);

                    if (scanner.hasNextInt()) {
                        matrix[i][j] = scanner.nextInt();
                        valid = true;
                    } else {
                        System.out.println("Error: Must enter an integer!");
                        scanner.next();
                    }
                }
            }
        }
        return matrix;
    }

    private static int[][] createMatrixRandom(int rows, int cols) {
        int[][] matrix = new int[rows][cols];

        int range = MAX_RANDOM_VALUE - MIN_RANDOM_VALUE + 1;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = RANDOM_GENERATOR.nextInt(range) + MIN_RANDOM_VALUE;
            }
        }
        System.out.printf("Matrix generated with random integers between %d and %d.\n", MIN_RANDOM_VALUE, MAX_RANDOM_VALUE);
        return matrix;
    }

    private static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int val : row) {
                System.out.printf("%6d", val);
            }
            System.out.println();
        }
    }

    private static int findMinElement(int[][] matrix) {
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

    private static int findMaxElement(int[][] matrix) {
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

    private static double calculateGeometricMean(int[][] matrix) {
        double product = 1.0;
        int positiveCount = 0;

        for (int[] row : matrix) {
            for (int val : row) {
                if (val > 0) {
                    product *= val;
                    positiveCount++;
                }
            }
        }

        if (positiveCount == 0) {
            System.out.println("No positive elements found. Cannot calculate Geometric Mean.");
            return 0.0;
        }

        return Math.pow(product, 1.0 / positiveCount);
    }
}