package com.gamesys.mars.domain;

public class Rover {
    private Direction direction;
    private Coordinates coordinates;
    private final Area area;

    public Rover(Direction direction,
                 Coordinates coordinates,
                 Area area) {
        this.direction = direction;
        this.coordinates = coordinates;
        this.area = area;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public Area getArea() {
        return area;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public String getCurrentLocation() {
        return "(" +
                this.coordinates.getX() +
                ", " +
                this.coordinates.getY() +
                ", " +
                this.direction.name().substring(0, 1).toUpperCase() +
                ')';
    }
}
