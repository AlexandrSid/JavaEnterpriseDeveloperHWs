package com.alexsid.lesson10.task4;


import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 4. Дан интерфейс
 * <p>
 * public interface Worker {
 * <p>
 * void doWork();
 * <p>
 * }
 * <p>
 * <p>
 * Необходимо написать программу, выполняющую следующее:
 * <p>
 * - Программа с консоли построчно считывает код метода doWork. Код не должен требовать импорта дополнительных классов.
 * <p>
 * - После ввода пустой строки считывание прекращается и считанные строки добавляются в тело метода public void doWork() в файле SomeClass.java.
 * <p>
 * - Файл SomeClass.java компилируется программой (в рантайме) в файл SomeClass.class.
 * <p>
 * - Полученный файл подгружается в программу с помощью кастомного загрузчика
 * <p>
 * - Метод, введенный с консоли, исполняется в рантайме (вызывается у экземпляра объекта подгруженного класса)
 */
public class Main {
    public static final Path rootFolder = Path.of("src/main/java/com/alexsid/lesson10/task4");
    public static final Path javaFilePath = rootFolder.resolve("SomeClass.java");

    public static void main(String[] args) throws IOException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        List<String> codeLines = getMethodBody();
        writeMethodBody(javaFilePath, "void doWork()", codeLines);
//        Path resolve = compileSource();//перенёс в CustomClassLoader (например)
        CustomClassLoader classLoader = new CustomClassLoader();
        //всё-равно грузится AppClassLoader-ом, чтобы своим грузилось видимо
        // надо в .jar упаковать (как в примере с inlineCompiller, который в лучшем случае у меня падает с NoClassDefFoundError при попытке загрузить скомпилированный класс),
        // или в out/production/...../task4 SomeClass.class переписывать свежескомпилированным. потому как загружается всё-равно именно он.
        // ни один из примеров не заработал при подмене существующего класса модифицированным.

        /**
         * чесно списанный с гита вариант решения дзшки
         * и он тоже не работает - выкидывает NoClassDefFoundError при загрузке класса
         * может нужно версию явы до 6й откатить, чтобы эти фокусы заработаи? или среду как-то особым образом перенастроить?*/
        JavaCompiler jc = ToolProvider.getSystemJavaCompiler();
        jc.run(null, null, null, javaFilePath.toAbsolutePath().toString());
        URLClassLoader urlClassLoader = URLClassLoader.newInstance(new URL[] { rootFolder.toUri().toURL() });
        Class<?> cls = Class.forName("SomeClass", true, urlClassLoader);//вот тут. NoClassDefFoundError
        Worker instance = (Worker) cls.newInstance();
        instance.doWork();


//        Class<?> aClass = classLoader.findClass(javaFilePath.toString());
////        trickTheOutputs(); такой трюк тоже не заработал((
//        Worker modifiedWorker = (Worker) aClass.newInstance();
//        modifiedWorker.doWork();
    }

    private static void trickTheOutputs() throws IOException {
        File compiledAfter = new File("/home/alex/IdeaProjects/JavaEnterpriseDeveloperHWs/src/main/java/com/alexsid/lesson10/task4/SomeClass.class");
        File compiledBefore = new File("/home/alex/IdeaProjects/JavaEnterpriseDeveloperHWs/out/production/JavaEnterpriseDeveloperHWs/main/java/com/alexsid/lesson10/task4/SomeClass.class");
        try (
                InputStream in = new BufferedInputStream(
                        new FileInputStream(compiledAfter));
                OutputStream out = new BufferedOutputStream(
                        new FileOutputStream(compiledBefore))) {

            byte[] buffer = new byte[1024];
            int lengthRead;
            while ((lengthRead = in.read(buffer)) > 0) {
                out.write(buffer, 0, lengthRead);
                out.flush();
            }
        }
    }

    private static Object getInstanceOfNew(Path resolve) throws MalformedURLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        URL classUrl = resolve.getParent().toFile().toURI().toURL();
        URLClassLoader classLoader = URLClassLoader.newInstance(new URL[]{classUrl});
        Class<?> clazz = classLoader.loadClass(javaFilePath.toString());//Class.forName(javaFilePath.toString(), false, classLoader);
        System.out.println(clazz);
        return clazz.newInstance();
    }

    private static Path compileSource() {
        Path someClassPath = Path.of(javaFilePath.toString());
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        compiler.run(null, null, null, someClassPath.toFile().getAbsolutePath());
        Path resolve = someClassPath.getParent().resolve("SomeClass.class");
        return resolve;
    }

    private static List<String> getMethodBody() throws IOException {
        BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
        List<String> codeLines = new ArrayList<>();
        System.out.println("Введите код метода doWork строчка за строчкой, пустая строка означает конец метода");
        while (true) {
            String line = consoleReader.readLine();
            if (line.isEmpty()) break;
            codeLines.add(line);
        }
        return codeLines;
    }

    private static void writeMethodBody(Path path, String methodSignature, List<String> methodBody) throws IOException {
        System.out.println(path);
        List<String> classLines = Files.lines(path, StandardCharsets.UTF_8).collect(Collectors.toList());
        List<String> newClassLines = new LinkedList<>();
        for (String s : classLines) {
            newClassLines.add(s);
            if (s.contains(methodSignature)) {
                newClassLines.addAll(methodBody);
            }
        }
        FileWriter writer = new FileWriter(path.toFile());
        for (String line : newClassLines) {
            writer.write(line + System.lineSeparator());
        }
        writer.flush();
        writer.close();
    }
}
