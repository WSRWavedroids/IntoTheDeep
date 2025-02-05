package org.firstinspires.ftc.teamcode.INTO_THE_DEEP_BOT.Autonomous.Blue;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.INTO_THE_DEEP_BOT.Autonomous.AutonomousPLUS;

@Autonomous(group = "Basic", name = "Observation Zone High Chamber")
public class BLUE_OBSZONE_HighChamber extends AutonomousPLUS {

    public String currentPosition;
    public String target;

    public void runOpMode() {

        super.runOpMode();

        waitForStart();

        //First movements to line up under bar
        prepareAuto();
        setMotorTolerance(25);
        speed = 1;
        moveDiagonalLeft(495, 0); //Trusting Pythagorus
        moveRobotForward(150, 0);

        //moveRobotForward(500, 0);
        //moveRobotLeft(350, 0);
        moveArm(1500, 1, 0);
        speed = .4;
        moveRobotForward(450, 0);

        moveArm(1800, 1, 0); //Places the starting specimen on the bar
        robot.outakeclawOpenClose("OPEN"); //Releases said specimen


        moveRobotBackward(500,500);//Back away

        speed = 1;
        moveArm(143, 1, 2);
        moveRobotRight(1150, 2);
        turnRobotRight(1530, 0);
        speed = .4;
        moveRobotForward(400,2);
        prepareNextAction(500);
        robot.outakeclawOpenClose("CLOSED");
        prepareNextAction(1000);
        moveRobotBackward(20, 0);
        moveArm(400, 1, 0);
        speed = 1;
        moveRobotBackward(450, 0);
        turnRobotRight(1530, 0);
        moveRobotLeft(1300, 0);
        moveArm(1700, 1, 0);
        speed=.4;
        moveRobotForward(500, 0);
        moveArm(1150, 1, 0);
        robot.outakeclawOpenClose("OPEN");
        moveRobotBackward(200, 0);
        moveArm(0, 1, 0);
        speed = 1;
        moveRobotBackward(550, 0);
        moveRobotRight(1700, 0);




        /*
        moveRobotForward(1150, 0);
        prepareNextAction(500);
        moveRobotRight(375, 0);
        moveRobotBackward(1300, 0);
        prepareNextAction(500);
        moveRobotForward(450, 0);

        turnRobotRight(1530, 0);
        speed = .3;
        prepareNextAction(2000);
        moveRobotForward(900, 0);
        robot.outakeclawOpenClose("CLOSED");
        prepareNextAction(200);
        moveRobotBackward(20, 0);
        moveArm(400, 1, 0);
        moveRobotBackward(450, 0);
        speed = .65;
        turnRobotLeft(1530, 0);
        moveRobotLeft(2000, 0);
        moveArm(1700, 1, 0);
        moveRobotForward(275, 0);
        moveArm(1200, 1, 0);
        robot.outakeclawOpenClose("OPEN");


        /*robot.outakeclawOpenClose("CLOSED"); //This is the line that picks up the specimen from the field wall
        prepareNextAction(500);
        moveArm(230, 1, 2);
        speed = 1;
        moveRobotBackward(400, 2);
        moveRobotRight(1575, 2);
        turnRobotLeft(1525, 2);*/
    }
}