package org.programming.labs.lab4;

import java.util.List;
import java.util.Map;

public class QueryVisualizer {

    private static final String HORIZONTAL_LINE = "─";
    private static final String VERTICAL_LINE = "│";
    private static final String INTERSECTION = "┼";

    public void displayResults(QueryResultData resultData) {
        if (resultData == null || resultData.getHeaders().isEmpty()) {
            System.out.println("No data to display.");
            return;
        }

        List<String> headers = resultData.getHeaders();
        List<Map<String, String>> data = resultData.getData();
        int columnCount = headers.size();

        int[] columnWidths = calculateColumnWidths(headers, data);

        drawTableBorder(columnWidths, '┌', '┬', '┐');

        printHeaders(headers, columnWidths);

        drawTableBorder(columnWidths, '├', '┼', '┤');

        printDataRows(headers, data, columnWidths);

        drawTableBorder(columnWidths, '└', '┴', '┘');
    }

    private int[] calculateColumnWidths(List<String> headers, List<Map<String, String>> data) {
        int columnCount = headers.size();
        int[] widths = new int[columnCount];

        for (int i = 0; i < columnCount; i++) {
            widths[i] = headers.get(i).length();
        }

        for (Map<String, String> row : data) {
            for (int i = 0; i < columnCount; i++) {
                String header = headers.get(i);
                String value = row.getOrDefault(header, "");
                if (value.length() > widths[i]) {
                    widths[i] = value.length();
                }
            }
        }
        return widths;
    }

    private void drawTableBorder(int[] widths, char startChar, char midChar, char endChar) {
        StringBuilder line = new StringBuilder();
        line.append(startChar);
        for (int i = 0; i < widths.length; i++) {
            line.append(HORIZONTAL_LINE.repeat(widths[i] + 2));
            line.append(i < widths.length - 1 ? midChar : endChar);
        }
        System.out.println(line);
    }

    private void printHeaders(List<String> headers, int[] widths) {
        for (int i = 0; i < headers.size(); i++) {
            System.out.printf(VERTICAL_LINE + " %-" + widths[i] + "s ", headers.get(i));
        }
        System.out.println(VERTICAL_LINE);
    }

    private void printDataRows(List<String> headers, List<Map<String, String>> data, int[] widths) {
        for (Map<String, String> row : data) {
            for (int i = 0; i < headers.size(); i++) {
                String header = headers.get(i);
                String value = row.getOrDefault(header, "NULL");
                System.out.printf(VERTICAL_LINE + " %-" + widths[i] + "s ", value);
            }
            System.out.println(VERTICAL_LINE);
        }
    }
}
