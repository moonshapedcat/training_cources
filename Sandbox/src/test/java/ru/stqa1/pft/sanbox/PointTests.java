
package ru.stqa1.pft.sanbox;

import org.testng.annotations.Test;
import ru.stqa1.pft.sandbox.Point;

public class PointTests {

  @Test
  public void testArea(){
  Point p1 = new Point(1,2);

 assert p1.distance(new Point(3,4)) == 2.449489742783178;

  }
}