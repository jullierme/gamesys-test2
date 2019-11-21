package com.gamesys.mars.rover;

import com.gamesys.mars.domain.Area;
import com.gamesys.mars.domain.Coordinates;
import com.gamesys.mars.domain.Direction;
import com.gamesys.mars.domain.Rover;
import com.gamesys.mars.exception.InvalidCommandException;
import com.gamesys.mars.exception.InvalidPositionException;
import com.gamesys.mars.movement.MovementLeft;
import com.gamesys.mars.movement.MovementRight;
import com.gamesys.mars.movement.MovementStraight;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static com.gamesys.mars.domain.Direction.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class RoverCommandsServiceTest {
    private Area area;
    private RoverCommandsService roverService;

    @BeforeEach
    void setUp() {
        area = new Area(new Coordinates(5, 5), new Coordinates(0,0));
        roverService = new RoverServiceImpl(
                new MovementLeft(),
                new MovementRight(),
                new MovementStraight()
        );
    }

    @ParameterizedTest
    @MethodSource("commandsToPerform")
    @DisplayName("Should runs the command list correctly")
    void givenACommand_whenRunCommands_thenShouldMoveAndGetTheCorrectString(String commandList,
                                                                            String expectedToString,
                                                                            int expectedCoordinateX,
                                                                            int expectedCoordinateY,
                                                                            Direction expectedDirection)
            throws InvalidPositionException, InvalidCommandException {
        //given
        Rover rover = getDummyRover(NORTH);

        //when
        roverService.runCommands(rover, commandList);

        //then
        assertEquals(expectedToString, rover.getCurrentLocation());
        assertEquals(expectedCoordinateX, rover.getCoordinates().getX());
        assertEquals(expectedCoordinateY, rover.getCoordinates().getY());
        assertEquals(expectedDirection, rover.getDirection());
    }

    private static Stream<Arguments> commandsToPerform() {
        return Stream.of(
                arguments("MML", "(0, 2, W)", 0, 2, WEST),
                arguments("MMMR", "(0, 3, E)", 0, 3, EAST),
                arguments("MMM", "(0, 3, N)", 0, 3, NORTH),
                arguments("MRMLL", "(1, 1, W)", 1, 1, WEST),
                arguments("RLMRLM", "(0, 2, N)", 0, 2, NORTH),
                arguments("R", "(0, 0, E)", 0, 0, EAST),
                arguments("L", "(0, 0, W)", 0, 0, WEST)
        );
    }

    @ParameterizedTest
    @ValueSource(strings = {"MMLS", "ABC", "123"})
    @DisplayName("Should NOT accept invalid commands")
    void givenAnInvalidCommand_whenRunCommands_thenShouldThrowException(String commandList) {
        //given
        Rover rover = getDummyRover(NORTH);

        //when
        Executable executable = () -> roverService.runCommands(rover, commandList);

        //then
        assertThrows(InvalidCommandException.class, executable);
    }

    @ParameterizedTest
    @MethodSource("emptyValuesToRunCommands")
    @DisplayName("Should NOT accept empty/null command")
    void givenAnEmptyString_whenRunCommands_thenShouldThrowException(String command) {
        //given
        Rover rover = getDummyRover(NORTH);

        //when
        Executable executable = () -> roverService.runCommands(rover, command);

        //then
        assertThrows(IllegalArgumentException.class, executable);
    }

    private static Stream<Arguments> emptyValuesToRunCommands() {
        Long NULL = null;

        return Stream.of(
                arguments(""),
                arguments(NULL)
        );
    }


    @ParameterizedTest
    @MethodSource("singleCommandsToMoveTheRobot")
    void givenSingleCommand_whenRunCommands_thenShouldSucceed(Direction initialDirection,
                                                              Character command,
                                                              Direction expectedDirection,
                                                              int expectedCoordinateX,
                                                              int expectedCoordinateY) throws Exception {
        //given
        Rover rover = getDummyRover(initialDirection);

        //when
        roverService.runCommands(rover, command.toString());

        //then
        assertEquals(expectedDirection, rover.getDirection());
        assertEquals(expectedCoordinateX, rover.getCoordinates().getX());
        assertEquals(expectedCoordinateY, rover.getCoordinates().getY());
    }

    private static Stream<Arguments> singleCommandsToMoveTheRobot() {
        return Stream.of(
                arguments(NORTH, 'L', WEST, 0, 0),
                arguments(NORTH, 'R', EAST, 0, 0),
                arguments(NORTH, 'M', NORTH, 0, 1),

                arguments(SOUTH, 'L', EAST, 0, 0),
                arguments(SOUTH, 'R', WEST, 0, 0),

                arguments(EAST, 'L', NORTH, 0, 0),
                arguments(EAST, 'R', SOUTH, 0, 0),
                arguments(EAST, 'M', EAST, 1, 0),

                arguments(WEST, 'L', SOUTH, 0, 0),
                arguments(WEST, 'R', NORTH, 0, 0)
        );
    }

    private Rover getDummyRover(Direction startPosition) {
        return new Rover(startPosition,
                new Coordinates(0, 0),
                area);
    }


}
