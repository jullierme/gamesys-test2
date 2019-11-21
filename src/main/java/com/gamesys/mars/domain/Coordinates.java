package com.gamesys.mars.domain;

public class Coordinates {
    private final int x;
    private final int y;

    public Coordinates(final int x, final int y) {
        this.x = x;
        this.y = y;
    }

    public boolean isOutsideBounds(Coordinates coordinates) {
        return !isWithinBounds(coordinates);
    }

    public boolean isWithinBounds(Coordinates coordinates) {
        return coordinates.getX() <= this.x && coordinates.getY() <= this.y;
    }

    public Coordinates newCoordinatesFor(int x, int y) {
        return new Coordinates(this.x + x, this.y + y);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
