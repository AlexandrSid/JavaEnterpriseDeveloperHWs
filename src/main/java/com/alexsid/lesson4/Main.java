package main.java.com.alexsid.lesson4;

public class Main {
    public static void main(String[] args) {
        MathBox box = new MathBox(new Number[]{1, 2.4f, 10, 33, 1.23542d, 182985L});
        System.out.println(box.summator());
        System.out.println(box);
        box.splitter(2);
        System.out.println(box);
        box.removeInteger(5);
        System.out.println(box);
        box.dump();
    }
}
