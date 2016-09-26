package edu.clemson.cpsc2150.project2;

public class ArrayGrid implements Grid {

    Ship[] ships = new ShipImpl[5];
    Status[][] attempts;
    Ship lastsunk;
    int shipnum = 0;
    int rows;
    int cols;
    @Override
    public void setGridDimensions(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        attempts = new Status[rows][cols];
        for(int i = 0; i < rows; i++)
        {
            for(int j = 0; j < cols; j++)
            {
                attempts[i][j] = Status.EMPTY;
            }
        }
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

        for(int i = 0; i < shipnum; i++)
        {
            if(ships[i].shoot(coord) == Status.SUNK)
                lastsunk = ships[i];
                return Status.SUNK;
        }
        for(int z = 0; z < shipnum; z++)
        {
            if(ships[z].shoot(coord) == Status.HIT)
                return Status.HIT;
        }
        return Status.MISS;
    }

    @Override
    public Ship getLastSunkShip() {
        return lastsunk;
    }

    @Override
    public boolean hasBeenAttempted(Coordinate coord) {
        return false;
    }

    @Override
    public void displayGrid(boolean showShips) {
        System.out.print("  ");
        for(int j = 0; j < cols; j++)
        {
            System.out.print(j + " ");

        }
        System.out.println();
        if(showShips == true)
        {
            for(int i = 0; i < rows; i++)
            {
                System.out.print(i + " ");
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
            System.out.print("  ");
            for(int j = 0; j < cols; j++)
            {
                System.out.print(j + " ");

            }
            System.out.println();
            for(int i = 0; i < rows; i++)
            {
                System.out.print(i + " ");
                for(int z = 0; z < cols; z++)
                {

                    if(attempts[i][z] == Status.EMPTY)
                    {
                        System.out.print("- ");
                    }
                    else if (attempts[i][z] == Status.MISS)
                    {
                        System.out.print("+ ");
                    }
                    else
                    {
                        System.out.print("X ");
                    }
                }
                System.out.println();
            }
        }
    }
    public void attempt(int rows, int cols, Status s)
    {
        attempts[rows][cols] = s;
    }

    @Override
    public boolean isGameOver() {
        return false;
    }
}
