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
        robot.liftyL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.liftyR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.encoderReset();
        robot.liftyL.setPower(0);
        robot.liftyR.setPower(0);
        robot.tempOutakePos("DOWN");
        robot.intakePosition("UP");
        robot.intakePosition("UP");
        robot.slidesIn();
        speed = .65;
        robot.outakeclawOpenClose("CLOSED");
        moveRobotForward(500, 2);
        moveRobotRight(1100, 2);

        moveArm(1500, 1, 1000);


        moveRobotForward(415, 2);

        moveArm(1750, 1, 1000);

        robot.outakeclawOpenClose("OPEN");
        prepareNextAction(2);
        moveRobotBackward(450,2);
        moveArm(0,1, 200);
        moveRobotLeft(1500, 2);
        turnRobotRight(1000, 2);//
        /*Move Slides
        robot.intake_spin(.5);
        robot.intakePosition("DOWN");
        while(robot.intakeFlipper.getPosition() > .15)
        {
            robot.intakePosition("DOWN");
        }
        robot.intake_spin(0);
        robot.TransferSequence();
        //turn robot
        //move slide
        robot.tempOutakePos("UP");
        while(robot.leftFlippyOutakeServo.getPosition() > 0)
        {
            robot.intakePosition("DOWN");
        }
        robot.TransferSequence();

    */
        prepareNextAction(2000);

    }
}