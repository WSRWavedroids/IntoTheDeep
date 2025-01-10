package org.firstinspires.ftc.teamcode.INTO_THE_DEEP_BOT._.Autonomous.Blue;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.INTO_THE_DEEP_BOT._.Autonomous.AutonomousPLUS;

@Autonomous(group = "Basic", name = "Blue Basic Observation Zone")
public class BlueBasicObservationZone extends AutonomousPLUS {

    public String currentPosition;
    public String target;

    public void runOpMode() {

        super.runOpMode();


        waitForStart();
        //telemetry.addData(currentPosition,"Start position");
        robot.waterslide.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        robot.liftyL.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        robot.liftyL.setPower(0);
        robot.waterslide.setPower(0);
        robot.tempOutakePos("UP");
        robot.intakePosition("UP");

        robot.outakeclawOpenClose("CLOSED");
        moveRobotForward(80, 2);
        moveRobotLeft(-1200, 2);
        //telemetry.addData(currentPosition,"Moved to parking spot");




    }
}