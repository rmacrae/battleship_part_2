package Test;

import edu.clemson.cpsc2150.project2.*;
import org.junit.Assert;


public class ShipTest {
    Ship s = new ShipImpl(ShipType.SUBMARINE, Direction.DOWN);
    Coordinate c1 = new Coordinate(0,0);
    Coordinate comparison[] = new Coordinate[3];
    @org.junit.Before
    public void setUp() throws Exception {
        comparison[0] = new Coordinate(0,0);
        comparison[1] = new Coordinate(0,1);
        comparison[2] = new Coordinate(0,2);
    }

    @org.junit.Test
    public void setCoordinates() throws Exception {
        s.setCoordinates(c1,Direction.DOWN);
        Assert.assertEquals(comparison,s.getCoordinates());
    }

    @org.junit.Test
    public void getCoordinates() throws Exception {
        s.setCoordinates(c1,Direction.DOWN);
        Assert.assertEquals(comparison,s.getCoordinates());
    }

    @org.junit.Test
    public void getType() throws Exception {
        Assert.assertEquals(ShipType.SUBMARINE,s.getType());
    }

    @org.junit.Test
    public void shoot() throws Exception {
        Coordinate temp = new Coordinate(0,0);
        Assert.assertEquals(Status.HIT,s.shoot(temp));
    }

    @org.junit.Test
    public void isSunk() throws Exception {
        Assert.assertFalse(s.isSunk());
    }

}