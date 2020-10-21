package com.alexsid.lesson6;
/**
 * Необходимо написать метод getFiles(String path, int n, int size, String[] words),
 * который создаст n файлов размером size в каталоге path. words - массив слов.
 */

import java.io.*;
import java.util.Random;

public class TextFileGenerator {

    public void getFiles(String path, int n, int size, String[] words) {
        TextGenerator generator = new TextGenerator(
                new WordProvider() {//Генератор слов из массива
                    Random random = new Random();
                    @Override
                    public String getWord() {
                        return words[random.nextInt(words.length)];
                    }
                });
        //
        for (int i = 0; i < n; i++) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(path + String.valueOf(i)))) {
                for (int j = 0; j < size; j++) {
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
}
