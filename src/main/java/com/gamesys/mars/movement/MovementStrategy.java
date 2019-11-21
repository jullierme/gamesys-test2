package com.gamesys.mars.movement;

import com.gamesys.mars.domain.Rover;
import com.gamesys.mars.exception.InvalidPositionException;

public interface MovementStrategy {
    void move(Rover rover) throws InvalidPositionException;
}
