package pl.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTests {

    @Test
    public void testDistance() {
        Point p1 = new Point(0,1);
        Point p2 = new Point(2,-2);
        Assert.assertEquals(p1.distance(p2), 3.605551275463989);
    }

    @Test
    public void testDistance2(){
        Point p1 = new Point(9,7);
        Point p2 = new Point(3,2);
        Assert.assertEquals(p1.distance(p2), 7.810249675906654);
    }

}
