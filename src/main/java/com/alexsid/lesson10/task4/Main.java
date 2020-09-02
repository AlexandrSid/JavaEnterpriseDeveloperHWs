package main.java.com.alexsid.lesson10.task4;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 4. Дан интерфейс
 *
 * public interface Worker {
 *
 *     void doWork();
 *
 * }
 *
 *
 * Необходимо написать программу, выполняющую следующее:
 *
 * - Программа с консоли построчно считывает код метода doWork. Код не должен требовать импорта дополнительных классов.
 *
 * - После ввода пустой строки считывание прекращается и считанные строки добавляются в тело метода public void doWork() в файле SomeClass.java.
 *
 * - Файл SomeClass.java компилируется программой (в рантайме) в файл SomeClass.class.
 *
 * - Полученный файл подгружается в программу с помощью кастомного загрузчика
 *
 * - Метод, введенный с консоли, исполняется в рантайме (вызывается у экземпляра объекта подгруженного класса)
 */
public class Main {

    public static void main(String[] args) throws IOException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
        List<String> codeLines = new ArrayList<>();
        System.out.println("Введите код метода doWork строчка за строчкой, пустая строка означает конец метода");
        while (true){
            String line = consoleReader.readLine();
            if(line.isEmpty()) break;
            codeLines.add(line);
        }

        System.out.println("до вставки кода");
        writeMethodBody("src/main/java/com/alexsid/lesson10/task4/SomeClass.java", "void doWork()", codeLines);
        System.out.println("после вставки кода");

        //выделение в другую нить блока кода с загрузкой класса ни на что не повлияло

        Thread secondThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    ClassLoader custom = new CustomClassLoader();
                    Class<?> aClass = custom.loadClass(SomeClass.class.getName());
                    Worker worker = (Worker)aClass.newInstance();
                    worker.doWork();
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException  e) {
                    e.printStackTrace();
                }
            }
        });
        secondThread.run();
    }

    //я хз будет ли это работать вообще
    private static void writeMethodBody(String className, String methodSignature, List<String> methodBody) throws IOException {
        List<String> classLines = Files.lines(Paths.get(className), StandardCharsets.UTF_8).collect(Collectors.toList());
        List<String> newClassLines = new LinkedList<>();
        for(String s : classLines){
            newClassLines.add(s);
            if (s.contains(methodSignature)){
                newClassLines.addAll(methodBody);
            }
        }
        FileWriter writer = new FileWriter(className);
        for(String line : newClassLines){
            writer.write(line + System.lineSeparator());
        }
        writer.flush();
        writer.close();
    }
}
