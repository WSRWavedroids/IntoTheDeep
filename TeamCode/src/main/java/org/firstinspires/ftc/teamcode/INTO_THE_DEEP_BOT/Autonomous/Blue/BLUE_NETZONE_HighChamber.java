package org.firstinspires.ftc.teamcode.INTO_THE_DEEP_BOT.Autonomous.Blue;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.INTO_THE_DEEP_BOT.Autonomous.AutonomousPLUS;

@Autonomous(group = "Basic", name = "Net Zone High Chamber")
public class BLUE_NETZONE_HighChamber extends AutonomousPLUS {

    public String currentPosition;
    public String target;

    public void runOpMode() {

        super.runOpMode();


        waitForStart();
        robot.liftyL.setPower(0);
        robot.liftyR.setPower(0);
        robot.tempOutakePos("DOWN");
        robot.intakePosition("UP");
        robot.slidesIn();
        robot.outakeclawOpenClose("CLOSED");
        robot.frontLeftDrive.setTargetPositionTolerance(50);
        robot.frontRightDrive.setTargetPositionTolerance(50);
        robot.backLeftDrive.setTargetPositionTolerance(50);
        robot.backRightDrive.setTargetPositionTolerance(50);
        robot.liftyL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.liftyR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.encoderReset();
        speed = .3;
        sleep(200);
        moveRobotForward(500, 0);
        speed=.6;
        moveRobotRight(1100, 0);

        moveArm(1500, 1, 1000);

        moveRobotForward(395, 0);

        moveArm(1800, 1, 0);

        robot.outakeclawOpenClose("OPEN");
        prepareNextAction(2);
        moveRobotBackward(450,0);
        moveArm(0,1, 0);
        moveRobotLeft(1550, 0);
        turnRobotRight(1500, 0);//

        autoSlides(.70, 2000);
        robot.intake_spin(.5);
        robot.intakeFlipper.setPosition(.15);
        prepareNextAction(2000);
        robot.intake_spin(0);
        robot.TransferSequence();
        robot.tempOutakePos("UP");

        robot.TransferSequence();
        turnRobotRight(450, 0);
        moveRobotForward(250, 0);
        moveArm(1901, 1, 0);
        robot.tempOutakePos("UP");
        prepareNextAction(2000);

    }
}