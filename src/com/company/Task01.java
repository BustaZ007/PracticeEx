package com.company;

import java.util.Scanner;

public class Task01 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Введите строку:");
        String str = in.nextLine();
        if (str.length() > 0) {
            StringBuilder sb ;
            if(str.length() % 2 == 1)
                sb = new StringBuilder(str.substring(str.length()/2 + 1));
            else
                sb = new StringBuilder(str.substring(str.length()/2));
            if (str.substring(0, str.length()/2).equals(sb.reverse().toString()))
                System.out.println("Введенная строка является полиндромом!");
            else
                System.out.println("Введенная строка не является полиндромом!");
        }
        else {
            System.out.println("Введена пустая строка!");
        }
    }
}
