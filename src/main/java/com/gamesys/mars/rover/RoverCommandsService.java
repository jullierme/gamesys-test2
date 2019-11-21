package com.gamesys.mars.rover;

import com.gamesys.mars.domain.Rover;
import com.gamesys.mars.exception.InvalidCommandException;
import com.gamesys.mars.exception.InvalidPositionException;

public interface RoverCommandsService {
    String runCommands(final Rover rover, final String commandList)
            throws InvalidPositionException, InvalidCommandException;
}
