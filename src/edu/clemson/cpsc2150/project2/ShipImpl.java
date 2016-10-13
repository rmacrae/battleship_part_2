package edu.clemson.cpsc2150.project2;

public class ShipImpl implements Ship
{
    int length;
    Direction dir;
    Coordinate coordinates[];
    Status status[];
    ShipType ship_type;

    public ShipImpl(ShipType shipt, Direction dir)
    {
        this.dir = dir;

        switch(shipt)      {
            case CARRIER:
                length = 5;
                ship_type = ShipType.CARRIER;
                break;
            case BATTLESHIP:
                length = 4;
                ship_type = ShipType.BATTLESHIP;
                break;
            case CRUISER:
                length = 3;
                ship_type = ShipType.CRUISER;
                break;
            case SUBMARINE:
                length = 3;
                ship_type = ShipType.SUBMARINE;
                break;
            case DESTROYER:
                length = 2;
                ship_type = ShipType.DESTROYER;
                break;
        }
        coordinates = new Coordinate[length];
        for(int i = 0; i < length; i++)
        {
            coordinates[i] = new Coordinate(0,0);
        }
        status = new Status[length];

        for(int z = 0; z < length; z++)
        {
            status[z] = Status.EMPTY;
        }
    }
    @Override
    public void setCoordinates(Coordinate coord, Direction dir)
    {

        if(this.dir == Direction.DOWN)
        {
            for(int i = 0; i < length; i++)
            {
                coordinates[i].col = coord.col + i;
                coordinates[i].row = coord.row;
                status[i] = Status.SHIP;
            }
        }
        else
        {
            for(int i = 0; i < length; i++)
            {
                coordinates[i].col = coord.col;
                coordinates[i].row = coord.row + i;
                status[i] = Status.SHIP;
            }
        }

    }

    @Override
    public Coordinate[] getCoordinates()
    {
        return coordinates;
    }

    @Override
    public ShipType getType() {
        return ship_type;
    }

    @Override
    public Status shoot(Coordinate coord)
    {
        for(int i = 0; i < length; i++)
        {
            if(coordinates[i].equals(coord))
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
            if (status[z] == Status.HIT)
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
