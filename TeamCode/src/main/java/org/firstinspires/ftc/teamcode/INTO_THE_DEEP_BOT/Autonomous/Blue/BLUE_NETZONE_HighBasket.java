package org.firstinspires.ftc.teamcode.INTO_THE_DEEP_BOT.Autonomous.Blue;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.INTO_THE_DEEP_BOT.Autonomous.AutonomousPLUS;

@Autonomous(group = "Basic", name = "Net Zone High Basket")
public class BLUE_NETZONE_HighBasket extends AutonomousPLUS {

    public String currentPosition;
    public String target;

    public void runOpMode() {

        super.runOpMode();


        waitForStart();
        prepareAuto();
        setMotorTolerance(10);
        prepareNextAction(250);

        // Preloaded sample
        moveRobotForward(300,0);
        moveRobotLeft(500,0);
        turnRobotLeft(1150,0);
        moveArm(1901,1,0);
        moveRobotForward(200,0);
        robot.tempOutakePos("UP");
        prepareNextAction(750);
        moveRobotBackward(300,0);
        robot.safeCollapse();

        // Pickup 1
        turnRobotLeft(400,0);
        autoSlides(.30,0);
        pickupSample(1000,0);
        robot.TransferSequence();
        turnRobotRight(400,0);
        moveArm(1901,1,0);
        moveRobotForward(300,0);
        robot.tempOutakePos("UP");
        prepareNextAction(750);
        moveRobotBackward(300,0);
        robot.safeCollapse();


        prepareNextAction(2000);

    }
}