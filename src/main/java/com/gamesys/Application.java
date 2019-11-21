package com.gamesys;

import com.gamesys.mars.ROVER;
import com.gamesys.mars.domain.Area;
import com.gamesys.mars.domain.Coordinates;
import com.gamesys.mars.domain.Direction;
import com.gamesys.mars.domain.Rover;
import com.gamesys.mars.movement.MovementLeft;
import com.gamesys.mars.movement.MovementRight;
import com.gamesys.mars.movement.MovementStraight;
import com.gamesys.mars.rover.RoverCommandsService;
import com.gamesys.mars.rover.RoverServiceImpl;

import static com.gamesys.mars.domain.Direction.NORTH;


public class Application {

    public static void oldRover() throws Exception {
        ROVER rover = new ROVER("N");
        rover.createArea(5, 5);
        rover.runCommands("ML");
        System.out.println(rover.getCurrentLocation());
    }

    public static void newRover() throws Exception {
        Rover rover = new Rover(NORTH,
                new Coordinates(0, 0),
                new Area(new Coordinates(5, 5), new Coordinates(0,0))
        );

        RoverCommandsService roverCommandsService = new RoverServiceImpl(
                new MovementLeft(),
                new MovementRight(),
                new MovementStraight()
        );

        System.out.println(roverCommandsService.runCommands(rover, "ML"));
    }

    public static void main(String[] args) throws Exception {
        oldRover();
        newRover();
    }
}
