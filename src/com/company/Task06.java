package com.company;

import java.util.Scanner;
public class Task06 {
    public static class Point{
        int x;
        int y;

        public Point(int x, int y){
            this.x = x;
            this.y = y;
        }

        public int getX(){
            return this.x;
        }

        public int getY(){
            return this.y;
        }

        public Point summ(Point p){
            return new Point(this.x + p.getX(), this.y + p.getY());
        }

        public boolean check(Point p, char[][] cArr){
            return this.x + p.getX() >= 0 && this.x + p.getX() < 8 &&
                    this.y + p.getY() >= 0 && this.y + p.getY() < 8 && cArr[this.x + p.getX()][this.y + p.getY()] == 'O';
        }
    }
    public static Point[] steps = new Point[]{new Point(2, -1), new Point(-2, 1),new Point(1, -2),new Point(-1, -2),
            new Point(2, 1),new Point(-2, -1),new Point(1, 2),new Point(-1, 2)};
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Введите в пределах от 0 до 7 координату Х начального положени коня:");
        int x = in.nextInt();
        System.out.println("Введите в пределах от 0 до 7 координату У начального положени коня:");
        int y = in.nextInt();
        while (x < 0 || x > 7 || y < 0 || y > 7){
            System.out.println("Введены неверные координаты, попробуйте снова:");
            System.out.println("Введите в пределах от 0 до 7 координату Х начального положени коня:");
            x = in.nextInt();
            System.out.println("Введите в пределах от 0 до 7 координату У начального положени коня:");
            y = in.nextInt();
        }
        char[][] arr = new char[8][8];
        for (int i = 0; i < arr.length; i++ ){
            for (int j = 0; j < arr[i].length; j++){
                arr[i][j] = 'O';
            }
        }
        arr[x][y] = 'X';
        Point currentP = new Point(x,y);
        print(arr, currentP);
        while (true){
            int optimalCount = 10;
            int optimalSteps = 0;
            for(int i = 0; i < steps.length;i++){
                if(currentP.check(steps[i], arr)){
                    int c = countOfStep(currentP.summ(steps[i]), arr);
                    if (c < optimalCount){
                        optimalCount = c;
                        optimalSteps = i;
                    }
                }
            }
            currentP = currentP.summ(steps[optimalSteps]);
            arr[currentP.getX()][currentP.getY()] = 'X';
            print(arr, currentP);
            if (optimalCount == 0){
                break;
            }
        }
    }

    public static void print(char[][] cArr, Point p){
        for (int i = 0; i < cArr.length; i++ ){
            for (int j = 0; j < cArr[i].length; j++){
                System.out.print(cArr[i][j] + "  ");
            }
            System.out.println();
        }
        System.out.println();
        System.out.println("Текущий ход: " + p.getX() + ":" + p.getY());
        System.out.println();
    }

    public static int countOfStep(Point p, char[][] cArr){
        int resultCount = 0;
        if(p.getX() + 2 < 8 && p.getY() - 1 >= 0 && cArr[p.getX() + 2][p.getY() - 1] == 'O')
            resultCount += 1;
        if(p.getX() - 2 >= 0 && p.getY() + 1 < 8 && cArr[p.getX() - 2][p.getY() + 1] == 'O')
            resultCount += 1;
        if(p.getX() + 1 < 8 && p.getY() - 2 >= 0 && cArr[p.getX() + 1][p.getY() - 2] == 'O')
            resultCount += 1;
        if(p.getX() - 1 >= 0 && p.getY() - 2 >= 0 && cArr[p.getX() - 1][p.getY() - 2] == 'O')
            resultCount += 1;
        if(p.getX() + 2 < 8 && p.getY() + 1 < 8 && cArr[p.getX() + 2][p.getY() + 1] == 'O')
            resultCount += 1;
        if(p.getX() - 2 >= 0 && p.getY() - 1 >= 0 && cArr[p.getX() - 2][p.getY() - 1] == 'O')
            resultCount += 1;
        if(p.getX() + 1 < 8 && p.getY() + 2 < 8 && cArr[p.getX() + 1][p.getY() + 2] == 'O')
            resultCount += 1;
        if(p.getX() - 1 >= 0 && p.getY() + 2 < 8 && cArr[p.getX() - 1][p.getY() + 2] == 'O')
            resultCount += 1;
        return resultCount;
    }
}
