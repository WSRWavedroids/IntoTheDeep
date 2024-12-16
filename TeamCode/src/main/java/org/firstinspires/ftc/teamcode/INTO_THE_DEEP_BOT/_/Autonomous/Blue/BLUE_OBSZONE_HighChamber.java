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
        robot.encoderReset();
        robot.lifty.setPower(0);
        robot.tempOutakePos("UP");
        robot.intakePosition("UP");
        robot.slidesIn();
        speed = .3;
        robot.outakeclawOpenClose("CLOSED");
        moveRobotForward(500, 1000);
        moveRobotLeft(200, 1000);
        speed = .3;
        robot.lifty.setPower(1);
        robot.lifty.setTargetPosition(1500);
        robot.lifty.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        sleep(1000);
        moveRobotForward(450, 2);
        robot.lifty.setPower(1);
        robot.lifty.setTargetPosition(2500);
        robot.lifty.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        prepareNextAction(1000);
        robot.outakeclawOpenClose("OPEN");
        prepareNextAction(2);
        moveRobotBackward(750,500);
        moveRobotRight(2000, 2);
        /*robot.lifty.setPower(1);
        robot.lifty.setTargetPosition(0);
        robot.lifty.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        prepareNextAction(1000);
        moveRobotBackward(500, 2);
        turnRobotRight(1550, 2);//not tested
        moveRobotLeft(1500, 2);*/


    }
}