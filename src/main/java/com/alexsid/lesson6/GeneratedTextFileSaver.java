package main.java.com.alexsid.lesson6;
/**Необходимо написать метод getFiles(String path, int n, int size, String[] words),
 * который создаст n файлов размером size в каталоге path. words - массив слов.
 * */
import java.io.*;

public class GeneratedTextFileSaver {
    private static String outputFileName = "src/main/resources/generatedText.txt";
    private static int numberOfParagraphs = 100;

    public static void main(String[] args) {
        TextGenerator generator = new TextGenerator(15, 15, 20, 5);
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(outputFileName))){
            for (int i = 0; i < numberOfParagraphs; i++) {
                writer.write(generator.generateParagraph());
            }
            writer.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
