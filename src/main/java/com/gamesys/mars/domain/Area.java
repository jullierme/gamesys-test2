package com.gamesys.mars.domain;

public class Area {
    private final Coordinates rightCoordinates;
    private final Coordinates bottomLeftCoordinates;

    /*public Area(final int topRightXCoordinate,
                final int topRightYCoordinate) {
        this.rightCoordinates =  new Coordinates(topRightXCoordinate, topRightYCoordinate);
        this.bottomLeftCoordinates = new Coordinates(0, 0);
    }*/

    public Area(final Coordinates rightCoordinates,
                final Coordinates bottomLeftCoordinates) {
        this.rightCoordinates = rightCoordinates;
        this.bottomLeftCoordinates =  bottomLeftCoordinates;
    }

    public boolean isWithinBounds(Coordinates coordinates) {
        return this.bottomLeftCoordinates.isOutsideBounds(coordinates)
                && this.rightCoordinates.isWithinBounds(coordinates);
    }
}
