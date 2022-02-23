package utils;


import Exceptions.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class ExceptionHandling {

    public static void isWord(String word) {
        for (char ch : word.toLowerCase().toCharArray())
            if (!(ch >= 97 && ch <= 122))
                throw new WordException();
    }

    public static boolean isWords(String word) {
        for (char ch : word.toLowerCase().toCharArray())
            if (!(ch >= 97 && ch <= 122))
                return false;
        return true;
    }

    public static void isDate(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            sdf.parse(date);
            sdf.setLenient(false);
        } catch (ParseException e) {
            throw new DateException();
        }
    }

    public static void isDigit(String digit) {
        for (char chr : digit.toCharArray())
            if (!Character.isDigit(chr))
                throw new DigitException();
    }

    public static void isDigit(Integer digit) {
        isDigit(String.valueOf(digit));
    }

    public static void isNationalCode(String nationalCode) {
        if (nationalCode.length() != 10)
            throw new NationalCodeException();

        for (char chr : nationalCode.toCharArray())
            if (!Character.isDigit(chr))
                throw new NationalCodeException();
    }

    public static void isPhoneNumber(String phoneNumber) {
        if (phoneNumber.length() != 11)
            throw new PhoneNumberException();

        if (phoneNumber.charAt(0) != '0' || phoneNumber.charAt(1) != '9')
            throw new PhoneNumberException();
    }

    public static void isMoney(String money) {
        for (char chr : money.toCharArray()) {
            if (!Character.isDigit(chr))
                throw new MoneyException();
        }
    }


    public static void isCinemaName(String cinemaName) {
        for (char ch : cinemaName.toLowerCase().toCharArray())
            if (!(ch >= 97 && ch <= 122))
                throw new CinemaNameException();
    }

    public static void isTitle(String title) {
        if (!isWords(title))
            throw new TitleException();
    }

    public static void isFirstName(String firstName) {
        if (!isWords(firstName)) {
            throw new FirstNameException();
        }
    }

    public static void isLastName(String lastName) {
        if (!isWords(lastName)) {
            throw new LastNameException();
        }
    }

    public static void isDepartment(String department) {
        if (!(department.equals(String.valueOf(DEPARTMENT.COMPUTER)) ||
                department.equals(String.valueOf(DEPARTMENT.ELECTRICAL)) ||
                department.equals(String.valueOf(DEPARTMENT.ELECTRONIC)) ||
                department.equals(String.valueOf(DEPARTMENT.ACCOUNTING)))) {
            throw new DepartmentException();
        }
    }

    public static void isStatus(String status) {
        if (!(status.equals(String.valueOf(STATUS.SCIENCE_COMMITTEE)) ||
                status.equals(String.valueOf(STATUS.TUITION_FEE)))) {
            throw new StatusException();
        }
    }

    public static void isUnit(String unit) {
        for (char chr : unit.toCharArray())
            if (!Character.isDigit(chr))
                throw new UnitException();
    }

    public static void isScore(String score) {
        for (char chr : score.toCharArray())
            if (!Character.isDigit(chr))
                throw new ScoreException();
        double scr = Double.parseDouble(score);
        if (!(scr >= 0 && scr <= 20))
            throw new ScoreException();
    }
}
