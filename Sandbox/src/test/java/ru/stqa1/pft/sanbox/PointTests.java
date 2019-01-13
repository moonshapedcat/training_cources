
package ru.stqa1.pft.sanbox;

import org.testng.annotations.Test;
import ru.stqa1.pft.sandbox.Point;

public class PointTests {

  @Test
  public void testArea(){
  Point p = new Point(1,2, 3,4);

 assert p.distance()==2.8284271247461903 ;



  }
}