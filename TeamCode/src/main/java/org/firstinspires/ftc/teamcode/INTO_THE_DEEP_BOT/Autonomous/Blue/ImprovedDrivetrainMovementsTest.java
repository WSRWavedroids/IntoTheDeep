package org.firstinspires.ftc.teamcode.INTO_THE_DEEP_BOT.Autonomous.Blue;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.INTO_THE_DEEP_BOT.Autonomous.AutonomousPLUS;

@Autonomous(group = "Basic", name = "Improved Movements?")
public class ImprovedDrivetrainMovementsTest extends AutonomousPLUS {

    public String currentPosition;
    public String target;

    public void runOpMode() {

        super.runOpMode();

        waitForStart();
       goofymoveForward(1000, 200);
       goofymoveBackward(1000, 200);/*robot.lifty.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.encoderReset();
        robot.lifty.setPower(0);
        robot.tempOutakePos("UP");
        robot.intakePosition("UP");
        robot.slidesIn();
        speed = .65;
        robot.outakeclawOpenClose("CLOSED");


        moveDiagonalRight(1000, 2);
        moveDiagonalRight(-1000, 2);

        moveDiagonalLeft(1000, 2);
        moveDiagonalLeft(-1000, 2);


*/
    }
}