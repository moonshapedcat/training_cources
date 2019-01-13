package ru.stqa1.pft.sandbox;

public class Point {
  public double a;
  public double b;
  public double c;
  public double d;

  public Point(double a, double b, double c, double d) {
    this.a = a;
    this.b = b;
    this.c = c;
    this.d = d;

  }

  public double distance(){
    return Math.sqrt(((this.c - this.a) * (this.c - this.a)) + ((this.d - this.b) * (this.d - this.b)));
  }

}

