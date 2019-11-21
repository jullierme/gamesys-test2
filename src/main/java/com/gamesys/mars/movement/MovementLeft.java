package com.gamesys.mars.movement;

import com.gamesys.mars.domain.Direction;
import com.gamesys.mars.domain.Direction;
import com.gamesys.mars.domain.Rover;

import static com.gamesys.mars.domain.Direction.*;


public class MovementLeft implements MovementStrategy {
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
                return WEST;
            case SOUTH:
                return EAST;
            case EAST:
                return NORTH;
            default:
                return SOUTH;
        }
    }
}
