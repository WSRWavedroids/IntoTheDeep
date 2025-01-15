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
        prepareAuto();
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
        robot.intake_spin(.5); //Pick up yellow sample
        robot.intakeFlipper.setPosition(.15);
        prepareNextAction(2000);
        robot.intake_spin(0);
        robot.TransferSequence();
        //robot.tempOutakePos("UP");

        //robot.TransferSequence();
        turnRobotRight(450, 0);
        moveRobotForward(250, 0);
        moveArm(1901, 1, 0);
        robot.tempOutakePos("UP");
        prepareNextAction(2000);
        moveRobotBackward(300,0);
        robot.TransferSequence(); // Collapses the robot so the robot doesn't fall when auto ends
        prepareNextAction(2000);
    }
}