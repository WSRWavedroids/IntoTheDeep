package org.firstinspires.ftc.teamcode.INTO_THE_DEEP_BOT._.Autonomous.Blue;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.INTO_THE_DEEP_BOT._.Autonomous.AutonomousPLUS;

@Autonomous(group = "Basic", name = "Observation Zone High Chamber")
public class BLUE_OBSZONE_HighChamber extends AutonomousPLUS {

    public String currentPosition;
    public String target;

    public void runOpMode() {

        super.runOpMode();

        waitForStart();
        /*
        robot.lifty.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.encoderReset();
        robot.lifty.setPower(0);
        robot.tempOutakePos("UP");
        robot.intakePosition("IN");
        robot.slidesIn();
        speed = .65;
        robot.outakeclawOpenClose("CLOSED");
        moveRobotForward(500, 2);
        moveRobotLeft(350, 2);
        moveArm(1500, 1, 2);
        speed=.4;
        moveRobotForward(350, 2);

        moveArm(2500, 1, 2); //Places the starting specimen on the bar
        robot.outakeclawOpenClose("OPEN"); //Releases said specimen

        speed = .6;
        moveRobotBackward(500,500);
        speed = 1;
        moveArm(0, 1, 2);
        moveRobotRight(1575, 2);
        speed = .5;
        turnRobotRight(1530, 2);
        speed = .3;
        moveRobotForward(400, 2);
        robot.outakeclawOpenClose("CLOSED"); //This is the line that picks up the specimen from the field wall
        prepareNextAction(500);
        moveArm(230, 1, 2);
        speed = 1;
        moveRobotBackward(400, 2);
        moveRobotRight(1575, 2);
        turnRobotLeft(1525, 2);*/
    }
}