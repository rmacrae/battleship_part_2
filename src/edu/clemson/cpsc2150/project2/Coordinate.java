package edu.clemson.cpsc2150.project2;

import java.util.Objects;

public class Coordinate
{
    public int row, col;

    public Coordinate(int row, int col)
    {
        this.row = row;
        this.col = col;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null)
            return false;
        if (getClass() != o.getClass())
            return false;
        Coordinate c = (Coordinate) o;
        return Objects.equals(row, c.row)
                && Objects.equals(col, c.col);
    }
}
