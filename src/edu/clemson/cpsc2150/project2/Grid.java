package edu.clemson.cpsc2150.project2;

public interface Grid {
    /**
     * @param rows max row grid dimensions
     * @param cols max col grid dimensions
     * @ensures grid is initialized and dimensions are set
     * @requires rows > 0 and  cols > 0
     */
    void setGridDimensions(int rows, int cols);

    /**
     * @param ship ship to place on grid
     * @requires ship != null
     * @ensures ship is placed on grid
     */
    void placeShip(Ship ship);

    /**
     * @param ship ship to be placed
     * @requires ship != null
     * @ensures ship will not overlap with others already placed
     * @return true if out of bounds or ship already placed in that location, false otherwise
     */
    boolean isConflictingShipPlacement(Ship ship);

    /**
     * @param coord location to shoot
     * @requires coord to be within bounds
     * @ensures grid is unchanged, ship updated if shot
     * @return sunk if sunk, hit if hit, miss otherwise
     */
    Status shoot(Coordinate coord);
    /**
     * @return name of last sunk ship
     * @requires a ship to be sunk
     * @ensures ship name is unchanged
     */
    Ship getLastSunkShip();

    /**
     * @param coord coordinate to check if shot has been atempted
     * @ensures attempts is unchanged.
     * @requires coord to be within bounds
     * @return true if shot attempted at location, false otherwise
     */
    boolean hasBeenAttempted(Coordinate coord);
    void displayGrid(boolean showShips);

    /**
     * @return true if game is over, false otherwise
     * @requires ships to be placed
     * @ensures grid is unchanged
     */
    boolean isGameOver();
}