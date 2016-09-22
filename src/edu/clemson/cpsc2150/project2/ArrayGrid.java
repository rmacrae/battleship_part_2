package edu.clemson.cpsc2150.project2;

public class ArrayGrid implements Grid {

    Ship[] ships = new ShipImpl[5];

    int shipnum = 0;
    int rows;
    int cols;
    @Override
    public void setGridDimensions(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
    }

    @Override
    public void placeShip(Ship ship) {
        ships[shipnum] = ship;
        shipnum++;
    }

    @Override
    public boolean isConflictingShipPlacement(Ship ship) {
        for(int i = 0; i <= shipnum; i++)
        {

        }
        return false;
    }

    @Override
    public Status shoot(Coordinate coord) {

        return null;
    }

    @Override
    public Ship getLastSunkShip() {
        return null;
    }

    @Override
    public boolean hasBeenAttempted(Coordinate coord) {
        return false;
    }

    @Override
    public void displayGrid(boolean showShips) {
        if(showShips == true)
        {
            for(int i = 0; i < rows; i++)
            {
                for(int z = 0; z < cols; z++)
                {

                }
            }
        }
    }

    @Override
    public boolean isGameOver() {
        return false;
    }
}
