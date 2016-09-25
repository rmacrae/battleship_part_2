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
                    Status stat = Status.EMPTY;
                    for(int x = 0; x < shipnum; x++)
                    {
                        Coordinate[] cur = ships[x].getCoordinates();
                        Coordinate check = new Coordinate(i,z);
                        for(int y = 0; y < cur.length; y++)
                        {
                            if(cur[y].col == i && cur[y].row == z)
                            {
                                stat = Status.SHIP;
                            }
                        }
                    }
                    if(stat == Status.EMPTY)
                    {
                        System.out.print("- ");
                    }
                    else
                    {
                        System.out.print("@ ");
                    }
                }
                System.out.println();
            }
        }
        else
        {

        }
    }

    @Override
    public boolean isGameOver() {
        return false;
    }
}
