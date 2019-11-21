package com.gamesys.mars.movement;

import com.gamesys.mars.domain.Direction;
import com.gamesys.mars.domain.Rover;

import static com.gamesys.mars.domain.Direction.*;

public class MovementRight implements MovementStrategy {
    @Override
    public void move(Rover rover) {
        if(rover == null)
            throw new IllegalArgumentException();

        Direction direction = getNextDirection(rover.getDirection());
        rover.setDirection(direction);
    }

    private Direction getNextDirection(final Direction currentDirection) {
        switch (currentDirection) {
            case NORTH:
                return EAST;
            case SOUTH:
                return WEST;
            case EAST:
                return SOUTH;
            default:
                return NORTH;
        }
    }
}
