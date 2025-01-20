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
        setMotorTolerance(10);

        speed = .6;
        sleep(200);

        moveRobotForward(500, 0);
        //moveRobotRight(950, 0); // This is the code to start on a different tile
        moveArm(1500, 1, 1000);
        speed = .3;
        moveRobotForward(350, 0);
        speed = .6;
        moveArm(1800, 1, 0);
        robot.outakeclawOpenClose("OPEN");
        prepareNextAction(2);
        moveRobotBackward(425,0);
        moveArm(0,1, 0);

        // First sample
        moveRobotLeft(1345, 0);
        setMotorTolerance(8);
        turnRobotRight(1530, 0);
        setMotorTolerance(10);
        autoSlides(.50, 2000);
        robot.intake_spin(.5); //Pick up yellow sample
        prepareNextAction(750);
        robot.intakeFlipper.setPosition(.15);
        prepareNextAction(2000);
        robot.intake_spin(0);
        robot.TransferSequence();
        turnRobotRight(450, 0);
        speed  = .3;
        moveArm(1901, 1, 0);
        moveRobotForward(240, 0);//I moved this to after the liftarm to prevent catching on the basket
        robot.tempOutakePos("UP");
        prepareNextAction(2000);
        moveRobotBackward(200,0);
        robot.safeCollapse(); // Collapses the robot so the robot doesn't fall when auto ends

        /*// Second sample
        speed = .6;
        setMotorTolerance(8);
        turnRobotLeft(460,0);
        setMotorTolerance(10);
        moveRobotRight(275, 0);
        autoSlides(.65,2000);
        robot.intake_spin(1); //Pick up other yellow sample
        prepareNextAction(750);
        robot.intakeFlipper.setPosition(.15);
        prepareNextAction(2000);
        robot.intake_spin(0);
        robot.TransferSequence();*/
        
        prepareNextAction(2000);


    }
}