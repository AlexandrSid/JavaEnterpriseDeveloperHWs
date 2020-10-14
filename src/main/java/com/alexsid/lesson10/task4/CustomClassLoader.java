package main.java.com.alexsid.lesson10.task4;


import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Path;

public class CustomClassLoader extends ClassLoader {

    //кастомный загрузчик из туториала
    @Override
    public Class findClass(String name) throws ClassNotFoundException {
        byte[] b = loadClassFromFile(name);
        //далее вылезает NoClassDefFoundError - при самом успешном типе запуска, при котором исходный код меняется, компилируется и пытается загрузиться в defineClass методе
        //тот же error в примере InlineCompiller.
        return defineClass(name, b, 0, b.length);
    }

    private byte[] loadClassFromFile(String fileName)  {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(
                fileName.replace('.', File.separatorChar) + ".class");
        byte[] buffer;
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        int nextValue = 0;
        try {
            while ( (nextValue = inputStream.read()) != -1 ) {
                byteStream.write(nextValue);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        buffer = byteStream.toByteArray();
        return buffer;
    }


    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        Path path = compileSource();
        try {
            return getInstanceOfNew(path);
        } catch (MalformedURLException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
            return super.loadClass(name);
        }
    }

//компиляция в класслоадере согласно совету
    private static Class<?> getInstanceOfNew(Path resolve) throws MalformedURLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        URL classUrl = resolve.getParent().toFile().toURI().toURL();
        URLClassLoader classLoader = URLClassLoader.newInstance(new URL[]{classUrl});
        return classLoader.loadClass("main.java.com.alexsid.lesson10.task4.SomeClass");
    }

    private static Path compileSource() {
        Path someClassPath = Path.of("src/main/java/com/alexsid/lesson10/task4/SomeClass.java");
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        compiler.run(null, null, null, someClassPath.toFile().getAbsolutePath());
        Path resolve = someClassPath.getParent().resolve("SomeClass.class");
        return resolve;
    }
}