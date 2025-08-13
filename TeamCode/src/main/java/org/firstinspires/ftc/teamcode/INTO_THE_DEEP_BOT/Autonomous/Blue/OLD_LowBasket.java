package org.firstinspires.ftc.teamcode.INTO_THE_DEEP_BOT.Autonomous.Blue;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.teamcode.INTO_THE_DEEP_BOT.Autonomous.AutonomousPLUS;

@Disabled
@Autonomous(group = "Basic", name = "Blue Basic Net Zone (Basket)")
public class OLD_LowBasket extends AutonomousPLUS {

    public String currentPosition;
    public String target;

    public void runOpMode() {

        super.runOpMode();


        waitForStart();
        telemetry.addData(currentPosition,"Start position");
/*
        robot.lifty.setPower(0);
        robot.tempOutakePos("UP");
        robot.slidesIn();
        speed = .65;
        robot.clawOpenClose("CLOSED");
        //Movement Starts
        moveRobotForward(200,2);
        turnRobotLeft(850,2);
        robot.lifty.setPower(1);
        robot.lifty.setTargetPosition(1500);
        robot.lifty.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        sleep(1500);
        moveRobotForward(550,2);
        moveRobotRight(450,2);
        turnRobotLeft(400,2);
        robot.lifty.setPower(1);
        robot.lifty.setTargetPosition(2500);
        robot.lifty.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        sleep(1500);
        moveRobotForward(360,2);
        robot.clawOpenClose("OPEN");
        sleep(4000);
        */
 
    }
}