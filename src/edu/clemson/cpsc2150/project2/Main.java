package edu.clemson.cpsc2150.project2;

import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        // setup the grid for each player
        Grid[] grids = new Grid[2];
        ShipType s[] = new ShipType[5];
        s[0] = ShipType.CARRIER;
        s[1] = ShipType.BATTLESHIP;
        s[2] = ShipType.CRUISER;
        s[3] = ShipType.SUBMARINE;
        s[4] = ShipType.DESTROYER;
        grids[0] = new ArrayGrid();
        grids[0].setGridDimensions(10, 10);
        grids[1] = new BoundedSetGrid();
        grids[1].setGridDimensions(10, 10);
        for (int player = 0; player < 2; ++player) {
            // place the ships
            System.out.printf("PLAYER %d TURN\n", player + 1);

            for (int ship = 0; ship < 5; ++ship) {
                boolean shipPlaced = false;
                do {
                    // display the grid
                    grids[player].displayGrid(true);

                    // read in the coordinates
                    System.out.printf("\nPlace your %s: ", s[ship]);
                    Coordinate coords = parseCoordinates(reader.readLine());

                    // read in the direction
                    System.out.print("Choose direction (d/r): ");
                    Direction dir = parseDirection(reader.readLine());

                    // can we place the ship here?
                    Ship temp = new ShipImpl(s[ship],dir);
                    temp.setCoordinates(coords,dir);
                    if (!grids[player].isConflictingShipPlacement(temp)) {
                        // place the ship!
                        grids[player].placeShip(temp);
                        shipPlaced = true;
                    } else {
                        // print error message
                        System.out.println("Unable to place ship at that location! Please try again.");
                    }
                } while (!shipPlaced);
            }
        }

        // loop until the game is over
        int player = 0, opponent = 1;
        while (!grids[0].isGameOver() && !grids[1].isGameOver()) {
            // display player turn
            System.out.printf("\nPLAYER %d TURN\n", player + 1);
            grids[opponent].displayGrid(false);

            // get the coordinates for the next shot
            boolean isValidShot = false;
            Coordinate coords;
            do {
                System.out.print("Take a shot: ");
                coords = parseCoordinates(reader.readLine());

                // has a shot at this location already been attempted?
                if (grids[opponent].hasBeenAttempted(coords)) {
                    System.out.println("You have already shot at that location! Please try again.");
                } else {
                    isValidShot = true;
                }
            } while (!isValidShot);

            // take the shot at the opponent's grid
            Status result = grids[opponent].shoot(coords);

            // display the result of the shot
            switch (result) {
                case MISS:
                    System.out.println("Miss!");
                    break;
                case HIT:
                    System.out.println("Hit!");
                    break;
                case SUNK:
                    Ship ship = grids[opponent].getLastSunkShip();
                    System.out.printf("Hit! Sunk the  %s", ship.getType().toString());
                    break;
            }

            // switch players!
            player = (player + 1) % 2;
            opponent = (opponent + 1) % 2;
        }

        // game is over... determine who the winner is
        int winner;
        if (grids[1].isGameOver()) {
            winner = 1;
        } else {
            winner = 2;
        }

        System.out.printf("\nGame over! Player %d wins!\n", winner);
    }

    public static Coordinate parseCoordinates(String input) {
        // initialize coordinate array
        String[] tokens = input.split("\\D+");
        Coordinate coords = new Coordinate(Integer.parseInt(tokens[0]),Integer.parseInt(tokens[1]));
        return coords;
    }

    public static Direction parseDirection(String input) {
        if (input.toLowerCase().equals("d")) {
            return Direction.DOWN;
        } else if (input.toLowerCase().equals("r")) {
            return Direction.RIGHT;
        }
        return Direction.RIGHT;
    }
}
