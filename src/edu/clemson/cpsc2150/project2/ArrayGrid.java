package edu.clemson.cpsc2150.project2;

public class ArrayGrid implements Grid {

    private Ship[] ships_array = new ShipImpl[5];
    private Status[][] attempts;
    private Ship lastsunk;
    private int shipnum;
    private int rows;
    private int cols;
    private int count = 0;

    @Override
    public void setGridDimensions(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        shipnum = 0;
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
        ships_array[shipnum] = ship;
        shipnum++;
    }

    @Override
    public boolean isConflictingShipPlacement(Ship ship) {

        Coordinate temp[] = ship.getCoordinates();
        for(int a = 0; a < temp.length; a++)
        {
            if(temp[a].row > rows || temp[a].col > cols)
            {
                return true;
            }
        }
        if(shipnum == 0)
        {
            return false;
        }
        for(int i = 0; i < shipnum; i++)
        {
                Coordinate[] cur_ship = ships_array[i].getCoordinates();
                for (int z = 0; z < cur_ship.length; z++) {
                    for (int y = 0; y < temp.length; y++) {
                        if (cur_ship[z].equals(temp[y])) {
                            return true;
                        }
                    }
                }
        }
        return false;
    }

    @Override
    public Status shoot(Coordinate coord) {

        for(int i = 0; i < shipnum; i++)
        {
            if(ships_array[i].shoot(coord) == Status.SUNK) {
                lastsunk = ships_array[i];
                attempts[coord.row][coord.col] = Status.SUNK;
                return Status.SUNK;
            }
        }
        for(int z = 0; z < shipnum; z++)
        {
            if(ships_array[z].shoot(coord) == Status.HIT) {
                attempts[coord.row][coord.col] = Status.HIT;
                count++;
                return Status.HIT;
            }
        }
        attempts[coord.row][coord.col] = Status.MISS;
        return Status.MISS;
    }

    @Override
    public Ship getLastSunkShip() {
        return lastsunk;
    }

    @Override
    public boolean hasBeenAttempted(Coordinate coord) {
        return attempts[coord.row][coord.col] == Status.HIT || attempts[coord.row][coord.col] == Status.MISS;

    }

    public void displayGrid(boolean showships_array) {
        System.out.print("  ");
        for(int j = 0; j < cols; j++)
        {
            System.out.print(j + " ");

        }
        System.out.println();
        if(showships_array)
        {
            for(int i = 0; i < rows; i++)
            {
                System.out.print(i + " ");
                for(int z = 0; z < cols; z++)
                {
                    Status stat = Status.EMPTY;
                    for(int x = 0; x < shipnum; x++)
                    {
                        Coordinate[] cur = ships_array[x].getCoordinates();
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

    @Override
    public boolean isGameOver() {
        return count == 17;
    }
}
