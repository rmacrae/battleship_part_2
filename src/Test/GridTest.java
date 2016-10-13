package Test;

import edu.clemson.cpsc2150.project2.*;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class GridTest {
    Grid[]g = new Grid[2];
    Ship s = new ShipImpl(ShipType.DESTROYER, Direction.DOWN);
    Coordinate test = new Coordinate(4,4);
    Coordinate comp[] = new Coordinate[2];
    @org.junit.Before
    public void setUp() throws Exception {

        g[0] = new ArrayGrid();
        g[1] = new BoundedSetGrid();
        g[0].setGridDimensions(10,10);
        g[1].setGridDimensions(10,10);
        s.setCoordinates(test,Direction.DOWN);
        comp[0] = new Coordinate(4,4);
        comp[1] = new Coordinate(4,5);
        g[0].placeShip(s);
        g[1].placeShip(s);

    }

    @Test
    public void setGridDimensions() throws Exception {
        //Assert.assertEquals(10,);
        
    }

    @org.junit.Test
    public void placeShip() throws Exception {
        Assert.assertEquals(true,g[0].isConflictingShipPlacement(s));

    }

    @org.junit.Test
    public void isConflictingShipPlacement() throws Exception {
        g[0].placeShip(s);
        g[1].placeShip(s);
        Assert.assertEquals(true,g[0].isConflictingShipPlacement(s));
        Assert.assertEquals(true,g[1].isConflictingShipPlacement(s));
    }

    @org.junit.Test
    public void shoot() throws Exception {
        g[0].shoot(comp[0]);
        g[0].shoot(comp[1]);
        g[1].shoot(comp[1]);
        g[1].shoot(comp[0]);
        Assert.assertEquals(true,g[0].hasBeenAttempted(comp[0]));
        Assert.assertEquals(true,g[0].hasBeenAttempted(comp[1]));
        Assert.assertEquals(true,g[1].hasBeenAttempted(comp[0]));
        Assert.assertEquals(true,g[1].hasBeenAttempted(comp[1]));
    }

    @org.junit.Test
    public void getLastSunkShip() throws Exception {

        g[1].shoot(comp[0]);
        g[1].shoot(comp[1]);
        Assert.assertEquals(ShipType.DESTROYER,g[1].getLastSunkShip().getType());

        g[0].shoot(comp[0]);
        g[0].shoot(comp[1]);
        Assert.assertEquals(ShipType.DESTROYER,g[0].getLastSunkShip().getType());

    }

    @org.junit.Test
    public void hasBeenAttempted() throws Exception {
        g[0].shoot(comp[0]);
        g[0].shoot(comp[1]);
        g[1].shoot(comp[1]);
        g[1].shoot(comp[0]);
        Assert.assertEquals(true,g[0].hasBeenAttempted(comp[0]));
        Assert.assertEquals(true,g[0].hasBeenAttempted(comp[1]));
        Assert.assertEquals(true,g[1].hasBeenAttempted(comp[0]));
        Assert.assertEquals(true,g[1].hasBeenAttempted(comp[1]));
    }

    @org.junit.Test
    public void isGameOver() throws Exception {
        Assert.assertEquals(false,g[0].isGameOver());
        Assert.assertEquals(false,g[1].isGameOver());
    }

}