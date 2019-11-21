package com.gamesys.mars.movement;

import com.gamesys.mars.domain.Coordinates;
import com.gamesys.mars.domain.Direction;
import com.gamesys.mars.domain.Rover;
import com.gamesys.mars.exception.InvalidPositionException;

import static com.gamesys.mars.domain.Direction.*;

public class MovementStraight implements MovementStrategy {
    @Override
    public void move(Rover rover) throws InvalidPositionException {
        if(rover == null)
            throw new IllegalArgumentException();

        Coordinates finalPosition = rover.getCoordinates().newCoordinatesFor(
                nextPositionX(rover.getDirection()),
                nextPositionY(rover.getDirection()));

        if (!rover.getArea().isWithinBounds(finalPosition)) throw new InvalidPositionException();

        rover.setCoordinates(finalPosition);
    }

    private int nextPositionX(final Direction direction) {
        switch (direction) {
            case EAST:
                return 1;
            case WEST:
                return -1;
            default: return 0;
        }
    }

    private int nextPositionY(final Direction direction) {
        switch (direction) {
            case NORTH:
                return 1;
            case SOUTH:
                return -1;
            default: return 0;
        }
    }
}
