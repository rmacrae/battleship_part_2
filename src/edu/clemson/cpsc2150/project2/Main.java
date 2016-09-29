package edu.clemson.cpsc2150.project2;

import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        // setup the grid for each player
        Grid[] grids = new Grid[2];
        for (int player = 0; player < 2; ++player) {
            // create the grid
            grids[0] = new ArrayGrid();
            grids[0].setGridDimensions(10, 10);
            grids[1] = new BoundedSetGrid();
            grids[1].setGridDimensions(10,10);

            // place the ships
            System.out.printf("PLAYER %d TURN\n", player + 1);

            for (int ship = 0; ship < 5; ++ship) {
                boolean shipPlaced = false;
                do {
                    // display the grid
                    grids[player].displayGrid(true);

                    // read in the coordinates
                    System.out.printf("\nPlace your %s: ", Grid.SHIP_NAMES[ship]);
                    int[] coords = parseCoordinates(reader.readLine());

                    // read in the direction
                    System.out.print("Choose direction (d/r): ");
                    int dir = parseDirection(reader.readLine());

                    // can we place the ship here?
                    if (dir != Grid.UNKNOWN && !grids[player].isConflictingShipPlacement(coords[0], coords[1], Grid.SHIP_LENGTHS[ship], dir)) {
                        // place the ship!
                        grids[player].setShipToBePlaced(ship);
                        grids[player].placeShip(coords[0], coords[1], Grid.SHIP_LENGTHS[ship], dir);
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
            int[] coords;
            do {
                System.out.print("Take a shot: ");
                coords = parseCoordinates(reader.readLine());

                // has a shot at this location already been attempted?
                if (grids[opponent].hasBeenAttempted(coords[0], coords[1])) {
                    System.out.println("You have already shot at that location! Please try again.");
                } else {
                    isValidShot = true;
                }
            } while (!isValidShot);

            // take the shot at the opponent's grid
            int result = grids[opponent].shoot(coords[0], coords[1]);

            // display the result of the shot
            switch (result) {
                case Grid.SHOT_MISS:
                    System.out.println("Miss!");
                    break;
                case Grid.SHOT_HIT:
                    System.out.println("Hit!");
                    break;
                case Grid.SHOT_SUNK:
                    int ship = grids[opponent].getLastSunkShip();
                    System.out.printf("Hit!\nSunk the %s!\n", Grid.SHIP_NAMES[ship]);
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

}
