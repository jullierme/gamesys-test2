package com.gamesys.mars.coordinates;

import com.gamesys.mars.domain.Coordinates;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static java.util.stream.Stream.of;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

@DisplayName("Coordinates class suite tests")
class CoordinatesTest {

    private Coordinates coordinates;

    @BeforeEach
    void setUp() {
        //The terrain should be initialised with 5x5 positions
        coordinates = new Coordinates(5, 5);
    }

    @ParameterizedTest
    @MethodSource("invalidCoordinates")
    @DisplayName("Should return FALSE when the coordinates are NOT within bounds")
    void givenWrongCoordinates_whenIsWithinBounds_thenShouldReturnFalse(int x, int y) {
        //given parameters

        Coordinates newCoordinates = new Coordinates(x, y);

        //when
        boolean result = coordinates.isWithinBounds(newCoordinates);

        //then
        assertFalse(result);
    }

    @ParameterizedTest
    @MethodSource("validCoordinates")
    @DisplayName("Should return TRUE when the coordinates are within bounds")
    void givenValidCoordinates_whenIsWithinBounds_thenShouldReturnTrue(int x, int y) {
        //given parameters

        Coordinates newCoordinates = new Coordinates(x, y);

        //when
        boolean result = coordinates.isWithinBounds(newCoordinates);

        //then
        assertTrue(result);
    }

    @ParameterizedTest
    @MethodSource("invalidCoordinates")
    @DisplayName("Should return TRUE when the coordinates are outside bounds")
    void givenWrongCoordinates_whenIsOutsideBounds_thenShouldReturnFalse(int x, int y) {
        //given parameters

        Coordinates newCoordinates = new Coordinates(x, y);

        //when
        boolean result = coordinates.isOutsideBounds(newCoordinates);

        //then
        assertTrue(result);
    }

    @ParameterizedTest
    @MethodSource("validCoordinates")
    @DisplayName("Should return FALSE when the coordinates are NOT outside bounds")
    void givenValidCoordinates_whenIsOutsideBounds_thenShouldReturnTrue(int x, int y) {
        //given parameters
        Coordinates newCoordinates = new Coordinates(x, y);

        //when
        boolean result = coordinates.isOutsideBounds(newCoordinates);

        //then
        assertFalse(result);
    }

    @Test
    @DisplayName("Should create a new Coordination considering the previous coordinate")
    void givenNewCoordinates_whenNewCoordinatesFor_thenShouldCreateNewInstance() {
        //given
        int x = 2;
        int y = 3;
        int expectedX = coordinates.getX() + x;
        int expectedY = coordinates.getY() + y;

        //when
        Coordinates newCoordinates = coordinates.newCoordinatesFor(x, y);

        //then
        assertNotNull(newCoordinates);
        assertEquals(expectedX, newCoordinates.getX());
        assertEquals(expectedY, newCoordinates.getY());
    }

    private static Stream<Arguments> invalidCoordinates() {
        return of(
                arguments(0, 6),
                arguments(7, 1),
                arguments(7, 8)
        );
    }

    private static Stream<Arguments> validCoordinates() {
        return of(
                arguments(0, 1),
                arguments(2, 3),
                arguments(0, 4)
        );
    }
}
