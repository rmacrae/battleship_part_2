package edu.clemson.cpsc2150.project2;

public class ShipImpl implements Ship
{
    int length;
    Direction dir;
    Coordinate coordinates[];
    Status status[];

    ShipImpl(ShipType shipt, Direction dir)
    {
        this.dir = dir;

        switch(shipt)      {
            case CARRIER:
                length = 5;
                break;
            case BATTLESHIP:
                length = 4;
                break;
            case CRUISER:
                length = 3;
                break;
            case SUBMARINE:
                length = 3;
                break;
            case DESTROYER:
                length = 2;
                break;
        }
        coordinates = new Coordinate[length];
        status = new Status[length];
    }
    @Override
    public void setCoordinates(Coordinate coord, Direction dir)
    {
        /*
        direction = dir;
        if(direction.equals(Direction.DOWN))
        {
            for(int i = 0; i < length; i++)
            {
                coordinates[i].col = coord.col;
                coordinates[i].row = coord.row + i;
                status[i] = Status.SHIP;
            }
        }
        else
        {
            for(int i = 0; i < length; i++)
            {
                coordinates[i].col = coord.col + i;
                coordinates[i].row = coord.row;
                status[i] = Status.SHIP;
            }
        }
*/
    }

    @Override
    public Coordinate[] getCoordinates()
    {
        return coordinates;
    }
    @Override
    public Status shoot(Coordinate coord)
    {
        for(int i = 0; i < length; i++)
        {
            if(coordinates[i].row == coord.row && coordinates[i].col == coord.col)
            {
                status[i] = Status.HIT;
                if(isSunk() == true)
                {
                    return Status.SUNK;
                }
                else
                {
                    return Status.HIT;
                }
            }
        }
        return Status.MISS;
    }
    @Override
    public boolean isSunk()
    {
        int hits = 1;
        for(int z = 0; z < length; z++)
        {
            if (status[z].equals(Status.HIT))
            {
                hits++;

            }
        }
        if(hits == length)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
