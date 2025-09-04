package org.firstinspires.ftc.teamcode.INTO_THE_DEEP_BOT.Autonomous.Training;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.INTO_THE_DEEP_BOT.Autonomous.AutonomousPLUS;
import org.firstinspires.ftc.teamcode.INTO_THE_DEEP_BOT.Robot;

@Autonomous(group = "Basic", name = "Training Auto")
    public class Training_Auto extends AutonomousPLUS {

        public String currentPosition;
        public String target;

        public void runOpMode() {

            super.runOpMode();
            waitForStart();

            //First lets get our robot ready to move once the script is initalized
            //"prepareAuto()" requests another script prepare the robot so all motors are ready
            //Start your script with this to prevent arms from dragging on the ground etc.
            prepareAuto();


            //TODO Speed
            //First lets start by setting the speed we want the robot to move
            //to do this simply type speed = (value between 0 and 1)
            speed = 0.5;

            //TODO Move
            //Now we need to move with either time or ticks.
            //Tick movement tracks how far the motor thinks it has moved... may be inconsistent
            //Time moves on a timer but the timers may be inconsistent based on battery power
            //There are better ways to be consistent but that comes later
            //Lets start with ticks. type: "moveRobotForward(# of ticks, pause for milliseconds);"
            moveRobotLeft(905, 1);  //Ones are placeholder values

            //TODO prepare
            //In between actions it can be a good idea to prep for the next one with the prepareNextAction() line
            prepareNextAction(10);

            //TODO Turn
            //Now lets turn using ticks
            //Remember that left and right are relative to the front of the robot
            //Similar to movement tick-based turning is typed "turnRobotDirection(ticks, pause);"
            turnRobotRight(479, 10);
            prepareNextAction(10);
            moveRobotBackward(300,10);
            prepareNextAction(10);
            turnRobotLeft(1200,10);
            prepareNextAction(10);
            moveRobotForward(1500,10);
            prepareNextAction(10);
            robot.clawOpenClose(Robot.openClose.OPEN);


            //TODO use other mechanisms / functions
            //Our auto system uses a script called AutonomousPLUS to hold all of our robot functions
            //With these presaved, we can use them easily just by calling their name, as seen above
            //If you want to use functions/mechanisms in auto you will need to make your own


            //Without my yapping this script is pretty simple, just:
            // prepareAuto();
            // moveRobotForward(1, 1);
            // prepareNextAction(1);
            // turnRobotRight(1, 1);











        }
    }

