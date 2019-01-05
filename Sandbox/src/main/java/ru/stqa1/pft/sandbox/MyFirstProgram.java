package ru.stqa1.pft.sandbox;

public class MyFirstProgram {

  public static void main(String[] args) {

    Point p1 = new Point();
    p1.a = 1.0;
    p1.b = 2.0;
    Point p2 = new Point();
    p2.a = 3.0;
    p2.b = 4.0;

   distance(p2,p1);

    System.out.println("Расстояние между точками составляет " + distance(p2,p1));
  }


  public static double distance(Point p1, Point p2){
    return Math.sqrt((p2.a - p1.a)*(p2.a - p1.a)+((p2.b - p1.b)*(p2.b - p1.b)));


  }

}