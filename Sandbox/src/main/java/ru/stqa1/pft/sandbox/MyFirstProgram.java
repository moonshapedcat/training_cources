package ru.stqa1.pft.sandbox;

public class MyFirstProgram {

  public static void main(String[] args) {


    Point p1 = new Point(1,2);
    Point p2 = new Point(3,4);
    System.out.println("Расстояние между точками с координатами "  +p1.a +";" +p1.b + " и " + p2.a +";" +p2.b + " равно " + p1.distance(p2));
  }

  }