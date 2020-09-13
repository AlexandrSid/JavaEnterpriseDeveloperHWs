package main.java.com.alexsid.lesson10.task4;

public class SomeClass implements Worker {
    static {
        System.out.println("SomeClass loaded with " + SomeClass.class.getClassLoader());
    }
    @Override
    public void doWork() {
System.out.println("QQY");
System.out.println("QQT");
System.out.println("QQR");
System.out.println("QQE");
System.out.println("QQW");
System.out.println("QQQ");
    }
}
