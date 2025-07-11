package org.firstinspires.ftc.teamcode.INTO_THE_DEEP_BOT.Autonomous.Blue;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.INTO_THE_DEEP_BOT.Autonomous.AutonomousPLUS;

@Autonomous(group = "Basic", name = "Blue Basic Net Zone")
public class BlueBasicNetZone extends AutonomousPLUS {

    public String currentPosition;
    public String target;

    public void runOpMode() {

        super.runOpMode();


        waitForStart();
        telemetry.addData(currentPosition,"Start position");

        //robot.waterslide.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        robot.liftyL.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        robot.liftyL.setPower(0);
        //robot.waterslide.setPower(0);
        robot.tempOutakePos("UP");
        robot.intakePosition("UP");
        speed = .5;
        robot.outakeclawOpenClose("CLOSED");
        moveRobotForward(80, 2);
        moveRobotLeft(600, 2);
        moveRobotRight(350, 2);

    }
}