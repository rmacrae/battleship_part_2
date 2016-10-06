package edu.clemson.cpsc2150.project2;

public class BoundedSetGrid implements Grid {

    private Ship[] ships = new ShipImpl[5];
    private BoundedSet hits;
    private BoundedSet miss;
    private Ship lastsunk;
    private int shipnum = 0;
    private int rows;
    private int cols;
    @Override
    public void setGridDimensions(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        hits = new BoundedSet(rows * cols);
        miss = new BoundedSet(rows * cols);
    }

    @Override
    public void placeShip(Ship ship) {
        ships[shipnum] = ship;
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

        for(int i = 0; i < ships.length; i++)
        {
            if(ships[i] != null) {
                Coordinate[] cur_ship = ships[i].getCoordinates();
                for (int z = 0; z < cur_ship.length; z++) {
                    for (int y = 0; y < temp.length; y++) {
                        if (cur_ship[z].equals(temp[y])) {
                            return true;
                        }
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
            if(ships[i].isSunk()) {
                lastsunk = ships[i];
                hits.insert(coord);
                return Status.SUNK;
            }
        }
        for(int z = 0; z < shipnum; z++)
        {
            if(ships[z].shoot(coord) == Status.HIT) {
                hits.insert(coord);
                return Status.HIT;
            }
        }
        miss.insert(coord);
        return Status.MISS;
    }

    @Override
    public Ship getLastSunkShip() {
        return lastsunk;
    }

    @Override
    public boolean hasBeenAttempted(Coordinate coord) {
        if(hits != null) {
            if (hits.contains(coord)) {
                return true;
            }
        }
        else if(miss != null){
            if (miss.contains(coord)) {
                return true;
            }
        }
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
        if(showShips)
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
                    Status stat = Status.EMPTY;
                    for(int x = 0; x < hits.sizeOfSet(); x++)
                    {
                        Coordinate cur = new Coordinate(i,z);
                        if(hits.contains(cur))
                        {
                            stat = Status.HIT;
                        }

                    }
                    for(int a = 0; a < miss.sizeOfSet(); a++)
                    {
                        Coordinate c = new Coordinate(i,z);
                        if(miss.contains(c))
                        {
                            stat = Status.MISS;
                        }
                    }
                    if(stat == Status.EMPTY)
                    {
                        System.out.print("- ");
                    }
                    else if(stat == Status.MISS)
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
        return hits.sizeOfSet() == 17;
    }
}
