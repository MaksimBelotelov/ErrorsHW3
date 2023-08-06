package org.example.ex01;
/*
Задача 1: Проверка пароля.

Создайте класс PasswordVerifier. Этот класс должен содержать метод, который принимает строку пароля и проверяет его на соответствие следующим правилам:

Пароль должен быть не менее 8 символов.
Пароль должен содержать хотя бы одну цифру.
Пароль должен содержать хотя бы одну заглавную букву.
Метод должен выбрасывать исключение, если пароль не соответствует какому-либо из этих правил.
 */

import java.security.InvalidParameterException;
import java.util.Scanner;

public class PasswordVerifier {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while(true) {
            try {
                System.out.println("Введите пароль для проверки: ");
                String psw = scanner.nextLine();
                isMyPassValid(psw);
                break;
            } catch (InvalidPasswordException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    /**
     * Метод проверяет пароль на соответствие требованиям.
     * @param password - пароль для проверки
     * @return - true, если пароль корректен
     * @throws InvalidPasswordException если не выполняется хотя бы одно из условий.
     */
    public static boolean isMyPassValid(String password)
        throws InvalidPasswordException {
        if(password.length() < 8)
            throw new InvalidPasswordException("Длина пароля не менее 8 символов. Попробуйте ещё.");
        if(!isThereCapitalInStr(password))
            throw new InvalidPasswordException("Пароль должен содержать хотя бы одну заглавную букву");
        if(!isThereDigitInStr(password))
            throw new InvalidPasswordException("Пароль должен содержать хотя бы одну цифру");
        return true;
    }

    /**
     * Метод проверяет есть ли в строке заглавные буквы
     * @param str - строка для анализа
     * @return - true, если в строке есть хотя бы одна заглавная буква
     */
    public static boolean isThereCapitalInStr(String str) {
        for(int i = 0; i < str.length(); ++i)
            if(Character.isUpperCase(str.charAt(i)))
                return true;
        return false;
    }

    /**
     * Метод проверяет, есть ли в строке цифры
     * @param str - строка для анализа
     * @return - true, если в строке есть хотя бы одна цифра
     */
    public static boolean isThereDigitInStr(String str) {
        for(int i = 0; i < str.length(); ++i)
            if(Character.isDigit(str.charAt(i)))
                return true;
        return false;
    }

}

class InvalidPasswordException extends InvalidParameterException {
    public InvalidPasswordException(String message) {
        super(message);
    }
}