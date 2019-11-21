package com.gamesys.mars.rover;

import com.gamesys.mars.domain.Rover;
import com.gamesys.mars.exception.InvalidCommandException;
import com.gamesys.mars.exception.InvalidPositionException;
import com.gamesys.mars.movement.MovementStrategy;

public class RoverServiceImpl implements RoverCommandsService {
    private MovementStrategy movementLeft;
    private MovementStrategy movementRight;
    private MovementStrategy movementStraight;

    public RoverServiceImpl(MovementStrategy movementLeft,
                            MovementStrategy movementRight,
                            MovementStrategy movementStraight) {
        this.movementLeft = movementLeft;
        this.movementRight = movementRight;
        this.movementStraight = movementStraight;
    }

    @Override
    public String runCommands(final Rover rover, final String commandList) throws InvalidPositionException,
            InvalidCommandException {
        validateCommandList(commandList);

        for (String character : commandList.split("")) {
            MovementStrategy movementStrategy = getMovementStrategy(character);
            movementStrategy.move(rover);
        }

        return rover.getCurrentLocation();
    }

    private MovementStrategy getMovementStrategy(String character) throws InvalidCommandException {
        switch (character) {
            case "L":
                return movementLeft;
            case "R":
                return movementRight;
            case "M":
                return movementStraight;
        }

        throw new InvalidCommandException();
    }

    private void validateCommandList(String commandList) throws InvalidCommandException {
        if (commandList == null || commandList.isEmpty())
            throw new IllegalArgumentException();

        for (String character : commandList.split("")) {
            if (isInvalidCharacter(character)) {
                throw new InvalidCommandException();
            }
        }
    }

    private boolean isInvalidCharacter(String character) {
        return !character.equals("L") && !character.equals("R") && !character.equals("M");
    }
}
