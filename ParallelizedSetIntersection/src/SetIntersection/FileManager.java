package SetIntersection;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class FileManager {

    private String sourceName;
    private String destinName;

    FileManager(String source, String destination) {
        this.sourceName = source;
        this.destinName = destination;
    }

    public int[] read(int capacity) {
        int[] data = new int[capacity];

        try {
            String text;
            String[] numbers;
            BufferedReader reader = new BufferedReader(new FileReader(sourceName));

            for (int i = 0; (i < capacity) && ((text = reader.readLine()) != null);) {
                numbers = text.split(" ");
                for (String number : numbers) {
                    data[i++] = Integer.parseInt(number);
                }
            }
        }
        catch (FileNotFoundException exc) {
            System.out.println("Cannot open the file : " + sourceName);
            exc.printStackTrace();
        }
        catch (IOException exc) {
            System.out.println("Cannot read from file : " + sourceName);
            exc.printStackTrace();
        }

        return data;
    }

    public void write(int[] data) {
        try {
            File dest = new File(destinName);
            BufferedWriter writer = new BufferedWriter(new FileWriter(dest, true));
            writer.write(Arrays.toString(data));
            writer.flush();
        } catch(IOException exc) {
            System.out.println("Cannot write to file : " + destinName);
            exc.printStackTrace();
        }
    }
}
