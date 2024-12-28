package org.firstinspires.ftc.teamcode.INTO_THE_DEEP_BOT._.Autonomous.Blue;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.INTO_THE_DEEP_BOT._.Autonomous.AutonomousPLUS;

@Autonomous(group = "Basic", name = "Net Zone High Chamber")
public class BLUE_NETZONE_HighChamber extends AutonomousPLUS {

    public String currentPosition;
    public String target;

    public void runOpMode() {

        super.runOpMode();
        waitForStart();
        robot.encoderReset();
        telemetry.addData(currentPosition,"Start position");
        robot.lifty.setPower(0);
        robot.tempOutakePos("UP");
        robot.intakePosition("IN");
        speed = .3;
        robot.outakeclawOpenClose("CLOSED");
        moveRobotForward(500, 2);
        moveRobotRight(1100, 2);


        robot.lifty.setPower(1);
        robot.lifty.setTargetPosition(1500);
        robot.lifty.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        sleep(1000);
        moveRobotForward(415, 2);
        robot.lifty.setPower(1);
        robot.lifty.setTargetPosition(2500);
        robot.lifty.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        prepareNextAction(1000);
        robot.outakeclawOpenClose("OPEN");
        prepareNextAction(2);
        moveRobotBackward(200,2);
        moveRobotLeft(900, 2);
        /*robot.lifty.setTargetPosition(1228);
        robot.lifty.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        moveRobotLeft(1000, 2);
        turnRobotRight(900, 2);
        moveRobotLeft(900, 2);
        moveRobotForward(200, 2);*/
        //robot.tempOutakePos("MOREUP");
        prepareNextAction(2000);

    }
}