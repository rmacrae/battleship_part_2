package edu.clemson.cpsc2150.project2;

public class Main {

    public static void main(String[] args) {
        ArrayGrid a = new ArrayGrid();
        a.setGridDimensions(10,10);
        Coordinate c = new Coordinate(1,2);
        ShipImpl s = new ShipImpl(ShipType.BATTLESHIP,Direction.DOWN);
        s.setCoordinates(c,Direction.DOWN);
        a.placeShip(s);
        a.displayGrid(true);
    }
}
