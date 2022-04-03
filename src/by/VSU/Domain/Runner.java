package by.VSU.Domain;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Runner {
    public static void main(String[] args) throws IOException {
        int length = 17;//длина слова для замены
        String word = "привет";//слово замены
        String line = "";
        FileInputStream inputStream = null;
        FileOutputStream fileOutputStream;
        StringHandler stringHandler = new StringHandler();
        Scanner scanner = null;
        try {
            inputStream = new FileInputStream("D:\\EPAM_Projects\\EpamTask2\\src\\by\\VSU\\TextFiles\\text.txt");
            scanner = new Scanner(inputStream, "UTF-8");


            while (scanner.hasNextLine()) {
                line += scanner.nextLine() + "\n";
            }
            line = stringHandler.AddWordIntoTheText(line, word, length);
            fileOutputStream = new FileOutputStream("D:\\EPAM_Projects\\EpamTask2\\src\\by\\VSU\\TextFiles\\text.txt");
            fileOutputStream.write(line.getBytes(StandardCharsets.UTF_8));

            if (scanner.ioException() != null) {
                throw scanner.ioException();
            }
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден!");
            ;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
            if (scanner != null) {
                scanner.close();
            }
        }
    }
}
