package com.company;

import java.util.Scanner;
import java.util.Stack;

public class Task03 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Введите строку:");
        String str = in.nextLine();
        char[] ch = str.toCharArray();
        Stack st = new Stack();
        int resultCount = 0;
        for (int i = 0; i < ch.length; i++){
            if (st.empty() && (ch[i] == '}' || ch[i] == ')' || ch[i] == ']')) {
                System.out.println("ОШИБКА");
                resultCount = -1;
                break;
            }
            if(ch[i] == '{' || ch[i] == '(' || ch[i] == '[')
                st.push(ch[i]);
            else if((ch[i] == '}' && '{' == (char)st.peek()) ||
                    (ch[i] == ']' && '[' == (char)st.peek()) ||
                    (ch[i] == ')' && '(' == (char)st.peek())){
                resultCount += 1;
                st.pop();
            }
        }
        if(!st.empty())
            System.out.println("ОШИБКА");
        else if (resultCount > 0)
            System.out.println(resultCount);
    }
}
