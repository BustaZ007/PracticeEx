package com.company;

import java.util.Scanner;

public class Task02 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Введите положительное начало диапазона:");
        int begin = in.nextInt();
        System.out.println("Введите положительный конец диапазона:");
        int end = in.nextInt();
        while (begin == end || end < begin || end < 0 || begin < 0){
            System.out.println("Введен неверный диапазон! Попробуйте снова.");
            System.out.println("Введите начало диапазона:");
            begin = in.nextInt();
            System.out.println("Введите конец диапазона:");
            end = in.nextInt();
        }
        System.out.println("Числа, сумма кубов цифр которых ровна самому числу:");
        int res = -1;
        for (int i = begin; i < end; i++){
            if(i == Summ(i)){
                res = i;
                System.out.println(i);
            }
        }
        if (res == -1)
            System.out.println("В введенном диапазоне нет таких чисел!");
    }
    public static int Summ(int a){
        int result = 0;
        while (a > 0){
            result += Math.pow(a % 10, 3);
            a = a / 10;
        }
        return result;
    }
}
