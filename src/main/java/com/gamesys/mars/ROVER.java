package com.gamesys.mars;

@Deprecated
public class ROVER {

    public coordinates c;
    public String d;
    public area a;

    public ROVER(final String startingDirection) {
        this.d = startingDirection;
        setStartingCoordinates(0, 0);
    }

    public void setStartingCoordinates(int x, int y){
        this.c = new coordinates(x,y);
    }

    public void createArea(int x, int y){
        this.a = new area(x,y);
    }

    public void performMovement(String m) throws Exception {
        if (m.equals("L")){
            this.d = nextdirectionleft(d);
        }

        if (m.equals("R")){
            this.d = nextdirectionright(d);
        }

        if (m.equals("M")){
            coordinates finalPosition = c.newcoordinatesFor(steps_x(d), steps_y(d));
            if (!a.IsWithinBounds(finalPosition)) throw new Exception("Invalid Position");

            c = c.newcoordinatesFor(steps_x(d), steps_y(d));
        }
    }

    public String nextdirectionleft(String currentDirection) throws Exception{
        if (currentDirection.equals("N")){
            return "W";
        }
        if (currentDirection.equals("S")){
            return "E";
        }
        if (currentDirection.equals("E")){
            return "N";
        }
        if (currentDirection.equals("W")){
            return "S";
        }
        throw new Exception();
    }

    public String nextdirectionright(String currentDirection) throws Exception{
        if (currentDirection.equals("N")){
            return "E";
        }
        if (currentDirection.equals("S")){
            return "W";
        }
        if (currentDirection.equals("E")){
            return "S";
        }
        if (currentDirection.equals("W")){
            return "N";
        }
        throw new Exception();
    }

    public int steps_x(String currentDirection) throws Exception {
        if (currentDirection.equals("N")){
            return 0;
        }
        if (currentDirection.equals("S")){
            return 0;
        }
        if (currentDirection.equals("E")){
            return 1;
        }
        if (currentDirection.equals("W")){
            return -1;
        }
        throw new Exception();
    }

    public int steps_y(String currentDirection) throws Exception  {
        if (currentDirection.equals("N")){
            return 1;
        }
        if (currentDirection.equals("S")){
            return -1;
        }
        if (currentDirection.equals("E")){
            return 0;
        }
        if (currentDirection.equals("W")){
            return 0;
        }
        throw new Exception();
    }

    public String getCurrentLocation() {
        StringBuilder output = new StringBuilder();
        output.append("(");
        output.append(this.c.toString());
        output.append(", ");
        output.append(this.d);
        output.append(")");
        return output.toString();
    }

    public void runCommands(final String commandList) throws Exception {
       for (String character : commandList.split("")) {
           this.performMovement(character);

            if (!character.equals("L") &&
                    !character.equals("R") &&
                    !character.equals("M")){
                throw new Exception();
            }
        }
    }

    class area {

        public coordinates r_coord = new coordinates(0, 0);
        public coordinates bottomleft_cord = new coordinates(0, 0);

        public area(final int topRightXCoordinate, final int topRightYCoordinate) {
            this.r_coord = this.r_coord.newcoordinatesFor(topRightXCoordinate, topRightYCoordinate);
        }

        public boolean IsWithinBounds(final coordinates coordinates) {
            return this.bottomleft_cord.isOutsideBounds(coordinates)
                    && this.r_coord.isWithinBounds(coordinates);
        }

    }

    class coordinates {

        public int x;
        public int y;

        public coordinates(final int xCoordinate, final int yCoordinate) {
            this.x = xCoordinate;
            this.y = yCoordinate;
        }

        public coordinates newcoordinatesFor(final int xCoordinateValue, final int yCoordinateValue) {
            return new coordinates(this.x + xCoordinateValue, this.y + yCoordinateValue);
        }

        public boolean isWithinBounds(final coordinates coordinates) {
            if (coordinates.x <= this.x && coordinates.y <= this.y) {
                return true;
            } else if (coordinates.x >= this.x && coordinates.y >= this.y) {
                return false;
            }
            return false;
        }

        public boolean isOutsideBounds(final coordinates coordinates) {
            if (coordinates.x >= this.x && coordinates.y >= this.y) {
                return true;
            } else if (coordinates.x <= this.x && coordinates.y <= this.y) {
                return false;
            }
            return false;
        }

        @Override
        public String toString() {
            StringBuilder output = new StringBuilder();
            output.append(x);
            output.append(", ");
            output.append(y);
            return output.toString();
        }
    }
}
