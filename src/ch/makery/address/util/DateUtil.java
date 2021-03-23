package ch.makery.address.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

//Вспомогательные функции для работы с датами.
public class DateUtil {

    // Шаблон даты, используемый для преобразования. Можно поменять на свой.
    private static final String DATE_PATTER = "dd.MM.yyyy";
    //Форматировщик даты
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(DATE_PATTER);

    //Возвращает полученную дату в виде хорошо отформатированной строки

    public static String format(LocalDate date){
        if (date == null){
            return null;
        }
        return DATE_FORMATTER.format(date);
    }
    //Преобразует строку, которая отформатирована по правилам шаблона

    public static LocalDate parse (String dateString){
        try {
            return DATE_FORMATTER.parse(dateString,LocalDate::from);
        }
        catch (DateTimeParseException e){
            return null;
        }
    }
    // Проверяет, является ли строка корректной датой

    public static boolean validDate (String dateString){
        //Пытаемся разобрать строку.
        return DateUtil.parse(dateString)!=null;
    }

}
