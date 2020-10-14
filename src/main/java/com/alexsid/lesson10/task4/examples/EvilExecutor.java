package main.java.com.alexsid.lesson10.task4.examples;

import main.java.com.alexsid.lesson10.task4.Worker;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;

import static java.nio.charset.StandardCharsets.UTF_8;

public class EvilExecutor {

    //reads the source code from an arbitrary file on the file system, and returns it as a string.
    //An alternative implementation would get the source from across the network.
    private String readCode(String sourcePath) throws FileNotFoundException {
        InputStream stream = new FileInputStream(sourcePath);
        String separator = System.getProperty("line.separator");
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        return reader.lines().collect(Collectors.joining(separator));
    }

    //creates a new file from the source code in a read-enabled directory.
    //The file name is hard-coded, more refined versions would parse the code parameter
    //to create a file named according to the class name it contains.
    private Path saveSource(String source) throws IOException {
        String tmpProperty = "src/main/java/com/alexsid/lesson10/task4";//System.getProperty("java.io.tmpdir");
        Path sourcePath = Paths.get(tmpProperty, "SomeClass.java");
        Files.write(sourcePath, source.getBytes(UTF_8));
        return sourcePath;
    }

    //compiles the class file out of the java file.
    private Path compileSource(Path javaFile) {
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        compiler.run(null, null, null, javaFile.toFile().getAbsolutePath());
        return javaFile.getParent().resolve("SomeClass.class");
    }

    //loads the compiled class and instantiates a new object.
    //To be independent from any cast, the to-be-executed code should be set in the constructor of the external source code class.
    private void runClass(Path javaClass)
            throws MalformedURLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        URL classUrl = javaClass.getParent().toFile().toURI().toURL();
        URLClassLoader classLoader = URLClassLoader.newInstance(new URL[]{classUrl});
        Class<?> clazz = Class.forName("main.java.com.alexsid.lesson10.task4.SomeClass", true, classLoader);
        Worker frankenstein = (Worker)clazz.newInstance();
        frankenstein.doWork();
    }

    public void doEvil(String sourcePath) throws Exception {
        String source = readCode(sourcePath);
        Path javaFile = saveSource(source);
        Path classFile = compileSource(javaFile);
        runClass(classFile);
    }

    public static void main(String... args) throws Exception {
        new EvilExecutor().doEvil("src/main/java/com/alexsid/lesson10/task4/SomeClass.java");
    }
}