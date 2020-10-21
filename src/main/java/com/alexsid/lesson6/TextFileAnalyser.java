package com.alexsid.lesson6;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 * 1. Написать программу, читающую текстовый файл.
 * Программа должна составлять отсортированный по алфавиту список слов, найденных в файле
 * и сохранять его в файл-результат.
 * Найденные слова не должны повторяться, регистр не должен учитываться.
 * Одно слово в разных падежах – это разные слова.
 */
public final class TextFileAnalyser {
    //не стал выводить в .property файл
    private String inputFileName = "src/main/resources/input.txt";
    private String outputFileName = "src/main/resources/output.txt";

    public void streamCompute() {
        try (PrintStream out = new PrintStream(new FileOutputStream(outputFileName))) {
            Files.lines(Paths.get(inputFileName), StandardCharsets.UTF_8)
                    .flatMap(line -> Stream.of(line.split(" ")))//разбиваем стороки на слова
                    .map(word -> word.replaceAll("\\p{P}", "").toLowerCase())//удаляем пунктуацию и переводим в нижний регистр для сравнения
                    .distinct()
                    .sorted()
                    .forEach(out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        TextFileAnalyser analizer = new TextFileAnalyser();
        analizer.streamCompute();
    }
}

