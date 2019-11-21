### Goal:
To refactor this NASA production (bad) code using the best practices

### Problem:
A Robot Team is being put by Nasa to explore Mars' terrain
This is a rectangular terrain, and it needs to be navigated by the robots in a way that their cameras can have a full overview of this region, sending pictures back to Earth.

Each robot's position is represented using cartesian coordinates (x, y) and a letter, which is used to represent each of the four orientation: NORTH, SOUTH, EAST and WEST.
A valid position would then be (0, 0, N), which means the robot is at the bottom left corner of the terrain, facing North. 
In order to control the robot, NASA sends a plain text string which may contain the following letters ("L", "R", "M"). 
The letters "L" and "R" are used to order a 90 degrees rotation for LEFT or RIGHT, while the letter "M" orders the robot to move straight.

Assume that the robot moves to NORTH on the Y-axis, represents the notation (x, y+1)
Example: If the robot is at (0,0,N), and the command is "MML", it needs to move to (0,2,W)

**Your task is to refactor this program and make it production ready to allow NASA engineers to have a reliable way to send commands to a Robot, currently deployed to Mars, and know where it is located.**

### Challenge requirements:
* The terrain should be initialised with 5x5 positions
* The robot should be initialised on coordinates (0,0,N)
* It must be possible to send the robot a command, and this should return its final position
* The robot should not move itself out of bounds
* The robot should not be stateful