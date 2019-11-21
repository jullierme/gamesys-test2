package com.gamesys.mars.movement;

import com.gamesys.mars.domain.Area;
import com.gamesys.mars.domain.Coordinates;
import com.gamesys.mars.domain.Direction;
import com.gamesys.mars.domain.Rover;
import com.gamesys.mars.exception.InvalidPositionException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static com.gamesys.mars.domain.Direction.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.params.provider.Arguments.arguments;

@DisplayName("MovementStraight class suite test")
class MovementStraightTest {
    private MovementStrategy movementStrategy;
    private Area area;

    @BeforeEach
    void setUp() {
        area = new Area(new Coordinates(5, 5), new Coordinates(0,0));
        movementStrategy = new MovementStraight();
    }

    @ParameterizedTest
    @MethodSource("expectedPositionsWhenMove")
    @DisplayName("Should move the robot to the correct positions")
    void givenRover_whenMoveStraight_thenShouldHaveTheCorrectDirection(int expectedPositionX,
                                                                         int expectedPositionY,
                                                                         Direction currentDirection)
            throws InvalidPositionException {
        //given
        Rover rover = getDummyRover(currentDirection);

        //when
        movementStrategy.move(rover);

        //then
        assertEquals(expectedPositionX, rover.getCoordinates().getX());
        assertEquals(expectedPositionY, rover.getCoordinates().getY());
    }

    @ParameterizedTest
    @MethodSource("invalidPositionsToMoveFromNorth")
    @DisplayName("Should exception when move from north to invalid positions")
    void givenInvalidPositionsToMoveFromNorth_whenMoveStraight_thenShouldException(Direction currentDirection) {
        //given
        Rover rover = getDummyRover(currentDirection);

        //when
        Executable executable = () -> movementStrategy.move(rover);

        //then
        assertThrows(InvalidPositionException.class, executable);
    }

    @Test
    void givenInvalidRover_whenMoveStraight_thenShouldThrowException() {
        //given
        Rover rover = null;

        //when
        Executable executable = () -> movementStrategy.move(rover);

        //then
        assertThrows(IllegalArgumentException.class, executable);
    }


    private static Stream<Arguments> expectedPositionsWhenMove() {
        return Stream.of(
                arguments(0, 1, NORTH),
                arguments(1, 0, EAST)
        );
    }

    private static Stream<Arguments> invalidPositionsToMoveFromNorth() {
        return Stream.of(
                arguments(SOUTH),
                arguments(WEST)
        );
    }

    private Rover getDummyRover(Direction startDirection) {
        return new Rover(startDirection,
                new Coordinates(0, 0),
                area);
    }

}
