package edu.clemson.cpsc2150.project2;

public interface Ship {
    // sets the placement of this ship starting at
    // coordinate "coord" and proceeding in direction "dir"
    void setCoordinates(Coordinate coord, Direction dir);
    // returns an array of the ship’s coordinates
    Coordinate[] getCoordinates();
    // takes a shot at this ship’s coordinates and
    // returns Status.MISS, Status.HIT, or STATUS.SUNK
    Status shoot(Coordinate coord);
    // returns true if all of the ship’s coordinates are hit
    // otherwise, false
    boolean isSunk();
}