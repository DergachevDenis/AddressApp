package ch.makery.address.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

// Вспомогательный класс для работы с датами
public class DateUtil {

    //Шаблон даты, используемый для преобразования
    private static final String DATE_PATTERN = "dd.MM.yyyy";

    //Форматировщик даты
    private static final DateTimeFormatter DATE_FORMATTER = 
            DateTimeFormatter.ofPattern(DATE_PATTERN);

    //Метод, который возвращает полученную дату в виде строки
    public static String format(LocalDate date) {
        if (date == null) {
            return null;
        }
        return DATE_FORMATTER.format(date);
    }

   //  Метод который преобразует строку, которая отформатирована по правилам шаблона
    public static LocalDate parse(String dateString) {
        try {
            return DATE_FORMATTER.parse(dateString, LocalDate::from);
        } catch (DateTimeParseException e) {
            return null;
        }
    }

    //Проверяет, является ли строка корректной датой.
    public static boolean validDate(String dateString) {
        // Try to parse the String.
        return DateUtil.parse(dateString) != null;
    }
}