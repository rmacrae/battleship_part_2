package edu.clemson.cpsc2150.project2;

public interface Ship {

    /**
     * @param coord the first coordinate of the ship
     * @param dir the direction (down/right)
     * @requires coord != null && dir != null
     * @ensures
     */
    void setCoordinates(Coordinate coord, Direction dir);

    /**
     * @ensures coordinates are unchanged
     * @requires ship location != null
     * @return coordinate array of ship location
     */

    Coordinate[] getCoordinates();

    /**
     * @ensures type is unchanged
     * @requires ship to be placed.
     * @return type of ship
     */
    ShipType getType();

    /**
     * @param coord coordinate to shoot
     * @ensures attempts are recorded.
     * @requires coord to be within bounds.
     * @return Hit if ship is hit, Sunk if all of ship is hit, sunk otherwise.
     */

    Status shoot(Coordinate coord);

    /**
     * @ensures ship is unchanged
     * @requres ship to be placed.
     * @return true if ship is sunk, false otherwise.
     */

    boolean isSunk();
}
