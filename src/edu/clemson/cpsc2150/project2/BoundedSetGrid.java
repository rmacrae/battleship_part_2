package edu.clemson.cpsc2150.project2;

public class BoundedSetGrid implements Grid {
    @Override
    public void setGridDimensions(int rows, int cols) {

    }

    @Override
    public void placeShip(Ship ship) {

    }

    @Override
    public boolean isConflictingShipPlacement(Ship ship) {
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

    }

    @Override
    public boolean isGameOver() {
        return false;
    }
}
