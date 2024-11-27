package org.firstinspires.ftc.teamcode.INTO_THE_DEEP_BOT._.Autonomous.Blue;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.INTO_THE_DEEP_BOT._.Autonomous.AutonomousPLUS;

@Autonomous(group = "Basic", name = "Blue Basic Net Zone (Basket) Better")
public class BlueBasicNetZoneBasketBetter extends AutonomousPLUS {

    public String currentPosition;
    public String target;

    public void runOpMode() {

        super.runOpMode();


        waitForStart();
        telemetry.addData(currentPosition,"Start position");
        robot.lifty.setPower(0);
        robot.tempOutakePos("UP");
        robot.intakePosition("UP");
        speed = .4;
        robot.outakeclawOpenClose("CLOSED");
        //Movement Starts
        moveRobotForward(900,2);
        turnRobotLeft(1250,2);
        moveRobotForward(950,2);
        robot.lifty.setPower(1);
        robot.lifty.setTargetPosition(2500);
        robot.lifty.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        sleep(3000);
        moveRobotForward(150,2);
        robot.outakeclawOpenClose("OPEN");
        sleep(1000);
        speed = 1;
        turnRobotLeft(15,2);

    }
}