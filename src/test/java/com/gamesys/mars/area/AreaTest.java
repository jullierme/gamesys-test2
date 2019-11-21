package com.gamesys.mars.area;

import com.gamesys.mars.domain.Area;
import com.gamesys.mars.domain.Coordinates;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.stream.Stream;

import static java.util.stream.Stream.of;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

@DisplayName("Area class suite tests")
class AreaTest {
    private Area area;

    @Mock
    private Coordinates coordinates;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        //The terrain should be initialised with 5x5 positions
        area = new Area(new Coordinates(5, 5), new Coordinates(0,0));
    }

    @Test
    @DisplayName("Should instantiate an Area ")
    void givenCoordinates_whenInstantiated_thenShouldCreatedTheRightCoordinationObject() {
        //given
        int x = 0;
        int y = 0;

        //when
        Area area = new Area(new Coordinates(x, y), new Coordinates(0,0));

        //then
        assertNotNull(area);
    }

    @ParameterizedTest
    @MethodSource("invalidCoordinates")
    @DisplayName("Should return FALSE when the coordinates are NOT within bounds")
    void givenWrongCoordinates_whenIsWithinBounds_thenShouldReturnFalse(int x, int y) {
        //given parameters
        Coordinates newCoordinates = new Coordinates(x, y);

        //when
        boolean result = area.isWithinBounds(newCoordinates);

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
        boolean result = area.isWithinBounds(newCoordinates);

        //then
        assertTrue(result);
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
