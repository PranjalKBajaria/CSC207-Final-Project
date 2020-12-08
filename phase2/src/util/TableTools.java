package util;

import java.util.ArrayList;
import java.util.List;

/**
 * Class to provide operations on tables
 */
public class TableTools {
    private List<List<String>> table;

    public TableTools(List<List<String>> table) {
        this.table = table;
    }

    /**
     * Format a 2D string array into a table
     *
     * @param title title for the table
     * @return table formatted as a string
     */
    public String stringifyTable(String title) {
        if (this.table.isEmpty()) {
            return "";
        }

        List<Integer> colWidths = new ArrayList<>();
        for (String s : this.table.get(0)) {
            colWidths.add(Math.floorDiv(150, this.table.get(0).size()));
        }
        int width = 0;
        for (int i : colWidths) {
            width = width + i;
        }

        width = width + this.table.get(0).size() - 1;
        StringBuilder table = new StringBuilder();
        StringBuilder topLine = new StringBuilder();
        StringBuilder bottomLine = new StringBuilder();
        topLine.append("╔");
        bottomLine.append("╚");

        for (int i = 0; i < width; i++) {
            topLine.append("═");
            bottomLine.append("═");
        }

        topLine.append("╗\r\n<br />");
        bottomLine.append("╝");
        table.append(topLine);

        StringBuilder titleLine = new StringBuilder();
        titleLine.append("║");
        titleLine.append(title);

        int numWhitespace = topLine.length() - titleLine.length();

        for (int j = 0; j < numWhitespace; j++) {
            titleLine.append("&nbsp;");
        }

        titleLine.append("║\r\n<br />");
        table.append(titleLine);

        StringBuilder hLine = new StringBuilder();
        hLine.append("╠");

        while (hLine.length() <= width) {
            hLine.append("-");
        }

        hLine.append("╣\r\n<br />");
        table.append(hLine);

        for (List<String> sub : this.table) {
            StringBuilder row = new StringBuilder();
            row.append("║");
            for (int i = 0; i < colWidths.size(); i++) {
                StringBuilder cell = new StringBuilder();

                cell.append(sub.get(i));

                int numWhiteSpace = colWidths.get(i) - cell.length();

                for (int k = 0; k < numWhiteSpace; k++) {
                    cell.append("&nbsp;");
                }

                cell.append("│");
                row.append(cell);
            }
            row.deleteCharAt(row.length() - 1);
            table.append(row);

            table.append("║\r\n<br />");
        }

        table.append(bottomLine);
        return table.toString();
    }
}

