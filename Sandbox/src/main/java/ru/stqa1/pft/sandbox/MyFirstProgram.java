package ru.stqa1.pft.sandbox;

public class MyFirstProgram {

  public static void main(String[] args) {


    Point p1 = new Point(1, 2);
    Point p2 = new Point(3, 4);
    System.out.println("Расстояние между точками равно" + distance(p1, p2));
  }


    public static double distance (Point p1, Point p2){
      return Math.sqrt(((p2.a - p1.a) * (p2.a - p1.a)) + ((p2.b - p1.b) * (p2.b - p1.b)));
    }
  }