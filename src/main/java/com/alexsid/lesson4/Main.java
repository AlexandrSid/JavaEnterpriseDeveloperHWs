package main.java.com.alexsid.lesson4;

public class Main {
    public static void main(String[] args) {
        MathBox box = new MathBox(new Number[]{1.0, 2.0, 10.0, 33.2, 12.1, 182985.2});
        System.out.println(box.summator());
        System.out.println(box);
        box.splitter(2.0);
        System.out.println(box);
        box.removeInteger(5);
        System.out.println(box);
        box.dump();
    }
}
