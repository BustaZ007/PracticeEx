package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Task10 {
    public static class Point{
        double x;
        double y;
        public Point(double x, double y){
            this.x = x;
            this.y = y;
        }
        public double getX(){
            return this.x;
        }
        public double getY(){
            return this.y;
        }
    }

    public static class Triangle{
        Point a;
        Point b;
        Point c;

        public Triangle(double x1, double y1, double x2, double y2, double x3, double y3){
            a = new Point(x1,y1);
            b = new Point(x2, y2);
            c = new Point(x3, y3);
        }
        public Point A(){
            return a;
        }
        public Point B(){
            return b;
        }
        public Point C(){
            return c;
        }
    }

    public static void main(String[] args) {
        System.out.println("Зададим координаты первого треугольника!");
        Triangle firstTri = getTriangle();
        System.out.println("Отлично! Теперь зададаим координаты второго треугольника!");
        Triangle secondTri = getTriangle();
        List<Point> pointsInter = getInterPoints(firstTri, secondTri);
        addInterPoints(pointsInter,firstTri,secondTri);
        double area = getArea(pointsInter);
        System.out.println("Площадь пересечения: " + area);
    }

    public static double getArea(List<Point> list){
        double result = 0;
        for (int i = 0; i < list.size() - 1; i++){
            result =result + list.get(i).getX()*list.get(i+1).getY();
        }
        result =result+ list.get(list.size()-1).getX()*list.get(0).getY();
        for (int i = 0; i < list.size() - 1; i++){
            result =result- list.get(i+1).getX()*list.get(i).getY();
        }
        result =result- list.get(0).getX()*list.get(list.size()-1).getY();
        return 0.5*Math.abs(result);
    }

    public static void addInterPoints(List<Point> list, Triangle fTri, Triangle sTri){
        Point A1 = fTri.A();
        Point B1 = fTri.B();
        Point C1 = fTri.C();
        Point A2 = sTri.A();
        Point B2 = sTri.B();
        Point C2 = sTri.C();
        //проверка на лежащие внутри точки
        if (checkBelong(A1, B1, C1, A2))
            list.add(A2);
        if (checkBelong(A1, B1, C1, B2))
            list.add(B2);
        if (checkBelong(A1, B1, C1, C2))
            list.add(C2);
        if (checkBelong(A2, B2, C2, A1))
            list.add(A1);
        if (checkBelong(A2, B2, C2, B1))
            list.add(B1);
        if (checkBelong(A2, B2, C2, C1))
            list.add(C1);
    }

    public static boolean checkBelong(Point A1, Point B1, Point C1, Point A2){
        //проверка лежит ли точка А2 в треугольнике А1В1С1
        double f = (A1.getX() - A2.getX()) * (B1.getY() - A1.getY()) - (B1.getX() - A1.getX()) * (A1.getY() - A2.getY());
        double s = (B1.getX() - A2.getX()) * (C1.getY() - B1.getY()) - (C1.getX() - B1.getX()) * (B1.getY() - A2.getY());
        double l = (C1.getX() - A2.getX()) * (A1.getY() - C1.getY()) - (A1.getX() - C1.getX()) * (C1.getY() - A2.getY());
        return f > 0 && s > 0 && l > 0 || f < 0 && s < 0 && l < 0;
    }

    public static List<Point> getInterPoints(Triangle fTri, Triangle sTri){
        List<Point> result = new ArrayList<>();
        //ребро АВ первого треугольника и AC второго
        Point AB1AC2 = checkInter(fTri.A(), fTri.B(), sTri.A(), sTri.C());
        if(AB1AC2 != null)
            result.add(AB1AC2);
        //ребро АВ первого треугольника и АВ второго
        Point AB1AB2 = checkInter(fTri.A(), fTri.B(), sTri.A(), sTri.B());
        if(AB1AB2 != null)
            result.add(AB1AB2);
        //ребро АВ первого треугольника и ВС второго
        Point AB1BC2 = checkInter(fTri.A(), fTri.B(), sTri.B(), sTri.C());
        if(AB1BC2 != null)
            result.add(AB1BC2);
        //и так далее
        Point BC1AB2 = checkInter(fTri.B(), fTri.C(), sTri.A(), sTri.B());
        if(BC1AB2 != null)
            result.add(BC1AB2);
        Point BC1BC2 = checkInter(fTri.B(), fTri.C(), sTri.B(), sTri.C());
        if(BC1BC2 != null)
            result.add(BC1BC2);
        Point BC1AC2 = checkInter(fTri.B(), fTri.C(), sTri.A(), sTri.C());
        if(BC1AC2 != null)
            result.add(BC1AC2);
        Point AC1BC2 = checkInter(fTri.A(), fTri.C(), sTri.B(), sTri.C());
        if(AC1BC2 != null)
            result.add(AC1BC2);
        Point AC1AC2 = checkInter(fTri.A(), fTri.C(), sTri.A(), sTri.C());
        if(AC1AC2 != null)
            result.add(AC1AC2);
        Point AC1AB2 = checkInter(fTri.A(), fTri.C(), sTri.A(), sTri.B());
        if(AC1AB2 != null)
            result.add(AC1AB2);
        return result;
    }

    public static Point checkInter(Point a1, Point b1,Point a2, Point b2){
        //считаем тангенсы отрезков, если k1=k2, то отрезки параллельны
        double k1 =b1.getX() - a1.getX()/(b1.getY() - a1.getY());
        double k2 = b2.getX() - a2.getX()/(b2.getY() - a2.getY());
        if(k1 == k2)
            return null;
        Point result = null;
        //считаем точку пересечания прямых
        double x = -((a1.getX() * b1.getY() - b1.getX() * a1.getY()) * (b2.getX() - a2.getX()) - (a2.getX() * b2.getY() - b2.getX() * a2.getY()) * (b1.getX() - a1.getX())) /
                ((a1.getY() - b1.getY()) * (b2.getX() - a2.getX()) - (a2.getY() - b2.getY()) * (b1.getX() - a1.getX()));
        double y = ((a2.getY() - b2.getY()) * (-x) - (a2.getX() * b2.getY() - b2.getX() * a2.getY())) / (b2.getX() - a2.getX());
        //проверяем попадают ли наши точки по проекции в отрезки
        if ((Math.min(a1.getX(),b1.getX()) <= x && Math.max(a1.getX(),b1.getX()) >= x &&
                Math.min(a2.getX(), b2.getX()) <= x && Math.max(a2.getX(), b2.getX()) >= x) &&
                (Math.min(a1.getY(), b1.getY()) <= y && Math.max(a1.getY(), b1.getY()) >= y &&
                        Math.min(a2.getY(), b2.getY()) <= y && Math.max(a2.getY(), b2.getY()) >= y))
            result = new Point(x,y);
        return result;
    }

    public static Triangle getTriangle(){
        Scanner in = new Scanner(System.in);
        System.out.println("Введите через Enter координаты первого угла:");
        double x1 = in.nextDouble();
        double y1 = in.nextDouble();
        System.out.println("Введите через Enter координаты второго угла:");
        double x2 = in.nextDouble();
        double y2 = in.nextDouble();
        System.out.println("Введите через Enter координаты третьего угла:");
        double x3 = in.nextDouble();
        double y3 = in.nextDouble();
        return new Triangle(x1,y1,x2,y2,x3,y3);
    }
}
