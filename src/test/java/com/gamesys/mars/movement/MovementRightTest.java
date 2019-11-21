package com.gamesys.mars.movement;

import com.gamesys.mars.domain.Area;
import com.gamesys.mars.domain.Coordinates;
import com.gamesys.mars.domain.Direction;
import com.gamesys.mars.domain.Rover;
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

@DisplayName("MovementRight class suite test")
class MovementRightTest {
    private MovementStrategy movementStrategy;
    private Area area;

    @BeforeEach
    void setUp() {
        area = new Area(new Coordinates(5, 5), new Coordinates(0,0));
        movementStrategy = new MovementRight();
    }

    @ParameterizedTest
    @MethodSource("expectedDirectionsWhenNextDirection")
    @DisplayName("Should move the robot to the correct direction")
    void givenRover_whenMoveRight_thenShouldHaveTheCorrectDirection(Direction expectedDirection,
                                                                    Direction currentDirection) throws Exception {
        //given
        Rover rover = getDummyRover(currentDirection);

        // when
        movementStrategy.move(rover);

        //then
        assertEquals(expectedDirection, rover.getDirection());
    }

    @Test
    @DisplayName("Should not accept invalid parameters")
    void givenInvalidRover_whenMoveRight_thenShouldThrowException() {
        //given
        Rover rover = null;

        //when
        Executable executable = () -> movementStrategy.move(rover);

        //then
        assertThrows(IllegalArgumentException.class, executable);
    }

    private static Stream<Arguments> expectedDirectionsWhenNextDirection() {
        return Stream.of(
                arguments(EAST, NORTH),
                arguments(WEST, SOUTH),
                arguments(SOUTH, EAST),
                arguments(NORTH, WEST)
        );
    }

    private Rover getDummyRover(Direction startPosition) {
        return new Rover(startPosition,
                new Coordinates(0, 0),
                area);
    }

}
