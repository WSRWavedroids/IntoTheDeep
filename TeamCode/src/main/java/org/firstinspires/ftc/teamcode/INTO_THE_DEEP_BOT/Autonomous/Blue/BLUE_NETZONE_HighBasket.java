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

        // Preloaded sample
        moveRobotForward(300,0);
        moveRobotLeft(500,0);
        turnRobotLeft(1150,0);
        moveArm(1901,1,0);
        moveRobotForward(200,0);
        robot.tempOutakePos("UP");
        prepareNextAction(1500);
        moveRobotBackward(300,0);
        robot.safeCollapse();
        turnRobotLeft(375,0);


        prepareNextAction(2000);

    }
}