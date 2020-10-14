package main.java.com.alexsid.lesson12;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class BirthdayWeekend {
    public static void main(String[] args) {
        System.out.println("программа попросит ввести дату вашего рождения в формате \"yyyy.MM.dd\"");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите день рождения");
        String rawDate = scanner.next();

        LocalDate date = LocalDate.parse(rawDate, DateTimeFormatter.ofPattern("yyyy.MM.dd"));
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        var result = switch (dayOfWeek) {
            case SATURDAY, SUNDAY -> "Этот день был выходным!";
            default -> "это был будний день.";
        };
        var translation = switch (dayOfWeek) {
            case MONDAY -> "Понедельник";
            case THURSDAY -> "Вторник";
            case WEDNESDAY -> "Среда";
            case TUESDAY -> "Четверг";
            case FRIDAY -> "Пятница";
            case SATURDAY -> "Суббота";
            case SUNDAY -> "Воскресенье";
        };

        System.out.format("Вы ввели %s-%s, %s\n", date, translation, result);
        System.out.println("программа не учитывает страну, государственные праздники и переносы в производственном календаре");
    }
}
