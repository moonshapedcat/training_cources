package ru.stqa1.pft.sandbox;

public class MyFirstProgram {

  public static void main(String[] args) {


    Point p = new Point(1,2, 3, 4);
    System.out.println("Расстояние между точками с координатами "  +p.a +";" +p.b + " и " + p.c +";" +p.d + " равно " + p.distance());
  }


   /*public static double distance (Point p){
      return Math.sqrt(((p.a - p.a) * (p.a - p.a)) + ((p.b - p.b) * (p.b - p.b)));
    }*/
  }