package lab1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Увести n рядків з консолі. Упорядкувати й вивести рядки в порядку зростання їх довжин, а також (другий пріоритет) значень їхніх довжин.
 */
public class Task2 {

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            start(reader);
        } catch(IOException e) {
            System.out.println("IO Exception");
            e.printStackTrace();
        }
        System.out.println("---------------------------");
    }

    public static void start(BufferedReader reader) {
        //Let's write the text to the pre-buffered stream of characters
        try {
            System.out.println("---------------------------");
            System.out.println("Enter rows : ");
            FillerListFromReader filler = new FillerListFromReader(reader);
            filler.fillListFromReader();
            List<String> rows = filler.getRows();
            RowsSorter rowsSorter = new RowsSorterImpl(rows);
            rowsSorter.sort();
            outputResult(rows);
        } catch (IOException e) {
            System.out.println("IO Exception");
            e.printStackTrace();
        }
    }

    private static void outputResult(List<String> list) {
        System.out.println("---------------------------");
        for (String row : list) {
            System.out.printf("Row: %s, it's length: %d%n", row, row.length());
        }
        System.out.println("---------------------------");
    }
}

interface RowsSorter {
    void sort();
}

class RowsSorterImpl implements RowsSorter {

    private List<String> rows;

    RowsSorterImpl(List<String> rows) {
        this.rows = rows;
    }

    @Override
    public void sort() {
        rows.sort(Comparator.comparingInt(String::length));
    }
}

class FillerListFromReader {

    private final BufferedReader reader;
    private List<String> rows;

    FillerListFromReader(BufferedReader reader) {
        this.reader = reader;
        this.rows = new ArrayList<>();
    }

    void fillListFromReader() throws IOException {
        while (true) {
            String row = reader.readLine();
            if (row.isEmpty()) {
                break;
            }
            rows.add(row);
        }
    }

    List<String> getRows() {
        return rows;
    }
}