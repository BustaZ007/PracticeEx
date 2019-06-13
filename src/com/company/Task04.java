package com.company;

import java.io.*;
import java.util.*;

public class Task04 {
    public static void main(String[] args) {
        try (FileReader reader = new FileReader("/Users/pavelzaborin/IdeaProjects/PracticeEx/src/com/company/text.txt")){
            int c;
            int countLetter= 0;
            Map<Character, Integer> map = new HashMap<>();
            while ((c = reader.read()) != -1){
                if(Character.isLetter(c)){
                    Character ch = Character.toLowerCase((char)c);
                    if (!map.containsKey(ch)){
                        map.put(ch,1);
                        countLetter += 1;
                    }
                    else {
                        map.put(ch, map.get(ch) + 1);
                        countLetter += 1;
                    }
                }
            }
            Object[] s = map.keySet().toArray();
            for (int i = 0;i < s.length;i++){
                System.out.printf("%s : %.2f%n",s[i].toString(),(double)map.get(s[i])/countLetter*100);
            }
        }
        catch (IOException ex){
            System.out.println(ex.getMessage());
        }
    }
}
